package fr.softeam.toscadesigner.handlers.propertypages.topologyTemplate;

import java.util.Arrays;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.Metamodel;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.api.tosca.standard.association.TRelationshipTemplate;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TCapabilityDefinition;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TCapabilityType;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeTemplate;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TRequirement;
import fr.softeam.toscadesigner.handlers.propertypages.core.ToscaElementPropertyPage;

@objid ("a9b12ce9-c580-4064-a9e1-3759bae79da7")
public class TRequirementPropertyPage<T extends TRequirement> extends ToscaElementPropertyPage<T> {
    @objid ("3537aa95-9aaa-4666-8457-ee805a5210b3")
    public  TRequirementPropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("8e4b1d26-1d05-49c2-8032-9fceee2e25dd")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {
        case 1:
            this._element.getElement().setName(value);
            break;
        case 2:
            for (ModelElement dep : TRelationshipTemplate.MdaTypes.STEREOTYPE_ELT.getExtendedElement()) {
                if (value.contains(dep.getUuid())) {
                    this._element.setRelationship(
                            TRelationshipTemplate.instantiate((org.modelio.metamodel.uml.statik.Association) dep));
        break;
        } else {
        this._element.setRelationship(null);
        }
            }
            break;
        case 3:
            for (ModelElement dep : TNodeTemplate.MdaTypes.STEREOTYPE_ELT.getExtendedElement()) {
                if (value.contains(dep.getUuid())) {
                    this._element.setNode(TNodeTemplate.instantiate((org.modelio.metamodel.uml.statik.Class) dep));
        break;
        } else {
        this._element.setNode(null);
        }
            }
            break;
        case 4:
            boolean conditionMet = false;
            for (ModelElement dep : TCapabilityDefinition.MdaTypes.STEREOTYPE_ELT.getExtendedElement()) {
                if (value.contains(dep.getUuid())) {
                    this._element.setCapability(
                            TCapabilityDefinition.instantiate((org.modelio.metamodel.uml.statik.Class) dep));
                    conditionMet = true;
        break;
        } 
            }
            if (!conditionMet) {
                for (ModelElement dep : TCapabilityType.MdaTypes.STEREOTYPE_ELT.getExtendedElement()) {
                    if (value.contains(dep.getUuid())) {
                        this._element
                                .setCapability(TCapabilityType.instantiate((org.modelio.metamodel.uml.statik.Class) dep));
            break;
        } else {
            this._element.setCapability(null);
        }
                }
            }
        
            break;
        }
    }

    @objid ("b00407f3-e32e-4d66-9967-47f98ca31356")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
        table.addProperty("Name", _element.getElement().getName());
        // relationship template
        table.addProperty("Relationship",
                this._element.getRelationship() != null ? this._element.getRelationship().getElement() : null,
                Arrays.asList(Metamodel.getMClass("Association")), new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return TRelationshipTemplate.canInstantiate(element);
                    }
                });
        // node template
        table.addProperty("Node", this._element.getNode() != null ? this._element.getNode().getElement() : null,
                Arrays.asList(Metamodel.getMClass("Class")), new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return TNodeTemplate.canInstantiate(element);
                    }
                });
        // Capability
        table.addProperty("Capability",
                this._element.getCapability() != null ? this._element.getCapability().getElement() : null,
                Arrays.asList(Metamodel.getMClass("Class")), new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return (TCapabilityDefinition.canInstantiate(element)
                                || TCapabilityType.canInstantiate(element));
                    }
                });
    }

}
