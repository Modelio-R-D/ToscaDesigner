package fr.softeam.toscadesigner.export.util;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Class;

import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeTemplate;

/**
 * Collects and filters node templates from a model element context.
 */
public final class NodeTemplateCollector {

    private final TemplateClassifier classifier;

    public NodeTemplateCollector(TemplateClassifier classifier) {
        this.classifier = classifier;
    }

    /**
     * Collect all node templates from a context.
     */
    java.util.List<TNodeTemplate> collectAll(ModelElement context) {
        return collect(context, template -> true);
    }

    /**
     * Collect compute templates from a context.
     */
    java.util.List<TNodeTemplate> collectComputeTemplates(ModelElement context) {
        return collect(context, classifier::isComputeTemplate);
    }

    /**
     * Collect pod templates from a context.
     */
    public java.util.List<TNodeTemplate> collectPodTemplates(ModelElement context) {
        return collect(context, classifier::isPodTemplate);
    }

    /**
     * Collect node templates matching a predicate.
     */
    public java.util.List<TNodeTemplate> collect(ModelElement context, Predicate<TNodeTemplate> predicate) {
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
                .filter(template -> !classifier.isSystemParametersTemplate(template))
                .filter(filter::test)
                .collect(java.util.stream.Collectors.toList());
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
}
