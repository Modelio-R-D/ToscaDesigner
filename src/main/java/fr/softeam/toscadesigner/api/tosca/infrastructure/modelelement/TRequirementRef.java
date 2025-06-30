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
 * Proxy class to handle a {@link ModelElement} with << TRequirementRef >> stereotype.
 * <p>Stereotype description:
 * <br/><i>null</i></p>
 */
@objid ("4fbda9cb-04e8-447a-a14f-32e95816289c")
public class TRequirementRef implements IMdaProxy {
    @objid ("e052d4d1-54f3-4f89-b417-b07dddcded1d")
    public static final String STEREOTYPE_NAME = "TRequirementRef";

    @objid ("e73e80ef-e80b-4f23-adf8-bc7ce3693b44")
    public static final String NAME_PROPERTY = "name";

    @objid ("dc6e95d5-23da-4328-aef4-699a62189c80")
    public static final String REF_PROPERTY = "ref";

    /**
     * The underlying {@link ModelElement} represented by this proxy, never null.
     */
    @objid ("de49ef65-c2a5-4b2d-b8c4-638ebdddd594")
    protected final ModelElement elt;

    /**
     * Tells whether a {@link TRequirementRef proxy} can be instantiated from a {@link MObject} checking it is a {@link ModelElement} stereotyped << TRequirementRef >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("4dbe9415-e9db-45e3-8aab-0809e5924335")
    public static boolean canInstantiate(final MObject elt) {
        return ((elt instanceof ModelElement) && (TRequirementRef.MdaTypes.STEREOTYPE_ELT!=null) && ((ModelElement) elt).isStereotyped(TRequirementRef.MdaTypes.STEREOTYPE_ELT));
    }

    /**
     * Create a new {@link ModelElement} stereotyped << TRequirementRef >> then instantiate a {@link TRequirementRef} proxy.
     * 
     * @return a {@link TRequirementRef} proxy on the created {@link ModelElement}.
     */
    @objid ("62ed09c7-bd28-4793-a172-67233d51709a")
    public static TRequirementRef create(final IModelingSession session) {
        ModelElement e = (ModelElement)session.getModel().createElement("Infrastructure.ModelElement");
        e.getExtension().add(TRequirementRef.MdaTypes.STEREOTYPE_ELT);
        return TRequirementRef.instantiate((ModelElement)e);
    }

    /**
     * Tries to instantiate a {@link TRequirementRef} proxy from a {@link ModelElement} stereotyped << TRequirementRef >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ModelElement
     * @return a {@link TRequirementRef} proxy or <i>null</i>.
     */
    @objid ("5cd0d3fd-f62b-463e-82f1-a9b90863404f")
    public static TRequirementRef instantiate(final ModelElement obj) {
        return TRequirementRef.canInstantiate(obj) ? new TRequirementRef(obj) : null;
    }

    /**
     * Tries to instantiate a {@link TRequirementRef} proxy from a {@link ModelElement} stereotyped << TRequirementRef >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ModelElement}
     * @return a {@link TRequirementRef} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("3110efb1-e676-456a-9387-b0d85d9d0d77")
    public static TRequirementRef safeInstantiate(final ModelElement obj) throws IllegalArgumentException {
        if (TRequirementRef.canInstantiate(obj))
        	return new TRequirementRef(obj);
        else
        	throw new IllegalArgumentException("TRequirementRef: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b849c30e-8173-4a09-9b36-e71730a34520")
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
        TRequirementRef other = (TRequirementRef) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ModelElement}. 
     * @return the ModelElement represented by this proxy, never null.
     */
    @objid ("d3a9bce7-e354-42b4-9bf4-48079b951b5c")
    public ModelElement getElement() {
        return this.elt;
    }

