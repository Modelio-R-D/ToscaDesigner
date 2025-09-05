/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ToscaDesigner v0.5.1

 * This file was generated on 29/07/2025 19:13 by Modelio Studio.
 */
package fr.softeam.toscadesigner.api.tosca.standard.class_;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ModelingSessionRegistry;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.api.ToscaDesignerProxyFactory;
import fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.TOperation;
import fr.softeam.toscadesigner.api.tosca.standard.attribute.TPropertyDef;
import fr.softeam.toscadesigner.impl.IMdaProxy;
import fr.softeam.toscadesigner.impl.MdaProxyException;

/**
 * Proxy class to handle a {@link Class} with << TInterface >> stereotype.
 * <p>Stereotype description:
 * <br/><i>null</i></p>
 */
@objid ("d25c1250-12da-451d-9068-8f7df07870b5")
public class TInterface extends TEntityType implements IMdaProxy {
    @objid ("fe27bce0-b286-41b9-bc0b-7528e9897225")
    public static final String STEREOTYPE_NAME = "TInterface";

    @objid ("6425a37f-5fe1-47c8-a14b-10a60bc4a46e")
    public static final String NOTIFICATIONS_PROPERTY = "notifications";

    /**
     * Tells whether a {@link TInterface proxy} can be instantiated from a {@link MObject} checking it is a {@link Class} stereotyped << TInterface >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("1f3c3f76-b52b-4d68-be9f-144253020cc7")
    public static boolean canInstantiate(final MObject elt) {
        return ((elt instanceof Class) && (TInterface.MdaTypes.STEREOTYPE_ELT!=null) && ((Class) elt).isStereotyped(TInterface.MdaTypes.STEREOTYPE_ELT));
    }

    /**
     * Create a new {@link Class} stereotyped << TInterface >> then instantiate a {@link TInterface} proxy.
     * @return a {@link TInterface} proxy on the created {@link Class}.
     */
    @objid ("018259fb-bc02-4159-9d3d-16f888abe57a")
    public static TInterface create(final IModelingSession session) {
        ModelElement e = (ModelElement)session.getModel().createElement("Standard.Class");
        e.getExtension().add(TInterface.MdaTypes.STEREOTYPE_ELT);
        return TInterface.instantiate((Class)e);
    }

    /**
     * Tries to instantiate a {@link TInterface} proxy from a {@link Class} stereotyped << TInterface >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Class
     * @return a {@link TInterface} proxy or <i>null</i>.
     */
    @objid ("33d60680-0a22-4b4a-8e49-51d7bfc0c37c")
    public static TInterface instantiate(final Class obj) {
        return TInterface.canInstantiate(obj) ? new TInterface(obj) : null;
    }

    /**
     * Tries to instantiate a {@link TInterface} proxy from a {@link Class} stereotyped << TInterface >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Class}
     * @return a {@link TInterface} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("156c0191-6610-40e9-b611-847d5a292dcd")
    public static TInterface safeInstantiate(final Class obj) throws IllegalArgumentException {
        if (TInterface.canInstantiate(obj))
        	return new TInterface(obj);
        else
        	throw new IllegalArgumentException("TInterface: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    /**
     * Add a value to the 'inputs' role.<p>
     * Role description:
     * null
     * 
     */
    @objid ("5ad0df49-1478-45cb-ab93-618439c0bf1b")
    public void addInputs(final TPropertyDef obj) {
        if (obj != null) {
            IModelingSession session = ModelingSessionRegistry.getSession(this.elt);
            Dependency d = session.getModel().createDependency(this.elt, obj.getElement(), TInterface.MdaTypes.MDAASSOCDEP);
            d.setName("inputs");
            d.putTagValue(TInterface.MdaTypes.MDAASSOCDEP_ROLE, "inputs");
        }
    }

    /**
     * Add a value to the 'operation' role.<p>
     * Role description:
     * null
     * 
     */
    @objid ("b545357d-54cb-4f3a-a691-558a150f7f31")
    public void addOperation(final TOperation obj) {
        if (obj != null) {
            IModelingSession session = ModelingSessionRegistry.getSession(this.elt);
            Dependency d = session.getModel().createDependency(this.elt, obj.getElement(), TInterface.MdaTypes.MDAASSOCDEP);
            d.setName("operation");
            d.putTagValue(TInterface.MdaTypes.MDAASSOCDEP_ROLE, "operation");
        }
    }

