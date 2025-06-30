package fr.softeam.toscadesigner.handlers.tools;

import java.util.List;

import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramLink.LinkRouterKind;
import org.modelio.api.modelio.diagram.ILinkRoute;
import org.modelio.api.modelio.diagram.tools.DefaultLinkTool;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Classifier;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.api.tosca.standard.association.TRelationshipTemplate;
import fr.softeam.toscadesigner.api.tosca.standard.associationend.TRelationshipTemplateEnd;
import fr.softeam.toscadesigner.impl.ToscaDesignerModule;

@objid ("22c4b9c3-4797-467a-905f-990f3d3d7c5a")
public class RelationshipTemplateTool extends DefaultLinkTool {
    @objid ("17d21ab6-098b-47c2-ade1-4da73adc63b6")
    @Override
    public boolean acceptFirstElement(final IDiagramHandle diagramHandle, final IDiagramGraphic targetNode) {
        return true;
    }

    @objid ("fddb1955-5834-4bbf-8680-c0313b6e973f")
    @Override
    public boolean acceptSecondElement(final IDiagramHandle diagramHandle, final IDiagramGraphic originNode, final IDiagramGraphic targetNode) {
        return true;
    }

    @objid ("933a3bc6-c126-4e84-bcfb-96dd2c01c131")
    @Override
    public void actionPerformed(IDiagramHandle diagramHandle, IDiagramGraphic source, IDiagramGraphic target, LinkRouterKind routerKInd, ILinkRoute route) {
        IModelingSession session = ToscaDesignerModule.getInstance().getModuleContext().getModelingSession();
        try (ITransaction transaction = session.createTransaction(" RelationshipTemplate")) {
            Association newAssoc = session.getModel().createAssociation((Classifier) source.getElement(),
                    (Classifier) target.getElement(), "");
            newAssoc.getExtension().add(TRelationshipTemplate.MdaTypes.STEREOTYPE_ELT);
        
            for (AssociationEnd end : newAssoc.getEnd()) {
                end.getExtension().add(TRelationshipTemplateEnd.MdaTypes.STEREOTYPE_ELT);
            }
        
            List<IDiagramGraphic> graphics = diagramHandle.unmask(newAssoc, 0, 0);
            if (graphics.size() > 0 && graphics.get(0) instanceof IDiagramLink) {
                IDiagramLink link = (IDiagramLink) graphics.get(0);
                link.setRouterKind(routerKInd);
                link.setRoute(route);
            }
        
            diagramHandle.save();
            diagramHandle.close();
            transaction.commit();
        
        }
    }

}