    /**
     * Getter for property 'TRequirementRef.name'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("31e96bc2-6c69-4d14-9a15-cd3a23e05444")
    public String getName() {
        String value = this.elt.getProperty(TRequirementRef.MdaTypes.STEREOTYPE_ELT,
                                            TRequirementRef.MdaTypes.NAME_PROPERTY_ELT.getName());
        if (value == null) {
          value = TRequirementRef.MdaTypes.NAME_PROPERTY_ELT.getDefaultValue();
        }
        return (String) PropertyConverter.convertToObject(TRequirementRef.MdaTypes.NAME_PROPERTY_ELT, value, this.elt);
        
    }

    /**
     * Getter for property 'TRequirementRef.ref'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("b5ca83b9-b9f5-45f3-9517-404a3ad28387")
    public String getRef() {
        String value = this.elt.getProperty(TRequirementRef.MdaTypes.STEREOTYPE_ELT,
                                            TRequirementRef.MdaTypes.REF_PROPERTY_ELT.getName());
        if (value == null) {
          value = TRequirementRef.MdaTypes.REF_PROPERTY_ELT.getDefaultValue();
        }
        return (String) PropertyConverter.convertToObject(TRequirementRef.MdaTypes.REF_PROPERTY_ELT, value, this.elt);
        
    }

    @objid ("4cad36e3-be93-4c5c-8515-8d6744af6173")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for property 'TRequirementRef.name'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("036b687f-e40e-4c38-aa33-224ab91e6f29")
    public void setName(final String value) {
        this.elt.setProperty(TRequirementRef.MdaTypes.STEREOTYPE_ELT,
                             TRequirementRef.MdaTypes.NAME_PROPERTY_ELT.getName(),
                             PropertyConverter.convertToString(TRequirementRef.MdaTypes.NAME_PROPERTY_ELT, value));
    }

    /**
     * Setter for property 'TRequirementRef.ref'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("31cbf8e7-5264-4d83-89dc-2ce35f247b4d")
    public void setRef(final String value) {
        this.elt.setProperty(TRequirementRef.MdaTypes.STEREOTYPE_ELT,
                             TRequirementRef.MdaTypes.REF_PROPERTY_ELT.getName(),
                             PropertyConverter.convertToString(TRequirementRef.MdaTypes.REF_PROPERTY_ELT, value));
    }

    @objid ("b90c08c7-4918-4655-8b43-8c32a1f18dd1")
    protected  TRequirementRef(final ModelElement elt) {
        this.elt = elt;
    }

    @objid ("d833a99d-2420-4cdc-8d2c-dc189615a6a1")
    public static final class MdaTypes {
        @objid ("82e946da-689a-437e-929c-b0d465368e7d")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ed63badb-df01-4b8b-b54f-2bddd489ad2d")
        public static PropertyDefinition NAME_PROPERTY_ELT;

        @objid ("cb26651d-d291-49d4-bd5d-278b9efd0d11")
        public static PropertyDefinition REF_PROPERTY_ELT;

        @objid ("fea4779c-23c1-4b9f-aeff-f1f312462ccb")
        private static Stereotype MDAASSOCDEP;

        @objid ("c99726ff-19df-47bf-b0b3-698ef87394b3")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3138a366-6963-44ab-92b7-785e0de1a52d")
        public static void init(final IModelingSession session) throws MdaProxyException {
            List <MRef> missingRefs = new ArrayList<>();
            MRef mRef;
            mRef = new MRef(Stereotype.MQNAME, "179fadf7-b82d-47bc-ae58-40df62e045ed", "TRequirementRef");
            STEREOTYPE_ELT = (Stereotype) session.findByRef(mRef);
            if (STEREOTYPE_ELT==null) missingRefs.add(mRef);
            
            mRef = new MRef(PropertyDefinition.MQNAME, "100afbca-9f3b-49e3-ac91-f1465edc6002", "name");
            NAME_PROPERTY_ELT = (PropertyDefinition) session.findByRef(mRef);
            if (NAME_PROPERTY_ELT==null) missingRefs.add(mRef);
            
            mRef = new MRef(PropertyDefinition.MQNAME, "d9c4cff9-b58a-410a-9b68-f76a8879a37f", "ref");
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
