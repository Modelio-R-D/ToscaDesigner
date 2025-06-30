/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ToscaDesigner v0.0.1

 * This file was generated on 04/06/2025 19:56 by Modelio Studio.
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
 * Proxy class to handle a {@link ModelElement} with << TCapabilityRef >> stereotype.
 * <p>Stereotype description:
 * <br/><i>null</i></p>
 */
@objid ("6d542b90-77a2-4f43-b0d8-a7f6e63c0073")
public class TCapabilityRef implements IMdaProxy {
    @objid ("f7c94260-584b-4035-a37e-a432c41bf2f6")
    public static final String STEREOTYPE_NAME = "TCapabilityRef";

    @objid ("1594a688-85b7-49e9-8b58-0cdf821d7461")
    public static final String NAME_PROPERTY = "name";

    @objid ("5881d79d-e472-46b0-b8ff-73b3b0a6e400")
    public static final String REF_PROPERTY = "ref";

    /**
     * The underlying {@link ModelElement} represented by this proxy, never null.
     */
    @objid ("959f8cde-8731-4465-a21e-260974144ec1")
    protected final ModelElement elt;

    /**
     * Tells whether a {@link TCapabilityRef proxy} can be instantiated from a {@link MObject} checking it is a {@link ModelElement} stereotyped << TCapabilityRef >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("0cca18bf-764b-4f0a-97ed-a51d620562cf")
    public static boolean canInstantiate(final MObject elt) {
        return ((elt instanceof ModelElement) && (TCapabilityRef.MdaTypes.STEREOTYPE_ELT!=null) && ((ModelElement) elt).isStereotyped(TCapabilityRef.MdaTypes.STEREOTYPE_ELT));
    }

    /**
     * Create a new {@link ModelElement} stereotyped << TCapabilityRef >> then instantiate a {@link TCapabilityRef} proxy.
     * 
     * @return a {@link TCapabilityRef} proxy on the created {@link ModelElement}.
     */
    @objid ("4f91839d-23e3-4f11-9d7a-07c752ddbc9b")
    public static TCapabilityRef create(final IModelingSession session) {
        ModelElement e = (ModelElement)session.getModel().createElement("Infrastructure.ModelElement");
        e.getExtension().add(TCapabilityRef.MdaTypes.STEREOTYPE_ELT);
        return TCapabilityRef.instantiate((ModelElement)e);
    }

    /**
     * Tries to instantiate a {@link TCapabilityRef} proxy from a {@link ModelElement} stereotyped << TCapabilityRef >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ModelElement
     * @return a {@link TCapabilityRef} proxy or <i>null</i>.
     */
    @objid ("6f9782fb-96b1-41c8-a309-3171d98f2a0f")
    public static TCapabilityRef instantiate(final ModelElement obj) {
        return TCapabilityRef.canInstantiate(obj) ? new TCapabilityRef(obj) : null;
    }

    /**
     * Tries to instantiate a {@link TCapabilityRef} proxy from a {@link ModelElement} stereotyped << TCapabilityRef >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ModelElement}
     * @return a {@link TCapabilityRef} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("a5d7ba21-b9fd-44cb-be93-859abd3267f0")
    public static TCapabilityRef safeInstantiate(final ModelElement obj) throws IllegalArgumentException {
        if (TCapabilityRef.canInstantiate(obj))
        	return new TCapabilityRef(obj);
        else
        	throw new IllegalArgumentException("TCapabilityRef: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("ab074420-98fa-43fc-b41b-db6a221b2b77")
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
        TCapabilityRef other = (TCapabilityRef) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ModelElement}. 
     * @return the ModelElement represented by this proxy, never null.
     */
    @objid ("d29c1016-8575-46e3-beba-89fe783a3d29")
    public ModelElement getElement() {
        return this.elt;
    }

