package fr.softeam.toscadesigner.export;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.modelio.api.module.context.log.ILogService;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.vcore.smkernel.mapi.MObject;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.helper.ConditionalHelpers;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.TParameter;
import fr.softeam.toscadesigner.api.tosca.standard.class_.CapabilityStereotype;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TCapabilityDefinition;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TCapabilityType;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeTemplate;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TPolicy;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TRelationshipType;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TRequirement;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TRequirementDefinition;

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

    private ArchitectureRenderer architectureRenderer;
    private RequirementsResolver requirementsResolver;

    protected void setLogger(ILogService logger) {
        this.logger = logger;
        this.architectureRenderer = new ArchitectureRenderer(logger);
        this.requirementsResolver = new RequirementsResolver(logger);
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
                        fr.softeam.toscadesigner.api.tosca.standard.association.TRelationshipTemplate relationship = tRequirement.getRelationship();
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
                        fr.softeam.toscadesigner.api.tosca.standard.association.TRelationshipTemplate tRelationshipTemplate = 
                            fr.softeam.toscadesigner.api.tosca.standard.association.TRelationshipTemplate.safeInstantiate(
                                (org.modelio.metamodel.uml.statik.Association) context);
                        TRelationshipType relationshipType = tRelationshipTemplate.getRelationshipType();
                        Class element = relationshipType.getElement();
                        propertyStringValue = element.getName();
                    }
                } else if (stereotype.getName().equals("TNodeTemplate")) {
                    TNodeTemplate tNodeTemplate = TNodeTemplate.safeInstantiate((Class) context);
                    if (searchedPropertyName.equals("nodeType")) {
                        fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeType nodeType = tNodeTemplate.getNodeType();
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
                        java.util.List<TNodeTemplate> targets = tPolicy.getTargets();
                        propertyStringValue = targets.stream()
                            .map(t -> t.getElement().getName())
                            .collect(java.util.stream.Collectors.joining(", "));

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
            Set<ImportCollector.Import> imports = ImportCollector.collectImports(context);
            return ImportCollector.generateImportString(imports, includeHeader, indent);
        });
        handlebars.registerHelper("nodeTypeLocalName", (Object candidate, Options options) -> nodeTypeLocalName(candidate));
        handlebars.registerHelper("formatYamlValue", (Object value, Options options) -> YamlFormatter.formatScalar(value));
        handlebars.registerHelper("isSystemParameters", (Object candidate, Options options) -> isSystemParameters(candidate));
        handlebars.registerHelper("lakesideSystemParams", (ModelElement context, Options options) -> {
            String indent = options.params.length > 0 ? Objects.toString(options.param(0), "  ") : "  ";
            return renderSystemParametersBlock(context, indent);
        });
        handlebars.registerHelper("lakesideNodeTemplates", (ModelElement context, Options options) -> {
            return renderTemplates(context, options, template -> true);
        });
        handlebars.registerHelper("lakesideComputeTemplates", (ModelElement context, Options options) -> {
            return renderTemplates(context, options, this::isComputeTemplate);
        });
        handlebars.registerHelper("lakesidePodTemplates", (ModelElement context, Options options) -> {
            return renderTemplates(context, options, this::isPodTemplate);
        });
        handlebars.registerHelper("lakesideArchitecture", (ModelElement context, Options options) -> {
            String indent = options.params.length > 0 ? Objects.toString(options.param(0), "  ") : "  ";
            ArchitectureRenderer.ArchitectureData architecture = architectureRenderer.buildArchitectureData(context);
            if (architecture.isEmpty()) {
                return "";
            }
            return ArchitectureRenderer.renderArchitectureBlock(architecture, indent);
        });
        handlebars.registerHelper("lakesideRequirements", (ModelElement context, Options options) -> {
            String indent = options.params.length > 0 ? Objects.toString(options.param(0), "  ") : "  ";
            return requirementsResolver.renderRequirementsBlock(context, indent);
        });
        handlebars.registerHelpers(ConditionalHelpers.class);
        return handlebars;
    }

    @objid("4e0fc0cf-420e-4626-b34d-fd6df12a1e01")
    private Set<ImportCollector.Import> collectImports(ModelElement context) {
        return ImportCollector.collectImports(context);
    }

    private String generateImportString(Set<ImportCollector.Import> imports, boolean includeHeader, String indent) {
        return ImportCollector.generateImportString(imports, includeHeader, indent);
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
        java.util.Map<String, Object> propertyTree = PropertyTreeBuilder.buildPropertyTree(systemParameters.get());
        if (propertyTree.isEmpty()) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        builder.append(indentUnit).append("system_params:\n");
        builder.append(PropertyTreeBuilder.renderYamlTree(propertyTree, 2, indentUnit));
        return builder.toString();
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
    private void collectNodeTemplatesForRendering(Object templateList) {
        // Stub for helper usage
    }

    private java.util.List<TNodeTemplate> collectNodeTemplatesForRendering(ModelElement context) {
        return collectNodeTemplatesForRendering(context, template -> true);
    }

    private java.util.List<TNodeTemplate> collectNodeTemplatesForRendering(ModelElement context,
            Predicate<TNodeTemplate> predicate) {
        if (!(context instanceof ModelTree)) {
            return java.util.Collections.emptyList();
        }
        Predicate<TNodeTemplate> filter = predicate != null ? predicate : template -> true;
        ModelTree tree = (ModelTree) context;
        return Stream.concat(Stream.of((ModelElement) tree), compositionDescendants(tree))
                .filter(Class.class::isInstance)
                .map(Class.class::cast)
                .filter(TNodeTemplate::canInstantiate)
                .map(TNodeTemplate::instantiate)
                .filter(Objects::nonNull)
                .filter(template -> !isSystemParametersTemplate(template))
                .filter(filter::test)
                .collect(java.util.stream.Collectors.toList());
    }

    private String renderTemplates(ModelElement context, Options options, Predicate<TNodeTemplate> predicate) {
        java.util.List<TNodeTemplate> nodeTemplates = collectNodeTemplatesForRendering(context, predicate);
        StringBuilder builder = new StringBuilder();
        for (TNodeTemplate template : nodeTemplates) {
            // Partial templates expect the raw Modelio element (ModelTree) so they can
            // navigate owned attributes while helpers can still cast back to
            // TNodeTemplate when needed.
            try {
                builder.append(options.fn(template.getElement()));
            } catch (IOException e) {
                throw new RuntimeException("Failed to render template block", e);
            }
        }
        return builder.toString();
    }

    private boolean isComputeTemplate(TNodeTemplate template) {
        String typeName = templateTypeKey(template);
        String localName = templateLocalTypeName(template);
        return "myrtus.dpe.compute".equals(typeName) || "myrtus.dpe.compute".equals(localName);
    }

    private boolean isPodTemplate(TNodeTemplate template) {
        String typeName = templateTypeKey(template);
        String localName = templateLocalTypeName(template);
        return "myrtus.dpe.nodes.lakesidepod".equals(typeName) || "myrtus.dpe.nodes.lakesidepod".equals(localName);
    }

    private String templateTypeKey(TNodeTemplate template) {
        if (template == null || template.getNodeType() == null) {
            return null;
        }
        fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeType nodeType = template.getNodeType();
        String localName = templateLocalTypeName(template);
        String namespace = nodeType.getTargetNamespace();
        if (localName == null) {
            return null;
        }
        if (namespace != null && !namespace.trim().isEmpty()) {
            return (namespace.trim() + "." + localName).toLowerCase();
        }
        return localName;
    }

    private String templateLocalTypeName(TNodeTemplate template) {
        if (template == null || template.getNodeType() == null || template.getNodeType().getElement() == null) {
            return null;
        }
        String localName = template.getNodeType().getElement().getName();
        return localName != null ? localName.trim().toLowerCase() : null;
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

    /**
     * Return true if none of the owned elements / attributes / composition children
     * (recursively) apply the given stereotype name.
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

    private String nodeTypeLocalName(Object candidate) {
        TNodeTemplate template = asNodeTemplate(candidate);
        if (template != null && template.getNodeType() != null && template.getNodeType().getElement() != null) {
            return template.getNodeType().getElement().getName();
        }
        return "";
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

}
