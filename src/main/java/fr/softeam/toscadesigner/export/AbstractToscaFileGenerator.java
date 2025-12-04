package fr.softeam.toscadesigner.export;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.modelio.api.module.context.log.ILogService;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.vcore.smkernel.mapi.MObject;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.helper.ConditionalHelpers;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.TParameter;
import fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.TPropertyMapping;
import fr.softeam.toscadesigner.api.tosca.standard.association.TRelationshipTemplate;
import fr.softeam.toscadesigner.api.tosca.standard.attribute.TPropertyDef;
import fr.softeam.toscadesigner.api.tosca.standard.class_.CapabilityStereotype;
import fr.softeam.toscadesigner.api.tosca.standard.class_.RequirementsType;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TCapabilityDefinition;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TCapabilityType;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TGroup;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeTemplate;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeType;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TPolicy;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TRelationshipType;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TRequirement;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TRequirementDefinition;
import fr.softeam.toscadesigner.impl.ToscaDesignerModule;

@objid("e7453252-f578-4da1-815c-d2ce0e765130")
public abstract class AbstractToscaFileGenerator {
    @objid("6ca66b17-c54f-42dc-862d-4c41f8cbec9b")
    private static final String TEMPLATE_PATH = "/fr/softeam/templates/";

    @objid("44617ecf-78ee-46d2-a367-cbdfee5c0854")
    private static final String MAIN_TEMPLATE = "_mainTemplate";

    @objid("a897a13e-b8dc-4f4f-b967-60133eb7f69d")
    protected Handlebars handlebars = setupHandlebars();

    @objid("7df196c3-8c6b-4da7-9831-3d2f51c3097f")
    private ILogService logger;

    protected void setLogger(ILogService logger) {
        this.logger = logger;
    }

    protected void logInfo(String message) {
        if (this.logger != null) {
            this.logger.info(message);
        }
    }

    protected void logDebug(String message) {
        logInfo("[LakesideDSE] " + message);
    }

    @objid("e1bdb1e7-0783-441d-96e1-fdaa0f8e8514")
    protected abstract String getFileType();

    @objid("ebedc3d7-f673-4680-a8b3-159124af40c8")
    protected abstract String[] getFileExtensions();

    @objid("a064e044-abc6-430f-9a31-680bf8f13dad")
    public abstract void generateContent(MObject object) throws IOException;

    @objid("0a64efd3-9d89-46b9-91e8-40aaa00626a4")
    protected String saveToFile(String[] fileExtensions, String fileType) {
        FileDialog fileDialog = new FileDialog(Display.getCurrent().getActiveShell(), SWT.SAVE);
        fileDialog.setFilterExtensions(fileExtensions);

        String filePath = fileDialog.open();

        return filePath;
    }

