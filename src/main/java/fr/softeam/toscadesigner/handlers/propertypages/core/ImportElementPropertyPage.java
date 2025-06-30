package fr.softeam.toscadesigner.handlers.propertypages.core;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.ImportElement;

@objid ("2b0ab9ab-3232-4c91-a37c-607fdd1dc98e")
public class ImportElementPropertyPage<T extends ImportElement> extends ToscaElementPropertyPage<T> {
    @objid ("b257f4e3-b3f6-4cc9-81f1-32389eceb267")
    public  ImportElementPropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("3873e117-b78c-4c70-9749-d6e409f3ad6b")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {
        case 1:
            this._element.setFile(value);
            break;
        
        case 2:
            this._element.setNamespace_prefix(value);
            break;
        
        case 3:
            this._element.setNamespace_uri(value);
            break;
        }
    }

    @objid ("fbd131f9-e3a1-4ac4-9c50-0bebb15bb171")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
        table.addProperty("Name", _element.getElement().getName());
        table.addProperty("Namespace Prefix", _element.getNamespace_prefix());
        table.addProperty("Namespace Uri", _element.getNamespace_uri());
    }

}
