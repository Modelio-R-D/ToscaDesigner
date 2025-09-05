/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ToscaDesigner v0.5.1

 * This file was generated on 29/07/2025 19:13 by Modelio Studio.
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
    @objid ("7897cd89-7ad2-4f45-8ea3-db100bbea39b")
    public static final String STEREOTYPE_NAME = "TParameter";

    @objid ("7f9471d3-10fb-4b5d-9ad5-60399af83647")
    public static final String CONSTRAINTS_PROPERTY = "constraints";

    @objid ("5e4fbb93-b65d-4a04-81a5-0a590a03c1dd")
    public static final String DEFAULT_PROPERTY = "default";

    @objid ("02fa54b5-e990-49b0-ab54-8591122d1f40")
    public static final String DESCRIPTION_PROPERTY = "description";

    @objid ("1fc9e9ae-ba0d-4e3f-a6bc-839181f1c83c")
    public static final String NAME_PROPERTY = "name";

    @objid ("60f6c21d-1aaf-4d53-901a-db63f869f678")
    public static final String REQUIRED_PROPERTY = "required";

    @objid ("c701c7de-0619-4edc-a9e8-285147a2591e")
    public static final String STATUS_PROPERTY = "status";

    @objid ("aecc6cb4-e5b3-4279-982f-e01ff3952184")
    public static final String TYPE_PROPERTY = "type";

    @objid ("17bedfc7-2703-408d-a582-f4f3a36ce529")
    public static final String VALUE_PROPERTY = "value";

    /**
     * The underlying {@link ModelElement} represented by this proxy, never null.
     */
    @objid ("12f36b05-3624-466b-a879-aec572209773")
    protected final ModelElement elt;

    /**
     * Tells whether a {@link TParameter proxy} can be instantiated from a {@link MObject} checking it is a {@link ModelElement} stereotyped << TParameter >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("b3c9df92-c695-4444-a159-0d4b82ed7b01")
    public static boolean canInstantiate(final MObject elt) {
        return ((elt instanceof ModelElement) && (TParameter.MdaTypes.STEREOTYPE_ELT!=null) && ((ModelElement) elt).isStereotyped(TParameter.MdaTypes.STEREOTYPE_ELT));
    }

    /**
     * Create a new {@link ModelElement} stereotyped << TParameter >> then instantiate a {@link TParameter} proxy.
     * @return a {@link TParameter} proxy on the created {@link ModelElement}.
     */
    @objid ("6d83d94f-223a-436c-b1a7-87f39df07339")
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
    @objid ("9fc2b54a-8ffc-48d9-aaef-c49802156c02")
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
    @objid ("3641d4e6-8112-4c06-8344-924b2d5fd40f")
    public static TParameter safeInstantiate(final ModelElement obj) throws IllegalArgumentException {
        if (TParameter.canInstantiate(obj))
        	return new TParameter(obj);
        else
        	throw new IllegalArgumentException("TParameter: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c4c5f723-a8ec-4af6-86fd-4a93cb41c861")
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
    @objid ("a37c2fb3-ea8a-46eb-994f-1bef0c4daf4b")
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
    @objid ("ff8e5430-dfa5-433a-b54d-8b636e6e4dd6")
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
    @objid ("c4aed424-0524-4363-b05c-b2046010c9cf")
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
    @objid ("9f13a3d1-0871-4e8f-b060-d025ee0e5b6b")
    public ModelElement getElement() {
        return this.elt;
    }

    /**
     * Getter for property 'TParameter.name'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("8b77c4c3-da5a-4e34-bc3a-557c5f1f6ca3")
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
    @objid ("b0c64ae5-85a9-4e44-aa23-8a21576279c2")
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
    @objid ("9c8f94db-2209-41ba-bd98-c5bf7913ce86")
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
    @objid ("a6b716e0-dcf1-4907-be19-9441b32dd43b")
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
    @objid ("851a5ea4-7a15-4b92-aebb-1bcd313484ca")
    public String getValue() {
        String value = this.elt.getProperty(TParameter.MdaTypes.STEREOTYPE_ELT,
                                            TParameter.MdaTypes.VALUE_PROPERTY_ELT.getName());
        if (value == null) {
          value = TParameter.MdaTypes.VALUE_PROPERTY_ELT.getDefaultValue();
        }
        return (String) PropertyConverter.convertToObject(TParameter.MdaTypes.VALUE_PROPERTY_ELT, value, this.elt);
        
    }

    @objid ("af37dbfe-bd8f-4f9b-82f9-e7232735cf5e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for property 'TParameter.constraints'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("1c4e6fb6-d1c4-4827-ad22-e31e898e9ad6")
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
    @objid ("63bcba74-304c-474f-9ca6-116683597ccb")
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
    @objid ("d26730f0-9f62-4d29-bcfd-533f2d81bc3d")
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
    @objid ("e7848940-bc85-4ae9-978b-c2dec8d22fe9")
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
    @objid ("1d2db0fc-e733-47ce-8870-0a2c770eb5a6")
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
    @objid ("fc4bd0f9-44b7-4f00-9b0e-1608f63a34f1")
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
    @objid ("f4186ac7-1d64-41d8-b7cb-809a17dce5f1")
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
    @objid ("94a7c1b1-1244-43f7-aba1-04df281618a7")
    public void setValue(final String value) {
        this.elt.setProperty(TParameter.MdaTypes.STEREOTYPE_ELT,
                             TParameter.MdaTypes.VALUE_PROPERTY_ELT.getName(),
                             PropertyConverter.convertToString(TParameter.MdaTypes.VALUE_PROPERTY_ELT, value));
    }

    @objid ("891b1c93-168f-401e-a0e4-1bf29db316ac")
    protected  TParameter(final ModelElement elt) {
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
    @objid ("7cb430c3-c37e-4c75-84df-48bf8f850b03")
    public static final class MdaTypes {
        @objid ("583785df-1a1a-4c5c-acf9-439b0ec5e1c7")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("0d16dfb7-8bd0-444f-afad-46f481793a26")
        public static PropertyDefinition NAME_PROPERTY_ELT;

        @objid ("4ed381f9-9f43-4d59-9aff-a3f8aa009108")
        public static PropertyDefinition REQUIRED_PROPERTY_ELT;

        @objid ("9a3adace-7281-4229-ae1f-d5798a96c8fb")
        public static PropertyDefinition TYPE_PROPERTY_ELT;

        @objid ("fea6213d-dd03-4f21-a95c-c7939cea26c2")
        public static PropertyDefinition DESCRIPTION_PROPERTY_ELT;

        @objid ("5f23d0a4-dfa7-4d58-909d-81ee05ce444d")
        public static PropertyDefinition VALUE_PROPERTY_ELT;

        @objid ("d1b434b6-dce9-44ca-9c24-37e48533bf5d")
        public static PropertyDefinition DEFAULT_PROPERTY_ELT;

        @objid ("3588d05f-1c17-480e-81e3-157ffb2100c7")
        public static PropertyDefinition STATUS_PROPERTY_ELT;

        @objid ("ea27c2ff-a2d1-4742-9a56-0146ce9bd2fb")
        public static PropertyDefinition CONSTRAINTS_PROPERTY_ELT;

        @objid ("2849a1f5-5329-426c-a5c3-ac44e2d869da")
        private static Stereotype MDAASSOCDEP;

        @objid ("f07ba536-c26c-4bc9-9250-75e4d990a575")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("38aff69d-57c5-434a-8da1-69909bcb696b")
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
