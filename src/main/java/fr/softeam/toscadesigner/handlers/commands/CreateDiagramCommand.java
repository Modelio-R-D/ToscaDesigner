package fr.softeam.toscadesigner.handlers.commands;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.standard.DiagramCreationStandardHandler;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("d090e250-dce0-4a9d-972a-5cb87dd0fc00")
public class CreateDiagramCommand extends DiagramCreationStandardHandler {
    @objid ("222a73c9-342f-4533-824c-82157f46fe67")
    @Override
    public boolean accept(final List<MObject> selectedElements, final IModule module) {
        // Generated call to the super method will check the scope conditions defined in
        // Studio.
        // Do not remove this call unless you need to take full control on the checks to
        // be carried out.
        // However you can safely extends the checked conditions by adding custom code.
        if (super.accept(selectedElements, module) == false) {
            return false;
        }
        return true;
    }

    @objid ("065c5fd2-82d6-4807-8908-c6d8e9841da5")
    @Override
    protected void postConfigureElement(final MObject newDiagram, final IModule module) {
        // This method is a hook called once the element is created and configured and
        // before the transaction is committed.
        // The super method applies the diagram style and open the diagram is needed and
        // should be first called in most cases.
        // Sub classes may redefine this method to make additional modifications.

        // Call the super method to apply the diagram style and open the diagram if
        // asked for.
        super.postConfigureElement(newDiagram, module);
        if (newDiagram instanceof AbstractDiagram) {
            AbstractDiagram diagram = (AbstractDiagram) newDiagram;

            IModuleContext moduleContext = module.getModuleContext();
            // moduleContext.getModelingSession().getModel().getDefaultNameService().setDefaultName(diagram,
            // diagram.getOrigin().getName() + " diagram");
            moduleContext.getModelioServices().getEditionService().openEditor(diagram);
        }
        // TODO Add additional behavior below
    }

}
