package fr.softeam.toscadesigner.handlers.propertypages.types;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TArtifactType;
import fr.softeam.toscadesigner.handlers.propertypages.core.ToscaElementPropertyPage;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;

@objid ("b7b1075e-0895-401d-bd4e-745225a8ec49")
public class TArtifactTypePropertyPage<T extends TArtifactType> extends ToscaElementPropertyPage<T> {
    @objid ("d0ef71ad-91c3-4915-ba66-643a82d7ea42")
    public  TArtifactTypePropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("8c5a2087-cc56-4d45-ac59-74b0e403498d")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {
        case 1:
            break;

        }
    }

    @objid ("84179c1e-4c06-424f-9a50-a1dadb4eccfc")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
    }

}
