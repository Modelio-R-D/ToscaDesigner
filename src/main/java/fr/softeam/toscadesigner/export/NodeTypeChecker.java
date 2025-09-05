package fr.softeam.toscadesigner.export;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.softeam.toscadesigner.api.IToscaDesignerPeerModule;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeType;
import org.modelio.api.module.context.log.ILogService;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("e0ed7350-ca3f-44e6-8dfe-d293953559ef")
public class NodeTypeChecker implements ToscaObjectTypeChecker {
    @objid ("3c6e1d4d-b40d-4939-b205-44407fa4260d")
    @Override
    public boolean isTypeOf(MObject object) {
        if(!(object instanceof ModelElement)) {
            return false;
        }
        ModelElement element = (ModelElement) object;
        return element.isStereotyped(IToscaDesignerPeerModule.MODULE_NAME, TNodeType.STEREOTYPE_NAME);
    }

    @objid ("787ab462-9dfb-4ea0-9ee4-f05a66187b0c")
    @Override
    public AbstractToscaFileGenerator createGenerator(ILogService logger) {
        return new ToscaFileGenerator(logger);
    }

}
