/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ToscaDesigner v0.5.1

 * This file was generated on 29/07/2025 19:13 by Modelio Studio.
 */
package fr.softeam.toscadesigner.api.tosca.standard.class_;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ModelingSessionRegistry;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.api.ToscaDesignerProxyFactory;
import fr.softeam.toscadesigner.api.tosca.standard.association.TRelationshipTemplate;
import fr.softeam.toscadesigner.impl.IMdaProxy;
import fr.softeam.toscadesigner.impl.MdaProxyException;

/**
 * Proxy class to handle a {@link Class} with << TRequirement >> stereotype.
 * <p>Stereotype description:
 * <br/><i>null</i></p>
 */
@objid ("f40dc47b-b0d9-4f4d-971c-08292696bed8")
public class TRequirement implements IMdaProxy {
    @objid ("0a1c250c-fc7a-43c2-bc1a-136594d7f93f")
    public static final String STEREOTYPE_NAME = "TRequirement";

    /**
     * The underlying {@link Class} represented by this proxy, never null.
     */
    @objid ("b8462925-6768-49bb-b2f5-fbe6f6817688")
    protected final Class elt;

    /**
     * Tells whether a {@link TRequirement proxy} can be instantiated from a {@link MObject} checking it is a {@link Class} stereotyped << TRequirement >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("044df6f1-50fe-416c-9621-1e19c8497b34")
    public static boolean canInstantiate(final MObject elt) {
        return ((elt instanceof Class) && (TRequirement.MdaTypes.STEREOTYPE_ELT!=null) && ((Class) elt).isStereotyped(TRequirement.MdaTypes.STEREOTYPE_ELT));
    }

    /**
     * Create a new {@link Class} stereotyped << TRequirement >> then instantiate a {@link TRequirement} proxy.
     * @return a {@link TRequirement} proxy on the created {@link Class}.
     */
    @objid ("2fab2d6c-b49e-4ea6-b65e-752f7863f1a3")
    public static TRequirement create(final IModelingSession session) {
        ModelElement e = (ModelElement)session.getModel().createElement("Standard.Class");
        e.getExtension().add(TRequirement.MdaTypes.STEREOTYPE_ELT);
        return TRequirement.instantiate((Class)e);
    }

    /**
     * Tries to instantiate a {@link TRequirement} proxy from a {@link Class} stereotyped << TRequirement >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Class
     * @return a {@link TRequirement} proxy or <i>null</i>.
     */
    @objid ("f97e201f-8853-499b-9ec7-e771fa8340cd")
    public static TRequirement instantiate(final Class obj) {
        return TRequirement.canInstantiate(obj) ? new TRequirement(obj) : null;
    }

    /**
     * Tries to instantiate a {@link TRequirement} proxy from a {@link Class} stereotyped << TRequirement >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Class}
     * @return a {@link TRequirement} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("2eabbf7b-b78c-4f74-9879-00ce0244f736")
    public static TRequirement safeInstantiate(final Class obj) throws IllegalArgumentException {
        if (TRequirement.canInstantiate(obj))
        	return new TRequirement(obj);
        else
        	throw new IllegalArgumentException("TRequirement: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("11f4f6c5-8977-43a7-a3d0-a724ed9b6bf3")
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
        TRequirement other = (TRequirement) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the value of the 'capability' role.<p>
     * Role description:
     * null
     * 
     */
    @objid ("aface6e3-75a6-4f88-a476-d9ce093f21b0")
    public CapabilityStereotype getCapability() {
        for (Dependency d : this.elt.getDependsOnDependency()) {
              if (d.isStereotyped(TRequirement.MdaTypes.MDAASSOCDEP)
                  && Objects.equals(d.getTagValue(TRequirement.MdaTypes.MDAASSOCDEP_ROLE), "capability")
                  && CapabilityStereotype.canInstantiate(d.getDependsOn())) {
                     return (CapabilityStereotype)ToscaDesignerProxyFactory.instantiate(d.getDependsOn(), CapabilityStereotype.MdaTypes.STEREOTYPE_ELT.getName());
              }
        }
        return null;
    }

    /**
     * Get the underlying {@link Class}.
     * @return the Class represented by this proxy, never null.
     */
    @objid ("4513052e-a9e6-4b7b-bb30-b2f62fa12ce2")
    public Class getElement() {
        return this.elt;
    }

    /**
     * Get the value of the 'node' role.<p>
     * Role description:
     * null
     * 
     */
    @objid ("b3f70f1d-1f8e-423b-bcdd-33410594a427")
    public TNodeTemplate getNode() {
        for (Dependency d : this.elt.getDependsOnDependency()) {
              if (d.isStereotyped(TRequirement.MdaTypes.MDAASSOCDEP)
                  && Objects.equals(d.getTagValue(TRequirement.MdaTypes.MDAASSOCDEP_ROLE), "node")
                  && TNodeTemplate.canInstantiate(d.getDependsOn())) {
                     return (TNodeTemplate)ToscaDesignerProxyFactory.instantiate(d.getDependsOn(), TNodeTemplate.MdaTypes.STEREOTYPE_ELT.getName());
              }
        }
        return null;
    }

