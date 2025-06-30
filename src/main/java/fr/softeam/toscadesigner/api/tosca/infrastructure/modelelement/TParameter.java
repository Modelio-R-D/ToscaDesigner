/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ToscaDesigner v0.1.0

 * This file was generated on 22/05/2025 15:07 by Modelio Studio.
 */
package fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement;

import java.util.ArrayList;
import java.util.List;

import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.impl.IMdaProxy;
import fr.softeam.toscadesigner.impl.MdaProxyException;

/**
 * Proxy class to handle a {@link ModelElement} with << TParameter >> stereotype.
 * <p>Stereotype description:
 * <br/><i>null</i></p>
 */
@objid ("788fffe4-0ee3-414e-8a2e-87343a731006")
public class TParameter implements IMdaProxy {
    @objid ("bcd7967b-87c4-453c-b3e1-1a2a8f22b158")
    public static final String STEREOTYPE_NAME = "TParameter";

    @objid ("f96b1c70-b32f-4a7c-adc5-5ca4391b6f05")
    public static final String CONSTRAINTS_PROPERTY = "constraints";

    @objid ("91b92cbb-ba8c-4e95-9940-d6b009167e07")
    public static final String DEFAULT_PROPERTY = "default";

    @objid ("5fbad70d-2131-4bd2-914a-b128b6e9107f")
    public static final String DESCRIPTION_PROPERTY = "description";

    @objid ("6c439f53-fa81-4622-af2c-795ee0815a81")
    public static final String NAME_PROPERTY = "name";

    @objid ("9b58b6da-f150-4def-a293-2c43867fecef")
    public static final String REQUIRED_PROPERTY = "required";

    @objid ("8b0f3840-6e24-40a7-8704-c274ee688c00")
    public static final String STATUS_PROPERTY = "status";

    @objid ("ce3b27b9-9402-4863-bb90-babacba76039")
    public static final String TYPE_PROPERTY = "type";

    @objid ("584c8e15-1658-4801-a22a-ef74a685a1e1")
    public static final String VALUE_PROPERTY = "value";

    /**
     * The underlying {@link ModelElement} represented by this proxy, never null.
     */
    @objid ("000bfe36-df07-4361-a7c8-4e6ac31ac1f8")
    protected final ModelElement elt;

    /**
     * Tells whether a {@link TParameter proxy} can be instantiated from a {@link MObject} checking it is a {@link ModelElement} stereotyped << TParameter >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("cff17c20-1eec-4423-a518-ca4cfb086e69")
    public static boolean canInstantiate(final MObject elt) {
        return ((elt instanceof ModelElement) && (TParameter.MdaTypes.STEREOTYPE_ELT!=null) && ((ModelElement) elt).isStereotyped(TParameter.MdaTypes.STEREOTYPE_ELT));
    }

    /**
     * Create a new {@link ModelElement} stereotyped << TParameter >> then instantiate a {@link TParameter} proxy.
     * 
     * @return a {@link TParameter} proxy on the created {@link ModelElement}.
     */
    @objid ("48a1ce31-58f0-4efe-ac5d-2df9016a806d")
    public static TParameter create(final IModelingSession session) {
        ModelElement e = (ModelElement)session.getModel().createElement("Infrastructure.ModelElement");
        e.getExtension().add(TParameter.MdaTypes.STEREOTYPE_ELT);
        return TParameter.instantiate((ModelElement)e);
    }

    /**
     * Tries to instantiate a {@link TParameter} proxy from a {@link ModelElement} stereotyped << TParameter >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ModelElement
     * @return a {@link TParameter} proxy or <i>null</i>.
     */
    @objid ("62d1f3fe-eae4-4aa8-8337-6c5f8859fd9e")
    public static TParameter instantiate(final ModelElement obj) {
        return TParameter.canInstantiate(obj) ? new TParameter(obj) : null;
    }

