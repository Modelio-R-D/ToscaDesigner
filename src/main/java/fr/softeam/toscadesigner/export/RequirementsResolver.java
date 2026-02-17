package fr.softeam.toscadesigner.export;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;

import fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.TParameter;
import fr.softeam.toscadesigner.api.tosca.standard.association.TRelationshipTemplate;
import fr.softeam.toscadesigner.api.tosca.standard.class_.RequirementsType;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeTemplate;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TRequirement;
import org.modelio.api.module.context.log.ILogService;

/**
 * Resolves and renders requirements (host and connectivity) for node templates.
 * Handles requirement definitions, target resolution, and YAML rendering.
 */
final class RequirementsResolver {
    
    private final ILogService logger;

    RequirementsResolver(ILogService logger) {
        this.logger = logger;
    }

    /**
     * Render requirements block for a node template as YAML.
     */
    String renderRequirementsBlock(ModelElement context, String indentUnit) {
        TNodeTemplate template = asNodeTemplate(context);
        if (template == null) {
            return "";
        }
        ModelTree root = findRootTree(template.getElement());
        List<TNodeTemplate> scopeTemplates = root != null ? collectNodeTemplates(root) : Collections.singletonList(template);
        Map<Class, TNodeTemplate> templateIndex = indexNodeTemplates(scopeTemplates);
        RequirementLines summary = summarizeRequirements(template, templateIndex);
        if (summary.isEmpty()) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        builder.append(indentUnit).append("requirements: # ADDED: Connectivity and Hosting\n");
        String entryIndent = indentUnit + "  ";
        if (!summary.getHostTargets().isEmpty()) {
            builder.append(entryIndent).append("- host: ").append(formatInlineList(summary.getHostTargets())).append("\n");
        }
        for (String connection : summary.getConnectTargets()) {
            builder.append(entryIndent).append("- connects_to: ").append(YamlFormatter.formatScalar(connection)).append("\n");
        }
        return builder.toString();
    }

    /**
     * Resolve requirements from a template and build summary lines.
     */
    private RequirementLines summarizeRequirements(TNodeTemplate template, Map<Class, TNodeTemplate> templateIndex) {
        RequirementLines lines = new RequirementLines();
        RequirementsType requirements = resolveRequirements(template);
        if (requirements == null) {
            return lines;
        }
        for (TRequirement requirement : requirements.getRequirements()) {
            if (requirement == null || requirement.getElement() == null) {
                continue;
            }
            String name = requirement.getElement().getName();
            if (name == null) {
                continue;
            }
            if (name.equalsIgnoreCase("host")) {
                lines.addHosts(resolveRequirementTargets(template, requirement, templateIndex));
            } else if (isConnectsRequirement(requirement)) {
                lines.addConnections(resolveRequirementTargets(template, requirement, templateIndex));
            }
        }
        return lines;
    }

    /**
     * Resolve the requirements type for a template, checking both direct reference and owned elements.
     */
    RequirementsType resolveRequirements(TNodeTemplate template) {
        if (template == null) {
            return null;
        }
        RequirementsType requirements = template.getRequirements();
        String templateName = template.getElement() != null ? template.getElement().getName() : "<unnamed>";
        if (requirements != null) {
            logDebug("Requirements dependency found for " + templateName);
            return requirements;
        }
        Class element = template.getElement();
        if (element == null) {
            logDebug("Template element missing for " + templateName + ", cannot recover requirements");
            return null;
        }
        for (ModelElement owned : element.getOwnedElement()) {
            if (owned instanceof Class && RequirementsType.canInstantiate(owned)) {
                logDebug("Recovered inline RequirementsType for " + templateName);
                return RequirementsType.instantiate((Class) owned);
            }
        }
        logDebug("No requirements available for " + templateName);
        return null;
    }

