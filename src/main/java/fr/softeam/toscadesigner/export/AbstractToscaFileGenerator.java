package fr.softeam.toscadesigner.export;

import java.io.IOException;
import java.io.StringWriter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.modelio.api.module.context.log.ILogService;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.export.registry.HandlebarsHelperRegistry;
import fr.softeam.toscadesigner.export.util.TemplateConstants;

@objid("e7453252-f578-4da1-815c-d2ce0e765130")
public abstract class AbstractToscaFileGenerator {
    @objid("a897a13e-b8dc-4f4f-b967-60133eb7f69d")
    protected Handlebars handlebars;

    @objid("7df196c3-8c6b-4da7-9831-3d2f51c3097f")
    private ILogService logger;

    /**
     * Initialize the logger and Handlebars instance.
     * Subclasses should call this in their constructor.
     */
    protected final void setLogger(ILogService logger) {
        this.logger = logger;
        HandlebarsHelperRegistry helperRegistry = createHelperRegistry(logger);
        if (helperRegistry != null) {
            this.handlebars = helperRegistry.createConfiguredHandlebars();
        }
        // If helperRegistry is null, subclass has already configured handlebars directly
    }

    /**
     * Factory method for creating the helper registry.
     * Subclasses can override to provide custom registries or return null if they configure handlebars directly.
     */
    protected HandlebarsHelperRegistry createHelperRegistry(ILogService logger) {
        return new HandlebarsHelperRegistry(logger);
    }

    protected ILogService getLogger() {
        return this.logger;
    }

    protected void logInfo(String message) {
        if (this.logger != null) {
            this.logger.info(message);
        }
    }

    @objid("e1bdb1e7-0783-441d-96e1-fdaa0f8e8514")
    protected abstract String getFileType();

    @objid("ebedc3d7-f673-4680-a8b3-159124af40c8")
    protected abstract String[] getFileExtensions();

    @objid("a064e044-abc6-430f-9a31-680bf8f13dad")
    public abstract void generateContent(MObject object) throws IOException;

    @objid("0a64efd3-9d89-46b9-91e8-40aaa00626a4")
    protected String saveToFile(String[] fileExtensions, String fileType) {
        FileDialog fileDialog = new FileDialog(Display.getCurrent().getActiveShell(), SWT.SAVE);
        fileDialog.setFilterExtensions(fileExtensions);

        String filePath = fileDialog.open();

        return filePath;
    }

    @objid("b6716c1e-6ab4-4dce-8bbe-f749a6185d60")
    protected String renderTemplate(Handlebars handlebars, Object data) throws IOException {
        return renderTemplate(handlebars, data, TemplateConstants.MAIN_TEMPLATE);
    }

    @objid("7c9a2f2f-e6e3-4c71-8db5-7b7dcbaa1cd0")
    protected String renderTemplate(Handlebars handlebars, Object data, String templateName) throws IOException {
        Template mainTemplate = handlebars.compile(templateName);
        try (StringWriter writer = new StringWriter()) {
            mainTemplate.apply(data, writer);
            return writer.toString();
        }
    }

    /**
     * Produce a concise description for logging purposes from an MObject.
     * Format: name=<name>, uuid=<uuid>, mclass=<mclass>
     */
    public String describeObject(MObject object) {
        return describeObjectStatic(object);
    }

    /**
     * Static version of describeObject for use in utility classes.
     */
    public static String describeObjectStatic(MObject object) {
        if (object == null) {
            return "null";
        }
        try {
            final String name = (object instanceof ModelElement) ? ((ModelElement) object).getName() : object.toString();
            final String id;
            String resolvedId;
            try {
                String uuid = object.getUuid();
                resolvedId = uuid != null ? uuid : "<no-uuid>";
            } catch (Throwable t) {
                resolvedId = "<uuid-unavailable>";
            }
            id = resolvedId;
            final String mclass = object.getMClass() != null ? object.getMClass().getName() : "<no-mclass>";
            return String.format("name=%s, uuid=%s, mclass=%s", name, id, mclass);
        } catch (Exception ex) {
            // Best-effort fallback
            return object.toString();
        }
    }
}
