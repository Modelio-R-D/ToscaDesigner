package fr.softeam.toscadesigner.handlers.tools;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.softeam.toscadesigner.api.automatic.standard.staticdiagram.TopologyTemplateDiagram;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TTopologyTemplate;
import fr.softeam.toscadesigner.impl.ToscaDesignerModule;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.uml.statik.Package;

@objid ("b0602c3f-1a03-401a-82ea-a3a86d7f20bc")
public class CreateTopologyTemplateTool extends CreateSubModelTool {
    @objid ("fbc9f5c8-1b63-48a7-912b-b1cb166418ad")
    @Override
    public boolean acceptElement(IDiagramHandle arg0, IDiagramGraphic arg1) {
        // TODO Auto-generated method stub
        return super.acceptElement(arg1);
    }

    @objid ("135b3028-74bf-49bd-bfb4-08aa44bc144f")
    @Override
    public void actionPerformed(IDiagramHandle diagramHandle, IDiagramGraphic parent, Rectangle rect) {
        /*
                                                                                                 * ClassDiagram diag = (ClassDiagram) parent.getElement();
                                                                                                 * org.modelio.metamodel.uml.statik.Package packageOwner =
                                                                                                 * (org.modelio.metamodel.uml.statik.Package) diag .getOrigin();
                                                                                                 */
        Package packageOwner = (Package) parent.getElement().getCompositionOwner();

        IModuleContext moduleContext = ToscaDesignerModule.getInstance().getModuleContext();
        IModelingSession session = moduleContext.getModelingSession();

        try (ITransaction transaction = session.createTransaction("Create Topology Template")) {

            TTopologyTemplate subModel = TTopologyTemplate.create(session);
            TopologyTemplateDiagram diagram = TopologyTemplateDiagram.create(session);

            packageOwner.getOwnedElement().add(subModel.getElement());
            subModel.getElement().getProduct().add(diagram.getElement());

            subModel.getElement().setName("Topology Template");
            diagram.getElement().setName(subModel.getElement().getName() + " diagram");
            this.openDiagram(diagram);

            transaction.commit();
        }
    }

}
