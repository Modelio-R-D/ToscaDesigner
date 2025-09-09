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
 * Proxy class to handle a {@link ModelElement} with << TOperation >> stereotype.
 * <p>Stereotype description:
 * <br/><i>null</i></p>
 */
@objid ("dd2a1df2-2e18-4e5b-8627-c2e2abb8652b")
public class TOperation implements IMdaProxy {
    @objid ("6374a784-2e12-48f4-8c1d-a4dbdec41cd0")
    public static final String STEREOTYPE_NAME = "TOperation";

    @objid ("ad4c7f76-31f2-4ec4-a213-7c9bea2c5a60")
    public static final String DESCRIPTION_PROPERTY = "description";

    @objid ("5e1fa0df-a035-4c4c-850c-41db18b17cad")
    public static final String IMPLEMENTATION_PROPERTY = "implementation";

    @objid ("4964052f-a393-4874-9d19-fe563da1b57f")
    public static final String INPUTS_PROPERTY = "inputs";

    @objid ("575de6ac-d7ad-407e-a991-6aa52b3f7252")
    public static final String NAME_PROPERTY = "name";

    @objid ("f39ff0a7-fccd-40fd-9add-5fb9a96561af")
    public static final String OUTPUTS_PROPERTY = "outputs";

    /**
     * The underlying {@link ModelElement} represented by this proxy, never null.
     */
    @objid ("370f01f7-8d33-4fc9-83e2-d4f1d6427a46")
    protected final ModelElement elt;

    /**
     * Tells whether a {@link TOperation proxy} can be instantiated from a {@link MObject} checking it is a {@link ModelElement} stereotyped << TOperation >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("07540ec4-8079-4719-8e2b-99db491c9210")
    public static boolean canInstantiate(final MObject elt) {
        return ((elt instanceof ModelElement) && (TOperation.MdaTypes.STEREOTYPE_ELT!=null) && ((ModelElement) elt).isStereotyped(TOperation.MdaTypes.STEREOTYPE_ELT));
    }

    /**
     * Create a new {@link ModelElement} stereotyped << TOperation >> then instantiate a {@link TOperation} proxy.
     * @return a {@link TOperation} proxy on the created {@link ModelElement}.
     */
    @objid ("b00f1509-aa4b-46db-bae6-0e2e61b6efd2")
    public static TOperation create(final IModelingSession session) {
        ModelElement e = (ModelElement)session.getModel().createElement("Infrastructure.ModelElement");
        e.getExtension().add(TOperation.MdaTypes.STEREOTYPE_ELT);
        return TOperation.instantiate((ModelElement)e);
    }

    /**
     * Tries to instantiate a {@link TOperation} proxy from a {@link ModelElement} stereotyped << TOperation >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ModelElement
     * @return a {@link TOperation} proxy or <i>null</i>.
     */
    @objid ("d46de743-bed3-41ec-a5e4-1a2537b545c3")
    public static TOperation instantiate(final ModelElement obj) {
        return TOperation.canInstantiate(obj) ? new TOperation(obj) : null;
    }

    /**
     * Tries to instantiate a {@link TOperation} proxy from a {@link ModelElement} stereotyped << TOperation >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ModelElement}
     * @return a {@link TOperation} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("a56ed8c8-94ba-440d-b721-17473a134ae5")
    public static TOperation safeInstantiate(final ModelElement obj) throws IllegalArgumentException {
        if (TOperation.canInstantiate(obj))
        	return new TOperation(obj);
        else
        	throw new IllegalArgumentException("TOperation: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("cd5113bc-9e67-4ae5-a014-8148d5380b24")
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
        TOperation other = (TOperation) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Getter for property 'TOperation.description'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("84679416-fd5d-4cdd-9b70-51fbf74fc5d4")
    public String getDescription() {
        String value = this.elt.getProperty(TOperation.MdaTypes.STEREOTYPE_ELT,
                                            TOperation.MdaTypes.DESCRIPTION_PROPERTY_ELT.getName());
        if (value == null) {
          value = TOperation.MdaTypes.DESCRIPTION_PROPERTY_ELT.getDefaultValue();
        }
        return (String) PropertyConverter.convertToObject(TOperation.MdaTypes.DESCRIPTION_PROPERTY_ELT, value, this.elt);
        
    }

    /**
     * Get the underlying {@link ModelElement}.
     * @return the ModelElement represented by this proxy, never null.
     */
    @objid ("893ae67b-f415-4770-8c72-d8e401e413c7")
    public ModelElement getElement() {
        return this.elt;
    }

