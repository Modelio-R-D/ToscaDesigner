package fr.softeam.toscadesigner.handlers.propertypages.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.softeam.toscadesigner.api.tosca.standard.attribute.PropertyDefinitionType;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;

@objid ("a3b5d0b2-7de4-4957-810a-595d7066797d")
public class PropertyDefinitionTypePropertyPage<T extends PropertyDefinitionType> extends ToscaElementPropertyPage<T> {
    @objid ("be84f2f9-7fef-457a-8f41-91e7a13e122a")
    public  PropertyDefinitionTypePropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("03c02773-f87c-4fe6-b5ce-d2b9fc76028b")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {
        case 1:
            this._element.getElement().setName(value);
            break;

        case 2:
            //this._element.getElement().setType(TypeFactory.);
            break;
        case 3:
            this._element.setDescription(value);
            break;
        case 4:
            this._element.setRequired(Boolean.valueOf(value));
            break;
        }
    }

    @objid ("302c4f9a-2f86-4efd-bf5f-9b0c90ef2f0a")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
        table.addProperty("Name", _element.getElement().getName());

        table.addProperty("Type", _element.getElement().getValue());
        table.addProperty("Description", _element.getDescription());
        table.addProperty("Required", _element.getRequired());
    }

}
