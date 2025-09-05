package fr.softeam.toscadesigner.handlers.propertypages.serviceTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.softeam.toscadesigner.api.IToscaDesignerPeerModule;
import fr.softeam.toscadesigner.api.ToscaDesignerProxyFactory;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TGroup;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeType;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TRelationshipType;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TTopologyTemplate;
import fr.softeam.toscadesigner.api.tosca.standard.package_.TServiceTemplate;
import fr.softeam.toscadesigner.handlers.propertypages.core.ToscaElementPropertyPage;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.Metamodel;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("a4a7341d-8b2f-4f32-992d-bf6f5f20b620")
public class TServiceTemplatePropertyPage<T extends TServiceTemplate> extends ToscaElementPropertyPage<T> {
    @objid ("8736ed70-ef55-40e6-a3d8-dc09e36df100")
    public  TServiceTemplatePropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("a8d1ee90-2b24-46f3-947c-bc26c82512e2")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {
        case 1:
            this._element.getElement().setName(value);
            break;

        case 2:
            for (ModelElement dep : TTopologyTemplate.MdaTypes.STEREOTYPE_ELT.getExtendedElement()) {
                if (value.contains(dep.getUuid())) {
                    this._element.setTopologyTemplate(
                            TTopologyTemplate.instantiate((org.modelio.metamodel.uml.statik.Class) dep));
        break;
        } else {
        this._element.setTopologyTemplate(null);
        }
            }
            break;

        case 3:
            Class elt2 = (Class) getModelElt(TNodeType.MdaTypes.STEREOTYPE_ELT.getExtendedElement(), value);
            if ((elt2 != null)
                    && (elt2.isStereotyped(IToscaDesignerPeerModule.MODULE_NAME, TNodeType.STEREOTYPE_NAME))) {
                Object pc = ToscaDesignerProxyFactory.instantiate(elt2);
                if (value.startsWith(this._add)) {
                    this._element.addNodeTypes((TNodeType) pc);
                } else {
                    this._element.removeNodeTypes((TNodeType) pc);
                }
            }
            break;

        case 4:
            Class elt3 = (Class) getModelElt(TRelationshipType.MdaTypes.STEREOTYPE_ELT.getExtendedElement(), value);
            if ((elt3 != null)
                    && (elt3.isStereotyped(IToscaDesignerPeerModule.MODULE_NAME, TRelationshipType.STEREOTYPE_NAME))) {
                Object pc = ToscaDesignerProxyFactory.instantiate(elt3);
                if (value.startsWith(this._add)) {
                    this._element.addRelationships((TRelationshipType) pc);
                } else {
                    this._element.removeRelationships((TRelationshipType) pc);
                }
            }
            break;
        case 5:
            Class elt1 = ( Class) getModelElt(TGroup.MdaTypes.STEREOTYPE_ELT.getExtendedElement(), value);
            if ((elt1 != null) && (elt1.isStereotyped(IToscaDesignerPeerModule.MODULE_NAME, TGroup.STEREOTYPE_NAME))) {
                Object pc = ToscaDesignerProxyFactory.instantiate(elt1);
                if (value.startsWith(this._add)) {
                    this._element.addGroups((TGroup) pc);
                } else {
                    this._element.removeGroups((TGroup) pc);
                }
            }
            break;
        }
    }

    @objid ("92067599-7071-495b-819f-27cdc2bc6282")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
        table.addProperty("Name", _element.getElement().getName());

        // TOPOLOGY TEMPLATE
        table.addProperty("Topology",
                this._element.getTopologyTemplate() != null ? this._element.getTopologyTemplate().getElement() : null,
                Arrays.asList(Metamodel.getMClass("Package")), new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return TTopologyTemplate.canInstantiate(element);
                    }
                });

        // NODE TYPES
        List<ModelElement> members_elt = extractModelElements(this._element.getNodeTypes());
        List<ModelElement> nodeTypeList = (TNodeType.MdaTypes.STEREOTYPE_ELT.getExtendedElement() != null)
                ? TNodeType.MdaTypes.STEREOTYPE_ELT.getExtendedElement()
                : Collections.emptyList();

        table.addProperty("Node types", getToscaValue(members_elt),
                getAddRemove(nodeTypeList, extractModelElements(this._element.getNodeTypes())));

        // RELATIONSHIP types
        members_elt = extractModelElements(this._element.getRelationships());
        List<ModelElement> relationships = (TRelationshipType.MdaTypes.STEREOTYPE_ELT.getExtendedElement() != null)
                ? TRelationshipType.MdaTypes.STEREOTYPE_ELT.getExtendedElement()
                : Collections.emptyList();

        table.addProperty("Relationship types", getToscaValue(members_elt),
                getAddRemove(relationships, extractModelElements(this._element.getRelationships())));

        // GROUPS
        members_elt = extractModelElements(this._element.getGroups());
        List<ModelElement> groupList = (TGroup.MdaTypes.STEREOTYPE_ELT.getExtendedElement() != null)
                ? TGroup.MdaTypes.STEREOTYPE_ELT.getExtendedElement()
                : Collections.emptyList();

        table.addProperty("Groups", getToscaValue(members_elt),
                getAddRemove(groupList, extractModelElements(this._element.getGroups())));
    }

}
