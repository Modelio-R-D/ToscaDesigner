package fr.softeam.toscadesigner.handlers.propertypages.cloudServiceArchive;

import java.util.Arrays;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.Metamodel;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.api.tosca.standard.class_.TPlans;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TTopologyTemplate;
import fr.softeam.toscadesigner.api.tosca.standard.package_.TServiceTemplate;
import fr.softeam.toscadesigner.handlers.propertypages.core.ToscaElementPropertyPage;

@objid ("bc49b194-4a77-4c9c-b9ca-d67a1e718cf0")
public class TServiceTemplatePropertyPage<T extends TServiceTemplate> extends ToscaElementPropertyPage<T> {
    @objid ("f7385a57-8dc4-43c5-8c43-8c0fdfda8497")
    public  TServiceTemplatePropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("608a732a-b885-4c49-b718-a975e6733652")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {
        case 1:
            this._element.getElement().setName(value);
            break;
        
        case 2:
            for (ModelElement nodet : TTopologyTemplate.MdaTypes.STEREOTYPE_ELT.getExtendedElement()) {
                if (value.contains(nodet.getUuid())) {
                    this._element.setTopologyTemplate(
                            TTopologyTemplate.instantiate((org.modelio.metamodel.uml.statik.Class) nodet));
        break;
        } else {
        this._element.setTopologyTemplate(null);
        }            }
            break;
        case 3:
            for (ModelElement nodet : TPlans.MdaTypes.STEREOTYPE_ELT.getExtendedElement()) {
                if (value.contains(nodet.getUuid())) {
                    this._element.setPlans(TPlans.instantiate((org.modelio.metamodel.uml.statik.Class) nodet));
        break;
        } else {
        this._element.setPlans(null);
        }  
            }
            break;
        
        }
    }

    @objid ("fdfd24f1-53bc-4204-883e-fbb8d95d5031")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
        table.addProperty("Name", _element.getElement().getName());
        
        // Topology Template
        table.addProperty("Topology Template",
                this._element.getTopologyTemplate() != null ? this._element.getTopologyTemplate().getElement() : null,
                Arrays.asList(Metamodel.getMClass("Package")), new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return TTopologyTemplate.canInstantiate(element);
                    }
                });
        
        // Plans
        table.addProperty("Plans", this._element.getPlans() != null ? this._element.getPlans().getElement() : null,
                Arrays.asList(Metamodel.getMClass("Class")), new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return TPlans.canInstantiate(element);
                    }
                });
    }

}
