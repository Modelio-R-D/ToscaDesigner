package fr.softeam.toscadesigner.handlers.propertypages.core;

import java.util.Arrays;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TGroupType;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.Metamodel;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("56dc8f1e-2070-4f63-8a98-2cd7b67a6f01")
public class TGroupTypePropertyPage<T extends TGroupType> extends ToscaElementPropertyPage<T> {
    @objid ("0b2326fd-be21-4fe1-90b4-2f72dccb5916")
    public  TGroupTypePropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("3b6ef44c-ee57-49c5-9ad4-8dd1ed5c8686")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {
        case 1:
            this._element.getElement().setName(value);
            break;
        case 2:
            for (ModelElement el : TGroupType.MdaTypes.STEREOTYPE_ELT.getExtendedElement()) {
                if (value.contains(el.getUuid())) {
                    this._element.setDerivedFrom(TGroupType.instantiate((org.modelio.metamodel.uml.statik.Class) el));
        break;
        } else {
        this._element.setDerivedFrom(null);
        }
            }
            break;
        }
    }

    @objid ("2c03f613-4025-4cf5-829b-b67cb80cc584")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
        table.addProperty("Name", _element.getElement().getName());
        table.addProperty("Derived From",
                this._element.getDerivedFrom() != null ? this._element.getDerivedFrom().getElement() : null,
                Arrays.asList(Metamodel.getMClass("Class")), new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return TGroupType.canInstantiate(element);
                    }
                });
    }

}
