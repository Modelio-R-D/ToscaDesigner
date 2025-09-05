package fr.softeam.toscadesigner.handlers.propertypages;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.softeam.toscadesigner.api.IToscaDesignerPeerModule;
import fr.softeam.toscadesigner.handlers.propertypages.core.CapabilitiesTypePropertyPage;
import fr.softeam.toscadesigner.handlers.propertypages.core.MetadataPropertyPage;
import fr.softeam.toscadesigner.handlers.propertypages.core.PropertyDefinitionTypePropertyPage;
import fr.softeam.toscadesigner.handlers.propertypages.core.TCapabilityPropertyPage;
import fr.softeam.toscadesigner.handlers.propertypages.core.TConstraintPropertyPage;
import fr.softeam.toscadesigner.handlers.propertypages.core.TGroupPropertyPage;
import fr.softeam.toscadesigner.handlers.propertypages.core.TGroupTypePropertyPage;
import fr.softeam.toscadesigner.handlers.propertypages.core.TInterfacePropertyPage;
import fr.softeam.toscadesigner.handlers.propertypages.core.TOperationPropertyPage;
import fr.softeam.toscadesigner.handlers.propertypages.core.TParameterPropertyPage;
import fr.softeam.toscadesigner.handlers.propertypages.core.TPolicyPropertyPage;
import fr.softeam.toscadesigner.handlers.propertypages.core.TPolicyTypePropertyPage;
import fr.softeam.toscadesigner.handlers.propertypages.core.TPropertyDefPropertyPage;
import fr.softeam.toscadesigner.handlers.propertypages.core.TriggerPropertyPage;
import fr.softeam.toscadesigner.handlers.propertypages.serviceTemplate.TArtifactTemplatePropertyPage;
import fr.softeam.toscadesigner.handlers.propertypages.serviceTemplate.TCapabilityDefinitionPropertyPage;
import fr.softeam.toscadesigner.handlers.propertypages.serviceTemplate.TCapabilityDefinitionsTypePropertyPage;
import fr.softeam.toscadesigner.handlers.propertypages.serviceTemplate.TNodeTypePropertyPage;
import fr.softeam.toscadesigner.handlers.propertypages.serviceTemplate.TRelationshipTypePropertyPage;
import fr.softeam.toscadesigner.handlers.propertypages.serviceTemplate.TRequirementDefinitionPropertyPage;
import fr.softeam.toscadesigner.handlers.propertypages.serviceTemplate.TRequirementDefinitionTypePropertyPage;
import fr.softeam.toscadesigner.handlers.propertypages.serviceTemplate.TServiceTemplatePropertyPage;
import fr.softeam.toscadesigner.handlers.propertypages.topologyTemplate.RequirementsTypePropertyPage;
import fr.softeam.toscadesigner.handlers.propertypages.topologyTemplate.TDeploymentArtifactPropertyPage;
import fr.softeam.toscadesigner.handlers.propertypages.topologyTemplate.TDeploymentArtifactsPropertyPage;
import fr.softeam.toscadesigner.handlers.propertypages.topologyTemplate.TNodeTemplatePropertyPage;
import fr.softeam.toscadesigner.handlers.propertypages.topologyTemplate.TRequirementPropertyPage;
import fr.softeam.toscadesigner.handlers.propertypages.topologyTemplate.TTopologyTemplatePropertyPage;
import org.modelio.metamodel.diagrams.ClassDiagram;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.Connector;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.visitors.IDefaultInfrastructureVisitor;
import org.modelio.metamodel.visitors.IDefaultModelVisitor;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;

@objid ("ea73d233-1e53-40f0-bdca-70d3b6668b02")
public class ToscaDesignerPropertyPageFactory {
    @objid ("0a1e4821-1cae-4e25-bce1-946d9094be54")
    private static final PropertyPageVisitor propertyVisitor = new PropertyPageVisitor();

