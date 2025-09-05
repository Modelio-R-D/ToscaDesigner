package fr.softeam.toscadesigner.handlers.commands.submodel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.softeam.toscadesigner.api.IToscaDesignerPeerModule;
import fr.softeam.toscadesigner.impl.ToscaDesignerModule;
import org.modelio.api.modelio.IModelioServices;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.api.modelio.diagram.dg.IDiagramDG;
import org.modelio.api.modelio.diagram.style.IStyleHandle;
import org.modelio.api.module.command.standard.DiagramCreationStandardHandler;
import org.modelio.metamodel.diagrams.AbstractDiagram;

@objid ("9d7d5cf7-a1b4-44da-ad66-f3ef94ca0a7f")
public class CreateSubModelCommand extends DiagramCreationStandardHandler {
    @objid ("99e09f62-56ba-4098-b3e2-57461e8b605e")
    protected void openDiagram(AbstractDiagram diagram) {
        IModelioServices modelioServices = ToscaDesignerModule.getInstance().getModuleContext().getModelioServices();
        IDiagramService ds = modelioServices.getDiagramService();

        try(  IDiagramHandle handler = ds.getDiagramHandle(diagram);){
            IDiagramDG dg = handler.getDiagramNode();

            for (IStyleHandle style : ds.listStyles()){
                if (style.getName().equals(IToscaDesignerPeerModule.TOSCA_STYLE)){
                    dg.setStyle(style);
                    break;
                }
            }
            handler.save();
            handler.close();

            modelioServices.getEditionService().openEditor(diagram);
        }
    }

}
