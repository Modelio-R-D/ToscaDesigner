package fr.softeam.toscadesigner.handlers.propertypages.topologyTemplate;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TDeploymentArtifacts;
import fr.softeam.toscadesigner.handlers.propertypages.core.ToscaElementPropertyPage;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;

@objid ("8d4b2254-4976-4ee7-87c2-affa70d6fb06")
public class TDeploymentArtifactsPropertyPage<T extends TDeploymentArtifacts> extends ToscaElementPropertyPage<T> {
    @objid ("2c29cefb-11cb-4023-96cb-69d8d6bb9428")
    public  TDeploymentArtifactsPropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("d46cffe5-12e5-47b4-8177-ade18b28c230")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {
        case 1:
            this._element.getElement().setName(value);
            break;

        }
    }

    @objid ("ad4871aa-1c85-4807-91b5-397f8d92b45a")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
        table.addProperty("Name", _element.getElement().getName());
    }

}