    /**
     * Getter for property 'TOperation.implementation'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("b0bdefab-e843-4850-b99e-f926cb2c4663")
    public String getImplementation() {
        String value = this.elt.getProperty(TOperation.MdaTypes.STEREOTYPE_ELT,
                                            TOperation.MdaTypes.IMPLEMENTATION_PROPERTY_ELT.getName());
        if (value == null) {
          value = TOperation.MdaTypes.IMPLEMENTATION_PROPERTY_ELT.getDefaultValue();
        }
        return (String) PropertyConverter.convertToObject(TOperation.MdaTypes.IMPLEMENTATION_PROPERTY_ELT, value, this.elt);
        
    }

    /**
     * Getter for property 'TOperation.inputs'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("6a7db2df-a9a8-414c-ad40-d5870d5644d3")
    public String getInputs() {
        String value = this.elt.getProperty(TOperation.MdaTypes.STEREOTYPE_ELT,
                                            TOperation.MdaTypes.INPUTS_PROPERTY_ELT.getName());
        if (value == null) {
          value = TOperation.MdaTypes.INPUTS_PROPERTY_ELT.getDefaultValue();
        }
        return (String) PropertyConverter.convertToObject(TOperation.MdaTypes.INPUTS_PROPERTY_ELT, value, this.elt);
        
    }

    /**
     * Getter for property 'TOperation.name'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("21a8337c-7966-46cf-be86-89add7c052bd")
    public String getName() {
        String value = this.elt.getProperty(TOperation.MdaTypes.STEREOTYPE_ELT,
                                            TOperation.MdaTypes.NAME_PROPERTY_ELT.getName());
        if (value == null) {
          value = TOperation.MdaTypes.NAME_PROPERTY_ELT.getDefaultValue();
        }
        return (String) PropertyConverter.convertToObject(TOperation.MdaTypes.NAME_PROPERTY_ELT, value, this.elt);
        
    }

    /**
     * Getter for property 'TOperation.outputs'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("5a1b92af-1ff2-46b6-b9e5-364f0d53c82f")
    public String getOutputs() {
        String value = this.elt.getProperty(TOperation.MdaTypes.STEREOTYPE_ELT,
                                            TOperation.MdaTypes.OUTPUTS_PROPERTY_ELT.getName());
        if (value == null) {
          value = TOperation.MdaTypes.OUTPUTS_PROPERTY_ELT.getDefaultValue();
        }
        return (String) PropertyConverter.convertToObject(TOperation.MdaTypes.OUTPUTS_PROPERTY_ELT, value, this.elt);
        
    }

    @objid ("8410bdfe-20a3-4fb9-b1af-14ab0a8475dc")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for property 'TOperation.description'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("ea7634b1-92b9-47d2-9234-4cf02f8e83c3")
    public void setDescription(final String value) {
        this.elt.setProperty(TOperation.MdaTypes.STEREOTYPE_ELT,
                             TOperation.MdaTypes.DESCRIPTION_PROPERTY_ELT.getName(),
                             PropertyConverter.convertToString(TOperation.MdaTypes.DESCRIPTION_PROPERTY_ELT, value));
    }

    /**
     * Setter for property 'TOperation.implementation'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("fd2a70c3-e33f-4a9d-bba6-793fcb170489")
    public void setImplementation(final String value) {
        this.elt.setProperty(TOperation.MdaTypes.STEREOTYPE_ELT,
                             TOperation.MdaTypes.IMPLEMENTATION_PROPERTY_ELT.getName(),
                             PropertyConverter.convertToString(TOperation.MdaTypes.IMPLEMENTATION_PROPERTY_ELT, value));
    }

    /**
     * Setter for property 'TOperation.inputs'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("f33f67a1-ae48-4486-8829-ed92446d37a0")
    public void setInputs(final String value) {
        this.elt.setProperty(TOperation.MdaTypes.STEREOTYPE_ELT,
                             TOperation.MdaTypes.INPUTS_PROPERTY_ELT.getName(),
                             PropertyConverter.convertToString(TOperation.MdaTypes.INPUTS_PROPERTY_ELT, value));
    }

    /**
     * Setter for property 'TOperation.name'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("93f5f59c-0607-42f8-89bc-4df6302be7c6")
    public void setName(final String value) {
        this.elt.setProperty(TOperation.MdaTypes.STEREOTYPE_ELT,
                             TOperation.MdaTypes.NAME_PROPERTY_ELT.getName(),
                             PropertyConverter.convertToString(TOperation.MdaTypes.NAME_PROPERTY_ELT, value));
    }

    /**
     * Setter for property 'TOperation.outputs'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("aaa89db6-609e-4658-8ddb-5cebd5f50a79")
    public void setOutputs(final String value) {
        this.elt.setProperty(TOperation.MdaTypes.STEREOTYPE_ELT,
                             TOperation.MdaTypes.OUTPUTS_PROPERTY_ELT.getName(),
                             PropertyConverter.convertToString(TOperation.MdaTypes.OUTPUTS_PROPERTY_ELT, value));
    }

    @objid ("a11b9718-6f64-499e-bfa6-c5ca0a3bc393")
    protected  TOperation(final ModelElement elt) {
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
    @objid ("44437658-3b7b-4810-9cac-e05ae1432689")
    public static final class MdaTypes {
        @objid ("a4a336c0-1e8d-4843-8990-43bf9ec94308")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("cff04e17-2e10-4185-a730-013302a254b0")
        public static PropertyDefinition NAME_PROPERTY_ELT;

        @objid ("a07f7967-b59a-4b57-b21f-12e8acacdd6c")
        public static PropertyDefinition DESCRIPTION_PROPERTY_ELT;

        @objid ("dd59a065-d9b3-4b6f-8d3e-24e529d61cc3")
        public static PropertyDefinition IMPLEMENTATION_PROPERTY_ELT;

        @objid ("f617177b-a0b7-4627-9b00-a576f6a8ee5f")
        public static PropertyDefinition INPUTS_PROPERTY_ELT;

        @objid ("c8ec0b1d-d9a8-44a4-8fd3-00bcf37b86c6")
        public static PropertyDefinition OUTPUTS_PROPERTY_ELT;

        @objid ("5b52cfa2-12ad-4180-9c85-a2971329b19f")
        private static Stereotype MDAASSOCDEP;

        @objid ("9d3c2b88-9385-44ff-9ebc-f712c2610108")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5cfaadd2-e78c-4d0e-8447-13ca0cb996d1")
        public static void init(final IModelingSession session) throws MdaProxyException {
            List <MRef> missingRefs = new ArrayList<>();
            MRef mRef;
            mRef = new MRef(Stereotype.MQNAME, "24e1e933-2b93-498c-9e0d-0d899a81eb20", "TOperation");
            STEREOTYPE_ELT = (Stereotype) session.findByRef(mRef);
            if (STEREOTYPE_ELT==null) missingRefs.add(mRef);

            mRef = new MRef(PropertyDefinition.MQNAME, "23652b9d-9d58-4dc9-9d5b-906fcf3594e4", "name");
            NAME_PROPERTY_ELT = (PropertyDefinition) session.findByRef(mRef);
            if (NAME_PROPERTY_ELT==null) missingRefs.add(mRef);

            mRef = new MRef(PropertyDefinition.MQNAME, "7e07cefa-0db7-4a6c-99f5-099c223834eb", "description");
            DESCRIPTION_PROPERTY_ELT = (PropertyDefinition) session.findByRef(mRef);
            if (DESCRIPTION_PROPERTY_ELT==null) missingRefs.add(mRef);

            mRef = new MRef(PropertyDefinition.MQNAME, "d3330a11-ae5b-464f-862b-e587feba80ac", "implementation");
            IMPLEMENTATION_PROPERTY_ELT = (PropertyDefinition) session.findByRef(mRef);
            if (IMPLEMENTATION_PROPERTY_ELT==null) missingRefs.add(mRef);

            mRef = new MRef(PropertyDefinition.MQNAME, "30a6aef6-f577-4f54-a2eb-9df4e6bc46dd", "inputs");
            INPUTS_PROPERTY_ELT = (PropertyDefinition) session.findByRef(mRef);
            if (INPUTS_PROPERTY_ELT==null) missingRefs.add(mRef);

            mRef = new MRef(PropertyDefinition.MQNAME, "d75d43de-20d1-4375-8764-f8048532f746", "outputs");
            OUTPUTS_PROPERTY_ELT = (PropertyDefinition) session.findByRef(mRef);
            if (OUTPUTS_PROPERTY_ELT==null) missingRefs.add(mRef);

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
