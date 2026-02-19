package fr.softeam.toscadesigner.export.util;

import org.modelio.metamodel.uml.infrastructure.ModelElement;

import fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.TParameter;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeTemplate;

/**
 * Classifies templates and parameters into specific types (compute, pod, system parameters).
 */
public final class TemplateClassifier {

    /**
     * Check if a template is a compute template.
     */
    public boolean isComputeTemplate(TNodeTemplate template) {
        String typeName = templateTypeKey(template);
        String localName = templateLocalTypeName(template);
        return "myrtus.dpe.compute".equals(typeName) || "myrtus.dpe.compute".equals(localName);
    }

    /**
     * Check if a template is a pod template.
     */
    public boolean isPodTemplate(TNodeTemplate template) {
        String typeName = templateTypeKey(template);
        String localName = templateLocalTypeName(template);
        return "myrtus.dpe.nodes.lakesidepod".equals(typeName) || "myrtus.dpe.nodes.lakesidepod".equals(localName);
    }

    /**
     * Check if a template is a system parameters template.
     */
    public boolean isSystemParametersTemplate(TNodeTemplate template) {
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

    /**
     * Check if a parameter is a system parameters parameter.
     */
    public boolean isSystemParametersParameter(TParameter parameter) {
        if (parameter == null || parameter.getElement() == null) {
            return false;
        }
        String elementName = parameter.getElement().getName();
        if (elementName != null && elementName.equalsIgnoreCase("SystemParameters")) {
            return true;
        }
        String typeName = parameter.getType();
        return typeName != null && typeName.toLowerCase().contains("lakesidesystemparameters");
    }

    /**
     * Check if a candidate object represents system parameters.
     */
    public boolean isSystemParameters(Object candidate) {
        TNodeTemplate template = TemplateConverter.asNodeTemplate(candidate);
        if (template != null && isSystemParametersTemplate(template)) {
            return true;
        }
        TParameter parameter = TemplateConverter.asParameter(candidate);
        return parameter != null && isSystemParametersParameter(parameter);
    }

    /**
     * Get the full type key for a template (namespace.localname).
     */
    String templateTypeKey(TNodeTemplate template) {
        if (template == null || template.getNodeType() == null) {
            return null;
        }
        fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeType nodeType = template.getNodeType();
        String localName = templateLocalTypeName(template);
        String namespace = nodeType.getTargetNamespace();
        if (localName == null) {
            return null;
        }
        if (namespace != null && !namespace.trim().isEmpty()) {
            return (namespace.trim() + "." + localName).toLowerCase();
        }
        return localName;
    }

    /**
     * Get the local type name for a template.
     */
    String templateLocalTypeName(TNodeTemplate template) {
        if (template == null || template.getNodeType() == null || template.getNodeType().getElement() == null) {
            return null;
        }
        String localName = template.getNodeType().getElement().getName();
        return localName != null ? localName.trim().toLowerCase() : null;
    }

    /**
     * Get the display name for a template (removes "node" suffix if present).
     */
    public String templateDisplayName(Object candidate) {
        String name = null;
        TNodeTemplate template = TemplateConverter.asNodeTemplate(candidate);
        if (template != null && template.getElement() != null) {
            name = template.getElement().getName();
        } else if (candidate instanceof ModelElement) {
            name = ((ModelElement) candidate).getName();
        }
        if (name == null) {
            return "";
        }
        String trimmed = name.trim();
        if (trimmed.length() > 4 && trimmed.toLowerCase().endsWith("node")) {
            return trimmed.substring(0, trimmed.length() - 4);
        }
        return trimmed;
    }

    /**
     * Get the node type local name for a candidate object.
     */
    public String nodeTypeLocalName(Object candidate) {
        TNodeTemplate template = TemplateConverter.asNodeTemplate(candidate);
        if (template != null && template.getNodeType() != null && template.getNodeType().getElement() != null) {
            return template.getNodeType().getElement().getName();
        }
        return "";
    }

    /**
     * Check if a property is allowed for a specific template type.
     */
    public boolean lakesidePropertyAllowed(Object templateCandidate, Object propertyCandidate) {
        if (propertyCandidate == null) {
            return true;
        }
        TNodeTemplate template = TemplateConverter.asNodeTemplate(templateCandidate);
        if (template == null) {
            return true;
        }
        String propertyName = propertyCandidate.toString();
        if (propertyName == null) {
            return true;
        }
        if (isComputeTemplate(template)) {
            String normalized = propertyName.trim().toLowerCase();
            return "cpu_cores".equals(normalized) || "memory_gb".equals(normalized);
        }
        return true;
    }
}
