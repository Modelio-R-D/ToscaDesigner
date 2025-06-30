package fr.softeam.toscadesigner.handlers.propertypages.serviceTemplate;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement.TArtifactTemplate;
import fr.softeam.toscadesigner.handlers.propertypages.core.ToscaElementPropertyPage;

@objid ("a4fc1851-2232-4ef2-b1c2-f9f07cfc4cc5")
public class TArtifactTemplatePropertyPage<T extends TArtifactTemplate> extends ToscaElementPropertyPage<T> {
    @objid ("ba3964b1-9e81-4e0c-a75a-fa0c9df64dc7")
    public  TArtifactTemplatePropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("382c297b-dbd4-4f45-b8f2-f3b5b9bfadb8")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {
        case 1:
            this._element.getElement().setName(value);
            break;
        case 2:
            this._element.setArtifactReferences(value);
            break;
        }
    }

    @objid ("11c30de4-0991-4e8d-a968-04ff3a947e9b")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
        table.addProperty("Name", this._element.getElement().getName());
        table.addProperty("Artifact References", this._element.getArtifactReferences());
    }

}
