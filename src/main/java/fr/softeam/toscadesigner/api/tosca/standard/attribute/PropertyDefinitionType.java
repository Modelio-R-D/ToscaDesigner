/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ToscaDesigner v0.1.0

 * This file was generated on 22/05/2025 15:07 by Modelio Studio.
 */
package fr.softeam.toscadesigner.api.tosca.standard.attribute;

import java.util.ArrayList;
import java.util.List;

import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.impl.IMdaProxy;
import fr.softeam.toscadesigner.impl.MdaProxyException;

/**
 * Proxy class to handle a {@link Attribute} with << PropertyDefinitionType >> stereotype.
 * <p>Stereotype description:
 * <br/><i>null</i></p>
 */
@objid ("877c3ec4-fea5-418d-824a-17072f906ba2")
public class PropertyDefinitionType implements IMdaProxy {
    @objid ("466bc712-2a25-4c31-aab9-974aaafcd135")
    public static final String STEREOTYPE_NAME = "PropertyDefinitionType";

    @objid ("a989bb7d-92df-4cc6-92e6-583c53d425a2")
    public static final String DESCRIPTION_PROPERTY = "description";

    @objid ("b1a64687-471c-4467-a1ed-dc2450e4f3ce")
    public static final String REQUIRED_PROPERTY = "required";

    /**
     * The underlying {@link Attribute} represented by this proxy, never null.
     */
    @objid ("1fecee0e-a3e2-4a94-8c02-32d72cc07a89")
    protected final Attribute elt;

    /**
     * Tells whether a {@link PropertyDefinitionType proxy} can be instantiated from a {@link MObject} checking it is a {@link Attribute} stereotyped << PropertyDefinitionType >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("4cdf9ea6-3414-4124-b353-10f9f8be529d")
    public static boolean canInstantiate(final MObject elt) {
        return ((elt instanceof Attribute) && (PropertyDefinitionType.MdaTypes.STEREOTYPE_ELT!=null) && ((Attribute) elt).isStereotyped(PropertyDefinitionType.MdaTypes.STEREOTYPE_ELT));
    }

    /**
     * Create a new {@link Attribute} stereotyped << PropertyDefinitionType >> then instantiate a {@link PropertyDefinitionType} proxy.
     * 
     * @return a {@link PropertyDefinitionType} proxy on the created {@link Attribute}.
     */
    @objid ("d55ed3c2-b901-4ed6-b70f-24affafc828e")
    public static PropertyDefinitionType create(final IModelingSession session) {
        ModelElement e = (ModelElement)session.getModel().createElement("Standard.Attribute");
        e.getExtension().add(PropertyDefinitionType.MdaTypes.STEREOTYPE_ELT);
        return PropertyDefinitionType.instantiate((Attribute)e);
    }

    /**
     * Tries to instantiate a {@link PropertyDefinitionType} proxy from a {@link Attribute} stereotyped << PropertyDefinitionType >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Attribute
     * @return a {@link PropertyDefinitionType} proxy or <i>null</i>.
     */
    @objid ("1d0212c1-21fe-47e7-ad25-12bb3216979d")
    public static PropertyDefinitionType instantiate(final Attribute obj) {
        return PropertyDefinitionType.canInstantiate(obj) ? new PropertyDefinitionType(obj) : null;
    }

    /**
     * Tries to instantiate a {@link PropertyDefinitionType} proxy from a {@link Attribute} stereotyped << PropertyDefinitionType >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Attribute}
     * @return a {@link PropertyDefinitionType} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("1475d74f-1144-4300-8b8a-29101afdae69")
    public static PropertyDefinitionType safeInstantiate(final Attribute obj) throws IllegalArgumentException {
        if (PropertyDefinitionType.canInstantiate(obj))
        	return new PropertyDefinitionType(obj);
        else
        	throw new IllegalArgumentException("PropertyDefinitionType: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("2dddc9d3-2c0e-41c4-b1a8-a9d54b6bc1ec")
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PropertyDefinitionType other = (PropertyDefinitionType) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Getter for property 'PropertyDefinitionType.description'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("b211be70-7092-42be-add6-9c54bee1e2a8")
    public String getDescription() {
        String value = this.elt.getProperty(PropertyDefinitionType.MdaTypes.STEREOTYPE_ELT,
                                            PropertyDefinitionType.MdaTypes.DESCRIPTION_PROPERTY_ELT.getName());
        if (value == null) {
          value = PropertyDefinitionType.MdaTypes.DESCRIPTION_PROPERTY_ELT.getDefaultValue();
        }
        return (String) PropertyConverter.convertToObject(PropertyDefinitionType.MdaTypes.DESCRIPTION_PROPERTY_ELT, value, this.elt);
        
    }

    /**
     * Get the underlying {@link Attribute}. 
     * @return the Attribute represented by this proxy, never null.
     */
    @objid ("044e1734-221b-49a4-b07b-7674d5e1b871")
    public Attribute getElement() {
        return this.elt;
    }

