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
@objid ("e3bcb1d6-5b60-4ba4-b8eb-702e7619b1b9")
public class SourceInterfacesType implements IMdaProxy {
    @objid ("609f91c8-796b-4c00-807c-4a9a2bf446c3")
    public static final String STEREOTYPE_NAME = "SourceInterfacesType";

    /**
     * The underlying {@link ModelElement} represented by this proxy, never null.
     */
    @objid ("d7110dd4-16d6-4dcd-8234-5ec85d0a8929")
    protected final ModelElement elt;

    /**
     * Tells whether a {@link SourceInterfacesType proxy} can be instantiated from a {@link MObject} checking it is a {@link ModelElement} stereotyped << SourceInterfacesType >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("299470d2-1af1-421d-86fd-cb21d78adc03")
    public static boolean canInstantiate(final MObject elt) {
        return ((elt instanceof ModelElement) && (SourceInterfacesType.MdaTypes.STEREOTYPE_ELT!=null) && ((ModelElement) elt).isStereotyped(SourceInterfacesType.MdaTypes.STEREOTYPE_ELT));
    }

    /**
     * Create a new {@link ModelElement} stereotyped << SourceInterfacesType >> then instantiate a {@link SourceInterfacesType} proxy.
     * @return a {@link SourceInterfacesType} proxy on the created {@link ModelElement}.
     */
    @objid ("7c30c732-d477-49ae-b8a6-a6401af9b13a")
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
    @objid ("ae06b0c5-2862-4627-8107-51c58d55f3bf")
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
    @objid ("331e9495-4b84-43bb-87a7-16a1644d3583")
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
    @objid ("50c07950-78dd-45a0-89e5-0b65e7bab539")
    public void addInterface(final TInterface obj) {
        if (obj != null) {
            IModelingSession session = ModelingSessionRegistry.getSession(this.elt);
            Dependency d = session.getModel().createDependency(this.elt, obj.getElement(), SourceInterfacesType.MdaTypes.MDAASSOCDEP);
            d.setName("interface");
            d.putTagValue(SourceInterfacesType.MdaTypes.MDAASSOCDEP_ROLE, "interface");
        }
    }

    @objid ("1e3810ef-6e2c-4fff-bd50-d0009f1f93d0")
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
    @objid ("5052d10e-9560-48cd-8555-3233a5959e9a")
    public ModelElement getElement() {
        return this.elt;
    }

    /**
     * Get the values of the 'interface' role.<p>
     * Role description:
     * null
     * 
     */
    @objid ("44282f9d-9e5e-4cc9-848c-6505fa46da11")
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

    @objid ("34776f56-b324-417f-9e44-b771bbc17a7b")
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
    @objid ("13fd5a6c-ca0d-4b12-8ba3-f7539b1edbef")
    public boolean removeInterface(final TInterface obj) {
        if (obj != null) {
          for (Dependency d : new ArrayList<>(this.elt.getDependsOnDependency())) {
            if (d.isStereotyped(SourceInterfacesType.MdaTypes.MDAASSOCDEP) && Objects.equals(d.getTagValue(SourceInterfacesType.MdaTypes.MDAASSOCDEP_ROLE), "interface")) 
              if (Objects.equals(d.getDependsOn(), obj.getElement())) {
                d.delete();
                return true;
              }
          }
        }
        return false;
        
    }

    @objid ("7f86990e-9642-46fe-8493-3ccf7a9a956b")
    protected  SourceInterfacesType(final ModelElement elt) {
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
    @objid ("7474002f-2748-4c66-b787-16feb42e5ce8")
    public static final class MdaTypes {
        @objid ("0e25b2f6-242a-44d3-8c3c-1a80f54304fa")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("8ab3b8b2-176d-4b92-985e-4cbc487ccb60")
        private static Stereotype MDAASSOCDEP;

        @objid ("249954e2-6db2-448c-97f2-abf09bd77987")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("895b4c23-b8ff-445d-88b4-823efee68f4f")
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
