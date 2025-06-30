package fr.softeam.toscadesigner.handlers.propertypages.core;

import java.util.Collections;
import java.util.List;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Class;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.api.IToscaDesignerPeerModule;
import fr.softeam.toscadesigner.api.ToscaDesignerProxyFactory;
import fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.CapabilitiesType;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TCapability;

@objid ("d6a5a8d2-a242-46eb-a46a-6bb7ab1cc01a")
public class CapabilitiesTypePropertyPage<T extends CapabilitiesType> extends ToscaElementPropertyPage<T> {
    @objid ("dd2633e1-365e-4659-a2d4-828f4e20e09f")
    public  CapabilitiesTypePropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("547f82db-ea96-42cf-a236-573257921d9a")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {
        case 1:
            this._element.getElement().setName(value);
            break;
        case 2:
            Class elt2 = (Class) getModelElt(TCapability.MdaTypes.STEREOTYPE_ELT.getExtendedElement(), value);
            if ((elt2 != null)
                    && (elt2.isStereotyped(IToscaDesignerPeerModule.MODULE_NAME, TCapability.STEREOTYPE_NAME))) {
                Object pc = ToscaDesignerProxyFactory.instantiate(elt2);
                if (value.startsWith(this._add)) {
                    this._element.addCapability((TCapability) pc);
                } else {
                    this._element.removeCapability((TCapability) pc);
                }
            }
            break;
        }
    }

    @objid ("6d4658f1-fc2c-4565-9f5e-3afbd007424a")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
        table.addProperty("Name", _element.getElement().getName());
        
        // Capability
        List<ModelElement> members_elt = extractModelElements(this._element.getCapability());
        List<ModelElement> capabilityList = (TCapability.MdaTypes.STEREOTYPE_ELT.getExtendedElement() != null)
                ? TCapability.MdaTypes.STEREOTYPE_ELT.getExtendedElement()
                : Collections.emptyList();
        table.addProperty("Capability", getToscaValue(members_elt),
                getAddRemove(capabilityList, extractModelElements(this._element.getCapability())));
    }

}
