package fr.softeam.toscadesigner.export.util;

import java.util.stream.Stream;

import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Class;

/**
 * Helper for checking stereotype applications in model elements.
 */
public final class StereotypeHelper {

    /**
     * Return true if none of the owned elements / attributes / composition children
     * (recursively) apply the given stereotype name.
     */
    public boolean hasNoStereotypeApplications(ModelTree context, String stereotypeName) {
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
}
