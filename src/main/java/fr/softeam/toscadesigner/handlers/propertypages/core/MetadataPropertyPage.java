package fr.softeam.toscadesigner.handlers.propertypages.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.softeam.toscadesigner.api.tosca.standard.attribute.Metadata;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;

@objid ("dfe7d2a5-7977-4f19-adbb-c3d1467b10c9")
public class MetadataPropertyPage<T extends Metadata> extends ToscaElementPropertyPage<T> {
    @objid ("7a86494a-262d-4434-9160-32687b2b4c1e")
    public  MetadataPropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("15c7b701-d06b-456f-953d-3cc7bbe0b636")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {
        case 1:
            this._element.getElement().setName(value);
            ;
            break;
        case 2:
            this._element.getElement().setValue(value);
            break;

        }
    }

    @objid ("1130a87e-a697-427d-9a74-ba106d60e17a")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
        table.addProperty("Name", _element.getElement().getName());
        table.addProperty("Value", _element.getElement().getValue());
    }

}
