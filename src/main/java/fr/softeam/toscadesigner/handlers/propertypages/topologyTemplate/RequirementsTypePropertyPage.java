package fr.softeam.toscadesigner.handlers.propertypages.topologyTemplate;

import java.util.Arrays;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.Metamodel;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.api.tosca.standard.class_.RequirementsType;
import fr.softeam.toscadesigner.handlers.propertypages.core.ToscaElementPropertyPage;

@objid ("25a6e5cf-3940-4d2f-879a-eb9caeb75c6b")
public class RequirementsTypePropertyPage<T extends RequirementsType> extends ToscaElementPropertyPage<T> {
    @objid ("1483a0f4-1e26-4355-b652-5ff55e86c690")
    public  RequirementsTypePropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("9abb1f7e-4929-4f8d-96fb-0895bcd49aab")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {
        case 1:
            this._element.getElement().setName(value);
            break;
        case 2:
            for (ModelElement el : RequirementsType.MdaTypes.STEREOTYPE_ELT.getExtendedElement()) {
                if (value.contains(el.getUuid())) {
                    this._element
                            .setDerivedFrom(RequirementsType.instantiate((org.modelio.metamodel.uml.statik.Class) el));
                    break;
                } else {
                    this._element.setDerivedFrom(null);
                }
            }
            break;
        }
    }

    @objid ("602f2124-2e7b-4e3f-a0bf-c8f2651f92ae")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
        table.addProperty("Name", _element.getElement().getName());
        table.addProperty("Derived From",
                this._element.getDerivedFrom() != null ? this._element.getDerivedFrom().getElement() : null,
                Arrays.asList(Metamodel.getMClass("Class")), new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return RequirementsType.canInstantiate(element);
                    }
                });
    }

}
