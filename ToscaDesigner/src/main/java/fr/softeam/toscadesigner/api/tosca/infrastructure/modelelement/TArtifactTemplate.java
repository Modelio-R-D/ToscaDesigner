/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ToscaDesigner v0.5.1

 * This file was generated on 29/07/2025 19:13 by Modelio Studio.
 */
package fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement;

import java.util.ArrayList;
import java.util.List;

import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.impl.IMdaProxy;
import fr.softeam.toscadesigner.impl.MdaProxyException;

/**
 * Proxy class to handle a {@link ModelElement} with << TArtifactTemplate >> stereotype.
 * <p>Stereotype description:
 * <br/><i>null</i></p>
 */
@objid ("51fb691c-983c-4331-b7b0-91d7833c0501")
public class TArtifactTemplate extends TEntityTemplate implements IMdaProxy {
    @objid ("69100082-4dd1-4cf1-8b20-d230d6426b3b")
    public static final String STEREOTYPE_NAME = "TArtifactTemplate";

    @objid ("e0380a01-5bbe-42ab-bde1-4aef2c555378")
    public static final String ARTIFACTREFERENCES_PROPERTY = "artifactReferences";

    @objid ("5a4a4836-385b-491e-be87-1413d197ca3c")
    public static final String NAME_PROPERTY = "name";

    /**
     * Tells whether a {@link TArtifactTemplate proxy} can be instantiated from a {@link MObject} checking it is a {@link ModelElement} stereotyped << TArtifactTemplate >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("6d80a4e3-d64e-434f-a481-e0c96db56283")
    public static boolean canInstantiate(final MObject elt) {
        return ((elt instanceof ModelElement) && (TArtifactTemplate.MdaTypes.STEREOTYPE_ELT!=null) && ((ModelElement) elt).isStereotyped(TArtifactTemplate.MdaTypes.STEREOTYPE_ELT));
    }

    /**
     * Create a new {@link ModelElement} stereotyped << TArtifactTemplate >> then instantiate a {@link TArtifactTemplate} proxy.
     * @return a {@link TArtifactTemplate} proxy on the created {@link ModelElement}.
     */
    @objid ("d7ba6adb-d53c-41e7-8369-1004681b301e")
    public static TArtifactTemplate create(final IModelingSession session) {
        ModelElement e = (ModelElement)session.getModel().createElement("Infrastructure.ModelElement");
        e.getExtension().add(TArtifactTemplate.MdaTypes.STEREOTYPE_ELT);
        return TArtifactTemplate.instantiate((ModelElement)e);
    }

    /**
     * Tries to instantiate a {@link TArtifactTemplate} proxy from a {@link ModelElement} stereotyped << TArtifactTemplate >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ModelElement
     * @return a {@link TArtifactTemplate} proxy or <i>null</i>.
     */
    @objid ("a9b74243-d75f-437d-a086-3d458c48de39")
    public static TArtifactTemplate instantiate(final ModelElement obj) {
        return TArtifactTemplate.canInstantiate(obj) ? new TArtifactTemplate(obj) : null;
    }

    /**
     * Tries to instantiate a {@link TArtifactTemplate} proxy from a {@link ModelElement} stereotyped << TArtifactTemplate >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ModelElement}
     * @return a {@link TArtifactTemplate} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("b699aa1d-b088-4f2b-9bbf-9aa450efff42")
    public static TArtifactTemplate safeInstantiate(final ModelElement obj) throws IllegalArgumentException {
        if (TArtifactTemplate.canInstantiate(obj))
        	return new TArtifactTemplate(obj);
        else
        	throw new IllegalArgumentException("TArtifactTemplate: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("43c7e442-29c1-448f-afbf-355d38c255ea")
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
        TArtifactTemplate other = (TArtifactTemplate) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Getter for property 'TArtifactTemplate.artifactReferences'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("36302870-f0c2-4e49-b90e-eb78df19aeee")
    public String getArtifactReferences() {
        String value = this.elt.getProperty(TArtifactTemplate.MdaTypes.STEREOTYPE_ELT,
                                            TArtifactTemplate.MdaTypes.ARTIFACTREFERENCES_PROPERTY_ELT.getName());
        if (value == null) {
          value = TArtifactTemplate.MdaTypes.ARTIFACTREFERENCES_PROPERTY_ELT.getDefaultValue();
        }
        return (String) PropertyConverter.convertToObject(TArtifactTemplate.MdaTypes.ARTIFACTREFERENCES_PROPERTY_ELT, value, this.elt);
        
    }

    /**
     * Get the underlying {@link ModelElement}.
     * @return the ModelElement represented by this proxy, never null.
     */
    @objid ("aa49a5fe-9e03-40c5-be6d-5b980c4b28f0")
    @Override
    public ModelElement getElement() {
        return (ModelElement)super.getElement();
    }