    @objid ("9317f7b7-aaa8-4cc2-a74d-35a6e1acfe5e")
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
        TInterface other = (TInterface) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Class}.
     * @return the Class represented by this proxy, never null.
     */
    @objid ("3f310bb7-3bfd-4b92-9f41-5e2b15c91649")
    @Override
    public Class getElement() {
        return (Class)super.getElement();
    }

    /**
     * Get the values of the 'inputs' role.<p>
     * Role description:
     * null
     * 
     */
    @objid ("80512a17-ad14-4fbf-a3d6-2b485e13563f")
    public List<TPropertyDef> getInputs() {
        List<TPropertyDef> results = new ArrayList<>();
        for (Dependency d : this.elt.getDependsOnDependency()) {
          if (d.isStereotyped(TInterface.MdaTypes.MDAASSOCDEP)
              && Objects.equals(d.getTagValue(TInterface.MdaTypes.MDAASSOCDEP_ROLE), "inputs")
              && TPropertyDef.canInstantiate(d.getDependsOn()))
                results.add((TPropertyDef)ToscaDesignerProxyFactory.instantiate(d.getDependsOn(), TPropertyDef.MdaTypes.STEREOTYPE_ELT.getName()));
        }
        return Collections.unmodifiableList(results);
    }

    /**
     * Getter for property 'TInterface.notifications'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("034a7961-982a-4da0-bbcd-3710976438fc")
    public String getNotifications() {
        String value = this.elt.getProperty(TInterface.MdaTypes.STEREOTYPE_ELT,
                                            TInterface.MdaTypes.NOTIFICATIONS_PROPERTY_ELT.getName());
        if (value == null) {
          value = TInterface.MdaTypes.NOTIFICATIONS_PROPERTY_ELT.getDefaultValue();
        }
        return (String) PropertyConverter.convertToObject(TInterface.MdaTypes.NOTIFICATIONS_PROPERTY_ELT, value, this.elt);
        
    }

    /**
     * Get the values of the 'operation' role.<p>
     * Role description:
     * null
     * 
     */
    @objid ("93d70a10-adc7-4ee0-b554-9868f1548475")
    public List<TOperation> getOperation() {
        List<TOperation> results = new ArrayList<>();
        for (Dependency d : this.elt.getDependsOnDependency()) {
          if (d.isStereotyped(TInterface.MdaTypes.MDAASSOCDEP)
              && Objects.equals(d.getTagValue(TInterface.MdaTypes.MDAASSOCDEP_ROLE), "operation")
              && TOperation.canInstantiate(d.getDependsOn()))
                results.add((TOperation)ToscaDesignerProxyFactory.instantiate(d.getDependsOn(), TOperation.MdaTypes.STEREOTYPE_ELT.getName()));
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("d7c9514e-5719-4b96-b3af-918fd959204c")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Remove a value from the 'inputs' role.<p>
     * Role description:
     * null
     * 
     */
    @objid ("aec37f0b-e86f-43b7-b57b-f0a10ea4717b")
    public boolean removeInputs(final TPropertyDef obj) {
        if (obj != null) {
          for (Dependency d : new ArrayList<>(this.elt.getDependsOnDependency())) {
            if (d.isStereotyped(TInterface.MdaTypes.MDAASSOCDEP) && Objects.equals(d.getTagValue(TInterface.MdaTypes.MDAASSOCDEP_ROLE), "inputs")) 
              if (Objects.equals(d.getDependsOn(), obj.getElement())) {
                d.delete();
                return true;
              }
          }
        }
        return false;
        
    }

    /**
     * Remove a value from the 'operation' role.<p>
     * Role description:
     * null
     * 
     */
    @objid ("be9ec561-a5f9-48b8-8c2c-3c4691015325")
    public boolean removeOperation(final TOperation obj) {
        if (obj != null) {
          for (Dependency d : new ArrayList<>(this.elt.getDependsOnDependency())) {
            if (d.isStereotyped(TInterface.MdaTypes.MDAASSOCDEP) && Objects.equals(d.getTagValue(TInterface.MdaTypes.MDAASSOCDEP_ROLE), "operation")) 
              if (Objects.equals(d.getDependsOn(), obj.getElement())) {
                d.delete();
                return true;
              }
          }
        }
        return false;
        
    }

    /**
     * Setter for property 'TInterface.notifications'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("0f3f7bc0-4955-48c3-9a6c-610ae5ff37d7")
    public void setNotifications(final String value) {
        this.elt.setProperty(TInterface.MdaTypes.STEREOTYPE_ELT,
                             TInterface.MdaTypes.NOTIFICATIONS_PROPERTY_ELT.getName(),
                             PropertyConverter.convertToString(TInterface.MdaTypes.NOTIFICATIONS_PROPERTY_ELT, value));
    }

    @objid ("0bd2c418-1fb8-4966-92da-2ff377f1b947")
    protected  TInterface(final Class elt) {
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
    @objid ("fec61bb7-ebc6-4c72-855c-e26017f81467")
    public static final class MdaTypes {
        @objid ("39a3be9a-c1b7-4855-8420-42f21e910de1")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("de335034-73ad-45f1-b715-f930365b5e0b")
        public static PropertyDefinition NOTIFICATIONS_PROPERTY_ELT;

        @objid ("8a958d27-5fc0-46e5-b727-b6560fff6b92")
        private static Stereotype MDAASSOCDEP;

        @objid ("69ca364b-8fd2-4217-b6b4-37dee4710cfb")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c75560f3-e456-4c44-bab4-b0762f4f9ce0")
        public static void init(final IModelingSession session) throws MdaProxyException {
            List <MRef> missingRefs = new ArrayList<>();
            MRef mRef;
            mRef = new MRef(Stereotype.MQNAME, "7a56a78f-21b0-463d-b927-b9a3a461b565", "TInterface");
            STEREOTYPE_ELT = (Stereotype) session.findByRef(mRef);
            if (STEREOTYPE_ELT==null) missingRefs.add(mRef);

            mRef = new MRef(PropertyDefinition.MQNAME, "1c86cfbe-dda6-4b27-b5aa-59b0433d0603", "notifications");
            NOTIFICATIONS_PROPERTY_ELT = (PropertyDefinition) session.findByRef(mRef);
            if (NOTIFICATIONS_PROPERTY_ELT==null) missingRefs.add(mRef);

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
