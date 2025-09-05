package fr.softeam.toscadesigner.handlers.tools;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.softeam.toscadesigner.api.IToscaDesignerPeerModule;
import fr.softeam.toscadesigner.api.automatic.standard.staticdiagram.ServiceTemplateDiagram;
import fr.softeam.toscadesigner.api.automatic.standard.staticdiagram.TopologyTemplateDiagram;
import fr.softeam.toscadesigner.impl.ToscaDesignerModule;
import org.modelio.api.modelio.IModelioServices;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.api.modelio.diagram.dg.IDiagramDG;
import org.modelio.api.modelio.diagram.style.IStyleHandle;
import org.modelio.api.modelio.diagram.tools.DefaultBoxTool;
import org.modelio.metamodel.diagrams.StaticDiagram;

@objid ("a21c4bc4-6a0a-43e5-9eb0-fd739505e023")
public abstract class CreateSubModelTool extends DefaultBoxTool {
    @objid ("80a8c047-42b8-4460-a8ad-808fcfd7a0e7")
    public boolean acceptElement(final IDiagramGraphic targetNode) {
        return true;
    }

    @objid ("f533bb8f-ac01-410c-bf60-3fda3f9c1958")
    protected void openDiagram(ServiceTemplateDiagram diagram2) {
        IModelioServices modelioServices = ToscaDesignerModule.getInstance().getModuleContext().getModelioServices();
        StaticDiagram diagram = diagram2.getElement();
        IDiagramService ds = modelioServices.getDiagramService();

        try (IDiagramHandle handler = ds.getDiagramHandle(diagram);) {
            IDiagramDG dg = handler.getDiagramNode();

            for (IStyleHandle style : ds.listStyles()) {
                if (style.getName().equals(IToscaDesignerPeerModule.TOSCA_STYLE)) {
                    dg.setStyle(style);
                    break;
                }
            }

            handler.save();
            handler.close();

            modelioServices.getEditionService().openEditor(diagram);
        }
    }

    @objid ("0ce9fb98-0d0c-4319-b8bc-6c964fd9e8ca")
    protected void openDiagram(TopologyTemplateDiagram diagram2) {
        IModelioServices modelioServices = ToscaDesignerModule.getInstance().getModuleContext().getModelioServices();
        StaticDiagram diagram = diagram2.getElement();
        IDiagramService ds = modelioServices.getDiagramService();

        try (IDiagramHandle handler = ds.getDiagramHandle(diagram);) {
            IDiagramDG dg = handler.getDiagramNode();

            for (IStyleHandle style : ds.listStyles()) {
                if (style.getName().equals(IToscaDesignerPeerModule.TOSCA_STYLE)) {
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
