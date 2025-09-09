package fr.softeam.toscadesigner.handlers.propertypages.types;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TCapabilityType;
import fr.softeam.toscadesigner.handlers.propertypages.core.ToscaElementPropertyPage;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;

@objid ("fc40a086-4b51-4fac-b383-3dc322b43f67")
public class TCapabilityTypePropertyPage<T extends TCapabilityType> extends ToscaElementPropertyPage<T> {
    @objid ("07c26eb1-c448-4c0c-8123-55bb13dc9426")
    public  TCapabilityTypePropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("b52771c5-a95b-4714-ac16-0aee431a7f35")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {
        case 1:
            break;

        }
    }

    @objid ("1e525070-9a9e-4e5f-bf4e-2800e8090c15")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
    }

}
