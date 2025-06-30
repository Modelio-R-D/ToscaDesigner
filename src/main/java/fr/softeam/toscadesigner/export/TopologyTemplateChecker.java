package fr.softeam.toscadesigner.export;

import org.modelio.api.module.context.log.ILogService;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.api.IToscaDesignerPeerModule;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TTopologyTemplate;

@objid ("56215833-c85a-4c5a-869d-d955dfe474cd")
public class TopologyTemplateChecker implements ToscaObjectTypeChecker {
    @objid ("87ad2c0b-c237-47f4-9fa1-4647e51a0a34")
    @Override
    public boolean isTypeOf(MObject object) {
        if(!(object instanceof ModelElement)) {
            return false;
        }
        ModelElement element = (ModelElement) object;
        return element.isStereotyped(IToscaDesignerPeerModule.MODULE_NAME, TTopologyTemplate.STEREOTYPE_NAME);
    }

    @objid ("1005a7b6-5261-4e7d-8a16-d6cbc1e3c5dc")
    @Override
    public AbstractToscaFileGenerator createGenerator(ILogService logger) {
        return new ToscaFileGenerator(logger);
    }

}
