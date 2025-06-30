package fr.softeam.toscadesigner.handlers.propertypages.serviceTemplate;

import java.util.Arrays;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.Metamodel;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.api.tosca.standard.class_.TRelationshipType;
import fr.softeam.toscadesigner.handlers.propertypages.core.TEntityTypePropertyPage;

@objid ("5de69f13-68dc-4489-bca1-b2e732a50ca9")
public class TRelationshipTypePropertyPage<T extends TRelationshipType> extends TEntityTypePropertyPage<T> {
    @objid ("d8b700e8-5092-4f3b-a08e-a84d393dc18e")
    public  TRelationshipTypePropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("86c38ef5-9af9-42c3-afb5-392191c97b25")
    @Override
    public void changeProperty(int row, String value) {
        super.changeProperty(row, value);
        
        switch (row) {
        case 1:
            this._element.getElement().setName(value);
        
        case 2:
            for (ModelElement el : TRelationshipType.MdaTypes.STEREOTYPE_ELT.getExtendedElement()) {
                if (value.contains(el.getUuid())) {
                    this._element
                            .setDerivedFrom(TRelationshipType.instantiate((org.modelio.metamodel.uml.statik.Class) el));
                    break;
                } else {
                    this._element.setDerivedFrom(null);
                }
            }
            break;
        case 3:
            this._element.setValidSource(value);
            break;
        case 4:
            this._element.setValidTarget(value);
            break;
        
        case 5:
            // this._element.setInstanceStates(null);
            break;
        case 6:
            // this._element.setSourceInterfaces(null);;
            break;
        case 7:
            // this._element.setTargetInterfaces(null);
        
        }
    }

    @objid ("83888144-0dc4-4620-8f1a-bde83d9522b2")
    @Override
    public void update(IModulePropertyTable table) {
        // super.update(table);
        /*
                         * //Instance states table.addProperty("Instance States",
                         * this._element.getInstanceStates() != null ?
                         * this._element.getInstanceStates().getElement() : null,
                         * Arrays.asList(Metamodel.getMClass("Class")), new IMObjectFilter() {
                         * 
                         * @Override public boolean accept(MObject element) { return
                         * TTopologyElementInstanceStates.canInstantiate(element); } }); //Source
                         * Interfaces table.addProperty("Source Interfaces",
                         * this._element.getSourceInterfaces() != null ?
                         * this._element.getSourceInterfaces().getElement() : null,
                         * Arrays.asList(Metamodel.getMClass("Class")), new IMObjectFilter() {
                         * 
                         * @Override public boolean accept(MObject element) { return
                         * SourceInterfacesType.canInstantiate(element); } });
                         * 
                         * //Target Interfaces table.addProperty("Target Interfaces",
                         * this._element.getTargetInterfaces() != null ?
                         * this._element.getTargetInterfaces().getElement() : null,
                         * Arrays.asList(Metamodel.getMClass("Class")), new IMObjectFilter() {
                         * 
                         * @Override public boolean accept(MObject element) { return
                         * TargetInterfacesType.canInstantiate(element); } });
                         */
        table.addProperty("Name", _element.getElement().getName());
        table.addProperty("Derived From",
                this._element.getDerivedFrom() != null ? this._element.getDerivedFrom().getElement() : null,
                Arrays.asList(Metamodel.getMClass("Class")), new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return TRelationshipType.canInstantiate(element);
                    }
                });
        table.addProperty("Valid Source", this._element.getValidSource());
        table.addProperty("Valid Target", this._element.getValidTarget());
    }

}
