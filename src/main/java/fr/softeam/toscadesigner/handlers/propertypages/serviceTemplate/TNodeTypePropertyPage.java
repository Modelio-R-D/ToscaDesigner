package fr.softeam.toscadesigner.handlers.propertypages.serviceTemplate;

import java.util.Arrays;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.Metamodel;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.CapabilitiesType;
import fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.InterfacesType;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TCapabilityDefinitionsType;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeType;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TTopologyElementInstanceStates;
import fr.softeam.toscadesigner.handlers.propertypages.core.ToscaElementPropertyPage;

@objid ("1b7c8e78-2f52-4575-9b25-ab31be3a9dca")
public class TNodeTypePropertyPage<T extends TNodeType> extends ToscaElementPropertyPage<T> {
    @objid ("03b2bf18-305b-44ff-95b5-22123dbcd421")
    public  TNodeTypePropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("ecb85ea3-57af-4b34-aed7-7d62e3a033db")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {
        case 1:
            this._element.getElement().setName(value);
            break;
        
        case 2:
            this._element.setDescription(value);
            break;
        
        case 3:
            for (ModelElement el : TNodeType.MdaTypes.STEREOTYPE_ELT.getExtendedElement()) {
                if (value.contains(el.getUuid())) {
                    this._element.setDerivedFrom(TNodeType.instantiate((org.modelio.metamodel.uml.statik.Class) el));
                    break;
                }
                else {
                    this._element.setDerivedFrom(null);
                }
            }
            break;
        case 4:
            for (ModelElement dep : InterfacesType.MdaTypes.STEREOTYPE_ELT.getExtendedElement()) {
                if (value.contains(dep.getUuid())) {
                    this._element
                            .setInterfaces(InterfacesType.instantiate((org.modelio.metamodel.uml.statik.Class) dep));
                    break;
                }else {
                    this._element.setInterfaces(null);
                }
            }
            break;
        
        case 5:
            for (ModelElement dep : TTopologyElementInstanceStates.MdaTypes.STEREOTYPE_ELT.getExtendedElement()) {
                if (value.contains(dep.getUuid())) {
                    this._element.setInstanceStates(
                            TTopologyElementInstanceStates.instantiate((org.modelio.metamodel.uml.statik.Class) dep));
                    break;
                }else {
                    this._element.setInstanceStates(null);
                }
            }
            break;
        
        case 6:
            for (ModelElement dep : TCapabilityDefinitionsType.MdaTypes.STEREOTYPE_ELT.getExtendedElement()) {
                if (value.contains(dep.getUuid())) {
                    this._element.setCapabilityDefinitions(
                            TCapabilityDefinitionsType.instantiate((org.modelio.metamodel.uml.statik.Class) dep));
                    break;
                }else {
                    this._element.setCapabilityDefinitions(null);
                }
            }
            break;
        
        }
    }

    @objid ("3021057a-6f35-4748-91ec-06427ca0d0c3")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
        table.addProperty("Name", _element.getElement().getName());
        table.addProperty("Description", _element.getDescription());
        table.addProperty("Derived From",
                this._element.getDerivedFrom() != null ? this._element.getDerivedFrom().getElement() : null,
                Arrays.asList(Metamodel.getMClass("Class")), new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return TNodeType.canInstantiate(element);
                    }
                });
        // Interfaces Type
        table.addProperty("Interfaces Type",
                this._element.getInterfaces() != null ? this._element.getInterfaces().getElement() : null,
                Arrays.asList(Metamodel.getMClass("Class")), new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return InterfacesType.canInstantiate(element);
                    }
                });
        
        // Instance States
        table.addProperty("Instance States",
                this._element.getInstanceStates() != null ? this._element.getInstanceStates().getElement() : null,
                Arrays.asList(Metamodel.getMClass("Class")), new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return TTopologyElementInstanceStates.canInstantiate(element);
                    }
                });
        
        // Capability Definition
        table.addProperty("Capability Definition",
                this._element.getCapabilityDefinitions() != null ? this._element.getCapabilityDefinitions().getElement()
                        : null,
                Arrays.asList(Metamodel.getMClass("Class")), new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return CapabilitiesType.canInstantiate(element);
                    }
                });
    }

}