    /**
     * Getter for property 'PropertyDefinitionType.required'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("e313ff57-a0b3-44d4-8e5d-b736193a18f2")
    public Boolean getRequired() {
        String value = this.elt.getProperty(PropertyDefinitionType.MdaTypes.STEREOTYPE_ELT,
                                            PropertyDefinitionType.MdaTypes.REQUIRED_PROPERTY_ELT.getName());
        if (value == null) {
          value = PropertyDefinitionType.MdaTypes.REQUIRED_PROPERTY_ELT.getDefaultValue();
        }
        return (Boolean) PropertyConverter.convertToObject(PropertyDefinitionType.MdaTypes.REQUIRED_PROPERTY_ELT, value, this.elt);
        
    }

    @objid ("04ea7e4b-6c68-44af-b134-db89f2b8a1be")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for property 'PropertyDefinitionType.description'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("63cd844a-159b-4f0d-8bca-d7f55f5a703c")
    public void setDescription(final String value) {
        this.elt.setProperty(PropertyDefinitionType.MdaTypes.STEREOTYPE_ELT,
                             PropertyDefinitionType.MdaTypes.DESCRIPTION_PROPERTY_ELT.getName(),
                             PropertyConverter.convertToString(PropertyDefinitionType.MdaTypes.DESCRIPTION_PROPERTY_ELT, value));
    }

    /**
     * Setter for property 'PropertyDefinitionType.required'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("d2dc25a9-a326-4ce4-a420-5c4c4a4b6d8a")
    public void setRequired(final Boolean value) {
        this.elt.setProperty(PropertyDefinitionType.MdaTypes.STEREOTYPE_ELT,
                             PropertyDefinitionType.MdaTypes.REQUIRED_PROPERTY_ELT.getName(),
                             PropertyConverter.convertToString(PropertyDefinitionType.MdaTypes.REQUIRED_PROPERTY_ELT, value));
    }

    @objid ("e4e5c918-3759-4ef7-95db-609e73383962")
    protected  PropertyDefinitionType(final Attribute elt) {
        this.elt = elt;
    }

    @objid ("491fe30b-6755-4b43-94bf-7f2f15b3ce59")
    public static final class MdaTypes {
        @objid ("9c83e4d7-b5a4-44b2-9ef1-27c968ff841c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("8fbcca99-4555-4f6b-aa4d-30649eafaa78")
        public static PropertyDefinition DESCRIPTION_PROPERTY_ELT;

        @objid ("cdcebeda-cbf7-45e6-8075-1a22860e05fc")
        public static PropertyDefinition REQUIRED_PROPERTY_ELT;

        @objid ("434e2662-e1bd-40f4-b0b9-2f9985e677c8")
        private static Stereotype MDAASSOCDEP;

        @objid ("e3a8bb71-9381-49d7-ad93-fb066b9792d1")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e255ab51-b8a1-4161-869d-ba44c12c55a4")
        public static void init(final IModelingSession session) throws MdaProxyException {
            List <MRef> missingRefs = new ArrayList<>();
            MRef mRef;
            mRef = new MRef(Stereotype.MQNAME, "34943474-c962-4064-b5f1-c6008ebb3c9d", "PropertyDefinitionType");
            STEREOTYPE_ELT = (Stereotype) session.findByRef(mRef);
            if (STEREOTYPE_ELT==null) missingRefs.add(mRef);
            
            mRef = new MRef(PropertyDefinition.MQNAME, "d7bd8dcf-45a6-4906-95f9-a874b3a36709", "description");
            DESCRIPTION_PROPERTY_ELT = (PropertyDefinition) session.findByRef(mRef);
            if (DESCRIPTION_PROPERTY_ELT==null) missingRefs.add(mRef);
            
            mRef = new MRef(PropertyDefinition.MQNAME, "f35e9a83-e034-47a1-95bf-a2e1a1203952", "required");
            REQUIRED_PROPERTY_ELT = (PropertyDefinition) session.findByRef(mRef);
            if (REQUIRED_PROPERTY_ELT==null) missingRefs.add(mRef);
            
            mRef = new MRef(Stereotype.MQNAME, "94b7efa5-f94c-4d1d-896f-f103e56a8e2e", "MDAAssocDep");
            MDAASSOCDEP = (Stereotype) session.findByRef(mRef);
            if (MDAASSOCDEP==null) missingRefs.add(mRef);
            
            mRef = new MRef(TagType.MQNAME, "7637f2fd-b750-43c1-a15c-5d0b084ca1cd", "MDAAssocDepRole");
            MDAASSOCDEP_ROLE = (TagType) session.findByRef(mRef);
            if (MDAASSOCDEP_ROLE==null) missingRefs.add(mRef);
            
            if (! missingRefs.isEmpty()) throw new MdaProxyException(MdaProxyException.MdaProxyExceptionReason.MISSING_MDA_ELEMENT, missingRefs);
            
        }

    }

}
