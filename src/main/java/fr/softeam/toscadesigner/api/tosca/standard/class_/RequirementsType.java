/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ToscaDesigner v0.0.1

 * This file was generated on 04/06/2025 19:56 by Modelio Studio.
 */
package fr.softeam.toscadesigner.api.tosca.standard.class_;

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
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.api.ToscaDesignerProxyFactory;
import fr.softeam.toscadesigner.impl.IMdaProxy;
import fr.softeam.toscadesigner.impl.MdaProxyException;

/**
 * Proxy class to handle a {@link Class} with << RequirementsType >> stereotype.
 * <p>Stereotype description:
 * <br/><i>null</i></p>
 */
@objid ("afd47426-851a-4142-8c0f-1922d9144e7a")
public class RequirementsType extends TEntityType implements IMdaProxy {
    @objid ("333ac399-9558-428f-8284-1a83b6f04b74")
    public static final String STEREOTYPE_NAME = "RequirementsType";

    /**
     * Tells whether a {@link RequirementsType proxy} can be instantiated from a {@link MObject} checking it is a {@link Class} stereotyped << RequirementsType >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("097775cf-98c3-48f3-bf8a-2850cdd49ed2")
    public static boolean canInstantiate(final MObject elt) {
        return ((elt instanceof Class) && (RequirementsType.MdaTypes.STEREOTYPE_ELT!=null) && ((Class) elt).isStereotyped(RequirementsType.MdaTypes.STEREOTYPE_ELT));
    }

    /**
     * Create a new {@link Class} stereotyped << RequirementsType >> then instantiate a {@link RequirementsType} proxy.
     * 
     * @return a {@link RequirementsType} proxy on the created {@link Class}.
     */
    @objid ("66598258-d2e4-41c9-858d-2bcf6fda0787")
    public static RequirementsType create(final IModelingSession session) {
        ModelElement e = (ModelElement)session.getModel().createElement("Standard.Class");
        e.getExtension().add(RequirementsType.MdaTypes.STEREOTYPE_ELT);
        return RequirementsType.instantiate((Class)e);
    }

    /**
     * Tries to instantiate a {@link RequirementsType} proxy from a {@link Class} stereotyped << RequirementsType >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Class
     * @return a {@link RequirementsType} proxy or <i>null</i>.
     */
    @objid ("c8fad1de-053b-4534-b5f7-48f000c68bce")
    public static RequirementsType instantiate(final Class obj) {
        return RequirementsType.canInstantiate(obj) ? new RequirementsType(obj) : null;
    }

    /**
     * Tries to instantiate a {@link RequirementsType} proxy from a {@link Class} stereotyped << RequirementsType >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Class}
     * @return a {@link RequirementsType} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("33033d2f-3647-4445-b7ee-31265bd7efaf")
    public static RequirementsType safeInstantiate(final Class obj) throws IllegalArgumentException {
        if (RequirementsType.canInstantiate(obj))
        	return new RequirementsType(obj);
        else
        	throw new IllegalArgumentException("RequirementsType: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    /**
     * Add a value to the 'requirements' role.<p>
     * Role description:
     * null
     * 
     */
    @objid ("f73e98d3-c96a-4d68-9b51-7ad5436c796c")
    public void addRequirements(final TRequirement obj) {
        if (obj != null) {
            IModelingSession session = ModelingSessionRegistry.getSession(this.elt);
            Dependency d = session.getModel().createDependency(this.elt, obj.getElement(), RequirementsType.MdaTypes.MDAASSOCDEP);
            d.setName("requirements");
            d.putTagValue(RequirementsType.MdaTypes.MDAASSOCDEP_ROLE, "requirements");
        }
    }

    @objid ("b70fa4a5-4470-4a81-8b12-9ae128057371")
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
        RequirementsType other = (RequirementsType) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Class}. 
     * @return the Class represented by this proxy, never null.
     */
    @objid ("847409a0-8a43-498d-8a6f-f89333716c20")
    @Override
    public Class getElement() {
        return (Class)super.getElement();
    }

    /**
     * Get the values of the 'requirements' role.<p>
     * Role description:
     * null
     * 
     */
    @objid ("3b74bf02-d872-4c89-95af-470abb708c5d")
    public List<TRequirement> getRequirements() {
        List<TRequirement> results = new ArrayList<>();
        for (Dependency d : this.elt.getDependsOnDependency()) {
          if (d.isStereotyped(RequirementsType.MdaTypes.MDAASSOCDEP)
              && Objects.equals(d.getTagValue(RequirementsType.MdaTypes.MDAASSOCDEP_ROLE), "requirements")
              && TRequirement.canInstantiate(d.getDependsOn()))
                results.add((TRequirement)ToscaDesignerProxyFactory.instantiate(d.getDependsOn(), TRequirement.MdaTypes.STEREOTYPE_ELT.getName()));
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("bcabfdb8-a7a5-4123-82a1-86c16147f6ac")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Remove a value from the 'requirements' role.<p>
     * Role description:
     * null
     * 
     */
    @objid ("5bfb60cd-9702-4497-8f73-e28f5f768391")
    public boolean removeRequirements(final TRequirement obj) {
        if (obj != null) {
          for (Dependency d : new ArrayList<>(this.elt.getDependsOnDependency())) {
            if (d.isStereotyped(RequirementsType.MdaTypes.MDAASSOCDEP) && Objects.equals(d.getTagValue(RequirementsType.MdaTypes.MDAASSOCDEP_ROLE), "")) 
              if (Objects.equals(d.getDependsOn(), obj.getElement())) {
                d.delete();
                return true;
              }
          }
        }
        return false;
        
    }

    @objid ("0be5bf27-596c-4009-a231-be84ff04e87a")
    protected  RequirementsType(final Class elt) {
        super(elt);
    }

    @objid ("eb3ffa37-ba59-4751-b3a4-b56ebb585cc0")
    public static final class MdaTypes {
        @objid ("9ac3c2c3-9452-4cac-b368-1f3174b92b79")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("764a6a60-7ed9-404d-81e4-cc883be71cf3")
        private static Stereotype MDAASSOCDEP;

        @objid ("55d9a465-abde-45ea-b63c-6c58d3d8f3a3")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2b09445e-02f7-4f36-94f2-7cf0b6c0708b")
        public static void init(final IModelingSession session) throws MdaProxyException {
            List <MRef> missingRefs = new ArrayList<>();
            MRef mRef;
            mRef = new MRef(Stereotype.MQNAME, "1a39bd85-e28f-4e17-b2e9-a1803359cd83", "RequirementsType");
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