    @objid("3a782c82-535e-40a1-96b6-10d70a8fd4b4")
    private Handlebars setupHandlebars() {
        Handlebars handlebars = new Handlebars(new ClassPathTemplateLoader(TEMPLATE_PATH, ".hbs"));
        handlebars.setPrettyPrint(true);
        handlebars.registerHelper("getProperty", (ModelElement context, Options options) -> {
            String searchedPropertyName = (String) options.params[0];
            for (Stereotype stereotype : context.getExtension()) {
                String propertyStringValue;
                propertyStringValue = context.getProperty(stereotype, searchedPropertyName);

                if (stereotype.getName().equals("TRequirement")) {
                    TRequirement tRequirement = TRequirement.safeInstantiate((Class) context);
                    if (searchedPropertyName.equals("node")) {
                        TNodeTemplate node = tRequirement.getNode();
                        propertyStringValue = node != null ? node.getElement().getName() : "''";
                    } else if (searchedPropertyName.equals("capability")) {
                        CapabilityStereotype capability = tRequirement.getCapability();
                        propertyStringValue = capability != null ? capability.getElement().getName() : "";
                    } else if (searchedPropertyName.equals("relationship")) {
                        TRelationshipTemplate relationship = tRequirement.getRelationship();
                        propertyStringValue = relationship != null ? relationship.getElement().getName() : "";
                    }
                } else if (stereotype.getName().equals("TRequirementDefinition")) {

                    TRequirementDefinition tRequirementDefinition = TRequirementDefinition
                            .safeInstantiate((Class) context);
                    if (searchedPropertyName.equals("node")) {
                        propertyStringValue = tRequirementDefinition.getNodeType().getName();
                    } else if (searchedPropertyName.equals("capability")) {
                        TCapabilityType capability = tRequirementDefinition.getCapability();
                        propertyStringValue = capability.getElement().getName();
                    } else if (searchedPropertyName.equals("relationshipType")) {
                        propertyStringValue = tRequirementDefinition.getRelationshipType().getElement().getName();
                    } else if (searchedPropertyName.equals("lowerBound")) {
                        propertyStringValue = tRequirementDefinition.getLowerBound();
                    } else if (searchedPropertyName.equals("upperBound")) {
                        propertyStringValue = tRequirementDefinition.getUpperBound();
                    }
                } else if (stereotype.getName().equals("TRelationshipTemplate")) {
                    if (searchedPropertyName.equals("type")) {
                        TRelationshipTemplate tRelationshipTemplate = TRelationshipTemplate
                                .safeInstantiate((Association) context);
                        TRelationshipType relationshipType = tRelationshipTemplate.getRelationshipType();
                        Class element = relationshipType.getElement();
                        propertyStringValue = element.getName();
                    }
                } else if (stereotype.getName().equals("TNodeTemplate")) {
                    TNodeTemplate tNodeTemplate = TNodeTemplate.safeInstantiate((Class) context);
                    if (searchedPropertyName.equals("nodeType")) {
                        TNodeType nodeType = tNodeTemplate.getNodeType();
                        propertyStringValue = nodeType.getTargetNamespace() + "." + nodeType.getElement().getName();
                    }
                } else if (stereotype.getName().equals("TCapabilityDefinition")) {
                    TCapabilityDefinition tCapabilityDefinition = TCapabilityDefinition
                            .safeInstantiate((Class) context);
                    if (searchedPropertyName.equals("capabilityType")) {
                        propertyStringValue = tCapabilityDefinition.getCapabilityType().getElement().getName();
                    }
                } else if (stereotype.getName().equals("TPolicy")) {
                    TPolicy tPolicy = TPolicy.safeInstantiate((Class) context);
                    if (searchedPropertyName.equals("type")) {
                        propertyStringValue = tPolicy.getType().getElement().getName();
                    } else if(searchedPropertyName.equals("policyTargets")){
                        List<TNodeTemplate> targets = tPolicy.getTargets();
                        propertyStringValue = targets.stream()
                            .map(t -> t.getElement().getName())
                            .collect(Collectors.joining(", "));

                    }
                }
                // if it didn't find the property with this stereotype, look for the parent
                // stereotypes
                while (propertyStringValue == null && stereotype.getParent() != null) {
                    stereotype = stereotype.getParent();
                    propertyStringValue = context.getProperty(stereotype, (String) searchedPropertyName);
                }
                return propertyStringValue;
            }
            throw new RuntimeException("Stereotype property " + searchedPropertyName + " not found in " + context);
        });
        // Helper returning true if none of the owned elements/attributes/ends apply the
        // given stereotype name
        handlebars.registerHelper("noStereotypeApplications",
                (context, options) -> hasNoStereotypeApplications((ModelTree) context, (String) options.params[0]));
        handlebars.registerHelper("imports", (ModelElement context, Options options) -> {
            boolean includeHeader = options.params.length == 0 || asBoolean(options.param(0), true);
            String indent = options.params.length > 1 ? Objects.toString(options.param(1), "  ") : "  ";
            Set<Import> imports = collectImports(context);
            return generateImportString(imports, includeHeader, indent);
        });
        handlebars.registerHelper("nodeTypeLocalName", (Object candidate, Options options) -> nodeTypeLocalName(candidate));
        handlebars.registerHelper("formatYamlValue", (Object value, Options options) -> formatScalar(value));
        handlebars.registerHelper("isSystemParameters", (Object candidate, Options options) -> isSystemParameters(candidate));
        handlebars.registerHelper("lakesideSystemParams", (ModelElement context, Options options) -> {
            String indent = options.params.length > 0 ? Objects.toString(options.param(0), "  ") : "  ";
            return renderSystemParametersBlock(context, indent);
        });
        handlebars.registerHelper("lakesideNodeTemplates", (ModelElement context, Options options) -> {
            List<TNodeTemplate> nodeTemplates = collectNodeTemplates(context);
            StringBuilder builder = new StringBuilder();
            for (TNodeTemplate template : nodeTemplates) {
                // Partial templates expect the raw Modelio element (ModelTree) so they can
                // navigate owned attributes while helpers can still cast back to
                // TNodeTemplate when needed.
                builder.append(options.fn(template.getElement()));
            }
            return builder.toString();
        });
        handlebars.registerHelper("lakesideArchitecture", (ModelElement context, Options options) -> {
            String indent = options.params.length > 0 ? Objects.toString(options.param(0), "  ") : "  ";
            ArchitectureData architecture = buildArchitectureData(context);
            if (architecture.isEmpty()) {
                return "";
            }
            return renderArchitectureBlock(architecture, indent);
        });
        handlebars.registerHelper("lakesideRequirements", (ModelElement context, Options options) -> {
            String indent = options.params.length > 0 ? Objects.toString(options.param(0), "  ") : "  ";
            return renderRequirementsBlock(context, indent);
        });
        handlebars.registerHelpers(ConditionalHelpers.class);
        return handlebars;
    }

