package fr.softeam.toscadesigner.export.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Class;

import fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.TPropertyMapping;
import fr.softeam.toscadesigner.api.tosca.standard.attribute.TPropertyDef;

/**
 * Builds and renders YAML property trees from model elements.
 * Converts nested property structures into YAML format.
 */
public final class PropertyTreeBuilder {
    
    private PropertyTreeBuilder() {
        // Utility class - no instances
    }

    /**
     * Build a property tree from a model element, collecting all property values.
     */
    public static Map<String, Object> buildPropertyTree(ModelTree source) {
        Map<String, Object> root = new LinkedHashMap<>();
        if (source == null) {
            return root;
        }
        collectPropertyValues(source, "", root);
        return root;
    }

    /**
     * Recursively collect property values from a model tree into the accumulator map.
     */
    private static void collectPropertyValues(ModelTree source, String prefix, Map<String, Object> accumulator) {
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

        List<ModelTree> childTrees = new java.util.ArrayList<>();
        source.getOwnedElement().stream()
            .filter(ModelTree.class::isInstance)
            .map(ModelTree.class::cast)
            .forEach(childTrees::add);
        source.getCompositionChildren().stream()
            .filter(ModelTree.class::isInstance)
            .map(ModelTree.class::cast)
            .forEach(childTrees::add);

        childTrees.stream()
                .filter(PropertyTreeBuilder::isPropertyContainer)
                .forEach(child -> {
                    String childName = child.getName();
                    String nextPrefix = prefix;
                    if (childName != null && !childName.isEmpty()) {
                        nextPrefix = prefix.isEmpty() ? childName : prefix + "." + childName;
                    }
                    collectPropertyValues(child, nextPrefix, accumulator);
                });
    }

    private static boolean isPropertyContainer(ModelTree element) {
        if (element == null) {
            return false;
        }
        if (element instanceof Class && TPropertyMapping.canInstantiate((Class) element)) {
            return true;
        }
        return TPropertyMapping.canInstantiate((ModelElement) element);
    }

    /**
     * Insert a dot-separated property path into the accumulator map, creating nested structures.
     */
    @SuppressWarnings("unchecked")
    private static void insertProperty(Map<String, Object> parent, String propertyName, String value) {
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

    /**
     * Render a property tree as YAML, with proper nesting and indentation.
     */
    @SuppressWarnings("unchecked")
    static public String renderYamlTree(Map<String, Object> tree, int depth, String indentUnit) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Object> entry : tree.entrySet()) {
            String indent = repeatIndent(depth, indentUnit);
            builder.append(indent).append(entry.getKey()).append(":");
            Object value = entry.getValue();
            if (value instanceof Map) {
                builder.append("\n");
                builder.append(renderYamlTree((Map<String, Object>) value, depth + 1, indentUnit));
            } else {
                builder.append(" ").append(YamlFormatter.formatScalar(value)).append("\n");
            }
        }
        return builder.toString();
    }

    /**
     * Repeat an indent unit string depth times.
     */
    static public String repeatIndent(int depth, String indentUnit) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            builder.append(indentUnit);
        }
        return builder.toString();
    }
}
