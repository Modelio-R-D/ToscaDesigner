package fr.softeam.toscadesigner.handlers.propertypages.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TPolicyType;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;

@objid ("18f85fd8-7dac-4fff-8a80-58ad4d5d4883")
public class TPolicyTypePropertyPage<T extends TPolicyType> extends ToscaElementPropertyPage<T> {
    @objid ("5ca97508-62ea-4fbe-8320-3b626342b8f4")
    public  TPolicyTypePropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("bdf07b47-266f-4161-9d67-f2fc2bed7cf3")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {

        case 1:
            this._element.getElement().setName(value);
            ;
            break;

        case 2:
            this._element.setPolicyLanguage(value);
            break;

        }
    }

    @objid ("badeb0a8-73d0-4b93-990d-24a6da07d8f0")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
        table.addProperty("Name", _element.getElement().getName());
        table.addProperty("Language", _element.getPolicyLanguage());
    }

}