    @objid("4e0fc0cf-420e-4626-b34d-fd6df12a1e01")
    private Set<Import> collectImports(ModelElement context) {
        Set<Import> imports = new LinkedHashSet<>();
        if (context == null) {
            return imports;
        }

        if (new NodeTypeChecker().isTypeOf(context)) {
            TNodeType tNodeType = TNodeType.safeInstantiate((Class) context);
            String derivedFromValue = tNodeType.getDerivedFrom().getName();
            String targetNamespace = tNodeType.getTargetNamespace();
            if (derivedFromValue != null && !derivedFromValue.startsWith("tosca")) {
                imports.add(new Import(derivedFromValue + ".tosca", targetNamespace, "MYRTUS-"));
            }
        } else if (new TopologyTemplateChecker().isTypeOf(context)) {
            List<TNodeTemplate> nodeTemplates = context.getCompositionChildren().stream().filter(object -> {
                Stereotype tNodeTemplateStereotype = ToscaDesignerModule.getInstance().getModuleContext()
                        .getModelingSession().getMetamodelExtensions().getStereotype("TNodeTemplate", object.getMClass());
                return tNodeTemplateStereotype != null && ((ModelElement) object).isStereotyped(tNodeTemplateStereotype);
            }).map(Class.class::cast).map(TNodeTemplate::safeInstantiate).collect(Collectors.toList());
            for (TNodeTemplate nodeTemplate : nodeTemplates) {
                TNodeType nodeType = nodeTemplate.getNodeType();
                if (nodeType != null) {
                    String typeName = nodeType.getElement().getName();
                    String targetNamespace = nodeType.getTargetNamespace();
                    imports.add(new Import(typeName + ".tosca", targetNamespace, "MYRTUS-"));
                }
            }
        }

        return imports;
    }

    private String generateImportString(Set<Import> imports, boolean includeHeader, String indent) {
        if (imports.isEmpty()) {
            return "";
        }
        StringBuilder importString = new StringBuilder();
        if (includeHeader) {
            importString.append("imports:\n");
        }
        for (Import anImport : imports) {
            importString.append(indent).append("- file: ").append(anImport.getFile()).append("\n")
                    .append(indent).append("  namespace_uri: ").append(anImport.getNamespaceUri()).append("\n")
                    .append(indent).append("  namespace_prefix: ").append(anImport.getNamespacePrefix()).append("\n");
        }

        return importString.toString();
    }

