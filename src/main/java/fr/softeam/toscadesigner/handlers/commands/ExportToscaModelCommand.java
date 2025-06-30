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

import fr.softeam.toscadesigner.export.AbstractToscaFileGenerator;
import fr.softeam.toscadesigner.export.NodeTypeChecker;
import fr.softeam.toscadesigner.export.TopologyTemplateChecker;
import fr.softeam.toscadesigner.export.ToscaModelChecker;
import fr.softeam.toscadesigner.export.ToscaObjectTypeChecker;

@objid ("8a84facc-79c8-4a3e-9ae0-2a5310c69779")
public class ExportToscaModelCommand extends DefaultModuleCommandHandler {
    @objid ("7b47b7a1-f829-4217-8dde-3feeff75281f")
    private ILogService logger;

    @objid ("670e5efc-bdb5-499b-9735-52c1dcdd2556")
    private final ToscaObjectTypeChecker[] checkers = { new ToscaModelChecker(), new NodeTypeChecker(),
                        			new TopologyTemplateChecker() };

    @objid ("d43647a9-2319-464f-b59a-8242372555dd")
    @Override
    public void actionPerformed(List<MObject> selectedObjects, IModule module) {
        IModuleContext moduleContext = module.getModuleContext();
        logger = moduleContext.getLogService();
        if (selectedObjects == null || selectedObjects.isEmpty()) {
            logger.error("No objects selected");
            MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error", "No objects selected");
            return;
        }
        if (selectedObjects.size() > 1) {
            logger.error("Multiple objects selected");
            MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error",
                    "Select only one: TOSCA model, node type, or topology template");
            return;
        }
        
        MObject object = selectedObjects.get(0);
        AbstractToscaFileGenerator generator = null;
        for (ToscaObjectTypeChecker checker : checkers) {
            if (checker.isTypeOf(object)) {
                generator = checker.createGenerator(logger); // Choose appropriate generator based on type
                break;
            }
        }
        
        if (generator != null) {
            try {
                generator.generateContent(object);
            } catch (IOException e) {
                logger.error(e);
                MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error",
                        "Generation unfortunately failed");
                return;
            }
        } else {
            logger.error("No suitable generator found");
            MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error",
                    "Generation unfortunately failed, no suitable generator found ");
            return;
        }
    }

}
