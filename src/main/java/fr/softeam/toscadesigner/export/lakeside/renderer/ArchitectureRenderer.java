package fr.softeam.toscadesigner.export.lakeside.renderer;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelio.api.module.context.log.ILogService;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;

import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeTemplate;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeType;
import fr.softeam.toscadesigner.export.util.PropertyTreeBuilder;
import fr.softeam.toscadesigner.export.util.YamlFormatter;

/**
 * Renders architecture information from node templates.
 * Extracts application components, determines layers, and builds edge connections.
 */
public final class ArchitectureRenderer {
    
    private final ILogService logger;

    public ArchitectureRenderer(ILogService logger) {
        this.logger = logger;
    }

    /**
     * Build architecture data from model element context.
     */
    public ArchitectureData buildArchitectureData(ModelElement context) {
        ModelTree root = findRootTree(context);
        List<TNodeTemplate> templates = root != null ? collectNodeTemplates(root) : collectNodeTemplates(context);
        Map<String, String> computeTemplatesByLayer = buildComputeTemplateNamesByLayer(templates);

        ArchitectureData fromArchitecture = buildFromArchitecturePackage(root, computeTemplatesByLayer);
        if (!fromArchitecture.isEmpty()) {
            return fromArchitecture;
        }

        Map<String, ArchitectureNode> nodesByName = new LinkedHashMap<>();
        for (TNodeTemplate template : templates) {
            if (!isComputeNode(template)) {
                continue;
            }
            ArchitectureNode node = toArchitectureNode(template, computeTemplatesByLayer);
            if (node != null) {
                nodesByName.putIfAbsent(node.getName(), node);
            }
        }
        List<ArchitectureEdge> edges = Collections.emptyList();
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

    private ArchitectureNode toArchitectureNode(TNodeTemplate template, Map<String, String> computeTemplatesByLayer) {
        if (template == null || template.getElement() == null) {
            return null;
        }
        String name = template.getElement().getName();
        if (name == null || name.trim().isEmpty()) {
            return null;
        }
        String layer = determineLayer(template);
        String normalizedLayer = layer != null ? layer : "edge";
        String type = resolveComputeTemplate(normalizedLayer, computeTemplatesByLayer);
        return new ArchitectureNode(name, normalizedLayer, type);
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

    private ArchitectureData buildFromArchitecturePackage(ModelTree root, Map<String, String> computeTemplatesByLayer) {
        if (root == null) {
            return new ArchitectureData(Collections.emptyList(), Collections.emptyList());
        }
        ModelTree architectureRoot = findArchitecturePackage(root);
        if (architectureRoot == null) {
            return new ArchitectureData(Collections.emptyList(), Collections.emptyList());
        }

        Map<Class, ArchitectureNode> nodesByClass = new LinkedHashMap<>();
        for (Class nodeClass : collectArchitectureNodeClasses(architectureRoot)) {
            ArchitectureNode node = toArchitectureNode(nodeClass, computeTemplatesByLayer);
            if (node != null) {
                nodesByClass.putIfAbsent(nodeClass, node);
            }
        }
        if (nodesByClass.isEmpty()) {
            return new ArchitectureData(Collections.emptyList(), Collections.emptyList());
        }
        List<ArchitectureEdge> edges = collectArchitectureEdges(root, architectureRoot, nodesByClass);
        return new ArchitectureData(new ArrayList<>(nodesByClass.values()), edges);
    }

    private List<ArchitectureEdge> collectArchitectureEdges(ModelTree root, ModelTree architectureRoot,
            Map<Class, ArchitectureNode> nodesByClass) {
        List<ArchitectureEdge> edges = new ArrayList<>();
        Set<String> seen = new LinkedHashSet<>();
        Map<String, ArchitectureNode> nodesByName = new LinkedHashMap<>();
        for (ArchitectureNode node : nodesByClass.values()) {
            if (node != null && node.getName() != null) {
                nodesByName.putIfAbsent(node.getName(), node);
            }
        }
        Set<Association> associations = collectArchitectureAssociations(architectureRoot);
        associations.addAll(collectAssociationsFromNodes(nodesByClass.keySet()));
        associations.addAll(collectAssociationsFromRoot(root));

        for (Association association : associations) {
            List<String> nodeNames = new ArrayList<>();
            for (AssociationEnd end : association.getEnd()) {
                Classifier classifier = end.getSource();
                ArchitectureNode node = null;
                if (classifier instanceof Class) {
                    Class clazz = (Class) classifier;
                    node = nodesByClass.get(clazz);
                }
                if (node == null && classifier != null && classifier.getName() != null) {
                    node = nodesByName.get(classifier.getName());
                }
                if (node != null && node.getName() != null) {
                    nodeNames.add(node.getName());
                }
            }
            if (nodeNames.size() < 2) {
                continue;
            }
            String first = nodeNames.get(0);
            String second = nodeNames.get(1);
            if (first == null || second == null || first.equals(second)) {
                continue;
            }
            String key = normalizedEdgeKey(first, second);
            if (seen.add(key)) {
                edges.add(new ArchitectureEdge(first, second));
            }
        }
        return edges;
    }

    private Set<Association> collectArchitectureAssociations(ModelTree architectureRoot) {
        if (architectureRoot == null) {
            return Collections.emptySet();
        }
        Set<Association> associations = new LinkedHashSet<>();
        java.util.stream.Stream<ModelElement> candidates = java.util.stream.Stream.concat(
                architectureRoot.getOwnedElement().stream(),
                architectureRoot.getCompositionChildren().stream())
                .filter(ModelElement.class::isInstance)
                .map(ModelElement.class::cast);
        candidates.filter(Association.class::isInstance)
                .map(Association.class::cast)
                .forEach(associations::add);
        compositionDescendants(architectureRoot)
                .filter(Association.class::isInstance)
                .map(Association.class::cast)
                .forEach(associations::add);
        return associations;
    }

    private Set<Association> collectAssociationsFromRoot(ModelTree root) {
        if (root == null) {
            return Collections.emptySet();
        }
        Set<Association> associations = new LinkedHashSet<>();
        java.util.stream.Stream<ModelElement> candidates = java.util.stream.Stream.concat(
                root.getOwnedElement().stream(),
                root.getCompositionChildren().stream())
                .filter(ModelElement.class::isInstance)
                .map(ModelElement.class::cast);
        candidates.filter(Association.class::isInstance)
                .map(Association.class::cast)
                .forEach(associations::add);
        compositionDescendants(root)
                .filter(Association.class::isInstance)
                .map(Association.class::cast)
                .forEach(associations::add);
        return associations;
    }

    private Set<Association> collectAssociationsFromNodes(Set<Class> nodeClasses) {
        if (nodeClasses == null || nodeClasses.isEmpty()) {
            return Collections.emptySet();
        }
        Set<Association> associations = new LinkedHashSet<>();
        for (Class nodeClass : nodeClasses) {
            if (nodeClass == null) {
                continue;
            }
            for (AssociationEnd end : collectOwnedAssociationEnds(nodeClass)) {
                Association association = end.getAssociation();
                if (association != null) {
                    associations.add(association);
                }
            }
        }
        return associations;
    }

    private List<AssociationEnd> collectOwnedAssociationEnds(Class nodeClass) {
        List<AssociationEnd> ends = new ArrayList<>();
        if (nodeClass == null) {
            return ends;
        }
        for (ModelElement element : nodeClass.getOwnedElement()) {
            if (element instanceof AssociationEnd) {
                ends.add((AssociationEnd) element);
            }
        }
        try {
            Method method = nodeClass.getClass().getMethod("getOwnedEnd");
            Object result = method.invoke(nodeClass);
            if (result instanceof Collection) {
                for (Object item : (Collection<?>) result) {
                    if (item instanceof AssociationEnd) {
                        ends.add((AssociationEnd) item);
                    }
                }
            }
        } catch (ReflectiveOperationException ex) {
            // Best-effort: not all Modelio versions expose getOwnedEnd.
        }
        return ends;
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

    private ModelTree findArchitecturePackage(ModelTree root) {
        if (root == null) {
            return null;
        }
        if (nameEquals(root.getName(), "architecture")) {
            return root;
        }
        return compositionDescendants(root)
                .filter(ModelTree.class::isInstance)
                .map(ModelTree.class::cast)
                .filter(tree -> nameEquals(tree.getName(), "architecture"))
                .findFirst()
                .orElse(null);
    }

    private List<Class> collectArchitectureNodeClasses(ModelTree architectureRoot) {
        List<Class> nodes = new ArrayList<>();
        java.util.stream.Stream<ModelElement> candidates = java.util.stream.Stream.concat(
            architectureRoot.getOwnedElement().stream(),
            architectureRoot.getCompositionChildren().stream())
            .filter(ModelElement.class::isInstance)
            .map(ModelElement.class::cast);
        candidates.filter(Class.class::isInstance)
            .map(Class.class::cast)
            .filter(this::isArchitectureNode)
            .forEach(nodes::add);
        compositionDescendants(architectureRoot)
                .filter(Class.class::isInstance)
                .map(Class.class::cast)
                .filter(this::isArchitectureNode)
                .forEach(nodes::add);
        return nodes;
    }

    private boolean isArchitectureNode(Class candidate) {
        if (candidate == null) {
            return false;
        }
        return candidate.getExtension().stream()
                .anyMatch(st -> st.getName() != null && st.getName().equalsIgnoreCase("node"));
    }

    private ArchitectureNode toArchitectureNode(Class nodeClass, Map<String, String> computeTemplatesByLayer) {
        if (nodeClass == null) {
            return null;
        }
        String name = nodeClass.getName();
        if (name == null || name.trim().isEmpty()) {
            return null;
        }
        String layer = determineLayer(nodeClass);
        String normalizedLayer = layer != null ? layer : "edge";
        String type = resolveComputeTemplate(normalizedLayer, computeTemplatesByLayer);
        return new ArchitectureNode(name, normalizedLayer, type);
    }

    private String determineLayer(Class nodeClass) {
        String explicitLayer = getAttributeValue(nodeClass, "layer");
        if (explicitLayer != null) {
            return normalizeLayer(explicitLayer);
        }
        return inferLayerFromName(nodeClass.getName());
    }

    private String inferLayerFromName(String name) {
        if (name == null) {
            return null;
        }
        String normalized = name.trim().toLowerCase();
        if (normalized.startsWith("edge")) {
            return "edge";
        }
        if (normalized.startsWith("fog")) {
            return "fog";
        }
        if (normalized.startsWith("cloud")) {
            return "cloud";
        }
        return null;
    }

    private String getAttributeValue(Class nodeClass, String attributeName) {
        if (nodeClass == null || attributeName == null) {
            return null;
        }
        for (org.modelio.metamodel.uml.statik.Attribute attribute : nodeClass.getOwnedAttribute()) {
            String name = attribute.getName();
            if (name != null && name.equalsIgnoreCase(attributeName)) {
                return normalizeValue(attribute.getValue());
            }
        }
        return null;
    }

    private Map<String, String> buildComputeTemplateNamesByLayer(List<TNodeTemplate> templates) {
        Map<String, String> mapping = new LinkedHashMap<>();
        for (TNodeTemplate template : templates) {
            if (!isComputeNode(template)) {
                continue;
            }
            String layer = getTemplatePropertyValue(template, "layer");
            if (layer == null) {
                continue;
            }
            String normalizedLayer = normalizeLayer(layer);
            String name = normalizeTemplateName(template.getElement() != null ? template.getElement().getName() : null);
            if (normalizedLayer != null && name != null && !name.isEmpty()) {
                mapping.putIfAbsent(normalizedLayer, name);
            }
        }
        return mapping;
    }

    private String normalizeTemplateName(String name) {
        if (name == null) {
            return null;
        }
        String trimmed = name.trim();
        if (trimmed.length() > 4 && trimmed.toLowerCase().endsWith("node")) {
            return trimmed.substring(0, trimmed.length() - 4);
        }
        return trimmed;
    }

    private String resolveComputeTemplate(String layer, Map<String, String> computeTemplatesByLayer) {
        if (layer != null && computeTemplatesByLayer.containsKey(layer)) {
            return computeTemplatesByLayer.get(layer);
        }
        if (layer == null) {
            return "EdgeCompute";
        }
        switch (layer.toLowerCase()) {
            case "fog":
                return "FogCompute";
            case "cloud":
                return "CloudCompute";
            case "edge":
            default:
                return "EdgeCompute";
        }
    }

    private String normalizedEdgeKey(String first, String second) {
        if (first == null || second == null) {
            return "";
        }
        if (first.compareTo(second) <= 0) {
            return first + "::" + second;
        }
        return second + "::" + first;
    }

    private boolean nameEquals(String candidate, String expected) {
        if (candidate == null || expected == null) {
            return false;
        }
        return candidate.trim().equalsIgnoreCase(expected);
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
    public static String renderArchitectureBlock(ArchitectureData data, String indentUnit) {
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
            builder.append(entryIndent)
                    .append("- { name: ")
                    .append(YamlFormatter.formatScalar(node.getName()))
                    .append(", layer: ")
                    .append(YamlFormatter.formatScalar(node.getLayer()))
                    .append(", type: ")
                    .append(YamlFormatter.formatScalar(node.getType()))
                    .append(" }\n");
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
            builder.append(entryIndent)
                    .append("- { first_node: ")
                    .append(YamlFormatter.formatScalar(edge.getFirstNode()))
                    .append(", second_node: ")
                    .append(YamlFormatter.formatScalar(edge.getSecondNode()))
                    .append(" }\n");
        }
    }

    static final class ArchitectureNode {
        private final String name;
        private final String layer;
        private final String type;

        ArchitectureNode(String name, String layer, String type) {
            this.name = name;
            this.layer = layer;
            this.type = type;
        }

        String getName() {
            return name;
        }

        String getLayer() {
            return layer;
        }

        String getType() {
            return type;
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

    public static final class ArchitectureData {
        private final List<ArchitectureNode> nodes;
        private final List<ArchitectureEdge> edges;

        ArchitectureData(List<ArchitectureNode> nodes, List<ArchitectureEdge> edges) {
            this.nodes = nodes != null ? nodes : Collections.emptyList();
            this.edges = edges != null ? edges : Collections.emptyList();
        }

        List<ArchitectureNode> getNodes() {
            return nodes;
        }

        List<ArchitectureEdge> getEdges() {
            return edges;
        }

        public boolean isEmpty() {
            return nodes.isEmpty() && edges.isEmpty();
        }
    }
}