    /**
     * Tries to instantiate a {@link TParameter} proxy from a {@link ModelElement} stereotyped << TParameter >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ModelElement}
     * @return a {@link TParameter} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("ebd471fa-dd7f-4944-8798-7963b9741448")
    public static TParameter safeInstantiate(final ModelElement obj) throws IllegalArgumentException {
        if (TParameter.canInstantiate(obj))
        	return new TParameter(obj);
        else
        	throw new IllegalArgumentException("TParameter: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("9cbe0c15-ee8b-4a25-a2a5-4f1b307a7577")
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
        TParameter other = (TParameter) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Getter for property 'TParameter.constraints'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("cebbbc31-e3cd-475d-94e8-8798c8115c91")
    public String getConstraints() {
        String value = this.elt.getProperty(TParameter.MdaTypes.STEREOTYPE_ELT,
                                            TParameter.MdaTypes.CONSTRAINTS_PROPERTY_ELT.getName());
        if (value == null) {
          value = TParameter.MdaTypes.CONSTRAINTS_PROPERTY_ELT.getDefaultValue();
        }
        return (String) PropertyConverter.convertToObject(TParameter.MdaTypes.CONSTRAINTS_PROPERTY_ELT, value, this.elt);
        
    }

    /**
     * Getter for property 'TParameter.default'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("833d59f2-aba4-4121-bda5-a9421f5c5baa")
    public String getDefault() {
        String value = this.elt.getProperty(TParameter.MdaTypes.STEREOTYPE_ELT,
                                            TParameter.MdaTypes.DEFAULT_PROPERTY_ELT.getName());
        if (value == null) {
          value = TParameter.MdaTypes.DEFAULT_PROPERTY_ELT.getDefaultValue();
        }
        return (String) PropertyConverter.convertToObject(TParameter.MdaTypes.DEFAULT_PROPERTY_ELT, value, this.elt);
        
    }

    /**
     * Getter for property 'TParameter.description'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("2474b832-7315-4d4d-a006-9693f3a0527b")
    public String getDescription() {
        String value = this.elt.getProperty(TParameter.MdaTypes.STEREOTYPE_ELT,
                                            TParameter.MdaTypes.DESCRIPTION_PROPERTY_ELT.getName());
        if (value == null) {
          value = TParameter.MdaTypes.DESCRIPTION_PROPERTY_ELT.getDefaultValue();
        }
        return (String) PropertyConverter.convertToObject(TParameter.MdaTypes.DESCRIPTION_PROPERTY_ELT, value, this.elt);
        
    }

    /**
     * Get the underlying {@link ModelElement}. 
     * @return the ModelElement represented by this proxy, never null.
     */
    @objid ("876b0ad4-49c8-4173-bdaf-3f04a861a892")
    public ModelElement getElement() {
        return this.elt;
    }