    /**
     * Resolve all requirement entries from a requirements type.
     */
    List<TRequirement> resolveRequirementEntries(RequirementsType requirements) {
        if (requirements == null) {
            return Collections.emptyList();
        }
        List<TRequirement> collected = new ArrayList<>(requirements.getRequirements());
        Class element = requirements.getElement();
        if (element != null) {
            for (ModelElement owned : element.getOwnedElement()) {
                if (owned instanceof Class && TRequirement.canInstantiate(owned)) {
                    TRequirement requirement = TRequirement.instantiate((Class) owned);
                    if (requirement != null) {
                        collected.add(requirement);
                    }
                }
            }
        }
        return collected;
    }

    /**
     * Resolve all target nodes for a requirement (may be direct node reference or via relationship).
     */
    Collection<String> resolveRequirementTargets(TNodeTemplate source, TRequirement requirement,
            Map<Class, TNodeTemplate> templateIndex) {
        LinkedHashSet<String> targets = new LinkedHashSet<>();
        if (requirement == null) {
            return targets;
        }
        TNodeTemplate linkedNode = requirement.getNode();
        if (linkedNode != null && linkedNode.getElement() != null) {
            String name = linkedNode.getElement().getName();
            if (name != null) {
                targets.add(name);
            }
        }
        if (targets.isEmpty()) {
            TRelationshipTemplate relationship = requirement.getRelationship();
            targets.addAll(resolveTargetsFromRelationship(source, relationship, templateIndex));
        }
        if (targets.isEmpty() && requirement.getElement() != null) {
            String taggedNode = readTaggedValue(requirement.getElement(), "node");
            if (taggedNode != null) {
                targets.add(taggedNode);
            }
        }
        return targets;
    }

    private Collection<String> resolveTargetsFromRelationship(TNodeTemplate source,
            TRelationshipTemplate relationship, Map<Class, TNodeTemplate> templateIndex) {
        if (relationship == null || relationship.getElement() == null) {
            return Collections.emptyList();
        }
        Class sourceClass = source != null ? source.getElement() : null;
        LinkedHashSet<String> targets = new LinkedHashSet<>();
        for (AssociationEnd end : relationship.getElement().getEnd()) {
            if (end == null) {
                continue;
            }
            Classifier classifier = end.getSource();
            if (!(classifier instanceof Class)) {
                continue;
            }
            Class clazz = (Class) classifier;
            if (sourceClass != null && sourceClass.equals(clazz)) {
                continue;
            }
            TNodeTemplate template = templateIndex.get(clazz);
            if (template != null && template.getElement() != null) {
                String name = template.getElement().getName();
                if (name != null) {
                    targets.add(name);
                }
            }
        }
        return targets;
    }

    private boolean isConnectsRequirement(TRequirement requirement) {
        if (requirement == null || requirement.getElement() == null) {
            return false;
        }
        String name = requirement.getElement().getName();
        return name != null && name.equalsIgnoreCase("connects");
    }

    private String readTaggedValue(ModelElement element, String propertyName) {
        if (element == null || propertyName == null) {
            return null;
        }
        for (org.modelio.metamodel.uml.infrastructure.Stereotype stereotype : element.getExtension()) {
            String value = element.getProperty(stereotype, propertyName);
            String normalized = normalizeValue(value);
            if (normalized != null) {
                return normalized.replace("\"", "").replace("'", "");
            }
        }
        return null;
    }

    private String normalizeValue(String raw) {
        if (raw == null) {
            return null;
        }
        String trimmed = raw.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }

    private String formatInlineList(Collection<String> values) {
        if (values == null || values.isEmpty()) {
            return "[]";
        }
        return values.stream()
                .filter(Objects::nonNull)
                .map(YamlFormatter::formatScalar)
                .collect(Collectors.joining(", ", "[ ", " ]"));
    }

    private TNodeTemplate asNodeTemplate(Object candidate) {
        if (candidate instanceof TNodeTemplate) {
            return (TNodeTemplate) candidate;
        }
        if (candidate instanceof Class) {
            Class clazz = (Class) candidate;
            if (TNodeTemplate.canInstantiate(clazz)) {
                return TNodeTemplate.instantiate(clazz);
            }
        } else if (candidate instanceof ModelElement) {
            ModelElement element = (ModelElement) candidate;
            if (element instanceof Class && TNodeTemplate.canInstantiate(element)) {
                return TNodeTemplate.instantiate((Class) element);
            }
        }
        return null;
    }