    /**
     * Getter for property 'TArtifactTemplate.name'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("be1046ac-9558-483f-82d6-782cab81f125")
    public String getName() {
        String value = this.elt.getProperty(TArtifactTemplate.MdaTypes.STEREOTYPE_ELT,
                                            TArtifactTemplate.MdaTypes.NAME_PROPERTY_ELT.getName());
        if (value == null) {
          value = TArtifactTemplate.MdaTypes.NAME_PROPERTY_ELT.getDefaultValue();
        }
        return (String) PropertyConverter.convertToObject(TArtifactTemplate.MdaTypes.NAME_PROPERTY_ELT, value, this.elt);
        
    }

    @objid ("712f4b62-b93c-4def-acc9-7844f95e318b")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for property 'TArtifactTemplate.artifactReferences'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("16eff088-66e8-4d77-aaec-bc374423d4ef")
    public void setArtifactReferences(final String value) {
        this.elt.setProperty(TArtifactTemplate.MdaTypes.STEREOTYPE_ELT,
                             TArtifactTemplate.MdaTypes.ARTIFACTREFERENCES_PROPERTY_ELT.getName(),
                             PropertyConverter.convertToString(TArtifactTemplate.MdaTypes.ARTIFACTREFERENCES_PROPERTY_ELT, value));
    }

    /**
     * Setter for property 'TArtifactTemplate.name'
     * <p>Property description:
     * <br/><i>null</i></p>
     */
    @objid ("cf8143f5-324f-4cdd-abde-ce90c13703e7")
    public void setName(final String value) {
        this.elt.setProperty(TArtifactTemplate.MdaTypes.STEREOTYPE_ELT,
                             TArtifactTemplate.MdaTypes.NAME_PROPERTY_ELT.getName(),
                             PropertyConverter.convertToString(TArtifactTemplate.MdaTypes.NAME_PROPERTY_ELT, value));
    }

    @objid ("a436902e-bfbe-4fcb-a351-1e8a2d1c4a5a")
    protected  TArtifactTemplate(final ModelElement elt) {
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
    @objid ("7af8036e-c5d0-4e6b-b182-1bdbb66a7882")
    public static final class MdaTypes {
        @objid ("20e405c5-663b-4013-96ef-b3778acbfd0e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("efd96b51-5d12-4327-aa40-bf0874af6229")
        public static PropertyDefinition NAME_PROPERTY_ELT;

        @objid ("a7da7b89-6576-49d3-86f0-a51e7ed8e6a4")
        public static PropertyDefinition ARTIFACTREFERENCES_PROPERTY_ELT;

        @objid ("f4bdcf64-85e9-42a1-b3de-43f9501d18b4")
        private static Stereotype MDAASSOCDEP;

        @objid ("264749a6-4bd5-48fc-84aa-e942b9f2da48")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("84f782a1-3ce3-495e-899e-e047c476a94c")
        public static void init(final IModelingSession session) throws MdaProxyException {
            List <MRef> missingRefs = new ArrayList<>();
            MRef mRef;
            mRef = new MRef(Stereotype.MQNAME, "1d06ae6d-75d2-4000-b80d-44d93145c847", "TArtifactTemplate");
            STEREOTYPE_ELT = (Stereotype) session.findByRef(mRef);
            if (STEREOTYPE_ELT==null) missingRefs.add(mRef);

            mRef = new MRef(PropertyDefinition.MQNAME, "860fced7-c742-4bd3-9210-90c4c2f1654d", "name");
            NAME_PROPERTY_ELT = (PropertyDefinition) session.findByRef(mRef);
            if (NAME_PROPERTY_ELT==null) missingRefs.add(mRef);

            mRef = new MRef(PropertyDefinition.MQNAME, "a02abde8-5b35-4be5-9d81-91cf765d9991", "artifactReferences");
            ARTIFACTREFERENCES_PROPERTY_ELT = (PropertyDefinition) session.findByRef(mRef);
            if (ARTIFACTREFERENCES_PROPERTY_ELT==null) missingRefs.add(mRef);

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
