package fr.softeam.toscadesigner.export.lakeside;

import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.module.context.log.ILogService;
import org.modelio.vcore.smkernel.mapi.MObject;

import com.github.jknack.handlebars.HandlebarsException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.export.AbstractToscaFileGenerator;
import fr.softeam.toscadesigner.export.util.ExportErrorHandler;
import fr.softeam.toscadesigner.export.registry.HandlebarsHelperRegistry;
import fr.softeam.toscadesigner.export.util.TemplateConstants;
import fr.softeam.toscadesigner.export.checker.TopologyTemplateChecker;
import fr.softeam.toscadesigner.export.lakeside.registry.LakesideHandlebarsHelperRegistry;

@objid("1e65fdd7-7b55-4d44-bec4-2dca7dd6e95e")
public class LakesideLabsDseFileGenerator extends AbstractToscaFileGenerator {
    private static final String[] DSE_FILE_EXTENSIONS = { "*.dse.yaml", "*.yaml", "*.yml" };

    private final TopologyTemplateChecker topologyChecker = new TopologyTemplateChecker();

    public LakesideLabsDseFileGenerator(ILogService logger) {
        setLogger(logger);
    }

    @Override
    protected HandlebarsHelperRegistry createHelperRegistry(ILogService logger) {
        // Use Lakeside-specific helper registry
        // Note: This returns a LakesideHandlebarsHelperRegistry but the base class expects HandlebarsHelperRegistry
        // Since we need different helper registration, we directly set handlebars instead
        LakesideHandlebarsHelperRegistry lakesideRegistry = new LakesideHandlebarsHelperRegistry(logger);
        // Directly configure handlebars so we bypass the base class registry
        this.handlebars = lakesideRegistry.createConfiguredHandlebars();
        // Return null since we've already configured handlebars
        return null;
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
            logInfo("Lakeside Labs DSE export cancelled by user");
            return;
        }

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            String content = renderTemplate(handlebars, object, TemplateConstants.LAKESIDE_DSE_TEMPLATE);
            fileWriter.write(content);
            MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "Success",
                    getFileType() + " saved successfully");
        } catch (IOException ex) {
            ExportErrorHandler.handleIoException(getLogger(), ex, getFileType(), filePath, object, this.getClass());
            throw ex;
        } catch (HandlebarsException ex) {
            ExportErrorHandler.handleHandlebarsException(getLogger(), ex, getFileType(), object, this.getClass());
        } catch (NullPointerException ex) {
            ExportErrorHandler.handleNullPointerException(getLogger(), ex, getFileType(), object, this.getClass());
        }
    }
}