    /**
     * Instantiates the right proxy class the given element. <br/>
     * The model element must be stereotyped by a 'CamelDesigner' module stereotype.
     * <br/>
     * In the other case the method will return <i>null</i>.
     * @param e A model element
     * @return the right proxy or <i>null</i>.
     */
    @objid ("48a9c8ae-edbb-4a97-9d7b-e3f4af761cae")
    public static final Object instantiatePropertyPage(final ModelElement e) {
        for (Stereotype s : e.getExtension()) {
            ModuleComponent module = s.getModule();
            if (module != null && module.getName().equals(IToscaDesignerPeerModule.MODULE_NAME)) {
                return instantiatePropertyPage(e, s.getName());
            }
        }
        return null;
    }

    /**
     * Instantiates the right proxy class the given element with a stereotype name.
     * The stereotype must be one of the 'CamelDesigner' module stereotypes. In the
     * other case the method will return <i>null</i>.
     * @param e A model element.
     * @param stName A stereotype name.
     * @return the right proxy or <i>null</i>.
     */
    @objid ("2460de5e-18de-46e6-9f5b-28ab12b772fc")
    public static final Object instantiatePropertyPage(final Element e, final String stName) {
        propertyVisitor.setStereotype(stName);
        return e.accept(propertyVisitor);
    }

    /*
     * WARNING: GENERATED FILE - DO NOT EDIT
     * Module: CamelDesigner v0.1.06

     * This file was generated on 11/30/20 8:06 PM by Modelio Studio.
     */
    @objid ("ed8ccd4c-3561-483a-a4bf-b120aeff9d4f")
    private static class PropertyPageVisitor implements IDefaultModelVisitor, IDefaultInfrastructureVisitor {
        @objid ("86d26d25-e6f3-4894-80fa-dee36d18ecb0")
        private String stName;

