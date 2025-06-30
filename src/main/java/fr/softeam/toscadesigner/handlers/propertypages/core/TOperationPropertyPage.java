package fr.softeam.toscadesigner.handlers.propertypages.core;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;

import fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.TOperation;

public class TOperationPropertyPage <T extends TOperation> extends ToscaElementPropertyPage<T> {

	public TOperationPropertyPage(T elt) {
		super(elt);
		// TODO Auto-generated constructor stub
	}

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
