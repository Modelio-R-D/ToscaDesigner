package fr.softeam.toscadesigner.handlers.propertypages.serviceTemplate;

import java.util.Arrays;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TCapabilityType;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeType;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TRelationshipType;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TRequirementDefinition;
import fr.softeam.toscadesigner.handlers.propertypages.core.ToscaElementPropertyPage;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.Metamodel;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("af53e6c8-17f0-414d-8253-f083796c9a36")
public class TRequirementDefinitionPropertyPage<T extends TRequirementDefinition> extends ToscaElementPropertyPage<T> {
    @objid ("65de60bc-e2c1-4680-b2ce-73267128939d")
    public  TRequirementDefinitionPropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("9fb19480-215a-4160-9d7d-cc716c2c02e2")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {
        case 1:
            this._element.getElement().setName(value);
            break;

        case 2:
            for (ModelElement dep : TCapabilityType.MdaTypes.STEREOTYPE_ELT.getExtendedElement()) {
                if (value.contains(dep.getUuid())) {
                    this._element
                            .setCapability(TCapabilityType.instantiate((org.modelio.metamodel.uml.statik.Class) dep));
        break;
        } else {
        this._element.setCapability(null);
        }

            }
            break;

        case 3:
            for (ModelElement dep : TNodeType.MdaTypes.STEREOTYPE_ELT.getExtendedElement()) {
                if (value.contains(dep.getUuid())) {
                    this._element.setNodeType(TNodeType.instantiate((org.modelio.metamodel.uml.statik.Class) dep));
        break;
        } else {
        this._element.setNodeType(null);
        }

            }
            break;

        case 4:
            for (ModelElement dep : TRelationshipType.MdaTypes.STEREOTYPE_ELT.getExtendedElement()) {
                if (value.contains(dep.getUuid())) {
                    this._element.setRelationshipType(
                            TRelationshipType.instantiate((org.modelio.metamodel.uml.statik.Class) dep));
        break;
        } else {
        this._element.setRelationshipType(null);
        }

            }
            break;
        case 5:
            this._element.setUpperBound(value);
            break;

        case 6:
            this._element.setLowerBound(value);
            break;

        }
    }

    @objid ("fc5eb528-5bfa-41d7-8e81-2cbc64d35373")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
        table.addProperty("Name", _element.getElement().getName());
        // Capability
        table.addProperty("Capability",
                this._element.getCapability() != null ? this._element.getCapability().getElement() : null,
                Arrays.asList(Metamodel.getMClass("Class")), new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return TCapabilityType.canInstantiate(element);
                    }
                });

        // Node
        table.addProperty("Node", this._element.getNodeType() != null ? this._element.getNodeType().getElement() : null,
                Arrays.asList(Metamodel.getMClass("Class")), new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return TNodeType.canInstantiate(element);
                    }
                });

        // Relationship type
        table.addProperty("Relationship type",
                this._element.getRelationshipType() != null ? this._element.getRelationshipType().getElement() : null,
                Arrays.asList(Metamodel.getMClass("Class")), new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return TRelationshipType.canInstantiate(element);
                    }
                });

        table.addProperty("Upper Bound", _element.getUpperBound());
        table.addProperty("Lower Bound", _element.getLowerBound());
    }

}
