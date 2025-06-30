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
 * Proxy class to handle a {@link ModelElement} with << TargetInterfacesType >> stereotype.
 * <p>Stereotype description:
 * <br/><i>null</i></p>
 */
@objid ("ee3927a9-35c2-4657-9e8e-4c516c431e3c")
public class TargetInterfacesType implements IMdaProxy {
    @objid ("5346b0bc-3d74-4ff0-8803-628236a131fb")
    public static final String STEREOTYPE_NAME = "TargetInterfacesType";

    /**
     * The underlying {@link ModelElement} represented by this proxy, never null.
     */
    @objid ("fd99d8d1-86eb-4dbf-bb71-b309c81d0b53")
    protected final ModelElement elt;

    /**
     * Tells whether a {@link TargetInterfacesType proxy} can be instantiated from a {@link MObject} checking it is a {@link ModelElement} stereotyped << TargetInterfacesType >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("4db627f2-6a4b-421b-a7e3-929d2f79c0c3")
    public static boolean canInstantiate(final MObject elt) {
        return ((elt instanceof ModelElement) && (TargetInterfacesType.MdaTypes.STEREOTYPE_ELT!=null) && ((ModelElement) elt).isStereotyped(TargetInterfacesType.MdaTypes.STEREOTYPE_ELT));
    }

    /**
     * Create a new {@link ModelElement} stereotyped << TargetInterfacesType >> then instantiate a {@link TargetInterfacesType} proxy.
     * 
     * @return a {@link TargetInterfacesType} proxy on the created {@link ModelElement}.
     */
    @objid ("cbdcf27f-d183-4ac9-9c7e-bcf150d9dc16")
    public static TargetInterfacesType create(final IModelingSession session) {
        ModelElement e = (ModelElement)session.getModel().createElement("Infrastructure.ModelElement");
        e.getExtension().add(TargetInterfacesType.MdaTypes.STEREOTYPE_ELT);
        return TargetInterfacesType.instantiate((ModelElement)e);
    }

    /**
     * Tries to instantiate a {@link TargetInterfacesType} proxy from a {@link ModelElement} stereotyped << TargetInterfacesType >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ModelElement
     * @return a {@link TargetInterfacesType} proxy or <i>null</i>.
     */
    @objid ("78f4dc44-2684-49f0-b5d9-d5671cdbcc40")
    public static TargetInterfacesType instantiate(final ModelElement obj) {
        return TargetInterfacesType.canInstantiate(obj) ? new TargetInterfacesType(obj) : null;
    }

    /**
     * Tries to instantiate a {@link TargetInterfacesType} proxy from a {@link ModelElement} stereotyped << TargetInterfacesType >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ModelElement}
     * @return a {@link TargetInterfacesType} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("7e31da92-6d4a-4614-8561-8a48f2f0b01a")
    public static TargetInterfacesType safeInstantiate(final ModelElement obj) throws IllegalArgumentException {
        if (TargetInterfacesType.canInstantiate(obj))
        	return new TargetInterfacesType(obj);
        else
        	throw new IllegalArgumentException("TargetInterfacesType: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    /**
     * Add a value to the 'interface' role.<p>
     * Role description:
     * null
     * 
     */
    @objid ("6b3ab4a0-8701-4050-b34e-f40cbcfa5b63")
    public void addInterface(final TInterface obj) {
        if (obj != null) {
            IModelingSession session = ModelingSessionRegistry.getSession(this.elt);
            Dependency d = session.getModel().createDependency(this.elt, obj.getElement(), TargetInterfacesType.MdaTypes.MDAASSOCDEP);
            d.setName("interface");
            d.putTagValue(TargetInterfacesType.MdaTypes.MDAASSOCDEP_ROLE, "interface");
        }
    }

    @objid ("b40752dd-1279-47f7-98ff-97f88496e7e2")
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
        TargetInterfacesType other = (TargetInterfacesType) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ModelElement}. 
     * @return the ModelElement represented by this proxy, never null.
     */
    @objid ("e8e4aacf-fd67-41f2-b6d0-320de5dfe69b")
    public ModelElement getElement() {
        return this.elt;
    }

    /**
     * Get the values of the 'interface' role.<p>
     * Role description:
     * null
     * 
     */
    @objid ("68ed0291-c58d-4279-a6fa-81d14a2c853f")
    public List<TInterface> getInterface() {
        List<TInterface> results = new ArrayList<>();
        for (Dependency d : this.elt.getDependsOnDependency()) {
          if (d.isStereotyped(TargetInterfacesType.MdaTypes.MDAASSOCDEP)
              && Objects.equals(d.getTagValue(TargetInterfacesType.MdaTypes.MDAASSOCDEP_ROLE), "interface")
              && TInterface.canInstantiate(d.getDependsOn()))
                results.add((TInterface)ToscaDesignerProxyFactory.instantiate(d.getDependsOn(), TInterface.MdaTypes.STEREOTYPE_ELT.getName()));
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("215f693f-193d-4f1a-ba3c-b7d2c444bb64")
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
    @objid ("5103df9a-1e65-4527-b113-8027e31f34ed")
    public boolean removeInterface(final TInterface obj) {
        if (obj != null) {
          for (Dependency d : new ArrayList<>(this.elt.getDependsOnDependency())) {
            if (d.isStereotyped(TargetInterfacesType.MdaTypes.MDAASSOCDEP) && Objects.equals(d.getTagValue(TargetInterfacesType.MdaTypes.MDAASSOCDEP_ROLE), "")) 
              if (Objects.equals(d.getDependsOn(), obj.getElement())) {
                d.delete();
                return true;
              }
          }
        }
        return false;
        
    }

    @objid ("099f2b9b-172d-4931-80ad-4759ed1a502c")
    protected  TargetInterfacesType(final ModelElement elt) {
        this.elt = elt;
    }

    @objid ("e409b719-9496-4308-9800-45394912cf51")
    public static final class MdaTypes {
        @objid ("6d2a6045-8a82-4438-b80c-3828c9249bc1")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("72fd2bde-7a0e-4210-8320-cad2c041821b")
        private static Stereotype MDAASSOCDEP;

        @objid ("83bf5ce5-ea54-4152-bd9c-a8fae2aa8087")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("dcb1328c-0fdc-4b8b-adbc-df7cf6f1f497")
        public static void init(final IModelingSession session) throws MdaProxyException {
            List <MRef> missingRefs = new ArrayList<>();
            MRef mRef;
            mRef = new MRef(Stereotype.MQNAME, "0b8a60bc-b7c2-43a8-8c93-f8be7f724b60", "TargetInterfacesType");
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
