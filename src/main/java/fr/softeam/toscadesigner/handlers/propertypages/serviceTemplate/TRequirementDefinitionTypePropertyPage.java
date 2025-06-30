package fr.softeam.toscadesigner.handlers.propertypages.serviceTemplate;

import java.util.Arrays;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.Metamodel;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.api.tosca.standard.class_.TRequirementDefinitionType;
import fr.softeam.toscadesigner.handlers.propertypages.core.ToscaElementPropertyPage;

@objid ("559da940-3e06-4be7-8179-e4239dfa9b71")
public class TRequirementDefinitionTypePropertyPage<T extends TRequirementDefinitionType> extends ToscaElementPropertyPage<T> {
    @objid ("a0b8cdf3-4fbb-4165-9424-f22d701766f9")
    public  TRequirementDefinitionTypePropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("96513ff6-9d80-47ba-bd65-f6afb3b267d8")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {
        case 1:
            this._element.getElement().setName(value);
            break;
        case 2:
            for (ModelElement el : TRequirementDefinitionType.MdaTypes.STEREOTYPE_ELT.getExtendedElement()) {
                if (value.contains(el.getUuid())) {
                    this._element.setDerivedFrom(
                            TRequirementDefinitionType.instantiate((org.modelio.metamodel.uml.statik.Class) el));
        break;
        } else {
        this._element.setDerivedFrom(null);
        }
            }
            break;
        
        }
    }

    @objid ("c55e3f0c-e3e9-4213-bcf2-5de8d2691a5d")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
        table.addProperty("Name", _element.getElement().getName());
        table.addProperty("Derived From",
                this._element.getDerivedFrom() != null ? this._element.getDerivedFrom().getElement() : null,
                Arrays.asList(Metamodel.getMClass("Class")), new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return TRequirementDefinitionType.canInstantiate(element);
                    }
                });
    }

}
