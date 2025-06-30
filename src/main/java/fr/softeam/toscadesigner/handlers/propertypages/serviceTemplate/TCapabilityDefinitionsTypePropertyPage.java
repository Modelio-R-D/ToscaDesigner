package fr.softeam.toscadesigner.handlers.propertypages.serviceTemplate;

import java.util.Arrays;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.Metamodel;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.api.tosca.standard.class_.TCapabilityDefinitionsType;
import fr.softeam.toscadesigner.handlers.propertypages.core.ToscaElementPropertyPage;

@objid ("5d16e2f1-125b-4a27-aef4-d5abc1d78cd2")
public class TCapabilityDefinitionsTypePropertyPage<T extends TCapabilityDefinitionsType> extends ToscaElementPropertyPage<T> {
    @objid ("6333f6af-f99f-4384-b941-8ad536f858d3")
    public  TCapabilityDefinitionsTypePropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("88c836b2-8289-4c2f-8ecf-280dfe58ddd3")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {
        case 1:
            this._element.getElement().setName(value);
            break;
        case 2:
            for (ModelElement el : TCapabilityDefinitionsType.MdaTypes.STEREOTYPE_ELT.getExtendedElement()) {
                if (value.contains(el.getUuid())) {
                    this._element.setDerivedFrom(
                            TCapabilityDefinitionsType.instantiate((org.modelio.metamodel.uml.statik.Class) el));
                    break;
                } else {
                    this._element.setDerivedFrom(null);
                }
            }
            break;
        }
    }

    @objid ("2e6c63b8-8ca0-4e17-becb-6f152b8834e0")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
        table.addProperty("Name", _element.getElement().getName());
        table.addProperty("Derived From",
                this._element.getDerivedFrom() != null ? this._element.getDerivedFrom().getElement() : null,
                Arrays.asList(Metamodel.getMClass("Class")), new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return TCapabilityDefinitionsType.canInstantiate(element);
                    }
                });
    }

}
