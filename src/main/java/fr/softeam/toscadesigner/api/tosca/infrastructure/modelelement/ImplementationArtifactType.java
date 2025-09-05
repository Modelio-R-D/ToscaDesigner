/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ToscaDesigner v0.5.1

 * This file was generated on 29/07/2025 19:13 by Modelio Studio.
 */
package fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement;

import java.util.ArrayList;
import java.util.List;

import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.impl.IMdaProxy;
import fr.softeam.toscadesigner.impl.MdaProxyException;

/**
 * Proxy class to handle a {@link ModelElement} with << ImplementationArtifactType >> stereotype.
 * <p>Stereotype description:
 * <br/><i>null</i></p>
 */
@objid ("7fce30f8-48d7-475d-a21a-ba93113c1d46")
public class ImplementationArtifactType extends TImplementationArtifact implements IMdaProxy {
    @objid ("85e1f5b8-dfd0-479c-a5d3-ac7cd7f56dcb")
    public static final String STEREOTYPE_NAME = "ImplementationArtifactType";

    /**
     * Tells whether a {@link ImplementationArtifactType proxy} can be instantiated from a {@link MObject} checking it is a {@link ModelElement} stereotyped << ImplementationArtifactType >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("638b4828-809d-4104-a5cd-7c9e8c60b039")
    public static boolean canInstantiate(final MObject elt) {
        return ((elt instanceof ModelElement) && (ImplementationArtifactType.MdaTypes.STEREOTYPE_ELT!=null) && ((ModelElement) elt).isStereotyped(ImplementationArtifactType.MdaTypes.STEREOTYPE_ELT));
    }

    /**
     * Create a new {@link ModelElement} stereotyped << ImplementationArtifactType >> then instantiate a {@link ImplementationArtifactType} proxy.
     * @return a {@link ImplementationArtifactType} proxy on the created {@link ModelElement}.
     */
    @objid ("2d411b38-a900-4f36-9dc9-ec826cd9142e")
    public static ImplementationArtifactType create(final IModelingSession session) {
        ModelElement e = (ModelElement)session.getModel().createElement("Infrastructure.ModelElement");
        e.getExtension().add(ImplementationArtifactType.MdaTypes.STEREOTYPE_ELT);
        return ImplementationArtifactType.instantiate((ModelElement)e);
    }

    /**
     * Tries to instantiate a {@link ImplementationArtifactType} proxy from a {@link ModelElement} stereotyped << ImplementationArtifactType >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ModelElement
     * @return a {@link ImplementationArtifactType} proxy or <i>null</i>.
     */
    @objid ("3ec32fa1-315e-4891-8ee6-e36ee8a58b2c")
    public static ImplementationArtifactType instantiate(final ModelElement obj) {
        return ImplementationArtifactType.canInstantiate(obj) ? new ImplementationArtifactType(obj) : null;
    }

    /**
     * Tries to instantiate a {@link ImplementationArtifactType} proxy from a {@link ModelElement} stereotyped << ImplementationArtifactType >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ModelElement}
     * @return a {@link ImplementationArtifactType} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("6541a60f-6c99-4e2e-8f17-2a8718a21e30")
    public static ImplementationArtifactType safeInstantiate(final ModelElement obj) throws IllegalArgumentException {
        if (ImplementationArtifactType.canInstantiate(obj))
        	return new ImplementationArtifactType(obj);
        else
        	throw new IllegalArgumentException("ImplementationArtifactType: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("24ead19f-f021-4cfd-8127-84284ec0dd10")
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
        ImplementationArtifactType other = (ImplementationArtifactType) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ModelElement}.
     * @return the ModelElement represented by this proxy, never null.
     */
    @objid ("78fc4887-4dcf-4989-a554-b4b9b6ad27ff")
    @Override
    public ModelElement getElement() {
        return (ModelElement)super.getElement();
    }

    @objid ("6a76a2d2-b57c-4a7c-8468-6ec4e1655092")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("f0f55f54-2cab-4352-9b4c-b29609e8e86e")
    protected  ImplementationArtifactType(final ModelElement elt) {
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
    @objid ("12f806c1-90ed-4f15-a63a-2e725c16e545")
    public static final class MdaTypes {
        @objid ("211d9d17-7156-4e60-94d3-08146f361bbd")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("14abc1b8-ce83-4697-a719-76134eca63d0")
        private static Stereotype MDAASSOCDEP;

        @objid ("d9ff1cdf-f4de-497d-8caf-4232b5867c7b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2a59aba3-a348-441b-8ac4-3bc4de50ac17")
        public static void init(final IModelingSession session) throws MdaProxyException {
            List <MRef> missingRefs = new ArrayList<>();
            MRef mRef;
            mRef = new MRef(Stereotype.MQNAME, "0599d5cf-f2a0-44c3-9214-4f2715bfc1b4", "ImplementationArtifactType");
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
