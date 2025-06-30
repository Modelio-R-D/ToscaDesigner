package fr.softeam.toscadesigner.handlers.propertypages.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.Metamodel;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.api.IToscaDesignerPeerModule;
import fr.softeam.toscadesigner.api.ToscaDesignerProxyFactory;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TGroupType;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeTemplate;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TGroup;

@objid ("9ba049d3-ee8c-4422-8d36-2464c310e0f6")
public class TGroupPropertyPage<T extends TGroup> extends ToscaElementPropertyPage<T> {
    @objid ("e85baca2-0be5-43c6-b58e-c312d69a0cbc")
    public  TGroupPropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("12f17f2a-9545-4b7d-870d-fdc358b3131e")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {
        case 1:
            this._element.getElement().setName(value);
            break;
        
        case 2:
            for (ModelElement dep : TGroupType.MdaTypes.STEREOTYPE_ELT.getExtendedElement()) {
                if (value.contains(dep.getUuid())) {
                    this._element.setType(TGroupType.instantiate((org.modelio.metamodel.uml.statik.Class) dep));
        break;
        } else {
        this._element.setType(null);
        }            }
            break;
        
        case 3:
            Class elt1 = (Class) getModelElt(TNodeTemplate.MdaTypes.STEREOTYPE_ELT.getExtendedElement(), value);
            if ((elt1 != null)
                    && (elt1.isStereotyped(IToscaDesignerPeerModule.MODULE_NAME, TNodeTemplate.STEREOTYPE_NAME))) {
                Object pc = ToscaDesignerProxyFactory.instantiate(elt1);
                if (value.startsWith(this._add)) {
                    this._element.addMembers((TNodeTemplate) pc);
                } else {
                    this._element.removeMembers((TNodeTemplate) pc);
                }
            }
            break;
        }
    }

    @objid ("e621c642-2ca4-484e-a8ff-25974c47477c")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
        table.addProperty("Name", _element.getElement().getName());
        table.addProperty("Type", this._element.getType() != null ? this._element.getType().getElement() : null,
                Arrays.asList(Metamodel.getMClass("Class")), new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return TGroupType.canInstantiate(element);
                    }
                });
        
        List<ModelElement> members_elt = extractModelElements(this._element.getMembers());
        List<ModelElement> nodeTemplateList = (TNodeTemplate.MdaTypes.STEREOTYPE_ELT.getExtendedElement() != null)
                ? TNodeTemplate.MdaTypes.STEREOTYPE_ELT.getExtendedElement()
                : Collections.emptyList();
        
        table.addProperty("Members", getToscaValue(members_elt),
                getAddRemove(nodeTemplateList, extractModelElements(this._element.getMembers())));
    }

}
