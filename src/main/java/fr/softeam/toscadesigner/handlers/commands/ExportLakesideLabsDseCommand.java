package fr.softeam.toscadesigner.handlers.commands;

import java.io.IOException;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.module.context.log.ILogService;
import org.modelio.vcore.smkernel.mapi.MObject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.export.checker.TopologyTemplateChecker;
import fr.softeam.toscadesigner.export.lakeside.LakesideLabsDseFileGenerator;

@objid("a440dcf7-85b8-489c-9246-b781368bb3a4")
public class ExportLakesideLabsDseCommand extends DefaultModuleCommandHandler {
    private ILogService logger;
    private final TopologyTemplateChecker topologyChecker = new TopologyTemplateChecker();

    @Override
    public void actionPerformed(List<MObject> selectedObjects, IModule module) {
        IModuleContext moduleContext = module.getModuleContext();
        this.logger = moduleContext.getLogService();

        if (selectedObjects == null || selectedObjects.isEmpty()) {
            reportSelectionError("No objects selected");
            return;
        }
        if (selectedObjects.size() > 1) {
            reportSelectionError("Multiple objects selected. Select exactly one topology template.");
            return;
        }

        MObject object = selectedObjects.get(0);
        if (!this.topologyChecker.isTypeOf(object)) {
            String objectDesc = describeObjectForLog(object);
            logger.error("Lakeside Labs export requires a TTopologyTemplate, got: " + objectDesc);
            MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error",
                    "Select a single TTopologyTemplate to export to Lakeside Labs DSE format.");
            return;
        }

        LakesideLabsDseFileGenerator generator = new LakesideLabsDseFileGenerator(logger);
        try {
            generator.generateContent(object);
        } catch (IOException e) {
            String objectDesc = describeObjectForLog(object);
            logger.error("Generation failed for object: " + objectDesc);
            logger.error(e);
            MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error",
                    "DSE export failed: " + e.getLocalizedMessage());
        } catch (RuntimeException e) {
            String objectDesc = describeObjectForLog(object);
            logger.error("Generation runtime error for object: " + objectDesc);
            logger.error(e);
            MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error",
                    "DSE export failed: " + e.getMessage());
        }
    }

    private void reportSelectionError(String message) {
        logger.error(message);
        MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error", message);
    }

    private String describeObjectForLog(MObject object) {
        if (object == null)
            return "null";
        try {
            String name = (object instanceof org.modelio.metamodel.uml.infrastructure.ModelElement)
                    ? ((org.modelio.metamodel.uml.infrastructure.ModelElement) object).getName()
                    : object.toString();
            String id = object.getUuid() != null ? object.getUuid().toString() : "<no-uuid>";
            String mclass = object.getMClass() != null ? object.getMClass().getName() : "<no-mclass>";
            return String.format("name=%s, uuid=%s, mclass=%s", name, id, mclass);
        } catch (Exception ex) {
            logger.error("Failed to describe object for log: " + ex.toString());
            logger.error(ex);
            return object.toString();
        }
    }
}