        @objid ("abe7f2a9-74c8-40be-9236-c082b8153d67")
        @Override
        public final Object visitGeneralClass(GeneralClass obj) {
            switch (this.stName) {
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitGeneralClass(obj);
        }

        @objid ("d15450f1-b618-4080-bb9d-cb4a203ec9d1")
        @Override
        public final Object visitDependency(Dependency obj) {
            switch (this.stName) {
            default:
                break;
            }
            return IDefaultInfrastructureVisitor.super.visitDependency(obj);
        }

        @objid ("dc04dbd4-1acf-45fd-aa17-f8bfd27ff835")
        @Override
        public final Object visitComponent(Component obj) {
            switch (this.stName) {
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitComponent(obj);
        }

        @objid ("adcdf055-876d-41b4-b264-c19cfc61370a")
        @Override
        public final Object visitInstance(Instance obj) {
            switch (this.stName) {
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitInstance(obj);
        }

        @objid ("a0574278-4bfe-4e5a-90da-0f4d0457f7cc")
        @Override
        public final Object visitClass(Class obj) {
            switch (this.stName) {
            case fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeType.STEREOTYPE_NAME:
                return new TNodeTypePropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeType.instantiate(obj));
            case fr.softeam.toscadesigner.api.tosca.standard.class_.TRelationshipType.STEREOTYPE_NAME:
                return new TRelationshipTypePropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.standard.class_.TRelationshipType.instantiate(obj));
            case fr.softeam.toscadesigner.api.tosca.standard.class_.TDeploymentArtifacts.STEREOTYPE_NAME:
                return new TDeploymentArtifactsPropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.standard.class_.TDeploymentArtifacts.instantiate(obj));
            case fr.softeam.toscadesigner.api.tosca.standard.class_.RequirementsType.STEREOTYPE_NAME:
                return new RequirementsTypePropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.standard.class_.RequirementsType.instantiate(obj));
            case fr.softeam.toscadesigner.api.tosca.standard.class_.TCapabilityDefinitionsType.STEREOTYPE_NAME:
                return new TCapabilityDefinitionsTypePropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.standard.class_.TCapabilityDefinitionsType.instantiate(obj));
            case fr.softeam.toscadesigner.api.tosca.standard.class_.TRequirementDefinitionType.STEREOTYPE_NAME:
                return new TRequirementDefinitionTypePropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.standard.class_.TRequirementDefinitionType.instantiate(obj));
            case fr.softeam.toscadesigner.api.tosca.standard.class_.TGroupType.STEREOTYPE_NAME:
                return new TGroupTypePropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.standard.class_.TGroupType.instantiate(obj));
            case fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeTemplate.STEREOTYPE_NAME:
                return new TNodeTemplatePropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeTemplate.instantiate(obj));
            case fr.softeam.toscadesigner.api.tosca.standard.class_.TPolicyType.STEREOTYPE_NAME:
                return new TPolicyTypePropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.standard.class_.TPolicyType.instantiate(obj));
            case fr.softeam.toscadesigner.api.tosca.standard.class_.TPolicy.STEREOTYPE_NAME:
                return new TPolicyPropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.standard.class_.TPolicy.instantiate(obj));
            case fr.softeam.toscadesigner.api.tosca.standard.class_.Trigger.STEREOTYPE_NAME:
                return new TriggerPropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.standard.class_.Trigger.instantiate(obj));
            case fr.softeam.toscadesigner.api.tosca.standard.class_.TRequirement.STEREOTYPE_NAME:
                return new TRequirementPropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.standard.class_.TRequirement.instantiate(obj));
            case fr.softeam.toscadesigner.api.tosca.standard.class_.TRequirementDefinition.STEREOTYPE_NAME:
                return new TRequirementDefinitionPropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.standard.class_.TRequirementDefinition.instantiate(obj));
            case fr.softeam.toscadesigner.api.tosca.standard.class_.TCapabilityDefinition.STEREOTYPE_NAME:
                return new TCapabilityDefinitionPropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.standard.class_.TCapabilityDefinition.instantiate(obj));
            case fr.softeam.toscadesigner.api.tosca.standard.class_.TCapability.STEREOTYPE_NAME:
                return new TCapabilityPropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.standard.class_.TCapability.instantiate(obj));
            case fr.softeam.toscadesigner.api.tosca.standard.class_.TDeploymentArtifact.STEREOTYPE_NAME:
                return new TDeploymentArtifactPropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.standard.class_.TDeploymentArtifact.instantiate(obj));
            case fr.softeam.toscadesigner.api.tosca.standard.class_.TTopologyTemplate.STEREOTYPE_NAME:
                return new TTopologyTemplatePropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.standard.class_.TTopologyTemplate.instantiate(obj));
            case fr.softeam.toscadesigner.api.tosca.standard.class_.TInterface.STEREOTYPE_NAME:
                return new TInterfacePropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.standard.class_.TInterface.instantiate(obj));
            case fr.softeam.toscadesigner.api.tosca.standard.class_.TGroup.STEREOTYPE_NAME:
                return new TGroupPropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.standard.class_.TGroup.instantiate(obj));
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitClass(obj);
        }

        @objid ("98cd0453-9c1c-405f-9194-9dabdc222a99")
        @Override
        public final Object visitAssociationEnd(AssociationEnd obj) {
            switch (this.stName) {
            case fr.softeam.toscadesigner.api.tosca.standard.associationend.TRelationshipTemplateEnd.STEREOTYPE_NAME:
                return new fr.softeam.toscadesigner.handlers.propertypages.topologyTemplate.TRelationshipTemplatePropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.standard.association.TRelationshipTemplate
                                .instantiate(obj.getAssociation()));

            default:
                break;
            }
            return IDefaultModelVisitor.super.visitAssociationEnd(obj);
        }

        @objid ("e7e2f584-f58c-4657-a1f8-5ee45d7ad1fc")
        @Override
        public final Object visitEnumeration(Enumeration obj) {
            switch (this.stName) {
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitEnumeration(obj);
        }

        @objid ("5d224d1c-c759-4482-a454-48b43a6ffc91")
        @Override
        public final Object visitModelElement(ModelElement obj) {
            switch (this.stName) {
            case fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.CapabilitiesType.STEREOTYPE_NAME:
                return new CapabilitiesTypePropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.CapabilitiesType
                                .instantiate(obj));
            case fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.TArtifactTemplate.STEREOTYPE_NAME:
                return new TArtifactTemplatePropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.TArtifactTemplate
                                .instantiate(obj));
            case fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.TOperation.STEREOTYPE_NAME:
                return new TOperationPropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.TOperation.instantiate(obj));
            case fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.TParameter.STEREOTYPE_NAME:
                return new TParameterPropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.TParameter.instantiate(obj));
            case fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.TConstraint.STEREOTYPE_NAME:
                return new TConstraintPropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.TConstraint.instantiate(obj));

            default:
                break;
            }
            return IDefaultInfrastructureVisitor.super.visitModelElement(obj);
        }

        @objid ("26cb88eb-51af-4841-af80-7a37ca41f395")
        @Override
        public final Object visitArtifact(Artifact obj) {
            switch (this.stName) {
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitArtifact(obj);
        }

        @objid ("c1a85541-ae98-4039-98d7-600eaaa39c69")
        @Override
        public final Object visitAttribute(Attribute obj) {
            switch (this.stName) {
            case fr.softeam.toscadesigner.api.tosca.standard.attribute.Metadata.STEREOTYPE_NAME:
                return new MetadataPropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.standard.attribute.Metadata.instantiate(obj));
            case fr.softeam.toscadesigner.api.tosca.standard.attribute.TPropertyDef.STEREOTYPE_NAME:
                return new TPropertyDefPropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.standard.attribute.TPropertyDef.instantiate(obj));
            case fr.softeam.toscadesigner.api.tosca.standard.attribute.PropertyDefinitionType.STEREOTYPE_NAME:
                return new PropertyDefinitionTypePropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.standard.attribute.PropertyDefinitionType.instantiate(obj));

            default:
                break;
            }
            return IDefaultModelVisitor.super.visitAttribute(obj);
        }

        @objid ("880151f4-5fc5-4c44-8a2d-ecc6a6bdcd4b")
        @Override
        public final Object visitAttributeLink(AttributeLink obj) {
            switch (this.stName) {
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitAttributeLink(obj);
        }

        @objid ("3a442ac7-c1f6-4e9e-bc54-3c3a5e0b61db")
        @Override
        public final Object visitClassDiagram(ClassDiagram obj) {
            switch (this.stName) {
            default:

                break;
            }
            return IDefaultModelVisitor.super.visitClassDiagram(obj);
        }

        @objid ("0d0d5a0a-5808-4fbe-9107-4b5b747de0a1")
        @Override
        public final Object visitPackage(Package obj) {
            switch (this.stName) {
            case fr.softeam.toscadesigner.api.tosca.standard.package_.TServiceTemplate.STEREOTYPE_NAME:
                return new TServiceTemplatePropertyPage<>(
                        fr.softeam.toscadesigner.api.tosca.standard.package_.TServiceTemplate.instantiate(obj));

            default:
                break;
            }
            return IDefaultModelVisitor.super.visitPackage(obj);
        }

        @objid ("8e84e650-afed-471d-bf82-514f1b918e85")
        @Override
        public final Object visitConnector(Connector obj) {
            switch (this.stName) {
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitConnector(obj);
        }

        @objid ("b8c8dd16-80c4-4765-b3db-e41ae1d877ef")
        @Override
        public final Object visitDataType(DataType obj) {
            switch (this.stName) {
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitDataType(obj);
        }

        @objid ("8b657b94-f7fb-41ec-8c86-0a1b7ada0258")
        @Override
        public final Object visitPort(Port obj) {
            switch (this.stName) {
            default:
                break;
            }
            return IDefaultModelVisitor.super.visitPort(obj);
        }

        @objid ("f55f8123-6631-4db9-bd21-9fb6b46c79f7")
        @Override
        public IInfrastructureVisitor getInfrastructureVisitor() {
            return this;
        }

        @objid ("1f1f773c-6849-4498-a1b9-86cd6537d931")
        public final void setStereotype(final String stName) {
            this.stName = stName;
        }

    }

}
