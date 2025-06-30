package fr.softeam.toscadesigner.handlers.propertypages.core;

import java.util.Arrays;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.Metamodel;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.api.tosca.standard.class_.TEntityType;

@objid ("e39a656e-a40d-4be9-bbdd-cbb81c28ec10")
public abstract class TEntityTypePropertyPage<T extends TEntityType> extends ToscaElementPropertyPage<T> {
    @objid ("b25e9f6b-cbd1-4411-9932-a662b03d950f")
    public  TEntityTypePropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("e476f4fa-b219-4e57-a736-6d5d56b9f242")
    @Override
    public void changeProperty(int row, String value) {
        super.changeProperty(row, value);
           switch (row) {
           case 1:
               this._element.getElement().setName(value);
               break;
           
           case 2:
               this._element.setDescription(value);
               break;
           
           case 3:
               for (ModelElement el : TEntityType.MdaTypes.STEREOTYPE_ELT.getExtendedElement()) {
                   if (value.contains(el.getUuid())) {
                       this._element.setDerivedFrom(TEntityType.instantiate((org.modelio.metamodel.uml.statik.Class) el));
        break;
        } else {
        this._element.setDerivedFrom(null);
        }
               }
               break;
           }
    }

    @objid ("ad7d906d-f5c6-48b9-aa72-2c5502bc473f")
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
                        return TEntityType.canInstantiate(element);
                    }
                });
    }

}
