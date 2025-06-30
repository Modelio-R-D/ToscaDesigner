package fr.softeam.toscadesigner.handlers.commands.submodel;

import java.util.List;

import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.IModule;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.vcore.smkernel.mapi.MObject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.api.automatic.standard.staticdiagram.ServiceTemplateDiagram;
import fr.softeam.toscadesigner.api.tosca.standard.package_.TServiceTemplate;

@objid ("655bb233-752c-4077-a575-77dcb91910a0")
public class CreateServiceTemplateDigramCommand extends CreateSubModelCommand {
    @objid ("b733d91b-9d9e-4cb9-88ae-1ccba7ddcb03")
    @Override
    public void actionPerformed(final List<MObject> selectedElements, final IModule module) {
        org.modelio.metamodel.uml.statik.Package packageOwner = (org.modelio.metamodel.uml.statik.Package) selectedElements.get(0);
        
        IModuleContext moduleContext = module.getModuleContext();
        IModelingSession session = moduleContext.getModelingSession();
        
        try( ITransaction transaction = session.createTransaction("Create Service Template")){
        
            TServiceTemplate subModel = TServiceTemplate.create(session);
            ServiceTemplateDiagram diagram = ServiceTemplateDiagram.create(session);
        
            packageOwner.getOwnedElement().add(subModel.getElement());
            subModel.getElement().getProduct().add(diagram.getElement());
            
            subModel.getElement().setName("Service Template");
            diagram.getElement().setName(subModel.getElement().getName() + " diagram");            
            this.openDiagram(diagram.getElement());
        
            transaction.commit();
            
        }
    }

}
