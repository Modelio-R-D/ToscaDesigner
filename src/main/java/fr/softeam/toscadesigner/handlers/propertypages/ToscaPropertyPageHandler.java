package fr.softeam.toscadesigner.handlers.propertypages;

import java.util.List;

import org.modelio.api.module.IModule;
import org.modelio.api.module.propertiesPage.AbstractModulePropertyPage;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("605b30e3-8090-4ad4-af23-0f35cc28c69f")
public class ToscaPropertyPageHandler extends AbstractModulePropertyPage {
    @objid ("b05cb9e6-0f59-442c-8827-5b968d1677b0")
    public  ToscaPropertyPageHandler(final IModule module, final String name, final String label, final String bitmap) {
        super(module, name, label, bitmap);
    }

    @objid ("fe3bb8ab-07c1-4f16-be18-525bd8803ac5")
    @Override
    public void update(final List<MObject> selectedElements, final IModulePropertyTable table) {
        if ((selectedElements != null) && (selectedElements.size() > 0) && (selectedElements.get(0) != null)
                && (selectedElements.get(0) instanceof ModelElement)) {
        
            ModelElement selectedElement = ((ModelElement) selectedElements.get(0));
        
            IPropertyContent propertyPage = getPropertyPage(selectedElement);
        
            if (propertyPage != null) {
                propertyPage.update(table);
            }
        }
    }

    @objid ("0dea3732-72a4-4d81-9157-dc02fc6c2066")
    @Override
    public void changeProperty(final List<MObject> selectedElements, final int row, final String value) {
        if ((selectedElements != null) && (selectedElements.size() > 0)
                && (selectedElements.get(0) instanceof ModelElement)) {
            ModelElement selectedElement = ((ModelElement) selectedElements.get(0));
        
            IPropertyContent propertyPage = getPropertyPage(selectedElement);
        
            if (propertyPage != null) {
                propertyPage.changeProperty(row, value);
            }
        }
    }

    @objid ("18f839de-f6ed-41ce-b4f1-9385a5b32dd5")
    private IPropertyContent getPropertyPage(ModelElement elt) {
        Object result = ToscaDesignerPropertyPageFactory.instantiatePropertyPage(elt);
        if (result instanceof IPropertyContent) {
            return (IPropertyContent) result;
        }
        return null;
    }

}
