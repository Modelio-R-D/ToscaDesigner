package fr.softeam.toscadesigner.handlers.propertypages.core;

import java.util.Collections;
import java.util.List;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Attribute;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.api.IToscaDesignerPeerModule;
import fr.softeam.toscadesigner.api.ToscaDesignerProxyFactory;
import fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.TOperation;
import fr.softeam.toscadesigner.api.tosca.standard.attribute.TPropertyDef;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TInterface;

@objid ("2e2429c8-a9ac-48ae-9435-eeb511f3c793")
public class TInterfacePropertyPage<T extends TInterface> extends ToscaElementPropertyPage<T> {
    @objid ("c152881e-9f11-4b48-a472-ef38c5dc6866")
    public  TInterfacePropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("b3388b5b-7df7-440c-ab0f-91200a0744cb")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {
        case 1:
            this._element.getElement().setName(value);
            break;
        
        case 2:
            this._element.setNotifications(value);
            break;
        
        case 3:
            ModelElement elt1 =  getModelElt(TOperation.MdaTypes.STEREOTYPE_ELT.getExtendedElement(), value);
            if ((elt1 != null)
                    && (elt1.isStereotyped(IToscaDesignerPeerModule.MODULE_NAME, TOperation.STEREOTYPE_NAME))) {
                Object pc = ToscaDesignerProxyFactory.instantiate(elt1);
                if (value.startsWith(this._add)) {
                    this._element.addOperation((TOperation) pc);
                } else {
                    this._element.removeOperation((TOperation) pc);
                }
            }
            break;
            
        case 4:
            Attribute elt2 = (Attribute) getModelElt(TPropertyDef.MdaTypes.STEREOTYPE_ELT.getExtendedElement(), value);
            if ((elt2 != null)
                    && (elt2.isStereotyped(IToscaDesignerPeerModule.MODULE_NAME, TPropertyDef.STEREOTYPE_NAME))) {
                Object pc = ToscaDesignerProxyFactory.instantiate(elt2);
                if (value.startsWith(this._add)) {
                    this._element.addInputs((TPropertyDef) pc);
                } else {
                    this._element.removeInputs((TPropertyDef) pc);
                }
            }
            break;
        }
    }

    @objid ("aee5cfe4-1abd-464a-ab42-d9ba788a4eec")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
        table.addProperty("Name", _element.getElement().getName());
        table.addProperty("Notifications", _element.getNotifications());
        
        // Operations
        
        List<ModelElement> members_elt = extractModelElements(this._element.getOperation());
        List<ModelElement> operationsList = (TOperation.MdaTypes.STEREOTYPE_ELT.getExtendedElement() != null)
                ? TOperation.MdaTypes.STEREOTYPE_ELT.getExtendedElement()
                : Collections.emptyList();
        table.addProperty("Operations", getToscaValue(members_elt),
                getAddRemove(operationsList, extractModelElements(this._element.getOperation())));
        
        // Inputs
        List<Attribute> members_elt2 = extractAttributes(this._element.getInputs());
        List<ModelElement> inputsList = (TPropertyDef.MdaTypes.STEREOTYPE_ELT.getExtendedElement() != null)
                ? TPropertyDef.MdaTypes.STEREOTYPE_ELT.getExtendedElement()
                : Collections.emptyList();
        table.addProperty("Inputs", getToscaValue(members_elt2),
                getAddRemove(inputsList, extractAttributes(this._element.getInputs())));
    }

}
