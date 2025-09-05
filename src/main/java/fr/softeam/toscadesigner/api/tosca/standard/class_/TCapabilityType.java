/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ToscaDesigner v0.5.1

 * This file was generated on 29/07/2025 19:13 by Modelio Studio.
 */
package fr.softeam.toscadesigner.api.tosca.standard.class_;

import java.util.ArrayList;
import java.util.List;

import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.impl.IMdaProxy;
import fr.softeam.toscadesigner.impl.MdaProxyException;

/**
 * Proxy class to handle a {@link Class} with << TCapabilityType >> stereotype.
 * <p>Stereotype description:
 * <br/><i>null</i></p>
 */
@objid ("b5f2d0d9-8204-4766-80f7-e7109b67a5f1")
public class TCapabilityType extends CapabilityStereotype implements IMdaProxy {
    @objid ("f02a7ed5-847a-4900-b03d-5db366b958fa")
    public static final String STEREOTYPE_NAME = "TCapabilityType";

    /**
     * Tells whether a {@link TCapabilityType proxy} can be instantiated from a {@link MObject} checking it is a {@link Class} stereotyped << TCapabilityType >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("e9ff3df7-5664-4c73-8f60-ff5037cec3e3")
    public static boolean canInstantiate(final MObject elt) {
        return ((elt instanceof Class) && (TCapabilityType.MdaTypes.STEREOTYPE_ELT!=null) && ((Class) elt).isStereotyped(TCapabilityType.MdaTypes.STEREOTYPE_ELT));
    }

    /**
     * Create a new {@link Class} stereotyped << TCapabilityType >> then instantiate a {@link TCapabilityType} proxy.
     * @return a {@link TCapabilityType} proxy on the created {@link Class}.
     */
    @objid ("748ee68e-86b0-47a4-b4ce-f9980e94e4a0")
    public static TCapabilityType create(final IModelingSession session) {
        ModelElement e = (ModelElement)session.getModel().createElement("Standard.Class");
        e.getExtension().add(TCapabilityType.MdaTypes.STEREOTYPE_ELT);
        return TCapabilityType.instantiate((Class)e);
    }

    /**
     * Tries to instantiate a {@link TCapabilityType} proxy from a {@link Class} stereotyped << TCapabilityType >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Class
     * @return a {@link TCapabilityType} proxy or <i>null</i>.
     */
    @objid ("d9c856be-9e55-43d8-872e-817157fd33c9")
    public static TCapabilityType instantiate(final Class obj) {
        return TCapabilityType.canInstantiate(obj) ? new TCapabilityType(obj) : null;
    }

    /**
     * Tries to instantiate a {@link TCapabilityType} proxy from a {@link Class} stereotyped << TCapabilityType >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Class}
     * @return a {@link TCapabilityType} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("88750de1-0070-43d3-807e-82881abf2923")
    public static TCapabilityType safeInstantiate(final Class obj) throws IllegalArgumentException {
        if (TCapabilityType.canInstantiate(obj))
        	return new TCapabilityType(obj);
        else
        	throw new IllegalArgumentException("TCapabilityType: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("89124256-8c76-4261-ac87-3867781afdfa")
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
        TCapabilityType other = (TCapabilityType) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Class}.
     * @return the Class represented by this proxy, never null.
     */
    @objid ("84da1133-08fa-4bfe-a3a2-136010c1ef8d")
    @Override
    public Class getElement() {
        return (Class)super.getElement();
    }

    @objid ("ba301725-6c19-443d-9793-6d69fabb8282")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("efa350fd-bfc4-494c-9a2b-aa4ab41360f8")
    protected  TCapabilityType(final Class elt) {
        super(elt);
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
    @objid ("62799cea-dfb7-4558-ae8a-d4727adcadb6")
    public static final class MdaTypes {
        @objid ("8df17fbb-f33c-4a00-a853-cb418fa79435")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f8e56e28-d894-48c9-9246-a7b0f0cce0c0")
        private static Stereotype MDAASSOCDEP;

        @objid ("d1554635-c484-4019-85fd-6ad61e9f7d36")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("63fbf7e3-47c6-4596-8b5f-d4eaea0d79fd")
        public static void init(final IModelingSession session) throws MdaProxyException {
            List <MRef> missingRefs = new ArrayList<>();
            MRef mRef;
            mRef = new MRef(Stereotype.MQNAME, "42438c96-0721-406a-a8b9-c4a85931cc60", "TCapabilityType");
            STEREOTYPE_ELT = (Stereotype) session.findByRef(mRef);
            if (STEREOTYPE_ELT==null) missingRefs.add(mRef);

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
