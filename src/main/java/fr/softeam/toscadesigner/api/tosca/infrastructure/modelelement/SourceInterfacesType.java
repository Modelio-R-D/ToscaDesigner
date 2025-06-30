/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ToscaDesigner v0.0.1

 * This file was generated on 04/06/2025 19:56 by Modelio Studio.
 */
package fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ModelingSessionRegistry;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.api.ToscaDesignerProxyFactory;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TInterface;
import fr.softeam.toscadesigner.impl.IMdaProxy;
import fr.softeam.toscadesigner.impl.MdaProxyException;

/**
 * Proxy class to handle a {@link ModelElement} with << SourceInterfacesType >> stereotype.
 * <p>Stereotype description:
 * <br/><i>null</i></p>
 */
@objid ("f7e60117-5382-4f4f-9fcc-af167d8c2e02")
public class SourceInterfacesType implements IMdaProxy {
    @objid ("8d5b307e-04e6-4e93-8c86-161bff2cad27")
    public static final String STEREOTYPE_NAME = "SourceInterfacesType";

    /**
     * The underlying {@link ModelElement} represented by this proxy, never null.
     */
    @objid ("6e135be0-1690-4b1d-8dbb-c5fc765076b4")
    protected final ModelElement elt;

    /**
     * Tells whether a {@link SourceInterfacesType proxy} can be instantiated from a {@link MObject} checking it is a {@link ModelElement} stereotyped << SourceInterfacesType >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("c8689637-fbed-424f-b2ca-cc222f8b649f")
    public static boolean canInstantiate(final MObject elt) {
        return ((elt instanceof ModelElement) && (SourceInterfacesType.MdaTypes.STEREOTYPE_ELT!=null) && ((ModelElement) elt).isStereotyped(SourceInterfacesType.MdaTypes.STEREOTYPE_ELT));
    }

    /**
     * Create a new {@link ModelElement} stereotyped << SourceInterfacesType >> then instantiate a {@link SourceInterfacesType} proxy.
     * 
     * @return a {@link SourceInterfacesType} proxy on the created {@link ModelElement}.
     */
    @objid ("2126f1f2-b7e1-45fc-9bb0-073fd3690f27")
    public static SourceInterfacesType create(final IModelingSession session) {
        ModelElement e = (ModelElement)session.getModel().createElement("Infrastructure.ModelElement");
        e.getExtension().add(SourceInterfacesType.MdaTypes.STEREOTYPE_ELT);
        return SourceInterfacesType.instantiate((ModelElement)e);
    }

    /**
     * Tries to instantiate a {@link SourceInterfacesType} proxy from a {@link ModelElement} stereotyped << SourceInterfacesType >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ModelElement
     * @return a {@link SourceInterfacesType} proxy or <i>null</i>.
     */
    @objid ("8bdb2ce4-d4c4-4dff-83c4-f5c160f90986")
    public static SourceInterfacesType instantiate(final ModelElement obj) {
        return SourceInterfacesType.canInstantiate(obj) ? new SourceInterfacesType(obj) : null;
    }

    /**
     * Tries to instantiate a {@link SourceInterfacesType} proxy from a {@link ModelElement} stereotyped << SourceInterfacesType >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ModelElement}
     * @return a {@link SourceInterfacesType} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("36fbcbba-3c41-40e2-80f3-0b4d3e23a473")
    public static SourceInterfacesType safeInstantiate(final ModelElement obj) throws IllegalArgumentException {
        if (SourceInterfacesType.canInstantiate(obj))
        	return new SourceInterfacesType(obj);
        else
        	throw new IllegalArgumentException("SourceInterfacesType: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    /**
     * Add a value to the 'interface' role.<p>
     * Role description:
     * null
     * 
     */
    @objid ("15c2988f-9efb-44fb-8d5c-55b83766b146")
    public void addInterface(final TInterface obj) {
        if (obj != null) {
            IModelingSession session = ModelingSessionRegistry.getSession(this.elt);
            Dependency d = session.getModel().createDependency(this.elt, obj.getElement(), SourceInterfacesType.MdaTypes.MDAASSOCDEP);
            d.setName("interface");
            d.putTagValue(SourceInterfacesType.MdaTypes.MDAASSOCDEP_ROLE, "interface");
        }
    }

    @objid ("f87cd8bc-0a92-4c1c-b0f8-1e8f711aa3d9")
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
        SourceInterfacesType other = (SourceInterfacesType) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ModelElement}. 
     * @return the ModelElement represented by this proxy, never null.
     */
    @objid ("a8f7ca91-4db5-4aad-955c-6a919bba928e")
    public ModelElement getElement() {
        return this.elt;
    }

    /**
     * Get the values of the 'interface' role.<p>
     * Role description:
     * null
     * 
     */
    @objid ("287a6273-7fec-42a1-835a-eab086e46799")
    public List<TInterface> getInterface() {
        List<TInterface> results = new ArrayList<>();
        for (Dependency d : this.elt.getDependsOnDependency()) {
          if (d.isStereotyped(SourceInterfacesType.MdaTypes.MDAASSOCDEP)
              && Objects.equals(d.getTagValue(SourceInterfacesType.MdaTypes.MDAASSOCDEP_ROLE), "interface")
              && TInterface.canInstantiate(d.getDependsOn()))
                results.add((TInterface)ToscaDesignerProxyFactory.instantiate(d.getDependsOn(), TInterface.MdaTypes.STEREOTYPE_ELT.getName()));
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("76e46290-799f-4f48-a526-f37a47b9de4d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Remove a value from the 'interface' role.<p>
     * Role description:
     * null
     * 
     */
    @objid ("05400036-1589-4a28-854f-911b70c48712")
    public boolean removeInterface(final TInterface obj) {
        if (obj != null) {
          for (Dependency d : new ArrayList<>(this.elt.getDependsOnDependency())) {
            if (d.isStereotyped(SourceInterfacesType.MdaTypes.MDAASSOCDEP) && Objects.equals(d.getTagValue(SourceInterfacesType.MdaTypes.MDAASSOCDEP_ROLE), "")) 
              if (Objects.equals(d.getDependsOn(), obj.getElement())) {
                d.delete();
                return true;
              }
          }
        }
        return false;
        
    }

    @objid ("0cf7c774-8fa0-4a5f-9b9f-dcb56ee61a3e")
    protected  SourceInterfacesType(final ModelElement elt) {
        this.elt = elt;
    }

    @objid ("67e38a15-c330-45b6-b5e5-396b357ce6b3")
    public static final class MdaTypes {
        @objid ("e4ccc003-5f97-4139-a5f1-2f40ed855315")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("01606c8a-1510-44ce-aa17-ae5b1198d7dc")
        private static Stereotype MDAASSOCDEP;

        @objid ("f58f547f-a417-454c-aa3c-3d189590ea69")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("800190be-f17a-42d0-9597-9f6589074b35")
        public static void init(final IModelingSession session) throws MdaProxyException {
            List <MRef> missingRefs = new ArrayList<>();
            MRef mRef;
            mRef = new MRef(Stereotype.MQNAME, "9623eee0-9e81-460b-a356-a2758dca32dc", "SourceInterfacesType");
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
