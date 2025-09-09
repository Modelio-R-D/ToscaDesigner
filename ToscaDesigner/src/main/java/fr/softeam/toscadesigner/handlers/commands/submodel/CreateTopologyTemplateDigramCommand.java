package fr.softeam.toscadesigner.handlers.commands.submodel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.tools.DefaultBoxTool;

@objid ("4a2c7b64-4169-46dd-b413-5e7ed10fca14")
public class CreateTopologyTemplateDigramCommand extends DefaultBoxTool {
    @objid ("7d89f13b-0aa4-46b8-9029-54a1a763692f")
    @Override
    public boolean acceptElement(final IDiagramHandle diagramHandle, final IDiagramGraphic targetNode) {
        // TODO Auto-generated method stub
        return false;
    }

    @objid ("f009a01a-b8e4-4ad9-a4d4-a69f09cd860b")
    @Override
    public void actionPerformed(final IDiagramHandle diagramHandle, final IDiagramGraphic parent, final Rectangle rect) {
        // TODO implement the CreateTopologyTemplateDigramCommand handler. 
        MessageDialog.openInformation(Display.getDefault().getActiveShell(), "CreateTopologyTemplateDigramCommand", "Tool not implemented!");
    }

}
