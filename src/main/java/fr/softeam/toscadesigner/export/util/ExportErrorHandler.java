package fr.softeam.toscadesigner.export.util;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.module.context.log.ILogService;
import org.modelio.vcore.smkernel.mapi.MObject;

import com.github.jknack.handlebars.HandlebarsException;

import fr.softeam.toscadesigner.export.AbstractToscaFileGenerator;

/**
 * Centralized error handling for export operations.
 * Logs errors and displays appropriate error dialogs to the user.
 */
public final class ExportErrorHandler {

    private ExportErrorHandler() {
        // Utility class
    }

    /**
     * Handle IOException during export operation.
     */
    public static void handleIoException(ILogService logger, Exception ex, String fileType, String filePath,
            MObject object, Class<?> generatorClass) {
        String objDesc = AbstractToscaFileGenerator.describeObjectStatic(object);
        String genClass = generatorClass.getName();
        logger.error(String.format("IOException while generating %s to path=%s using %s for object=%s : %s",
                fileType, filePath, genClass, objDesc, ex.toString()));
        logger.error(ex);
        MessageDialog.openError(Display.getCurrent().getActiveShell(), fileType + " export error",
                ex.getLocalizedMessage());
    }

    /**
     * Handle HandlebarsException during template rendering.
     */
    public static void handleHandlebarsException(ILogService logger, HandlebarsException ex, String fileType,
            MObject object, Class<?> generatorClass) {
        String objDesc = AbstractToscaFileGenerator.describeObjectStatic(object);
        String genClass = generatorClass.getName();
        logger.error(String.format("HandlebarsException while rendering %s using %s for object=%s : %s",
                fileType, genClass, objDesc, ex.toString()));
        logger.error(ex);
        MessageDialog.openError(Display.getCurrent().getActiveShell(), "Handlebars Error",
                "An error occurred while rendering the Handlebars template: " + ex.getMessage());
    }

    /**
     * Handle NullPointerException during export operation.
     */
    public static void handleNullPointerException(ILogService logger, NullPointerException ex, String fileType,
            MObject object, Class<?> generatorClass) {
        String objDesc = AbstractToscaFileGenerator.describeObjectStatic(object);
        String genClass = generatorClass.getName();
        logger.error(String.format("NullPointerException while generating %s using %s for object=%s : %s",
                fileType, genClass, objDesc, ex.toString()));
        logger.error(ex);
        MessageDialog.openError(Display.getCurrent().getActiveShell(), "NullPointerException",
                "A NullPointerException occurred. See log for details.");
    }

    /**
     * Handle generic exception during export operation.
     */
    public static void handleGenericException(ILogService logger, Exception ex, String fileType,
            MObject object, Class<?> generatorClass) {
        String objDesc = AbstractToscaFileGenerator.describeObjectStatic(object);
        String genClass = generatorClass.getName();
        logger.error(String.format("Exception while generating %s using %s for object=%s : %s",
                fileType, genClass, objDesc, ex.toString()));
        logger.error(ex);
        MessageDialog.openError(Display.getCurrent().getActiveShell(), fileType + " export error",
                "An error occurred: " + ex.getMessage());
    }
}