    /**
     * Getter for property 'TParameter.name'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("b04a84e4-f9de-43c7-b86e-80003c030142")
    public String getName() {
        String value = this.elt.getProperty(TParameter.MdaTypes.STEREOTYPE_ELT,
                                            TParameter.MdaTypes.NAME_PROPERTY_ELT.getName());
        if (value == null) {
          value = TParameter.MdaTypes.NAME_PROPERTY_ELT.getDefaultValue();
        }
        return (String) PropertyConverter.convertToObject(TParameter.MdaTypes.NAME_PROPERTY_ELT, value, this.elt);
        
    }

    /**
     * Getter for property 'TParameter.required'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("cde110f4-ed43-4064-aa76-83e43ff73064")
    public Boolean getRequired() {
        String value = this.elt.getProperty(TParameter.MdaTypes.STEREOTYPE_ELT,
                                            TParameter.MdaTypes.REQUIRED_PROPERTY_ELT.getName());
        if (value == null) {
          value = TParameter.MdaTypes.REQUIRED_PROPERTY_ELT.getDefaultValue();
        }
        return (Boolean) PropertyConverter.convertToObject(TParameter.MdaTypes.REQUIRED_PROPERTY_ELT, value, this.elt);
        
    }

    /**
     * Getter for property 'TParameter.status'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("a0b1dd48-ba3a-4b15-bae9-2c0a40c0d8a3")
    public String getStatus() {
        String value = this.elt.getProperty(TParameter.MdaTypes.STEREOTYPE_ELT,
                                            TParameter.MdaTypes.STATUS_PROPERTY_ELT.getName());
        if (value == null) {
          value = TParameter.MdaTypes.STATUS_PROPERTY_ELT.getDefaultValue();
        }
        return (String) PropertyConverter.convertToObject(TParameter.MdaTypes.STATUS_PROPERTY_ELT, value, this.elt);
        
    }

    /**
     * Getter for property 'TParameter.type'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("ec14e0b1-449a-4a49-af07-78294bf3c99e")
    public String getType() {
        String value = this.elt.getProperty(TParameter.MdaTypes.STEREOTYPE_ELT,
                                            TParameter.MdaTypes.TYPE_PROPERTY_ELT.getName());
        if (value == null) {
          value = TParameter.MdaTypes.TYPE_PROPERTY_ELT.getDefaultValue();
        }
        return (String) PropertyConverter.convertToObject(TParameter.MdaTypes.TYPE_PROPERTY_ELT, value, this.elt);
        
    }

    /**
     * Getter for property 'TParameter.value'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("9a65c2ed-fba6-4dcd-bf1e-256d2086bdb5")
    public String getValue() {
        String value = this.elt.getProperty(TParameter.MdaTypes.STEREOTYPE_ELT,
                                            TParameter.MdaTypes.VALUE_PROPERTY_ELT.getName());
        if (value == null) {
          value = TParameter.MdaTypes.VALUE_PROPERTY_ELT.getDefaultValue();
        }
        return (String) PropertyConverter.convertToObject(TParameter.MdaTypes.VALUE_PROPERTY_ELT, value, this.elt);
        
    }

    @objid ("d7f7a8e7-0240-4606-9581-a850a1b8ede9")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for property 'TParameter.constraints'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("826e5695-64e4-422c-8de0-3eeca4dbf0e2")
    public void setConstraints(final String value) {
        this.elt.setProperty(TParameter.MdaTypes.STEREOTYPE_ELT,
                             TParameter.MdaTypes.CONSTRAINTS_PROPERTY_ELT.getName(),
                             PropertyConverter.convertToString(TParameter.MdaTypes.CONSTRAINTS_PROPERTY_ELT, value));
    }

    /**
     * Setter for property 'TParameter.default'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("d9d4ecb9-a79c-4ec3-93f8-3efead617701")
    public void setDefault(final String value) {
        this.elt.setProperty(TParameter.MdaTypes.STEREOTYPE_ELT,
                             TParameter.MdaTypes.DEFAULT_PROPERTY_ELT.getName(),
                             PropertyConverter.convertToString(TParameter.MdaTypes.DEFAULT_PROPERTY_ELT, value));
    }

    /**
     * Setter for property 'TParameter.description'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("f360dd59-901c-44c7-bb25-a061b8a6663c")
    public void setDescription(final String value) {
        this.elt.setProperty(TParameter.MdaTypes.STEREOTYPE_ELT,
                             TParameter.MdaTypes.DESCRIPTION_PROPERTY_ELT.getName(),
                             PropertyConverter.convertToString(TParameter.MdaTypes.DESCRIPTION_PROPERTY_ELT, value));
    }

    /**
     * Setter for property 'TParameter.name'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("4cc21502-57c5-43ad-ad09-7ae70a3556b0")
    public void setName(final String value) {
        this.elt.setProperty(TParameter.MdaTypes.STEREOTYPE_ELT,
                             TParameter.MdaTypes.NAME_PROPERTY_ELT.getName(),
                             PropertyConverter.convertToString(TParameter.MdaTypes.NAME_PROPERTY_ELT, value));
    }

    /**
     * Setter for property 'TParameter.required'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("158f6df7-2f39-484c-813c-5c808471b976")
    public void setRequired(final Boolean value) {
        this.elt.setProperty(TParameter.MdaTypes.STEREOTYPE_ELT,
                             TParameter.MdaTypes.REQUIRED_PROPERTY_ELT.getName(),
                             PropertyConverter.convertToString(TParameter.MdaTypes.REQUIRED_PROPERTY_ELT, value));
    }

    /**
     * Setter for property 'TParameter.status'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("d9ae2d09-9aee-4c31-a2b1-d107f7146ae6")
    public void setStatus(final String value) {
        this.elt.setProperty(TParameter.MdaTypes.STEREOTYPE_ELT,
                             TParameter.MdaTypes.STATUS_PROPERTY_ELT.getName(),
                             PropertyConverter.convertToString(TParameter.MdaTypes.STATUS_PROPERTY_ELT, value));
    }

    /**
     * Setter for property 'TParameter.type'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("cacb4253-3dc6-4e44-a43a-43f929fee324")
    public void setType(final String value) {
        this.elt.setProperty(TParameter.MdaTypes.STEREOTYPE_ELT,
                             TParameter.MdaTypes.TYPE_PROPERTY_ELT.getName(),
                             PropertyConverter.convertToString(TParameter.MdaTypes.TYPE_PROPERTY_ELT, value));
    }

    /**
     * Setter for property 'TParameter.value'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("07267fe7-d9bf-443d-9a63-b8d35da077a6")
    public void setValue(final String value) {
        this.elt.setProperty(TParameter.MdaTypes.STEREOTYPE_ELT,
                             TParameter.MdaTypes.VALUE_PROPERTY_ELT.getName(),
                             PropertyConverter.convertToString(TParameter.MdaTypes.VALUE_PROPERTY_ELT, value));
    }

    @objid ("1f9fff47-4467-41a6-af5a-0793e4581531")
    protected  TParameter(final ModelElement elt) {
        this.elt = elt;
    }

    @objid ("7cb430c3-c37e-4c75-84df-48bf8f850b03")
    public static final class MdaTypes {
        @objid ("e7815900-1c53-4e57-9a3b-6a3f577d7b90")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e0540675-6388-4632-803e-eb0e44782455")
        public static PropertyDefinition NAME_PROPERTY_ELT;

        @objid ("3d1894d1-4695-41bb-865a-fb37e3ea509c")
        public static PropertyDefinition REQUIRED_PROPERTY_ELT;

        @objid ("160e6d41-e623-4b75-b8fb-c26dabb4e38c")
        public static PropertyDefinition TYPE_PROPERTY_ELT;

        @objid ("64632aa8-2fe1-4604-874f-bd138e85d112")
        public static PropertyDefinition DESCRIPTION_PROPERTY_ELT;

        @objid ("a74cfe63-c0e9-42c0-afe9-0181a52e1589")
        public static PropertyDefinition VALUE_PROPERTY_ELT;

        @objid ("06569dce-946e-469a-ad12-af4d76e2d223")
        public static PropertyDefinition DEFAULT_PROPERTY_ELT;

        @objid ("bb30c595-e748-498d-ac33-52de3d8eeab6")
        public static PropertyDefinition STATUS_PROPERTY_ELT;

        @objid ("733ae83a-b290-4728-8285-14fe70740858")
        public static PropertyDefinition CONSTRAINTS_PROPERTY_ELT;

        @objid ("cf0d3772-0e3b-4aea-bdbd-4ae34b054be0")
        private static Stereotype MDAASSOCDEP;

        @objid ("176a8877-317b-4d6a-8d8d-f29804f41336")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("11eb278c-8da9-4daa-a7a3-bd98fcaca8a3")
        public static void init(final IModelingSession session) throws MdaProxyException {
            List <MRef> missingRefs = new ArrayList<>();
            MRef mRef;
            mRef = new MRef(Stereotype.MQNAME, "88f765a0-a1fe-42d8-9b52-f45ff541469f", "TParameter");
            STEREOTYPE_ELT = (Stereotype) session.findByRef(mRef);
            if (STEREOTYPE_ELT==null) missingRefs.add(mRef);
            
            mRef = new MRef(PropertyDefinition.MQNAME, "ae68e1f0-3464-42e1-a2d5-d8d14a7aa4fe", "name");
            NAME_PROPERTY_ELT = (PropertyDefinition) session.findByRef(mRef);
            if (NAME_PROPERTY_ELT==null) missingRefs.add(mRef);
            
            mRef = new MRef(PropertyDefinition.MQNAME, "3091bbf3-2697-463a-92be-b410cc5a1f82", "required");
            REQUIRED_PROPERTY_ELT = (PropertyDefinition) session.findByRef(mRef);
            if (REQUIRED_PROPERTY_ELT==null) missingRefs.add(mRef);
            
            mRef = new MRef(PropertyDefinition.MQNAME, "a6d4ef82-d89c-4b98-ad06-c9abb7992402", "type");
            TYPE_PROPERTY_ELT = (PropertyDefinition) session.findByRef(mRef);
            if (TYPE_PROPERTY_ELT==null) missingRefs.add(mRef);
            
            mRef = new MRef(PropertyDefinition.MQNAME, "73c42be6-f152-4cd9-96e7-811cbdc19855", "description");
            DESCRIPTION_PROPERTY_ELT = (PropertyDefinition) session.findByRef(mRef);
            if (DESCRIPTION_PROPERTY_ELT==null) missingRefs.add(mRef);
            
            mRef = new MRef(PropertyDefinition.MQNAME, "b90789da-89e2-4e67-95f5-afaeae60527f", "value");
            VALUE_PROPERTY_ELT = (PropertyDefinition) session.findByRef(mRef);
            if (VALUE_PROPERTY_ELT==null) missingRefs.add(mRef);
            
            mRef = new MRef(PropertyDefinition.MQNAME, "77d5b3fc-e752-44d0-a52c-3d3b6fa2755a", "default");
            DEFAULT_PROPERTY_ELT = (PropertyDefinition) session.findByRef(mRef);
            if (DEFAULT_PROPERTY_ELT==null) missingRefs.add(mRef);
            
            mRef = new MRef(PropertyDefinition.MQNAME, "af62ecb0-8deb-4e68-bb5f-c38a90d1ddc6", "status");
            STATUS_PROPERTY_ELT = (PropertyDefinition) session.findByRef(mRef);
            if (STATUS_PROPERTY_ELT==null) missingRefs.add(mRef);
            
            mRef = new MRef(PropertyDefinition.MQNAME, "5e5812ea-e124-41ef-8d85-dc46d49636a9", "constraints");
            CONSTRAINTS_PROPERTY_ELT = (PropertyDefinition) session.findByRef(mRef);
            if (CONSTRAINTS_PROPERTY_ELT==null) missingRefs.add(mRef);
            
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