    /**
     * Get the value of the 'relationship' role.<p>
     * Role description:
     * null
     * 
     */
    @objid ("4fe57b49-f5a2-4b6f-bdf7-5adb246edeba")
    public TRelationshipTemplate getRelationship() {
        for (Dependency d : this.elt.getDependsOnDependency()) {
              if (d.isStereotyped(TRequirement.MdaTypes.MDAASSOCDEP)
                  && Objects.equals(d.getTagValue(TRequirement.MdaTypes.MDAASSOCDEP_ROLE), "relationship")
                  && TRelationshipTemplate.canInstantiate(d.getDependsOn())) {
                     return (TRelationshipTemplate)ToscaDesignerProxyFactory.instantiate(d.getDependsOn(), TRelationshipTemplate.MdaTypes.STEREOTYPE_ELT.getName());
              }
        }
        return null;
    }

    @objid ("587515a8-0db4-4c43-bf7a-f91950b3994b")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Set the value of the 'capability' role.<p>
     * Role description:
     * null
     * 
     */
    @objid ("b64e8335-f309-4f53-a74a-12ceda92abf5")
    public void setCapability(final CapabilityStereotype obj) {
        Dependency dep = null;
        for (Dependency d : this.elt.getDependsOnDependency())
          if (d.isStereotyped(TRequirement.MdaTypes.MDAASSOCDEP) && Objects.equals(d.getTagValue(TRequirement.MdaTypes.MDAASSOCDEP_ROLE), "capability")) {
             dep = d;
             break;
          }
        if (obj == null) {
           if(dep != null) dep.delete();
        } else {
          if (dep == null) {
              IModelingSession session = ModelingSessionRegistry.getSession(this.elt);
              dep = session.getModel().createDependency(this.elt, obj.getElement(), TRequirement.MdaTypes.MDAASSOCDEP);
              dep.setName("capability");      dep.putTagValue(TRequirement.MdaTypes.MDAASSOCDEP_ROLE, "capability");
          }
          dep.setDependsOn(obj.getElement());
        }
        
    }

    /**
     * Set the value of the 'node' role.<p>
     * Role description:
     * null
     * 
     */
    @objid ("678a1fdd-43be-4ecd-ba0f-cdf3ef9885a8")
    public void setNode(final TNodeTemplate obj) {
        Dependency dep = null;
        for (Dependency d : this.elt.getDependsOnDependency())
          if (d.isStereotyped(TRequirement.MdaTypes.MDAASSOCDEP) && Objects.equals(d.getTagValue(TRequirement.MdaTypes.MDAASSOCDEP_ROLE), "node")) {
             dep = d;
             break;
          }
        if (obj == null) {
           if(dep != null) dep.delete();
        } else {
          if (dep == null) {
              IModelingSession session = ModelingSessionRegistry.getSession(this.elt);
              dep = session.getModel().createDependency(this.elt, obj.getElement(), TRequirement.MdaTypes.MDAASSOCDEP);
              dep.setName("node");      dep.putTagValue(TRequirement.MdaTypes.MDAASSOCDEP_ROLE, "node");
          }
          dep.setDependsOn(obj.getElement());
        }
        
    }

    /**
     * Set the value of the 'relationship' role.<p>
     * Role description:
     * null
     * 
     */
    @objid ("3e82fb6a-22de-4b55-9b94-ec09bfe127ad")
    public void setRelationship(final TRelationshipTemplate obj) {
        Dependency dep = null;
        for (Dependency d : this.elt.getDependsOnDependency())
          if (d.isStereotyped(TRequirement.MdaTypes.MDAASSOCDEP) && Objects.equals(d.getTagValue(TRequirement.MdaTypes.MDAASSOCDEP_ROLE), "relationship")) {
             dep = d;
             break;
          }
        if (obj == null) {
           if(dep != null) dep.delete();
        } else {
          if (dep == null) {
              IModelingSession session = ModelingSessionRegistry.getSession(this.elt);
              dep = session.getModel().createDependency(this.elt, obj.getElement(), TRequirement.MdaTypes.MDAASSOCDEP);
              dep.setName("relationship");      dep.putTagValue(TRequirement.MdaTypes.MDAASSOCDEP_ROLE, "relationship");
          }
          dep.setDependsOn(obj.getElement());
        }
        
    }

    @objid ("d47b5419-a683-4392-a8b4-74415ae700f0")
    protected  TRequirement(final Class elt) {
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
    @objid ("7e3143f2-f9b1-465e-9d11-eba0e71d7d96")
    public static final class MdaTypes {
        @objid ("f708e033-5d69-4c87-ab46-07ecf1f34b67")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("6e335a54-7fe8-49cd-973f-1f4c41708497")
        private static Stereotype MDAASSOCDEP;

        @objid ("cfdb3b02-c836-47a6-96b9-4ab3a12d11e5")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("21acbfb8-e53b-42c8-9c82-e986831904b5")
        public static void init(final IModelingSession session) throws MdaProxyException {
            List <MRef> missingRefs = new ArrayList<>();
            MRef mRef;
            mRef = new MRef(Stereotype.MQNAME, "f0ddd3c2-c7ec-4156-84b0-d31b76098b17", "TRequirement");
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