    private boolean asBoolean(Object candidate, boolean defaultValue) {
        if (candidate == null) {
            return defaultValue;
        }
        if (candidate instanceof Boolean) {
            return (Boolean) candidate;
        }
        String value = candidate.toString();
        if (value == null || value.isEmpty()) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value);
    }

    private CharSequence renderSystemParametersBlock(ModelElement context, String indentUnit) {
        Optional<ModelTree> systemParameters = findSystemParametersElement(context);
        if (!systemParameters.isPresent()) {
            return "";
        }
        Map<String, Object> propertyTree = buildPropertyTree(systemParameters.get());
        if (propertyTree.isEmpty()) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        builder.append(indentUnit).append("system_params:\n");
        builder.append(renderYamlTree(propertyTree, 2, indentUnit));
        return builder.toString();
    }

    private Optional<ModelTree> findSystemParametersElement(ModelElement context) {
        if (!(context instanceof ModelTree)) {
            return Optional.empty();
        }
        Deque<ModelTree> toVisit = new ArrayDeque<>();
        toVisit.add((ModelTree) context);
        while (!toVisit.isEmpty()) {
            ModelTree current = toVisit.pollFirst();
            if (isSystemParametersElement(current)) {
                return Optional.of(current);
            }
            Stream.concat(current.getCompositionChildren().stream(), current.getOwnedElement().stream())
                    .filter(ModelTree.class::isInstance)
                    .map(ModelTree.class::cast)
                    .forEach(toVisit::addLast);
        }
        return Optional.empty();
    }

    private Map<String, Object> buildPropertyTree(ModelTree source) {
        Map<String, Object> root = new LinkedHashMap<>();
        if (source == null) {
            return root;
        }
        collectPropertyValues(source, "", root);
        return root;
    }

    private void collectPropertyValues(ModelTree source, String prefix, Map<String, Object> accumulator) {
        if (source == null) {
            return;
        }
        if (source instanceof Class) {
            Class clazz = (Class) source;
            for (Attribute attribute : clazz.getOwnedAttribute()) {
                if (!TPropertyDef.canInstantiate(attribute)) {
                    continue;
                }
                String name = attribute.getName();
                if (name == null || name.isEmpty()) {
                    continue;
                }
                String qualifiedName = prefix.isEmpty() ? name : prefix + "." + name;
                String value = attribute.getValue() != null ? attribute.getValue() : "";
                insertProperty(accumulator, qualifiedName, value);
            }
        }

        List<ModelTree> childTrees = new ArrayList<>();
        source.getOwnedElement().stream()
            .filter(ModelTree.class::isInstance)
            .map(ModelTree.class::cast)
            .forEach(childTrees::add);
        source.getCompositionChildren().stream()
            .filter(ModelTree.class::isInstance)
            .map(ModelTree.class::cast)
            .forEach(childTrees::add);

        childTrees.stream()
                .filter(this::isPropertyContainer)
                .forEach(child -> {
                    String childName = child.getName();
                    String nextPrefix = prefix;
                    if (childName != null && !childName.isEmpty()) {
                        nextPrefix = prefix.isEmpty() ? childName : prefix + "." + childName;
                    }
                    collectPropertyValues(child, nextPrefix, accumulator);
                });
    }

    @SuppressWarnings("unchecked")
    private void insertProperty(Map<String, Object> parent, String propertyName, String value) {
        String[] segments = propertyName.split("\\.");
        Map<String, Object> current = parent;
        for (int i = 0; i < segments.length - 1; i++) {
            String segment = segments[i];
            Object existing = current.get(segment);
            if (!(existing instanceof Map)) {
                Map<String, Object> child = new LinkedHashMap<>();
                current.put(segment, child);
                current = child;
            } else {
                current = (Map<String, Object>) existing;
            }
        }
        String leafKey = segments[segments.length - 1];
        current.put(leafKey, value);
    }

    @SuppressWarnings("unchecked")
    private String renderYamlTree(Map<String, Object> tree, int depth, String indentUnit) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Object> entry : tree.entrySet()) {
            String indent = repeatIndent(depth, indentUnit);
            builder.append(indent).append(entry.getKey()).append(":");
            Object value = entry.getValue();
            if (value instanceof Map) {
                builder.append("\n");
                builder.append(renderYamlTree((Map<String, Object>) value, depth + 1, indentUnit));
            } else {
                builder.append(" ").append(formatScalar(value)).append("\n");
            }
        }
        return builder.toString();
    }

    private boolean isPropertyContainer(ModelTree element) {
        if (element == null) {
            return false;
        }
        if (element instanceof Class && TGroup.canInstantiate((Class) element)) {
            return true;
        }
        return TPropertyMapping.canInstantiate((ModelElement) element);
    }

    private List<TNodeTemplate> collectNodeTemplates(ModelElement context) {
        if (!(context instanceof ModelTree)) {
            return Collections.emptyList();
        }
        ModelTree tree = (ModelTree) context;
        return Stream.concat(Stream.of((ModelElement) tree), compositionDescendants(tree))
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
                .collect(Collectors.toMap(TNodeTemplate::getElement, Function.identity(), (left, right) -> left,
                        LinkedHashMap::new));
    }

    private ArchitectureData buildArchitectureData(ModelElement context) {
        List<TNodeTemplate> templates = collectNodeTemplates(context);
        Map<String, ArchitectureNode> nodesByName = new LinkedHashMap<>();
        for (TNodeTemplate template : templates) {
            if (!isApplicationComponent(template)) {
                continue;
            }
            ArchitectureNode node = toArchitectureNode(template);
            if (node != null) {
                nodesByName.putIfAbsent(node.getName(), node);
            }
        }
        Map<Class, TNodeTemplate> templateIndex = indexNodeTemplates(templates);
        List<ArchitectureEdge> edges = collectArchitectureEdges(templates, templateIndex, nodesByName.keySet());
        return new ArchitectureData(new ArrayList<>(nodesByName.values()), edges);
    }

    private boolean isApplicationComponent(TNodeTemplate template) {
        if (template == null || template.getElement() == null) {
            return false;
        }
        TNodeType nodeType = template.getNodeType();
        if (nodeType != null && nodeType.getElement() != null) {
            String typeName = nodeType.getElement().getName();
            if (typeName != null) {
                String normalized = typeName.toLowerCase();
                if (normalized.contains("lakesidepod") || normalized.endsWith("pod") || normalized.contains("swcomponent")) {
                    return true;
                }
            }
        }
        return getTemplatePropertyValue(template, "dockerImage") != null
                || getTemplatePropertyValue(template, "docker_image") != null;
    }

    private ArchitectureNode toArchitectureNode(TNodeTemplate template) {
        if (template == null || template.getElement() == null) {
            return null;
        }
        String name = template.getElement().getName();
        if (name == null || name.trim().isEmpty()) {
            return null;
        }
        String layer = determineLayer(template);
        return new ArchitectureNode(name, layer != null ? layer : "edge");
    }

    private String determineLayer(TNodeTemplate template) {
        String hostLayer = resolveHostLayer(template);
        if (hostLayer != null) {
            return hostLayer;
        }
        String explicitLayer = getTemplatePropertyValue(template, "layer");
        if (explicitLayer != null) {
            return normalizeLayer(explicitLayer);
        }
        String restrictedLayer = getTemplatePropertyValue(template, "layer_restriction");
        if (restrictedLayer != null && !"none".equalsIgnoreCase(restrictedLayer)) {
            return normalizeLayer(restrictedLayer);
        }
        return "edge";
    }

    private String resolveHostLayer(TNodeTemplate template) {
        RequirementsType requirements = resolveRequirements(template);
        if (requirements == null) {
            return null;
        }
        for (TRequirement requirement : resolveRequirementEntries(requirements)) {
            if (requirement.getElement() == null) {
                continue;
            }
            String requirementName = requirement.getElement().getName();
            if (requirementName == null || !requirementName.equalsIgnoreCase("host")) {
                continue;
            }
            TNodeTemplate hostTarget = requirement.getNode();
            if (hostTarget == null) {
                continue;
            }
            String layer = getTemplatePropertyValue(hostTarget, "layer");
            if (layer != null) {
                return normalizeLayer(layer);
            }
        }
        return null;
    }

    private List<ArchitectureEdge> collectArchitectureEdges(List<TNodeTemplate> templates,
            Map<Class, TNodeTemplate> templateIndex, Set<String> allowedNodes) {
        List<ArchitectureEdge> edges = new ArrayList<>();
        Set<String> seen = new LinkedHashSet<>();
        logDebug("Collecting architecture edges for " + allowedNodes.size() + " candidate nodes");
        for (TNodeTemplate template : templates) {
            if (!isApplicationComponent(template) || template.getElement() == null) {
                continue;
            }
            String sourceName = template.getElement().getName();
            if (sourceName == null || !allowedNodes.contains(sourceName)) {
                continue;
            }
            RequirementsType requirements = resolveRequirements(template);
            if (requirements != null) {
                for (TRequirement requirement : resolveRequirementEntries(requirements)) {
                    if (!isConnectsRequirement(requirement)) {
                        continue;
                    }
                    Collection<String> targets = resolveRequirementTargets(template, requirement, templateIndex);
                    if (targets.isEmpty()) {
                        logDebug("Connects requirement on " + sourceName + " has no resolved targets");
                    } else {
                        logDebug("Connects requirement on " + sourceName + " targets " + targets);
                    }
                    addArchitectureEdges(edges, seen, allowedNodes, sourceName, targets);
                }
            } else {
                logDebug("No requirements found for " + sourceName + " – unable to derive edges");
            }
        }
        logDebug("Architecture edge collection complete: " + edges.size() + " edge(s) detected");
        return edges;
    }

    private void addArchitectureEdges(List<ArchitectureEdge> edges, Set<String> seen, Set<String> allowedNodes,
            String sourceName, Collection<String> targets) {
        for (String targetName : targets) {
            if (targetName == null || !allowedNodes.contains(targetName)) {
                continue;
            }
            String key = sourceName + "->" + targetName;
            if (seen.add(key)) {
                edges.add(new ArchitectureEdge(sourceName, targetName));
                logDebug("Recorded architecture edge " + sourceName + " -> " + targetName);
            }
        }
    }

    private boolean isConnectsRequirement(TRequirement requirement) {
        if (requirement == null || requirement.getElement() == null) {
            return false;
        }
        String name = requirement.getElement().getName();
        return name != null && name.equalsIgnoreCase("connects");
    }

    private Collection<String> resolveRequirementTargets(TNodeTemplate source, TRequirement requirement,
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

    private RequirementsType resolveRequirements(TNodeTemplate template) {
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

    private List<TRequirement> resolveRequirementEntries(RequirementsType requirements) {
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

    private String renderArchitectureBlock(ArchitectureData data, String indentUnit) {
        StringBuilder builder = new StringBuilder();
        builder.append(indentUnit).append("architecture:\n");
        appendNodesSection(builder, data.getNodes(), indentUnit);
        appendEdgesSection(builder, data.getEdges(), indentUnit);
        return builder.toString();
    }

    private void appendNodesSection(StringBuilder builder, List<ArchitectureNode> nodes, String indentUnit) {
        String nodesIndent = repeatIndent(2, indentUnit);
        builder.append(nodesIndent).append("nodes:");
        if (nodes.isEmpty()) {
            builder.append(" []\n");
            return;
        }
        builder.append("\n");
        String entryIndent = repeatIndent(3, indentUnit);
        for (ArchitectureNode node : nodes) {
            builder.append(entryIndent).append("- name: ").append(formatScalar(node.getName())).append("\n");
            builder.append(entryIndent).append("  layer: ").append(formatScalar(node.getLayer())).append("\n");
        }
    }

    private void appendEdgesSection(StringBuilder builder, List<ArchitectureEdge> edges, String indentUnit) {
        String edgesIndent = repeatIndent(2, indentUnit);
        builder.append(edgesIndent).append("edges:");
        if (edges.isEmpty()) {
            builder.append(" []\n");
            return;
        }
        builder.append("\n");
        String entryIndent = repeatIndent(3, indentUnit);
        for (ArchitectureEdge edge : edges) {
            builder.append(entryIndent).append("- first_node: ").append(formatScalar(edge.getFirstNode())).append("\n");
            builder.append(entryIndent).append("  second_node: ").append(formatScalar(edge.getSecondNode())).append("\n");
        }
    }

    private CharSequence renderRequirementsBlock(ModelElement context, String indentUnit) {
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
            builder.append(entryIndent).append("- connects_to: ").append(formatScalar(connection)).append("\n");
        }
        return builder.toString();
    }

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

    private String formatInlineList(Collection<String> values) {
        if (values == null || values.isEmpty()) {
            return "[]";
        }
        return values.stream()
                .filter(Objects::nonNull)
                .map(this::formatScalar)
                .collect(Collectors.joining(", ", "[ ", " ]"));
    }

    private String getTemplatePropertyValue(TNodeTemplate template, String propertyName) {
        if (template == null || template.getElement() == null || propertyName == null) {
            return null;
        }
        for (Attribute attribute : template.getElement().getOwnedAttribute()) {
            if (!TPropertyDef.canInstantiate(attribute)) {
                continue;
            }
            String attributeName = attribute.getName();
            if (attributeName == null || !attributeName.equalsIgnoreCase(propertyName)) {
                continue;
            }
            return normalizeValue(attribute.getValue());
        }
        return null;
    }

    private String readTaggedValue(ModelElement element, String propertyName) {
        if (element == null || propertyName == null) {
            return null;
        }
        for (Stereotype stereotype : element.getExtension()) {
            String value = element.getProperty(stereotype, propertyName);
            String normalized = normalizeValue(value);
            if (normalized != null) {
                return normalized.replace("\"", "").replace("'", "");
            }
        }
        return null;
    }

    private String normalizeLayer(String layer) {
        String normalized = normalizeValue(layer);
        return normalized != null ? normalized.toLowerCase() : null;
    }

    private String normalizeValue(String raw) {
        if (raw == null) {
            return null;
        }
        String trimmed = raw.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }

    private String repeatIndent(int depth, String indentUnit) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            builder.append(indentUnit);
        }
        return builder.toString();
    }

    private ModelTree findRootTree(ModelElement element) {
        if (element == null) {
            return null;
        }
        ModelElement current = element;
        ModelTree lastTree = null;
        while (current instanceof ModelTree) {
            lastTree = (ModelTree) current;
            MObject owner = ((ModelTree) current).getCompositionOwner();
            if (!(owner instanceof ModelElement)) {
                break;
            }
            current = (ModelElement) owner;
        }
        return lastTree;
    }

    private boolean isSystemParameters(Object candidate) {
        TNodeTemplate template = asNodeTemplate(candidate);
        if (template != null && isSystemParametersTemplate(template)) {
            return true;
        }
        TParameter parameter = asParameter(candidate);
        return parameter != null && isSystemParametersParameter(parameter);
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

    private boolean isSystemParametersElement(ModelTree candidate) {
        if (candidate == null) {
            return false;
        }
        if (candidate instanceof Class) {
            Class clazz = (Class) candidate;
            if (TNodeTemplate.canInstantiate(clazz)) {
                TNodeTemplate template = TNodeTemplate.instantiate(clazz);
                if (template != null && isSystemParametersTemplate(template)) {
                    return true;
                }
            }
            if (TParameter.canInstantiate(clazz)) {
                TParameter parameter = TParameter.instantiate(clazz);
                if (parameter != null && isSystemParametersParameter(parameter)) {
                    return true;
                }
            }
        } else if (candidate instanceof ModelElement) {
            ModelElement element = (ModelElement) candidate;
            if (TParameter.canInstantiate(element)) {
                TParameter parameter = TParameter.instantiate(element);
                return isSystemParametersParameter(parameter);
            }
        }
        return false;
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

    private TParameter asParameter(Object candidate) {
        if (candidate instanceof TParameter) {
            return (TParameter) candidate;
        }
        if (candidate instanceof ModelElement) {
            ModelElement element = (ModelElement) candidate;
            if (TParameter.canInstantiate(element)) {
                return TParameter.instantiate(element);
            }
        }
        return null;
    }

    private String nodeTypeLocalName(Object candidate) {
        TNodeTemplate template = asNodeTemplate(candidate);
        if (template != null && template.getNodeType() != null && template.getNodeType().getElement() != null) {
            return template.getNodeType().getElement().getName();
        }
        return "";
    }

    private String formatScalar(Object value) {
        if (value == null) {
            return "\"\"";
        }
        String asString = value.toString();
        if (asString == null) {
            return "\"\"";
        }
        String trimmed = asString.trim();
        if (trimmed.isEmpty()) {
            return "\"\"";
        }
        if (looksLikeBoolean(trimmed) || looksLikeNumber(trimmed) || "null".equalsIgnoreCase(trimmed)) {
            return trimmed;
        }
        String escaped = trimmed.replace("\"", "\\\"");
        return "\"" + escaped + "\"";
    }

    private boolean looksLikeBoolean(String value) {
        return "true".equalsIgnoreCase(value) || "false".equalsIgnoreCase(value);
    }

    private boolean looksLikeNumber(String value) {
        return value.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * Return true if none of the owned elements / attributes / composition children
     * (recursively)
     * apply the given stereotype name.
     * Recursive traversal replaces previous reliance on owned association ends.
     */
    private boolean hasNoStereotypeApplications(ModelTree context, String stereotypeName) {
        Stream<ModelElement> ownedElements = context.getOwnedElement().stream()
                .filter(ModelElement.class::isInstance)
                .map(ModelElement.class::cast);

        Stream<ModelElement> ownedAttributes = context instanceof Class
                ? ((Class) context).getOwnedAttribute().stream()
                        .filter(ModelElement.class::isInstance)
                        .map(ModelElement.class::cast)
                : Stream.empty();

        Stream<ModelElement> compositionDescendants = compositionDescendants(context);

        return Stream.concat(Stream.concat(ownedElements, ownedAttributes), compositionDescendants)
                .distinct()
                .noneMatch(element -> element.getExtension().stream()
                        .anyMatch(st -> st.getName().equals(stereotypeName)));
    }

    /**
     * Recursively collect all composition children below the given context.
     */
    private Stream<ModelElement> compositionDescendants(ModelTree parent) {
        return parent.getCompositionChildren().stream()
                .filter(ModelElement.class::isInstance)
                .map(ModelElement.class::cast)
                .flatMap(child -> {
                    if (child instanceof ModelTree) {
                        return Stream.concat(Stream.of(child), compositionDescendants((ModelTree) child));
                    }
                    return Stream.of(child);
                });
    }

    @objid("b6716c1e-6ab4-4dce-8bbe-f749a6185d60")
    protected String renderTemplate(Handlebars handlebars, Object data) throws IOException {
        return renderTemplate(handlebars, data, MAIN_TEMPLATE);
    }

    @objid("7c9a2f2f-e6e3-4c71-8db5-7b7dcbaa1cd0")
    protected String renderTemplate(Handlebars handlebars, Object data, String templateName) throws IOException {
        Template mainTemplate = handlebars.compile(templateName);
        try (StringWriter writer = new StringWriter()) {
            mainTemplate.apply(data, writer);
            return writer.toString();
        }
    }

    @objid("a0abc478-ed5d-497e-b9ec-7a8ca374ad06")
    final class Import {
        @objid("17c76b26-3910-426f-8cd6-38dede4173ba")
        private final String file;

        @objid("cd485831-b7d3-44c3-a39f-0cd6485a7afd")
        private final String namespaceUri;

        @objid("bc36ccf4-d7ce-4126-bb6a-e5b56eb5001f")
        private final String namespacePrefix;

        @objid("cab33e64-b830-4ee5-be1e-d67a046d71b3")
        public Import(String file, String namespaceUri, String namespacePrefix) {
            this.file = file;
            this.namespaceUri = namespaceUri;
            this.namespacePrefix = namespacePrefix;
        }

        @objid("9e614e14-9276-4de0-adbf-7d3284e36ffa")
        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Import anImport = (Import) o;

            return file.equals(anImport.file) && namespaceUri.equals(anImport.namespaceUri)
                    && namespacePrefix.equals(anImport.namespacePrefix);
        }

        @objid("7539be58-524e-48ff-911d-db847524b474")
        public String getFile() {
            return file;
        }

        @objid("e1838d70-2162-47c9-ad66-a1a204354ba1")
        public String getNamespaceUri() {
            return namespaceUri;
        }

        @objid("687bfd11-4d48-4a5c-89d0-f9dcd58d825d")
        public String getNamespacePrefix() {
            return namespacePrefix;
        }

        @objid("76de5aa3-91b2-44f3-9741-8405f5ca3e6d")
        @Override
        public int hashCode() {
            return Objects.hash(file, namespaceUri, namespacePrefix);
        }

    }

    /**
     * Produce a concise description for logging purposes from an MObject.
     * Format: name=<name>, uuid=<uuid>, mclass=<mclass>
     */
    public String describeObject(MObject object) {
        if (object == null) {
            return "null";
        }
        try {
            final String name = (object instanceof ModelElement) ? ((ModelElement) object).getName() : object.toString();
            final String id;
            String resolvedId;
            try {
                String uuid = object.getUuid();
                resolvedId = uuid != null ? uuid : "<no-uuid>";
            } catch (Throwable t) {
                resolvedId = "<uuid-unavailable>";
            }
            id = resolvedId;
            final String mclass = object.getMClass() != null ? object.getMClass().getName() : "<no-mclass>";
            return String.format("name=%s, uuid=%s, mclass=%s", name, id, mclass);
        } catch (Exception ex) {
            // Best-effort fallback
            return object.toString();
        }
    }

    private static final class ArchitectureNode {
        private final String name;
        private final String layer;

        ArchitectureNode(String name, String layer) {
            this.name = name;
            this.layer = layer;
        }

        String getName() {
            return name;
        }

        String getLayer() {
            return layer;
        }
    }

    private static final class ArchitectureEdge {
        private final String firstNode;
        private final String secondNode;

        ArchitectureEdge(String firstNode, String secondNode) {
            this.firstNode = firstNode;
            this.secondNode = secondNode;
        }

        String getFirstNode() {
            return firstNode;
        }

        String getSecondNode() {
            return secondNode;
        }
    }

    private static final class ArchitectureData {
        private final List<ArchitectureNode> nodes;
        private final List<ArchitectureEdge> edges;

        ArchitectureData(List<ArchitectureNode> nodes, List<ArchitectureEdge> edges) {
            this.nodes = nodes != null ? nodes : Collections.emptyList();
            this.edges = edges != null ? edges : Collections.emptyList();
        }

        boolean isEmpty() {
            return nodes.isEmpty();
        }

        List<ArchitectureNode> getNodes() {
            return nodes;
        }

        List<ArchitectureEdge> getEdges() {
            return edges;
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
