package fr.softeam.toscadesigner.export;

import java.io.FileWriter;
import java.io.IOException;
import com.github.jknack.handlebars.HandlebarsException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.module.context.log.ILogService;
import org.modelio.vcore.smkernel.mapi.MObject;

import fr.softeam.toscadesigner.export.util.ExportErrorHandler;

@objid ("25521c06-6c79-4148-89ee-f4e056e7d0da")
public class ToscaFileGenerator extends AbstractToscaFileGenerator {
    @objid ("52396f91-0550-447c-a930-a966c6c568a5")
    private static final String[] TOSCA_FILE_EXTENSIONS = { "*.tosca" };

    @objid ("169bcf1b-ca40-4c0d-952e-fc61c78c6d93")
    public  ToscaFileGenerator(ILogService logger) {
        setLogger(logger);
    }

    @objid ("5bc507c3-edf4-4540-9ae6-940802ed1bbe")
    @Override
    protected String getFileType() {
        return "TOSCA file";
    }

    @objid ("860129ad-8e0a-487c-9e3f-e4204a995ea8")
    @Override
    protected String[] getFileExtensions() {
        return TOSCA_FILE_EXTENSIONS;
    }

    @objid ("296eeef8-dd7f-44c5-9628-c7bea5ff7cf2")
    @Override
    public void generateContent(MObject object) throws IOException {
        String filePath = saveToFile(getFileExtensions(), getFileType());
        if (filePath != null) {
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                String content = renderTemplate(handlebars, object);
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

}
