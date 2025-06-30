package fr.softeam.toscadesigner.handlers.commands.submodel;

import java.util.List;

import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.IModule;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.vcore.smkernel.mapi.MObject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.api.automatic.standard.staticdiagram.TopologyTemplateDiagram;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TTopologyTemplate;

@objid ("13e27e1c-182e-493d-92fd-20f10e4c25c1")
public class CreateTopologyTemplateDiagramCommand extends CreateSubModelCommand {
    @objid ("642a3036-14b0-49d2-8b76-9089e2b2c9ca")
    @Override
    public void actionPerformed(final List<MObject> selectedElements, final IModule module) {
        org.modelio.metamodel.uml.statik.Package packageOwner = (org.modelio.metamodel.uml.statik.Package) selectedElements.get(0);
        
        IModuleContext moduleContext = module.getModuleContext();
        IModelingSession session = moduleContext.getModelingSession();
        
        try( ITransaction transaction = session.createTransaction("Create Topology Template")){
        
            TTopologyTemplate subModel = TTopologyTemplate.create(session);
            TopologyTemplateDiagram diagram = TopologyTemplateDiagram.create(session);
        
            packageOwner.getOwnedElement().add(subModel.getElement());
            subModel.getElement().getProduct().add(diagram.getElement());
        
            subModel.getElement().setName("Topology Template");
            diagram.getElement().setName(subModel.getElement().getName() + " diagram");            
            this.openDiagram(diagram.getElement());
        
            transaction.commit();
            
        }
    }

}