    /**
     * Getter for property 'TCapabilityRef.name'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("7168a747-9fc2-4829-a9ce-c9b690e8729e")
    public String getName() {
        String value = this.elt.getProperty(TCapabilityRef.MdaTypes.STEREOTYPE_ELT,
                                            TCapabilityRef.MdaTypes.NAME_PROPERTY_ELT.getName());
        if (value == null) {
          value = TCapabilityRef.MdaTypes.NAME_PROPERTY_ELT.getDefaultValue();
        }
        return (String) PropertyConverter.convertToObject(TCapabilityRef.MdaTypes.NAME_PROPERTY_ELT, value, this.elt);
        
    }

    /**
     * Getter for property 'TCapabilityRef.ref'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("81d562fd-0c43-4e5f-829b-099af618c43c")
    public String getRef() {
        String value = this.elt.getProperty(TCapabilityRef.MdaTypes.STEREOTYPE_ELT,
                                            TCapabilityRef.MdaTypes.REF_PROPERTY_ELT.getName());
        if (value == null) {
          value = TCapabilityRef.MdaTypes.REF_PROPERTY_ELT.getDefaultValue();
        }
        return (String) PropertyConverter.convertToObject(TCapabilityRef.MdaTypes.REF_PROPERTY_ELT, value, this.elt);
        
    }

    @objid ("9e27cb6b-ef81-4dc0-ab15-2a97fa7db709")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for property 'TCapabilityRef.name'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("1ae37586-da59-47e9-a6cf-aedf6a619196")
    public void setName(final String value) {
        this.elt.setProperty(TCapabilityRef.MdaTypes.STEREOTYPE_ELT,
                             TCapabilityRef.MdaTypes.NAME_PROPERTY_ELT.getName(),
                             PropertyConverter.convertToString(TCapabilityRef.MdaTypes.NAME_PROPERTY_ELT, value));
    }

    /**
     * Setter for property 'TCapabilityRef.ref'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("17da29d2-d9de-4116-95a5-05d3405d481c")
    public void setRef(final String value) {
        this.elt.setProperty(TCapabilityRef.MdaTypes.STEREOTYPE_ELT,
                             TCapabilityRef.MdaTypes.REF_PROPERTY_ELT.getName(),
                             PropertyConverter.convertToString(TCapabilityRef.MdaTypes.REF_PROPERTY_ELT, value));
    }

    @objid ("973932d5-2390-49d3-b1c4-8270ea2f97e3")
    protected  TCapabilityRef(final ModelElement elt) {
        this.elt = elt;
    }

    @objid ("b2213644-4bd9-46ec-9112-3176bf76e550")
    public static final class MdaTypes {
        @objid ("473744d5-7429-4bf5-a689-f95c7414a244")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d31ae8aa-248f-4097-9410-fecd7e490289")
        public static PropertyDefinition NAME_PROPERTY_ELT;

        @objid ("3376e9ed-e5ce-416a-8c63-e0ef895250db")
        public static PropertyDefinition REF_PROPERTY_ELT;

        @objid ("53a8a12c-b464-429c-bc6e-823fb9dac637")
        private static Stereotype MDAASSOCDEP;

        @objid ("cf3bde7f-a2db-4d6e-86c2-834c086f0120")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e03004e4-67e5-4302-90de-f28fb8a89a64")
        public static void init(final IModelingSession session) throws MdaProxyException {
            List <MRef> missingRefs = new ArrayList<>();
            MRef mRef;
            mRef = new MRef(Stereotype.MQNAME, "ca7c07e5-454b-4d20-8f3d-6949dc4be7fd", "TCapabilityRef");
            STEREOTYPE_ELT = (Stereotype) session.findByRef(mRef);
            if (STEREOTYPE_ELT==null) missingRefs.add(mRef);
            
            mRef = new MRef(PropertyDefinition.MQNAME, "cd1cff1f-be76-441b-98a5-771bdff3c02b", "name");
            NAME_PROPERTY_ELT = (PropertyDefinition) session.findByRef(mRef);
            if (NAME_PROPERTY_ELT==null) missingRefs.add(mRef);
            
            mRef = new MRef(PropertyDefinition.MQNAME, "fc4c56a2-5405-4410-8ce2-b12a6d2480ec", "ref");
            REF_PROPERTY_ELT = (PropertyDefinition) session.findByRef(mRef);
            if (REF_PROPERTY_ELT==null) missingRefs.add(mRef);
            
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
