package fr.softeam.toscadesigner.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.tools.DefaultBoxTool;

@objid ("e884de00-692d-4551-952a-7b96305b35a8")
public class handlers extends DefaultBoxTool {
    @objid ("b4d29356-b325-4801-b1f0-ed8f3b01a4c1")
    @Override
    public boolean acceptElement(final IDiagramHandle diagramHandle, final IDiagramGraphic targetNode) {
        // TODO Auto-generated method stub
        return false;
    }

    @objid ("bb194a01-adcf-4c60-98b8-fd46bb150531")
    @Override
    public void actionPerformed(final IDiagramHandle diagramHandle, final IDiagramGraphic parent, final Rectangle rect) {
        // TODO implement the  handler. 
        MessageDialog.openInformation(Display.getDefault().getActiveShell(), "", "Tool not implemented!");
    }

}
