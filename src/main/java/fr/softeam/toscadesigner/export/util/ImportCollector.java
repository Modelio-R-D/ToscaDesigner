package fr.softeam.toscadesigner.export.util;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.Class;

import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeTemplate;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeType;
import fr.softeam.toscadesigner.impl.ToscaDesignerModule;

/**
 * Collects and manages import statements for TOSCA files.
 * Generates proper import directives with namespace URIs and prefixes.
 */
public final class ImportCollector {
    
    private ImportCollector() {
        // Utility class - no instances
    }

    /**
     * Collect all imports from a model element context.
     */
    public static Set<Import> collectImports(ModelElement context) {
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

    /**
     * Generate import string from a set of imports.
     */
    public static String generateImportString(Set<Import> imports, boolean includeHeader, String indent) {
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

    /**
     * Represents a single TOSCA import statement with file, namespace URI, and prefix.
     */
    public static final class Import {
        private final String file;
        private final String namespaceUri;
        private final String namespacePrefix;

        Import(String file, String namespaceUri, String namespacePrefix) {
            this.file = file;
            this.namespaceUri = namespaceUri;
            this.namespacePrefix = namespacePrefix;
        }

        public String getFile() {
            return file;
        }

        public String getNamespaceUri() {
            return namespaceUri;
        }

        public String getNamespacePrefix() {
            return namespacePrefix;
        }

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

        @Override
        public int hashCode() {
            return Objects.hash(file, namespaceUri, namespacePrefix);
        }
    }

    /**
     * Helper class to check if an element is a NodeType.
     */
    private static final class NodeTypeChecker {
        boolean isTypeOf(ModelElement element) {
            return element instanceof Class && TNodeType.canInstantiate((Class) element);
        }
    }

    /**
     * Helper class to check if an element is a TopologyTemplate.
     */
    private static final class TopologyTemplateChecker {
        boolean isTypeOf(ModelElement element) {
            // TopologyTemplate detection logic - would need specific stereotype check
            return false;
        }
    }
}
