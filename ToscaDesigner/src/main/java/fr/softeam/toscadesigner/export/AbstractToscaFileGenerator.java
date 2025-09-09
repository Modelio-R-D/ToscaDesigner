package fr.softeam.toscadesigner.export;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.helper.ConditionalHelpers;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.softeam.toscadesigner.api.tosca.standard.association.TRelationshipTemplate;
import fr.softeam.toscadesigner.api.tosca.standard.class_.CapabilityStereotype;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TCapabilityDefinition;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TCapabilityType;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeTemplate;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeType;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TRelationshipType;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TRequirement;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TRequirementDefinition;
import fr.softeam.toscadesigner.impl.ToscaDesignerModule;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("e7453252-f578-4da1-815c-d2ce0e765130")
public abstract class AbstractToscaFileGenerator {
    @objid ("6ca66b17-c54f-42dc-862d-4c41f8cbec9b")
    private static final String TEMPLATE_PATH = "/fr/softeam/templates/";

    @objid ("44617ecf-78ee-46d2-a367-cbdfee5c0854")
    private static final String MAIN_TEMPLATE = "_mainTemplate";

    @objid ("a897a13e-b8dc-4f4f-b967-60133eb7f69d")
    protected Handlebars handlebars = setupHandlebars();

    @objid ("e1bdb1e7-0783-441d-96e1-fdaa0f8e8514")
    protected abstract String getFileType();

    @objid ("ebedc3d7-f673-4680-a8b3-159124af40c8")
    protected abstract String[] getFileExtensions();

    @objid ("a064e044-abc6-430f-9a31-680bf8f13dad")
    public abstract void generateContent(MObject object) throws IOException;

    @objid ("0a64efd3-9d89-46b9-91e8-40aaa00626a4")
    protected String saveToFile(String[] fileExtensions, String fileType) {
        FileDialog fileDialog = new FileDialog(Display.getCurrent().getActiveShell(), SWT.SAVE);
        fileDialog.setFilterExtensions(fileExtensions);

        String filePath = fileDialog.open();

        return filePath;
    }

    @objid ("3a782c82-535e-40a1-96b6-10d70a8fd4b4")
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
        handlebars.registerHelper("noStereotypeApplications", (ModelTree context, Options options) -> 
            Stream.concat(
                context.getOwnedElement().stream(), 
                context instanceof Class ? ((Class) context).getOwnedAttribute().stream() : Stream.empty()
            )
            .noneMatch(element -> element.getExtension().stream()
                .anyMatch(stereotype -> stereotype.getName().equals(options.params[0]))
            )
        );
        handlebars.registerHelper("imports", (ModelElement context, Options options) -> {

            Set<Import> imports = new HashSet<>();

            // Check if the context is a Node Type
            if (new NodeTypeChecker().isTypeOf(context)) {

                TNodeType tNodeType = TNodeType.safeInstantiate((Class) context);

                // 1. Check for non-tosca derived type

                String derivedFromValue = tNodeType.getDerivedFrom().getName();
                String targetNamespace = tNodeType.getTargetNamespace();
                if (derivedFromValue != null && !derivedFromValue.startsWith("tosca")) {
                    imports.add(new Import(derivedFromValue + ".tosca", targetNamespace, "MYRTUS-"));
                }

                // 2. Check for non-tosca valid source types in capabilities
        //                CapabilityDefinitionsType capabilityDefinitionsTypeInstance = tNodeType.getCapabilityDefinitions();
        //                List<TCapabilityDefinition> capabilityDefinitions = capabilityDefinitionsTypeInstance.getCapabilityDefinition();
                // To develop when capabilities will be supported
        //                for (TCapabilityDefinition tCapabilityDefinition : capabilityDefinitions) {
        //                    if (hasNonToscaValidSourceType(capability)) {
        //                        imports.add(/* import statement */);
        //                    }
        //                }
            } else if (new TopologyTemplateChecker().isTypeOf(context)) {
                // 3. Check for non-tosca types in node templates
        List<TNodeTemplate> nodeTemplates = context.getCompositionChildren().stream().filter(object -> {
        Stereotype tNodeTemplateStereotype = ToscaDesignerModule.getInstance().getModuleContext()
        .getModelingSession().getMetamodelExtensions()
        .getStereotype("TNodeTemplate", object.getMClass());
        return tNodeTemplateStereotype != null
        && ((ModelElement) object).isStereotyped(tNodeTemplateStereotype);
        }).map(Class.class::cast).map(c -> TNodeTemplate.safeInstantiate(c)).collect(Collectors.toList());
        for (TNodeTemplate nodeTemplate : nodeTemplates) {

        String targetNamespace = ((Class) context).getOwner().getName();
        imports.add(new Import(nodeTemplate.getElement().getName() + ".tosca", targetNamespace , "MYRTUS-"));

        }

            }

            // Generate the import statements based on the imports list
            String importString = generateImportString(imports);
            return importString;

        });
        handlebars.registerHelpers(ConditionalHelpers.class);
        return handlebars;
    }

    @objid ("4e0fc0cf-420e-4626-b34d-fd6df12a1e01")
    private String generateImportString(Set<Import> imports) {
        StringBuilder importString = new StringBuilder();
        if (!imports.isEmpty())
            importString.append("imports:\n");
        for (Import anImport : imports) {
            importString.append("  - file: ").append(anImport.getFile()).append("\n").append("    namespace_uri: ")
                    .append(anImport.getNamespaceUri()).append("\n").append("    namespace_prefix: ")
                    .append(anImport.getNamespacePrefix()).append("\n");
        }

        return importString.toString();
    }

    @objid ("b6716c1e-6ab4-4dce-8bbe-f749a6185d60")
    protected String renderTemplate(Handlebars handlebars, Object data) throws IOException {
        Template mainTemplate = handlebars.compile(MAIN_TEMPLATE);
        try (StringWriter writer = new StringWriter()) {
            mainTemplate.apply(data, writer);
            return writer.toString();
        }
    }

    @objid ("a0abc478-ed5d-497e-b9ec-7a8ca374ad06")
    final class Import {
        @objid ("17c76b26-3910-426f-8cd6-38dede4173ba")
        private String file;

        @objid ("cd485831-b7d3-44c3-a39f-0cd6485a7afd")
        private String namespaceUri;

        @objid ("bc36ccf4-d7ce-4126-bb6a-e5b56eb5001f")
        private String namespacePrefix;

        @objid ("cab33e64-b830-4ee5-be1e-d67a046d71b3")
        public  Import(String file, String namespaceUri, String namespacePrefix) {
            this.file = file;
            this.namespaceUri = namespaceUri;
            this.namespacePrefix = namespacePrefix;
        }

        @objid ("9e614e14-9276-4de0-adbf-7d3284e36ffa")
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

        @objid ("7539be58-524e-48ff-911d-db847524b474")
        public String getFile() {
            return file;
        }

        @objid ("e1838d70-2162-47c9-ad66-a1a204354ba1")
        public String getNamespaceUri() {
            return namespaceUri;
        }

        @objid ("687bfd11-4d48-4a5c-89d0-f9dcd58d825d")
        public String getNamespacePrefix() {
            return namespacePrefix;
        }

        @objid ("76de5aa3-91b2-44f3-9741-8405f5ca3e6d")
        @Override
        public int hashCode() {
            return Objects.hash(file, namespaceUri, namespacePrefix);
        }

    }

}
