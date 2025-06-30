package fr.softeam.toscadesigner.handlers.propertypages.topologyTemplate;

import java.util.Arrays;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.Metamodel;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.RelationshipConstraintsType;
import fr.softeam.toscadesigner.api.tosca.standard.association.TRelationshipTemplate;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TRelationshipType;
import fr.softeam.toscadesigner.handlers.propertypages.core.ToscaElementPropertyPage;

@objid ("1435e247-20e4-48c4-ba91-7f5a45f1ebfb")
public class TRelationshipTemplatePropertyPage<T extends TRelationshipTemplate> extends ToscaElementPropertyPage<T> {
    @objid ("0ad60ab9-27e2-40e1-bcd0-536d895b7970")
    public  TRelationshipTemplatePropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("8877949b-daf5-493a-bb8a-8099282f95b3")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {
        case 1:
            this._element.getElement().setName(value);
            break;
        
        case 2:
            for (ModelElement elt : TRelationshipType.MdaTypes.STEREOTYPE_ELT.getExtendedElement()) {
                if (value.contains(elt.getUuid())) {
                    this._element.setRelationshipType(
                            TRelationshipType.instantiate((org.modelio.metamodel.uml.statik.Class) elt));
                    break;
                } else {
                    this._element.setRelationshipType(null);
                }
            }
            break;
        
        case 3:
            for (ModelElement elt : RelationshipConstraintsType.MdaTypes.STEREOTYPE_ELT.getExtendedElement()) {
                if (value.contains(elt.getUuid())) {
                    this._element.setRelationshipConstraints(
                            RelationshipConstraintsType.instantiate((org.modelio.metamodel.uml.statik.Class) elt));
                    break;
                } else {
                    this._element.setRelationshipConstraints(null);
                }
            }
            break;
        
        }
    }

    @objid ("bd936b8f-d7d7-4412-9177-d4344b303aee")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
        
        table.addProperty("Name", _element.getElement().getName());
        
        // Type
        table.addProperty("Type",
                this._element.getRelationshipType() != null ? this._element.getRelationshipType().getElement() : null,
                Arrays.asList(Metamodel.getMClass("Class")), new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return TRelationshipType.canInstantiate(element);
                    }
                });
        
        // Constraints
        table.addProperty("Constraints",
                this._element.getRelationshipConstraints() != null
                        ? this._element.getRelationshipConstraints().getElement()
                        : null,
                Arrays.asList(Metamodel.getMClass("Class")), new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return RelationshipConstraintsType.canInstantiate(element);
                    }
                });
    }

}
