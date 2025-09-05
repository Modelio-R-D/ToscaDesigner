/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ToscaDesigner v0.5.1

 * This file was generated on 29/07/2025 19:13 by Modelio Studio.
 */
package fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement;

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
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.api.ToscaDesignerProxyFactory;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TCapability;
import fr.softeam.toscadesigner.impl.IMdaProxy;
import fr.softeam.toscadesigner.impl.MdaProxyException;

/**
 * Proxy class to handle a {@link ModelElement} with << CapabilitiesType >> stereotype.
 * <p>Stereotype description:
 * <br/><i>null</i></p>
 */
@objid ("c3454d71-9723-4617-9e29-b351e6a60f55")
public class CapabilitiesType implements IMdaProxy {
    @objid ("462684e2-ef51-4d6d-8107-ec98a68ff63f")
    public static final String STEREOTYPE_NAME = "CapabilitiesType";

    @objid ("5c0b0412-283d-4e46-a88e-ad4adde75b5e")
    public static final String NAME_PROPERTY = "name";

    /**
     * The underlying {@link ModelElement} represented by this proxy, never null.
     */
    @objid ("9e36602c-76e6-4312-8278-746669286cf2")
    protected final ModelElement elt;

    /**
     * Tells whether a {@link CapabilitiesType proxy} can be instantiated from a {@link MObject} checking it is a {@link ModelElement} stereotyped << CapabilitiesType >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("857d3640-c08a-4bf8-ab2d-de01f534ca81")
    public static boolean canInstantiate(final MObject elt) {
        return ((elt instanceof ModelElement) && (CapabilitiesType.MdaTypes.STEREOTYPE_ELT!=null) && ((ModelElement) elt).isStereotyped(CapabilitiesType.MdaTypes.STEREOTYPE_ELT));
    }

    /**
     * Create a new {@link ModelElement} stereotyped << CapabilitiesType >> then instantiate a {@link CapabilitiesType} proxy.
     * @return a {@link CapabilitiesType} proxy on the created {@link ModelElement}.
     */
    @objid ("1777ac97-6164-407e-92b4-4f8ac68db8c3")
    public static CapabilitiesType create(final IModelingSession session) {
        ModelElement e = (ModelElement)session.getModel().createElement("Infrastructure.ModelElement");
        e.getExtension().add(CapabilitiesType.MdaTypes.STEREOTYPE_ELT);
        return CapabilitiesType.instantiate((ModelElement)e);
    }

    /**
     * Tries to instantiate a {@link CapabilitiesType} proxy from a {@link ModelElement} stereotyped << CapabilitiesType >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ModelElement
     * @return a {@link CapabilitiesType} proxy or <i>null</i>.
     */
    @objid ("315584d5-b509-4e66-8ea5-8f340849f672")
    public static CapabilitiesType instantiate(final ModelElement obj) {
        return CapabilitiesType.canInstantiate(obj) ? new CapabilitiesType(obj) : null;
    }

    /**
     * Tries to instantiate a {@link CapabilitiesType} proxy from a {@link ModelElement} stereotyped << CapabilitiesType >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ModelElement}
     * @return a {@link CapabilitiesType} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f6a5b38f-01b3-42f4-b9d7-219b87db3229")
    public static CapabilitiesType safeInstantiate(final ModelElement obj) throws IllegalArgumentException {
        if (CapabilitiesType.canInstantiate(obj))
        	return new CapabilitiesType(obj);
        else
        	throw new IllegalArgumentException("CapabilitiesType: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    /**
     * Add a value of the 'capability' role.<p>
     * Role description:
     * null
     * 
     */
    @objid ("b61348cb-70fb-404b-999c-75f618f9b480")
    public void addCapability(final TCapability obj) {
        if (obj != null) {
            IModelingSession session = ModelingSessionRegistry.getSession(this.elt);
            Dependency d = session.getModel().createDependency(obj.getElement(), this.elt, CapabilitiesType.MdaTypes.MDAASSOCDEP);
            d.setName("");
            d.putTagValue(CapabilitiesType.MdaTypes.MDAASSOCDEP_ROLE, "");
        }
    }

