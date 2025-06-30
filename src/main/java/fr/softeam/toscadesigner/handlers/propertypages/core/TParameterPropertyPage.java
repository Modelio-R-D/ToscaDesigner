package fr.softeam.toscadesigner.handlers.propertypages.core;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;

import fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.TParameter;;

public class TParameterPropertyPage<T extends TParameter> extends ToscaElementPropertyPage<T> {

	public TParameterPropertyPage(T elt) {
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
