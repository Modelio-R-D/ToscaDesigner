/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ToscaDesigner v0.5.1

 * This file was generated on 29/07/2025 19:13 by Modelio Studio.
 */
package fr.softeam.toscadesigner.api.automatic.standard.staticdiagram;

import java.util.ArrayList;
import java.util.List;

import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.impl.IMdaProxy;
import fr.softeam.toscadesigner.impl.MdaProxyException;

/**
 * Proxy class to handle a {@link StaticDiagram} with << ToscaDiagram >> stereotype.
 * <p>Stereotype description:
 * <br/><i>null</i></p>
 */
@objid ("f69e9027-ea93-4817-83ee-f0667cde21da")
public class ToscaDiagram implements IMdaProxy {
    @objid ("b87792db-c84f-4e45-80d4-366894315571")
    public static final String STEREOTYPE_NAME = "ToscaDiagram";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("9a398b78-4867-4be7-8ef9-2f8ea281d805")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link ToscaDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << ToscaDiagram >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("53ec2714-87c1-46cf-968c-a7da232f7933")
    public static boolean canInstantiate(final MObject elt) {
        return ((elt instanceof StaticDiagram) && (ToscaDiagram.MdaTypes.STEREOTYPE_ELT!=null) && ((StaticDiagram) elt).isStereotyped(ToscaDiagram.MdaTypes.STEREOTYPE_ELT));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << ToscaDiagram >> then instantiate a {@link ToscaDiagram} proxy.
     * @return a {@link ToscaDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("c39469f8-9a97-4130-9c9a-4ff37c589458")
    public static ToscaDiagram create(final IModelingSession session) {
        ModelElement e = (ModelElement)session.getModel().createElement("Standard.StaticDiagram");
        e.getExtension().add(ToscaDiagram.MdaTypes.STEREOTYPE_ELT);
        return ToscaDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link ToscaDiagram} proxy from a {@link StaticDiagram} stereotyped << ToscaDiagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link ToscaDiagram} proxy or <i>null</i>.
     */
    @objid ("64a4cdd7-630b-4ac5-9dc0-2182d68e344d")
    public static ToscaDiagram instantiate(final StaticDiagram obj) {
        return ToscaDiagram.canInstantiate(obj) ? new ToscaDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link ToscaDiagram} proxy from a {@link StaticDiagram} stereotyped << ToscaDiagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link StaticDiagram}
     * @return a {@link ToscaDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("c906d028-0ef7-4f54-9815-3e4c5b938e26")
    public static ToscaDiagram safeInstantiate(final StaticDiagram obj) throws IllegalArgumentException {
        if (ToscaDiagram.canInstantiate(obj))
        	return new ToscaDiagram(obj);
        else
        	throw new IllegalArgumentException("ToscaDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("7b362f54-ef73-481b-afe2-8cf3cb5eff66")
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
        ToscaDiagram other = (ToscaDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link StaticDiagram}.
     * @return the StaticDiagram represented by this proxy, never null.
     */
    @objid ("e4d36e84-1b94-4f11-bb09-99be9b2ec200")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("e77e7e5b-4bc5-4958-9e83-95cb4cc630c1")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("72011374-9811-447b-9782-b799e768a417")
    protected  ToscaDiagram(final StaticDiagram elt) {
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
    @objid ("2ea70049-a79a-41f1-9653-1f0b748d8ae8")
    public static final class MdaTypes {
        @objid ("eabdc4d0-02cc-49d8-a092-e3070229dfcf")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b3175466-38e2-46e4-9b65-8538c3ca53e0")
        private static Stereotype MDAASSOCDEP;

        @objid ("a157601b-4e89-44fa-9f45-0a2af89ab1d7")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("cc4dd9ba-a79b-4572-94a2-d547ac915f7c")
        public static void init(final IModelingSession session) throws MdaProxyException {
            List <MRef> missingRefs = new ArrayList<>();
            MRef mRef;
            mRef = new MRef(Stereotype.MQNAME, "dddbe647-1f21-42d1-93cd-f61109a8afd3", "ToscaDiagram");
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
