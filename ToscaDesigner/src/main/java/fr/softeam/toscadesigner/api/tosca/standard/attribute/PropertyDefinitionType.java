/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ToscaDesigner v0.5.1

 * This file was generated on 29/07/2025 19:13 by Modelio Studio.
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
    @objid ("601c1248-d557-410b-9e7b-ab36aa4847e5")
    public static final String STEREOTYPE_NAME = "PropertyDefinitionType";

    @objid ("2f5551c4-5acf-43a3-a411-a8db0832b310")
    public static final String DESCRIPTION_PROPERTY = "description";

    @objid ("51d6e256-ea1a-4c9d-9164-8ec7fb994ea0")
    public static final String REQUIRED_PROPERTY = "required";

    /**
     * The underlying {@link Attribute} represented by this proxy, never null.
     */
    @objid ("b3202f5e-ea94-4e7f-bb66-21db2f34605c")
    protected final Attribute elt;

    /**
     * Tells whether a {@link PropertyDefinitionType proxy} can be instantiated from a {@link MObject} checking it is a {@link Attribute} stereotyped << PropertyDefinitionType >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("505143e4-067e-43f1-a5f5-d2bafae88236")
    public static boolean canInstantiate(final MObject elt) {
        return ((elt instanceof Attribute) && (PropertyDefinitionType.MdaTypes.STEREOTYPE_ELT!=null) && ((Attribute) elt).isStereotyped(PropertyDefinitionType.MdaTypes.STEREOTYPE_ELT));
    }

    /**
     * Create a new {@link Attribute} stereotyped << PropertyDefinitionType >> then instantiate a {@link PropertyDefinitionType} proxy.
     * @return a {@link PropertyDefinitionType} proxy on the created {@link Attribute}.
     */
    @objid ("f2b4dac8-c278-4501-9d76-221ac57999cf")
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
    @objid ("58f89ec6-935d-436f-9e37-6df028ce1525")
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
    @objid ("f6433f7c-97bc-4a68-ad27-4679c490e309")
    public static PropertyDefinitionType safeInstantiate(final Attribute obj) throws IllegalArgumentException {
        if (PropertyDefinitionType.canInstantiate(obj))
        	return new PropertyDefinitionType(obj);
        else
        	throw new IllegalArgumentException("PropertyDefinitionType: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("a6afc0d5-0e56-429a-b9b6-4d097a414601")
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
    @objid ("6938f9ad-df71-4bdd-9e25-bad8d9c4a423")
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
    @objid ("0d41afb4-7a0f-46c8-ab2d-94e68ab3a986")
    public Attribute getElement() {
        return this.elt;
    }

    /**
     * Getter for property 'PropertyDefinitionType.required'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("4628db87-b3a2-4ebe-9e63-23dd11ccac94")
    public Boolean getRequired() {
        String value = this.elt.getProperty(PropertyDefinitionType.MdaTypes.STEREOTYPE_ELT,
                                            PropertyDefinitionType.MdaTypes.REQUIRED_PROPERTY_ELT.getName());
        if (value == null) {
          value = PropertyDefinitionType.MdaTypes.REQUIRED_PROPERTY_ELT.getDefaultValue();
        }
        return (Boolean) PropertyConverter.convertToObject(PropertyDefinitionType.MdaTypes.REQUIRED_PROPERTY_ELT, value, this.elt);
        
    }

    @objid ("5b687246-5fae-44ab-b92b-c9f89fb8a295")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for property 'PropertyDefinitionType.description'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("268f37a9-b2b3-4798-8f19-cd7d66a1afad")
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
    @objid ("f3a68551-81a9-43bd-86a5-d5e757b5ac4b")
    public void setRequired(final Boolean value) {
        this.elt.setProperty(PropertyDefinitionType.MdaTypes.STEREOTYPE_ELT,
                             PropertyDefinitionType.MdaTypes.REQUIRED_PROPERTY_ELT.getName(),
                             PropertyConverter.convertToString(PropertyDefinitionType.MdaTypes.REQUIRED_PROPERTY_ELT, value));
    }

    @objid ("bec344b9-1370-48c0-8c34-9e5ca57ba1a0")
    protected  PropertyDefinitionType(final Attribute elt) {
        this.elt = elt;
    }

    /*
     * WARNING: GENERATED FILE - DO NOT EDIT
     * Module: ToscaDesigner v0.1.0

     * This file was generated on 28/07/2025 20:00 by Modelio Studio.
     */
    /*
     * WARNING: GENERATED FILE - DO NOT EDIT
     * Module: ToscaDesigner v0.5.1

     * This file was generated on 29/07/2025 18:14 by Modelio Studio.
     */
    /*
     * WARNING: GENERATED FILE - DO NOT EDIT
     * Module: ToscaDesigner v0.5.1

     * This file was generated on 29/07/2025 18:34 by Modelio Studio.
     */
    @objid ("491fe30b-6755-4b43-94bf-7f2f15b3ce59")
    public static final class MdaTypes {
        @objid ("e919d4d6-6889-485e-ad82-5d98db7fecaf")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("668c2c9c-07f8-4906-88dc-bf061ce74fad")
        public static PropertyDefinition DESCRIPTION_PROPERTY_ELT;

        @objid ("70fca96c-079c-4b5e-b0f1-a9dd45101bec")
        public static PropertyDefinition REQUIRED_PROPERTY_ELT;

        @objid ("1f804a23-9675-4b60-8802-49330bef5e5a")
        private static Stereotype MDAASSOCDEP;

        @objid ("3dd70cad-d97c-4950-a4f9-4a97c1b0755f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("04d2a1bf-4c3c-4a7f-bfc4-4c2e7daa7c45")
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
