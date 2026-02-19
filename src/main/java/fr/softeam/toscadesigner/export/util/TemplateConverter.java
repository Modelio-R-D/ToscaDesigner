package fr.softeam.toscadesigner.export.util;

import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Class;

import fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.TParameter;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeTemplate;

/**
 * Converts various candidate objects to specific TOSCA types.
 */
public final class TemplateConverter {

    private TemplateConverter() {
        // Utility class
    }

    /**
     * Convert a candidate object to TNodeTemplate if possible.
     */
    static TNodeTemplate asNodeTemplate(Object candidate) {
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

    /**
     * Convert a candidate object to TParameter if possible.
     */
    static TParameter asParameter(Object candidate) {
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
