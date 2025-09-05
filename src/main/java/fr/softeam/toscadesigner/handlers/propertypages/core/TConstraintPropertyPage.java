package fr.softeam.toscadesigner.handlers.propertypages.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.TConstraint;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;

@objid ("21a87971-cf63-4516-a337-d458781e7e56")
public class TConstraintPropertyPage<T extends TConstraint> extends ToscaElementPropertyPage<T> {
    @objid ("fea16feb-5bf4-4d01-99c6-f54c5ced071e")
    public  TConstraintPropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("7e14926e-f4ff-44d4-97f8-fde393f3e637")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {
        case 1:
            this._element.getElement().setName(value);
            break;

        case 2:
            this._element.setConstraintType(value);
            break;
        }
    }

    @objid ("d7b88888-751a-458b-8868-f67635515510")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
        table.addProperty("Name", _element.getElement().getName());
        table.addProperty("Type", _element.getConstraintType());
    }

}
