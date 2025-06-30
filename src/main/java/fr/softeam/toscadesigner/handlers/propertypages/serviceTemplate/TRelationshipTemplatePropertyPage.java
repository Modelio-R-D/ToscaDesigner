package fr.softeam.toscadesigner.handlers.propertypages.serviceTemplate;

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

@objid ("0712b188-0d7d-4435-812d-e7fd1660e66d")
public class TRelationshipTemplatePropertyPage<T extends TRelationshipTemplate> extends ToscaElementPropertyPage<T> {
    @objid ("5a745b69-0ce1-4eaf-a1f0-aedf95c348f4")
    public  TRelationshipTemplatePropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("bbb52768-cc76-4eb4-bd3c-4fd373737575")
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

    @objid ("91e712ee-cb40-4ce8-badb-680e7eeb5f49")
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