    @objid ("6abc351f-e469-4302-91fa-bc9848062e7b")
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
        CapabilitiesType other = (CapabilitiesType) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the values of the 'capability' role.<p>
     * Role description:
     * null
     * 
     */
    @objid ("a35b18fb-a6fe-4adb-81f0-b5d24bedd725")
    public List<TCapability> getCapability() {
        List<TCapability> results = new ArrayList<>();
        for (Dependency d : this.elt.getImpactedDependency()) {
          if (d.isStereotyped(CapabilitiesType.MdaTypes.MDAASSOCDEP)
              && Objects.equals(d.getTagValue(CapabilitiesType.MdaTypes.MDAASSOCDEP_ROLE), "")
              && TCapability.canInstantiate(d.getImpacted())) {
                 results.add((TCapability)ToscaDesignerProxyFactory.instantiate(d.getImpacted(), TCapability.STEREOTYPE_NAME));
          }
        }
        return Collections.unmodifiableList(results);
    }

    /**
     * Get the underlying {@link ModelElement}.
     * @return the ModelElement represented by this proxy, never null.
     */
    @objid ("fe910d98-1db2-4132-9360-e6a6c79a1b91")
    public ModelElement getElement() {
        return this.elt;
    }

    /**
     * Getter for property 'CapabilitiesType.name'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("6f84d6d1-bc91-48c2-ba95-b4ecced1de93")
    public String getName() {
        String value = this.elt.getProperty(CapabilitiesType.MdaTypes.STEREOTYPE_ELT,
                                            CapabilitiesType.MdaTypes.NAME_PROPERTY_ELT.getName());
        if (value == null) {
          value = CapabilitiesType.MdaTypes.NAME_PROPERTY_ELT.getDefaultValue();
        }
        return (String) PropertyConverter.convertToObject(CapabilitiesType.MdaTypes.NAME_PROPERTY_ELT, value, this.elt);
        
    }

    @objid ("0f9a9603-5c5b-4d09-9232-cffce66f0935")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Remove a value from 'capability' role.<p>
     * Role description:
     * null
     * 
     */
    @objid ("c5b0ba52-71b8-4629-9513-dfb0285d2db8")
    public boolean removeCapability(final TCapability obj) {
        if (obj != null) {
          for (Dependency d : new ArrayList<>(this.elt.getImpactedDependency())) {
            if (d.isStereotyped(CapabilitiesType.MdaTypes.MDAASSOCDEP) && Objects.equals(d.getTagValue(CapabilitiesType.MdaTypes.MDAASSOCDEP_ROLE), ""))
              if (Objects.equals(d.getImpacted(), obj.getElement())) {
                d.delete();
                return true;
              }
          }
        }
        return false;
        
    }

    /**
     * Setter for property 'CapabilitiesType.name'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("f6e3f95d-bb3d-4261-957c-006b9e9b09df")
    public void setName(final String value) {
        this.elt.setProperty(CapabilitiesType.MdaTypes.STEREOTYPE_ELT,
                             CapabilitiesType.MdaTypes.NAME_PROPERTY_ELT.getName(),
                             PropertyConverter.convertToString(CapabilitiesType.MdaTypes.NAME_PROPERTY_ELT, value));
    }

    @objid ("2e92530e-6397-459a-b252-31a9ae57d6c7")
    protected  CapabilitiesType(final ModelElement elt) {
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
    @objid ("24ac4c3b-7621-4e8b-921f-3e1352cb90e4")
    public static final class MdaTypes {
        @objid ("697c181f-0876-4379-93c6-fc54f8360e23")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f5428405-85d5-4a59-a9e1-7e778d2e736b")
        public static PropertyDefinition NAME_PROPERTY_ELT;

        @objid ("99626088-66bf-47fd-a968-88745bb5e9a4")
        private static Stereotype MDAASSOCDEP;

        @objid ("3459fc62-bbfd-4c7e-874f-acc2d835f824")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5b5d9d6e-9ee7-417e-a679-5ff850e3a2f2")
        public static void init(final IModelingSession session) throws MdaProxyException {
            List <MRef> missingRefs = new ArrayList<>();
            MRef mRef;
            mRef = new MRef(Stereotype.MQNAME, "37787f67-5e3f-4ce9-93fc-cc3101d5cb6f", "CapabilitiesType");
            STEREOTYPE_ELT = (Stereotype) session.findByRef(mRef);
            if (STEREOTYPE_ELT==null) missingRefs.add(mRef);

            mRef = new MRef(PropertyDefinition.MQNAME, "e4e6c35d-6cf7-42a3-a06c-d2932cba1eda", "name");
            NAME_PROPERTY_ELT = (PropertyDefinition) session.findByRef(mRef);
            if (NAME_PROPERTY_ELT==null) missingRefs.add(mRef);

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
