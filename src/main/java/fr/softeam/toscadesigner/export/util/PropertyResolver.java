package fr.softeam.toscadesigner.export.util;

import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.Class;

import fr.softeam.toscadesigner.api.tosca.standard.class_.CapabilityStereotype;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TCapabilityDefinition;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TCapabilityType;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeTemplate;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeType;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TPolicy;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TRelationshipType;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TRequirement;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TRequirementDefinition;

/**
 * Resolves TOSCA property values from stereotyped model elements.
 * Handles special property resolution logic for different stereotype types.
 */
public final class PropertyResolver {

    private PropertyResolver() {
        // Utility class
    }

    /**
     * Resolve a property value from a model element with TOSCA stereotypes.
     * 
     * @param context The model element to resolve the property from
     * @param propertyName The name of the property to resolve
     * @return The resolved property value, or null if not found
     * @throws RuntimeException if the property cannot be found in any stereotype
     */
    public static String resolveProperty(ModelElement context, String propertyName) {
        for (Stereotype stereotype : context.getExtension()) {
            String propertyValue = context.getProperty(stereotype, propertyName);

            // Handle special stereotype-specific property resolution
            propertyValue = resolveStereotypeSpecificProperty(context, propertyName, stereotype, propertyValue);

            // If property not found with this stereotype, look for parent stereotypes
            while (propertyValue == null && stereotype.getParent() != null) {
                stereotype = stereotype.getParent();
                propertyValue = context.getProperty(stereotype, propertyName);
            }

            if (propertyValue != null) {
                return propertyValue;
            }
        }
        throw new RuntimeException("Stereotype property " + propertyName + " not found in " + context);
    }

    /**
     * Resolve stereotype-specific properties that require special handling.
     */
    private static String resolveStereotypeSpecificProperty(ModelElement context, String propertyName,
            Stereotype stereotype, String currentValue) {
        String stereotypeName = stereotype.getName();

        switch (stereotypeName) {
            case "TRequirement":
                return resolveTRequirementProperty(context, propertyName);
            case "TRequirementDefinition":
                return resolveTRequirementDefinitionProperty(context, propertyName);
            case "TRelationshipTemplate":
                return resolveTRelationshipTemplateProperty(context, propertyName);
            case "TNodeTemplate":
                return resolveTNodeTemplateProperty(context, propertyName);
            case "TCapabilityDefinition":
                return resolveTCapabilityDefinitionProperty(context, propertyName);
            case "TPolicy":
                return resolveTPolicyProperty(context, propertyName);
            default:
                return currentValue;
        }
    }

    private static String resolveTRequirementProperty(ModelElement context, String propertyName) {
        TRequirement tRequirement = TRequirement.safeInstantiate((Class) context);
        switch (propertyName) {
            case "node":
                TNodeTemplate node = tRequirement.getNode();
                return node != null ? node.getElement().getName() : "''";
            case "capability":
                CapabilityStereotype capability = tRequirement.getCapability();
                return capability != null ? capability.getElement().getName() : "";
            case "relationship":
                fr.softeam.toscadesigner.api.tosca.standard.association.TRelationshipTemplate relationship = 
                    tRequirement.getRelationship();
                return relationship != null ? relationship.getElement().getName() : "";
            default:
                return null;
        }
    }

    private static String resolveTRequirementDefinitionProperty(ModelElement context, String propertyName) {
        TRequirementDefinition tRequirementDefinition = TRequirementDefinition.safeInstantiate((Class) context);
        switch (propertyName) {
            case "node":
                return tRequirementDefinition.getNodeType().getName();
            case "capability":
                TCapabilityType capability = tRequirementDefinition.getCapability();
                return capability.getElement().getName();
            case "relationshipType":
                return tRequirementDefinition.getRelationshipType().getElement().getName();
            case "lowerBound":
                return tRequirementDefinition.getLowerBound();
            case "upperBound":
                return tRequirementDefinition.getUpperBound();
            default:
                return null;
        }
    }

    private static String resolveTRelationshipTemplateProperty(ModelElement context, String propertyName) {
        if ("type".equals(propertyName)) {
            fr.softeam.toscadesigner.api.tosca.standard.association.TRelationshipTemplate tRelationshipTemplate = 
                fr.softeam.toscadesigner.api.tosca.standard.association.TRelationshipTemplate
                    .safeInstantiate((Association) context);
            TRelationshipType relationshipType = tRelationshipTemplate.getRelationshipType();
            Class element = relationshipType.getElement();
            return element.getName();
        }
        return null;
    }

    private static String resolveTNodeTemplateProperty(ModelElement context, String propertyName) {
        if ("nodeType".equals(propertyName)) {
            TNodeTemplate tNodeTemplate = TNodeTemplate.safeInstantiate((Class) context);
            TNodeType nodeType = tNodeTemplate.getNodeType();
            return nodeType.getTargetNamespace() + "." + nodeType.getElement().getName();
        }
        return null;
    }

    private static String resolveTCapabilityDefinitionProperty(ModelElement context, String propertyName) {
        if ("capabilityType".equals(propertyName)) {
            TCapabilityDefinition tCapabilityDefinition = TCapabilityDefinition.safeInstantiate((Class) context);
            return tCapabilityDefinition.getCapabilityType().getElement().getName();
        }
        return null;
    }

    private static String resolveTPolicyProperty(ModelElement context, String propertyName) {
        TPolicy tPolicy = TPolicy.safeInstantiate((Class) context);
        switch (propertyName) {
            case "type":
                return tPolicy.getType().getElement().getName();
            case "policyTargets":
                java.util.List<TNodeTemplate> targets = tPolicy.getTargets();
                return targets.stream()
                        .map(t -> t.getElement().getName())
                        .collect(java.util.stream.Collectors.joining(", "));
            default:
                return null;
        }
    }
}
