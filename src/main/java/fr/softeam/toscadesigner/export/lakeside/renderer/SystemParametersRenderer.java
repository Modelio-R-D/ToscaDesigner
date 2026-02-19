package fr.softeam.toscadesigner.export.lakeside.renderer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;
import java.util.stream.Stream;

import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Class;

import fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.TParameter;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeTemplate;
import fr.softeam.toscadesigner.export.util.NodeTemplateCollector;
import fr.softeam.toscadesigner.export.util.PropertyTreeBuilder;
import fr.softeam.toscadesigner.export.util.TemplateClassifier;
import fr.softeam.toscadesigner.export.util.YamlFormatter;

/**
 * Renders system parameters blocks for YAML output.
 */
public final class SystemParametersRenderer {

    private final TemplateClassifier classifier;
    private final NodeTemplateCollector collector;

    public SystemParametersRenderer(TemplateClassifier classifier, NodeTemplateCollector collector) {
        this.classifier = classifier;
        this.collector = collector;
    }

    /**
     * Render the complete system_params block.
     */
    public CharSequence renderSystemParametersBlock(ModelElement context, String indentUnit) {
        Optional<ModelTree> systemParameters = findSystemParametersElement(context);
        java.util.Map<String, Object> propertyTree = systemParameters.isPresent()
                ? PropertyTreeBuilder.buildPropertyTree(systemParameters.get())
                : java.util.Collections.emptyMap();
        java.util.List<TNodeTemplate> podTemplates = collector.collectPodTemplates(context);
        
        if (propertyTree.isEmpty() && podTemplates.isEmpty()) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        builder.append(indentUnit).append("system_params:\n");
        if (!propertyTree.isEmpty()) {
            builder.append(PropertyTreeBuilder.renderYamlTree(propertyTree, 2, indentUnit));
        }
        if (!podTemplates.isEmpty()) {
            builder.append(renderPodTemplatesBlock(podTemplates, indentUnit));
        }
        return builder.toString();
    }

    /**
     * Render the pod_templates section within system_params.
     */
    private String renderPodTemplatesBlock(java.util.List<TNodeTemplate> podTemplates, String indentUnit) {
        StringBuilder builder = new StringBuilder();
        String sectionIndent = PropertyTreeBuilder.repeatIndent(2, indentUnit);
        builder.append(sectionIndent).append("pod_templates:\n");
        String templateIndent = PropertyTreeBuilder.repeatIndent(3, indentUnit);
        String propertyIndent = PropertyTreeBuilder.repeatIndent(4, indentUnit);
        
        for (TNodeTemplate template : podTemplates) {
            if (template == null || template.getElement() == null) {
                continue;
            }
            String templateName = classifier.templateDisplayName(template);
            if (templateName == null || templateName.trim().isEmpty()) {
                continue;
            }
            builder.append(templateIndent).append(templateName).append(":\n");
            for (org.modelio.metamodel.uml.statik.Attribute attribute : template.getElement().getOwnedAttribute()) {
                if (!fr.softeam.toscadesigner.api.tosca.standard.attribute.TPropertyDef.canInstantiate(attribute)) {
                    continue;
                }
                String propertyName = attribute.getName();
                if (propertyName == null || propertyName.trim().isEmpty()) {
                    continue;
                }
                builder.append(propertyIndent)
                        .append(propertyName)
                        .append(": ")
                        .append(YamlFormatter.formatScalar(attribute.getValue()))
                        .append("\n");
            }
        }
        return builder.toString();
    }

    /**
     * Find the SystemParameters element in the context tree.
     */
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
     * Check if a model tree element represents system parameters.
     */
    private boolean isSystemParametersElement(ModelTree candidate) {
        if (candidate == null) {
            return false;
        }
        if (candidate instanceof Class) {
            Class clazz = (Class) candidate;
            if (TNodeTemplate.canInstantiate(clazz)) {
                TNodeTemplate template = TNodeTemplate.instantiate(clazz);
                if (template != null && classifier.isSystemParametersTemplate(template)) {
                    return true;
                }
            }
            if (TParameter.canInstantiate(clazz)) {
                TParameter parameter = TParameter.instantiate(clazz);
                if (parameter != null && classifier.isSystemParametersParameter(parameter)) {
                    return true;
                }
            }
        } else if (candidate instanceof ModelElement) {
            ModelElement element = (ModelElement) candidate;
            if (TParameter.canInstantiate(element)) {
                TParameter parameter = TParameter.instantiate(element);
                return classifier.isSystemParametersParameter(parameter);
            }
        }
        return false;
    }
}
