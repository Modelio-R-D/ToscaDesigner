package fr.softeam.toscadesigner.handlers.propertypages.topologyTemplate;

import java.util.Collections;
import java.util.List;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.Class;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.api.IToscaDesignerPeerModule;
import fr.softeam.toscadesigner.api.ToscaDesignerProxyFactory;
import fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.TParameter;
import fr.softeam.toscadesigner.api.tosca.standard.association.TRelationshipTemplate;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeTemplate;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TTopologyTemplate;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TGroup;
import fr.softeam.toscadesigner.handlers.propertypages.core.ToscaElementPropertyPage;

@objid ("30999161-0952-4789-9b87-97951cfefbd9")
public class TTopologyTemplatePropertyPage<T extends TTopologyTemplate> extends ToscaElementPropertyPage<T> {
    @objid ("bee17b8e-6370-40d3-ad9f-0679244210b6")
    public  TTopologyTemplatePropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("51cb11db-2e78-4664-9d63-fbbd878c1433")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {
        case 1:
            this._element.getElement().setName(value);
            break;
        
        case 2:
            Class elt1 = (Class) getModelElt(TGroup.MdaTypes.STEREOTYPE_ELT.getExtendedElement(), value);
            if ((elt1 != null) && (elt1.isStereotyped(IToscaDesignerPeerModule.MODULE_NAME, TGroup.STEREOTYPE_NAME))) {
                Object pc = ToscaDesignerProxyFactory.instantiate(elt1);
                if (value.startsWith(this._add)) {
                    this._element.addGroups((TGroup) pc);
                } else {
                    this._element.removeGroups((TGroup) pc);
                }
            }
            break;
        
        case 3:
            Class elt2 = (Class) getModelElt(TNodeTemplate.MdaTypes.STEREOTYPE_ELT.getExtendedElement(), value);
            if ((elt2 != null)
                    && (elt2.isStereotyped(IToscaDesignerPeerModule.MODULE_NAME, TNodeTemplate.STEREOTYPE_NAME))) {
                Object pc = ToscaDesignerProxyFactory.instantiate(elt2);
                if (value.startsWith(this._add)) {
                    this._element.addNodeTemplates((TNodeTemplate) pc);
                } else {
                    this._element.removeNodeTemplates((TNodeTemplate) pc);
                }
            }
            break;
        
        case 4:
            Association elt3 = (Association) getModelElt(
                    TRelationshipTemplate.MdaTypes.STEREOTYPE_ELT.getExtendedElement(), value);
            if ((elt3 != null) && (elt3.isStereotyped(IToscaDesignerPeerModule.MODULE_NAME,
                    TRelationshipTemplate.STEREOTYPE_NAME))) {
                Object pc = ToscaDesignerProxyFactory.instantiate(elt3);
                if (value.startsWith(this._add)) {
                    this._element.addRelationshipTemplates((TRelationshipTemplate) pc);
                } else {
                    this._element.removeRelationshipTemplates((TRelationshipTemplate) pc);
                }
            }
            break;
            
        case 5:
            ModelElement elt4 = getModelElt(TParameter.MdaTypes.STEREOTYPE_ELT.getExtendedElement(), value);
            if ((elt4 != null)
                    && (elt4.isStereotyped(IToscaDesignerPeerModule.MODULE_NAME, TParameter.STEREOTYPE_NAME))) {
                Object pc = ToscaDesignerProxyFactory.instantiate(elt4);
                if (value.startsWith(this._add)) {
                    this._element.addInputs((TParameter) pc);
                } else {
                    this._element.removeInputs((TParameter) pc);
                }
            }
            break;
            
        case 6:
            ModelElement elt5 = getModelElt(TParameter.MdaTypes.STEREOTYPE_ELT.getExtendedElement(), value);
            if ((elt5 != null)
                    && (elt5.isStereotyped(IToscaDesignerPeerModule.MODULE_NAME, TNodeTemplate.STEREOTYPE_NAME))) {
                Object pc = ToscaDesignerProxyFactory.instantiate(elt5);
                if (value.startsWith(this._add)) {
                    this._element.addOutputs((TParameter) pc);
                } else {
                    this._element.removeOutputs((TParameter) pc);
                }
            }
            break;
        }
    }

    @objid ("398a0096-e4af-4e29-8d94-b02e98ecf16a")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
        table.addProperty("Name", _element.getElement().getName());
        
        // groups
        List<ModelElement> members_elt = extractModelElements(this._element.getGroups());
        List<ModelElement> groupList = (TGroup.MdaTypes.STEREOTYPE_ELT.getExtendedElement() != null)
                ? TGroup.MdaTypes.STEREOTYPE_ELT.getExtendedElement()
                : Collections.emptyList();
        
        table.addProperty("Groups", getToscaValue(members_elt),
                getAddRemove(groupList, extractModelElements(this._element.getGroups())));
        
        // node templates
        members_elt = extractModelElements(this._element.getNodeTemplates());
        List<ModelElement> nodeTemplateList = (TNodeTemplate.MdaTypes.STEREOTYPE_ELT.getExtendedElement() != null)
                ? TNodeTemplate.MdaTypes.STEREOTYPE_ELT.getExtendedElement()
                : Collections.emptyList();
        
        table.addProperty("Node templates", getToscaValue(members_elt),
                getAddRemove(nodeTemplateList, extractModelElements(this._element.getNodeTemplates())));
        
        
        // relationship templates
        members_elt = extractModelElements(this._element.getRelationshipTemplates());
        List<ModelElement> relationshipTemplates = (TRelationshipTemplate.MdaTypes.STEREOTYPE_ELT
                .getExtendedElement() != null) ? TRelationshipTemplate.MdaTypes.STEREOTYPE_ELT.getExtendedElement()
                        : Collections.emptyList();
        
        table.addProperty("Relationship templates", getToscaValue(members_elt),
                getAddRemove(relationshipTemplates, extractModelElements(this._element.getRelationshipTemplates())));
        
        //inputs
        members_elt = extractModelElements(this._element.getInputs());
        List<ModelElement> inputs = (TParameter.MdaTypes.STEREOTYPE_ELT
                .getExtendedElement() != null) ? TParameter.MdaTypes.STEREOTYPE_ELT.getExtendedElement()
                        : Collections.emptyList();
        
        table.addProperty("Inputs", getToscaValue(members_elt),
                getAddRemove(inputs, extractModelElements(this._element.getInputs())));
        
        //outputs
        members_elt = extractModelElements(this._element.getOutputs());
        List<ModelElement> outputs = (TParameter.MdaTypes.STEREOTYPE_ELT
                .getExtendedElement() != null) ? TParameter.MdaTypes.STEREOTYPE_ELT.getExtendedElement()
                        : Collections.emptyList();
        
        table.addProperty("Outputs", getToscaValue(members_elt),
                getAddRemove(outputs, extractModelElements(this._element.getOutputs())));
    }

}