    private List<TNodeTemplate> collectNodeTemplates(ModelElement context) {
        if (!(context instanceof ModelTree)) {
            return Collections.emptyList();
        }
        ModelTree tree = (ModelTree) context;
        return java.util.stream.Stream.concat(java.util.stream.Stream.of((ModelElement) tree), compositionDescendants(tree))
                .filter(Class.class::isInstance)
                .map(Class.class::cast)
                .filter(TNodeTemplate::canInstantiate)
                .map(TNodeTemplate::instantiate)
                .filter(Objects::nonNull)
                .filter(template -> !isSystemParametersTemplate(template))
                .collect(Collectors.toList());
    }

    private Map<Class, TNodeTemplate> indexNodeTemplates(List<TNodeTemplate> templates) {
        return templates.stream()
                .filter(Objects::nonNull)
                .filter(template -> template.getElement() != null)
                .collect(Collectors.toMap(TNodeTemplate::getElement, java.util.function.Function.identity(), (left, right) -> left,
                        java.util.LinkedHashMap::new));
    }

    private boolean isSystemParametersTemplate(TNodeTemplate template) {
        if (template == null) {
            return false;
        }
        String elementName = template.getElement().getName();
        if (elementName != null && elementName.equalsIgnoreCase("SystemParameters")) {
            return true;
        }
        if (template.getNodeType() != null && template.getNodeType().getElement() != null) {
            String nodeTypeName = template.getNodeType().getElement().getName();
            if (nodeTypeName != null && nodeTypeName.toLowerCase().contains("lakesidesystemparameters")) {
                return true;
            }
        }
        return false;
    }

    private boolean isSystemParametersParameter(TParameter parameter) {
        if (parameter == null || parameter.getElement() == null) {
            return false;
        }
        String elementName = parameter.getElement().getName();
        if (elementName != null && elementName.equalsIgnoreCase("SystemParameters")) {
            return true;
        }
        String typeName = parameter.getType();
        return typeName != null && typeName.toLowerCase().contains("lakesidesystemparameters");
    }

    private java.util.stream.Stream<ModelElement> compositionDescendants(ModelTree parent) {
        return parent.getCompositionChildren().stream()
                .filter(ModelElement.class::isInstance)
                .map(ModelElement.class::cast)
                .flatMap(child -> {
                    if (child instanceof ModelTree) {
                        return java.util.stream.Stream.concat(java.util.stream.Stream.of(child), compositionDescendants((ModelTree) child));
                    }
                    return java.util.stream.Stream.of(child);
                });
    }

    private ModelTree findRootTree(ModelElement element) {
        if (element == null) {
            return null;
        }
        ModelElement current = element;
        ModelTree lastTree = null;
        while (current instanceof ModelTree) {
            lastTree = (ModelTree) current;
            org.modelio.vcore.smkernel.mapi.MObject owner = ((ModelTree) current).getCompositionOwner();
            if (!(owner instanceof ModelElement)) {
                break;
            }
            current = (ModelElement) owner;
        }
        return lastTree;
    }

    private void logDebug(String message) {
        if (this.logger != null) {
            this.logger.info("[LakesideDSE] " + message);
        }
    }

    private static final class RequirementLines {
        private final LinkedHashSet<String> hostTargets = new LinkedHashSet<>();
        private final LinkedHashSet<String> connectTargets = new LinkedHashSet<>();

        void addHosts(Collection<String> hosts) {
            if (hosts != null) {
                hosts.stream().filter(Objects::nonNull).forEach(hostTargets::add);
            }
        }

        void addConnections(Collection<String> connections) {
            if (connections != null) {
                connections.stream().filter(Objects::nonNull).forEach(connectTargets::add);
            }
        }

        boolean isEmpty() {
            return hostTargets.isEmpty() && connectTargets.isEmpty();
        }

        List<String> getHostTargets() {
            return new ArrayList<>(hostTargets);
        }

        List<String> getConnectTargets() {
            return new ArrayList<>(connectTargets);
        }
    }
}
