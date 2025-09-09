package fr.softeam.toscadesigner.handlers.propertypages.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.TParameter;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;

@objid ("218bb0cf-70cb-4b32-b1e3-afbc5284f376")
public class TParameterPropertyPage<T extends TParameter> extends ToscaElementPropertyPage<T> {
    @objid ("1a709244-a269-4c4c-b438-cba007676228")
    public  TParameterPropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("11ebe073-bfc7-4554-9e3c-4f6ebe9a406a")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {

        case 1:
            this._element.getElement().setName(value);
            break;
        case 2:
            this._element.setType(value);
            break;
        case 3:
            this._element.setDescription(value);
            break;
        case 4:
            this._element.setValue(value);
            break;
        case 5:
            this._element.setRequired(Boolean.valueOf(value));
            break;
        case 6:
            this._element.setDefault(value);
            break;
        case 7:
            this._element.setStatus(value);
            break;
        case 8:
            this._element.setConstraints(value);
            break;


        }
    }

    @objid ("f5d702a2-a894-4f63-b64c-cbf8edecd704")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
        table.addProperty("Name", _element.getElement().getName());
        table.addProperty("Type", _element.getType());
        table.addProperty("Description", _element.getDescription());
        table.addProperty("Value", _element.getValue());
        table.addProperty("Required", _element.getRequired());
        table.addProperty("Default", _element.getDefault());
        table.addProperty("Status", _element.getStatus());
        table.addProperty("Constraints", _element.getConstraints());
    }

}
