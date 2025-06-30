package fr.softeam.toscadesigner.handlers;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.tools.DefaultBoxTool;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("7938abb1-7de8-40d2-b8c7-7fa0d02a6620")
public class handlers extends DefaultBoxTool {
    @objid ("9d8be3a3-372c-402e-a18c-dd22423ef67c")
    @Override
    public boolean acceptElement(final IDiagramHandle diagramHandle, final IDiagramGraphic targetNode) {
        // TODO Auto-generated method stub
        return false;
    }

    @objid ("db738081-21ba-4edb-898d-fccc3dc2af9d")
    @Override
    public void actionPerformed(final IDiagramHandle diagramHandle, final IDiagramGraphic parent, final Rectangle rect) {
        // TODO implement the  handler. 
        MessageDialog.openInformation(Display.getDefault().getActiveShell(), "", "Tool not implemented!");
    }

}
