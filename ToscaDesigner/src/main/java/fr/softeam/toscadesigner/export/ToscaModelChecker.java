package fr.softeam.toscadesigner.export;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.softeam.toscadesigner.api.IToscaDesignerPeerModule;
import fr.softeam.toscadesigner.api.tosca.standard.package_.ToscaModel;
import org.modelio.api.module.context.log.ILogService;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("324798ff-0dad-4ce6-8d34-8f0b9df154e1")
public class ToscaModelChecker implements ToscaObjectTypeChecker {
    @objid ("63b822ca-c10e-4a47-9a3b-4894cbdbc422")
    @Override
    public boolean isTypeOf(MObject object) {
        if(!(object instanceof ModelElement)) {
            return false;
        }
        ModelElement element = (ModelElement) object;
        return element.isStereotyped(IToscaDesignerPeerModule.MODULE_NAME, ToscaModel.STEREOTYPE_NAME);
    }

    @objid ("4da92ca3-ebb1-43b7-8042-af607a689727")
    @Override
    public AbstractToscaFileGenerator createGenerator(ILogService logger) {
        return new CsarFileGenerator(logger);
    }

}
