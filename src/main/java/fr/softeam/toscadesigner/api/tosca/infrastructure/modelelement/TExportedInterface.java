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
import fr.softeam.toscadesigner.impl.IMdaProxy;
import fr.softeam.toscadesigner.impl.MdaProxyException;

/**
 * Proxy class to handle a {@link ModelElement} with << TExportedInterface >> stereotype.
 * <p>Stereotype description:
 * <br/><i>null</i></p>
 */
@objid ("d8ad5391-a427-4f93-acc9-a612ed04030c")
public class TExportedInterface implements IMdaProxy {
    @objid ("df0e9019-b516-4e77-beb6-56d5952e3c75")
    public static final String STEREOTYPE_NAME = "TExportedInterface";

    /**
     * The underlying {@link ModelElement} represented by this proxy, never null.
     */
    @objid ("f1df824e-194a-4eb3-84b0-b3310634946f")
    protected final ModelElement elt;

    /**
     * Tells whether a {@link TExportedInterface proxy} can be instantiated from a {@link MObject} checking it is a {@link ModelElement} stereotyped << TExportedInterface >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("26da2a17-3309-4075-ba33-b0f8472403d1")
    public static boolean canInstantiate(final MObject elt) {
        return ((elt instanceof ModelElement) && (TExportedInterface.MdaTypes.STEREOTYPE_ELT!=null) && ((ModelElement) elt).isStereotyped(TExportedInterface.MdaTypes.STEREOTYPE_ELT));
    }

    /**
     * Create a new {@link ModelElement} stereotyped << TExportedInterface >> then instantiate a {@link TExportedInterface} proxy.
     * 
     * @return a {@link TExportedInterface} proxy on the created {@link ModelElement}.
     */
    @objid ("1e9a9472-797a-4f4c-9e4f-74bd4bee6e42")
    public static TExportedInterface create(final IModelingSession session) {
        ModelElement e = (ModelElement)session.getModel().createElement("Infrastructure.ModelElement");
        e.getExtension().add(TExportedInterface.MdaTypes.STEREOTYPE_ELT);
        return TExportedInterface.instantiate((ModelElement)e);
    }

    /**
     * Tries to instantiate a {@link TExportedInterface} proxy from a {@link ModelElement} stereotyped << TExportedInterface >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ModelElement
     * @return a {@link TExportedInterface} proxy or <i>null</i>.
     */
    @objid ("ed17607d-0423-4545-b063-02b0f9b75194")
    public static TExportedInterface instantiate(final ModelElement obj) {
        return TExportedInterface.canInstantiate(obj) ? new TExportedInterface(obj) : null;
    }

    /**
     * Tries to instantiate a {@link TExportedInterface} proxy from a {@link ModelElement} stereotyped << TExportedInterface >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ModelElement}
     * @return a {@link TExportedInterface} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("415ad566-813f-4eca-a8a7-773f3c0095fa")
    public static TExportedInterface safeInstantiate(final ModelElement obj) throws IllegalArgumentException {
        if (TExportedInterface.canInstantiate(obj))
        	return new TExportedInterface(obj);
        else
        	throw new IllegalArgumentException("TExportedInterface: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    /**
     * Add a value to the 'operation' role.<p>
     * Role description:
     * null
     * 
     */
    @objid ("dd004110-7485-4e71-8185-a98878d341d0")
    public void addOperation(final TExportedOperation obj) {
        if (obj != null) {
            IModelingSession session = ModelingSessionRegistry.getSession(this.elt);
            Dependency d = session.getModel().createDependency(this.elt, obj.getElement(), TExportedInterface.MdaTypes.MDAASSOCDEP);
            d.setName("operation");
            d.putTagValue(TExportedInterface.MdaTypes.MDAASSOCDEP_ROLE, "operation");
        }
    }

    @objid ("23f6817a-ad13-4888-8610-1efd46f61ed4")
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
        TExportedInterface other = (TExportedInterface) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ModelElement}. 
     * @return the ModelElement represented by this proxy, never null.
     */
    @objid ("79e609cc-0ad6-417d-a07e-570f6a28993f")
    public ModelElement getElement() {
        return this.elt;
    }

    /**
     * Get the values of the 'operation' role.<p>
     * Role description:
     * null
     * 
     */
    @objid ("e896170c-9f08-445b-ae3f-af46619e3044")
    public List<TExportedOperation> getOperation() {
        List<TExportedOperation> results = new ArrayList<>();
        for (Dependency d : this.elt.getDependsOnDependency()) {
          if (d.isStereotyped(TExportedInterface.MdaTypes.MDAASSOCDEP)
              && Objects.equals(d.getTagValue(TExportedInterface.MdaTypes.MDAASSOCDEP_ROLE), "operation")
              && TExportedOperation.canInstantiate(d.getDependsOn()))
                results.add((TExportedOperation)ToscaDesignerProxyFactory.instantiate(d.getDependsOn(), TExportedOperation.MdaTypes.STEREOTYPE_ELT.getName()));
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("85b9adfc-6f65-4ca6-b0a4-55d402fd99da")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Remove a value from the 'operation' role.<p>
     * Role description:
     * null
     * 
     */
    @objid ("35d2a557-e778-4bae-8682-a97287d94f1a")
    public boolean removeOperation(final TExportedOperation obj) {
        if (obj != null) {
          for (Dependency d : new ArrayList<>(this.elt.getDependsOnDependency())) {
            if (d.isStereotyped(TExportedInterface.MdaTypes.MDAASSOCDEP) && Objects.equals(d.getTagValue(TExportedInterface.MdaTypes.MDAASSOCDEP_ROLE), "")) 
              if (Objects.equals(d.getDependsOn(), obj.getElement())) {
                d.delete();
                return true;
              }
          }
        }
        return false;
        
    }

    @objid ("1af45c7d-e347-4cb8-adf7-09048462b60f")
    protected  TExportedInterface(final ModelElement elt) {
        this.elt = elt;
    }

    @objid ("8cf5f7ad-3847-4b56-9b96-a2fcd4040f29")
    public static final class MdaTypes {
        @objid ("b52b47bc-97b5-42cc-b4eb-f0db19ec7034")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("61636129-6bc8-4c08-a3cd-b1f4843e8564")
        private static Stereotype MDAASSOCDEP;

        @objid ("96c8bc7b-a364-4f86-8704-82dcdd88fd49")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c8da210b-7f8c-42ae-b597-940be3335d5a")
        public static void init(final IModelingSession session) throws MdaProxyException {
            List <MRef> missingRefs = new ArrayList<>();
            MRef mRef;
            mRef = new MRef(Stereotype.MQNAME, "b71c026a-4df3-4c68-8c0e-728486b4744b", "TExportedInterface");
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
