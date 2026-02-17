package fr.softeam.toscadesigner.export;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.modelio.api.module.context.log.ILogService;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Class;

import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeTemplate;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeType;

/**
 * Renders architecture information from node templates.
 * Extracts application components, determines layers, and builds edge connections.
 */
final class ArchitectureRenderer {
    
    private final ILogService logger;

    ArchitectureRenderer(ILogService logger) {
        this.logger = logger;
    }

    /**
     * Build architecture data from model element context.
     */
    ArchitectureData buildArchitectureData(ModelElement context) {
        List<TNodeTemplate> templates = collectNodeTemplates(context);
        Map<String, ArchitectureNode> nodesByName = new LinkedHashMap<>();
        for (TNodeTemplate template : templates) {
            if (!isComputeNode(template)) {
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

    private List<TNodeTemplate> collectNodeTemplates(ModelElement context) {
        if (!(context instanceof ModelTree)) {
            return Collections.emptyList();
        }
        ModelTree tree = (ModelTree) context;
        return java.util.stream.Stream.concat(
                java.util.stream.Stream.of((ModelElement) tree), 
                compositionDescendants(tree))
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

    /**
     * Identify infrastructure compute nodes (hardware/host level), not software pods.
     */
    private boolean isComputeNode(TNodeTemplate template) {
        if (template == null || template.getElement() == null) {
            return false;
        }
        TNodeType nodeType = template.getNodeType();
        if (nodeType != null && nodeType.getElement() != null) {
            String typeName = nodeType.getElement().getName();
            if (typeName != null) {
                String normalized = typeName.toLowerCase();
                // Treat any node type containing "compute" as hardware infrastructure
                return normalized.contains("compute");
            }
        }
        return false;
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
        // Placeholder - would need RequirementsResolver imported
        return null;
    }

    private String getTemplatePropertyValue(TNodeTemplate template, String propertyName) {
        if (template == null || template.getElement() == null || propertyName == null) {
            return null;
        }
        for (org.modelio.metamodel.uml.statik.Attribute attribute : template.getElement().getOwnedAttribute()) {
            if (!fr.softeam.toscadesigner.api.tosca.standard.attribute.TPropertyDef.canInstantiate(attribute)) {
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

    private List<ArchitectureEdge> collectArchitectureEdges(List<TNodeTemplate> templates,
            Map<Class, TNodeTemplate> templateIndex, Set<String> allowedNodes) {
        List<ArchitectureEdge> edges = new ArrayList<>();
        Set<String> seen = new LinkedHashSet<>();
        logDebug("Collecting architecture edges for " + allowedNodes.size() + " candidate nodes");
        for (TNodeTemplate template : templates) {
            if (!isComputeNode(template) || template.getElement() == null) {
                continue;
            }
            String sourceName = template.getElement().getName();
            if (sourceName == null || !allowedNodes.contains(sourceName)) {
                continue;
            }
            // Edge collection would require RequirementsResolver integration
            logDebug("No requirements found for " + sourceName + " – unable to derive edges");
        }
        logDebug("Architecture edge collection complete: " + edges.size() + " edge(s) detected");
        return edges;
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

    private void logDebug(String message) {
        if (this.logger != null) {
            this.logger.info("[LakesideDSE] " + message);
        }
    }

    /**
     * Render architecture block as YAML.
     */
    static String renderArchitectureBlock(ArchitectureData data, String indentUnit) {
        StringBuilder builder = new StringBuilder();
        builder.append(indentUnit).append("architecture:\n");
        appendNodesSection(builder, data.getNodes(), indentUnit);
        appendEdgesSection(builder, data.getEdges(), indentUnit);
        return builder.toString();
    }

    private static void appendNodesSection(StringBuilder builder, List<ArchitectureNode> nodes, String indentUnit) {
        String nodesIndent = PropertyTreeBuilder.repeatIndent(2, indentUnit);
        builder.append(nodesIndent).append("nodes:");
        if (nodes.isEmpty()) {
            builder.append(" []\n");
            return;
        }
        builder.append("\n");
        String entryIndent = PropertyTreeBuilder.repeatIndent(3, indentUnit);
        for (ArchitectureNode node : nodes) {
            builder.append(entryIndent).append("- name: ").append(YamlFormatter.formatScalar(node.getName())).append("\n");
            builder.append(entryIndent).append("  layer: ").append(YamlFormatter.formatScalar(node.getLayer())).append("\n");
        }
    }

    private static void appendEdgesSection(StringBuilder builder, List<ArchitectureEdge> edges, String indentUnit) {
        String edgesIndent = PropertyTreeBuilder.repeatIndent(2, indentUnit);
        builder.append(edgesIndent).append("edges:");
        if (edges.isEmpty()) {
            builder.append(" []\n");
            return;
        }
        builder.append("\n");
        String entryIndent = PropertyTreeBuilder.repeatIndent(3, indentUnit);
        for (ArchitectureEdge edge : edges) {
            builder.append(entryIndent).append("- first_node: ").append(YamlFormatter.formatScalar(edge.getFirstNode())).append("\n");
            builder.append(entryIndent).append("  second_node: ").append(YamlFormatter.formatScalar(edge.getSecondNode())).append("\n");
        }
    }

    static final class ArchitectureNode {
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

    static final class ArchitectureEdge {
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

    static final class ArchitectureData {
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
}
