package fr.softeam.toscadesigner.handlers.propertypages.core;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.api.tosca.standard.class_.TCapability;

@objid ("3f614cc7-3f21-42f9-bef6-1deb16e9737d")
public class TCapabilityPropertyPage<T extends TCapability> extends ToscaElementPropertyPage<T> {
    @objid ("39bd4d3d-5084-4fc3-b28d-913bb74178fe")
    public  TCapabilityPropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("fa380b1e-f2f4-4b49-ad3c-f797de738166")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {
        case 1:
            this._element.getElement().setName(value);
            break;
        
        }
    }

    @objid ("1eb7b525-86b4-4efb-a153-657eef554349")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
        table.addProperty("Name", _element.getElement().getName());
    }

}
