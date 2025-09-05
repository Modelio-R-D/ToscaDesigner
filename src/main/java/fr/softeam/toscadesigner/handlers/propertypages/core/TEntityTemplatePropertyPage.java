package fr.softeam.toscadesigner.handlers.propertypages.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.TEntityTemplate;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;

@objid ("c8664bf6-f1ea-40d2-9fd3-65f68ad2bb76")
public abstract class TEntityTemplatePropertyPage<T extends TEntityTemplate> extends ToscaElementPropertyPage<T> {
    @objid ("f9af28a7-a6fa-4586-bd35-2e5a5343defd")
    public  TEntityTemplatePropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("ec432cf9-c209-4eec-865a-0fd57c5fa1ab")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {
        case 1:
            this._element.getElement().setName(value);
            break;

        case 2:
            this._element.setDescription(value);
            break;
        }
    }

    @objid ("f26b830b-eb62-4e10-a121-97c02f9db61d")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
        table.addProperty("Name", _element.getElement().getName());
        table.addProperty("Description", _element.getDescription());
    }

}
