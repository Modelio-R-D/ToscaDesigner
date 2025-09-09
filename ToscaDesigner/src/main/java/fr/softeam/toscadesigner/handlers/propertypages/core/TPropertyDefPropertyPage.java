package fr.softeam.toscadesigner.handlers.propertypages.core;

import java.util.Arrays;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.softeam.toscadesigner.api.tosca.standard.attribute.PropertyDefinitionType;
import fr.softeam.toscadesigner.api.tosca.standard.attribute.TPropertyDef;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.Metamodel;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("99fab3ef-82d7-47d9-8681-d6a7ad6f9726")
public class TPropertyDefPropertyPage<T extends TPropertyDef> extends ToscaElementPropertyPage<T> {
    @objid ("ef03f97a-12cf-45ae-8238-bb5d03fd1d0b")
    public  TPropertyDefPropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("3d137229-4918-493c-8a88-c716f5298afc")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {

        case 1:
            this._element.getElement().setName(value);
            break;

        case 2:
            for (ModelElement el : PropertyDefinitionType.MdaTypes.STEREOTYPE_ELT.getExtendedElement()) {
                if (value.contains(el.getUuid())) {
                    this._element.setType(
                            PropertyDefinitionType.instantiate((org.modelio.metamodel.uml.statik.Attribute) el));
                    break;
                } else {
                    this._element.setType(null);
                }
            }
            break;
        case 3:
            this._element.getElement().setValue(value);
            break;

        }
    }

    @objid ("b85d9ab2-9d2a-4691-ba9e-135681d9ec56")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
        table.addProperty("Name", _element.getElement().getName());
        table.addProperty("Type", this._element.getType() != null ? this._element.getType().getElement(): null,
                Arrays.asList(Metamodel.getMClass("Attribute")), new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return PropertyDefinitionType.canInstantiate(element);
                    }
                });        

        table.addProperty("Value", _element.getElement().getValue());
    }

}
