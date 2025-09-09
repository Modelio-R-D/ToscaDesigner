package fr.softeam.toscadesigner.handlers.propertypages.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.TOperation;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;

@objid ("c7a234b2-91af-4a3e-b027-1915d7a76135")
public class TOperationPropertyPage<T extends TOperation> extends ToscaElementPropertyPage<T> {
    @objid ("bc3847e9-114f-4888-865f-fdb4baa4052c")
    public  TOperationPropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("4e095afd-c32a-43a7-83cd-777e354959bf")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {

        case 1:
            this._element.getElement().setName(value);
            break;
        case 2:
            this._element.setDescription(value);
            break;
        case 3:
            this._element.setImplementation(value);
            break;
        case 4:
            this._element.setInputs(value);
            break;
        case 5:
            this._element.setOutputs(value);
            break;
        }
    }

    @objid ("dc649adf-e1f3-4506-8729-40eb27b67a74")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
        table.addProperty("Name", _element.getElement().getName());
        table.addProperty("Description", _element.getDescription());
        table.addProperty("Implementation", _element.getImplementation());
        table.addProperty("Inputs", _element.getInputs());
        table.addProperty("Outputs", _element.getOutputs());
    }

}
