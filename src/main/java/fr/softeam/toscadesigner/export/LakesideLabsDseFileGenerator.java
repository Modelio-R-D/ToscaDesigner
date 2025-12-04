package fr.softeam.toscadesigner.export;

import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.module.context.log.ILogService;
import org.modelio.vcore.smkernel.mapi.MObject;

import com.github.jknack.handlebars.HandlebarsException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid("1e65fdd7-7b55-4d44-bec4-2dca7dd6e95e")
public class LakesideLabsDseFileGenerator extends AbstractToscaFileGenerator {
    private static final String[] DSE_FILE_EXTENSIONS = { "*.dse.yaml", "*.yaml", "*.yml" };
    private static final String LAKESIDE_TEMPLATE = "_mainTemplate_lakeside_dse";

    private final ILogService logger;
    private final TopologyTemplateChecker topologyChecker = new TopologyTemplateChecker();

    public LakesideLabsDseFileGenerator(ILogService logger) {
        this.logger = logger;
        setLogger(logger);
    }

    @Override
    protected String getFileType() {
        return "Lakeside Labs DSE topology";
    }

    @Override
    protected String[] getFileExtensions() {
        return DSE_FILE_EXTENSIONS;
    }

    @Override
    public void generateContent(MObject object) throws IOException {
        if (!this.topologyChecker.isTypeOf(object)) {
            throw new IllegalArgumentException("Lakeside Labs DSE export only supports TTopologyTemplate elements.");
        }

        String filePath = saveToFile(getFileExtensions(), getFileType());
        if (filePath == null) {
            logger.info("Lakeside Labs DSE export cancelled by user");
            return;
        }

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            String content = renderTemplate(handlebars, object, LAKESIDE_TEMPLATE);
            fileWriter.write(content);
            MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "Success",
                    getFileType() + " saved successfully");
        } catch (IOException ex) {
            logAndReportError("IOException", object, ex);
            throw ex;
        } catch (HandlebarsException ex) {
            logAndReportError("HandlebarsException", object, ex);
            MessageDialog.openError(Display.getCurrent().getActiveShell(), "Handlebars Error",
                    "An error occurred while rendering the Lakeside Labs template: " + ex.getMessage());
        } catch (NullPointerException ex) {
            logAndReportError("NullPointerException", object, ex);
            MessageDialog.openError(Display.getCurrent().getActiveShell(), "NullPointerException",
                    "A NullPointerException occurred. See log for details.");
        }
    }

    private void logAndReportError(String errorType, MObject object, Exception ex) {
        String objDesc = describeObject(object);
        String genClass = this.getClass().getName();
        logger.error(String.format("%s while generating %s using %s for object=%s : %s", errorType, getFileType(),
                genClass, objDesc, ex.toString()));
        logger.error(ex);
    }
}
