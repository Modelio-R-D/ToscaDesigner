package fr.softeam.toscadesigner.export;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import com.github.jknack.handlebars.HandlebarsException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.module.context.log.ILogService;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("a5b394f4-d77e-46cc-b636-a67931ee5fe8")
public class CsarFileGenerator extends AbstractToscaFileGenerator {
    @objid ("1262333c-b8c6-478a-afca-ad376e525aa6")
    private static final String[] CSAR_FILE_EXTENSIONS = { "*.csar" };

    @objid ("bcfa835b-8879-4f7d-b443-8e14594377d6")
    private ILogService logger;

    @objid ("85b1c04d-d7bb-4a3e-870a-ad39c5803f85")
    public  CsarFileGenerator(ILogService logger) {
        this.logger = logger;
    }

    @objid ("01ec2a85-d9ae-4feb-8820-0041e8b9f90b")
    @Override
    protected String[] getFileExtensions() {
        return CSAR_FILE_EXTENSIONS;
    }

    @objid ("68c5dbf7-48b7-42a6-bf67-eaab2e2c9c6d")
    @Override
    public void generateContent(MObject toscaModel) throws IOException {
        try {
            String filePath = saveToFile(CSAR_FILE_EXTENSIONS, "CSAR file");

            if (filePath != null) {
                try (FileOutputStream fos = new FileOutputStream(filePath);
                        ZipOutputStream zos = new ZipOutputStream(fos)) {

                    // Create _definitions directory
                    zos.putNextEntry(new ZipEntry("_definitions/"));
                    zos.closeEntry();

                    // Create nodetypes directory
                    zos.putNextEntry(new ZipEntry("nodetypes/"));
                    zos.closeEntry();

                    // Create servicetemplates directory
                    zos.putNextEntry(new ZipEntry("servicetemplates/"));
                    zos.closeEntry();

                    // Create TOSCA-Metadata directory
                    zos.putNextEntry(new ZipEntry("TOSCA-Metadata/"));
                    zos.closeEntry();

                    // Add TOSCA-Metadata content
                    addToscaMetadata(zos);

                    // Add content to other directories
                    addDefinitions(zos, toscaModel);
                    addNodeTypes(zos, toscaModel);
                    addServiceTemplates(zos, toscaModel);

                    MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "Success",
                            "CSAR file generated successfully");
                }
            }
        } catch (IOException e) {
            logger.error(e);
            MessageDialog.openError(Display.getCurrent().getActiveShell(), "CSAR export error",
                    e.getLocalizedMessage());
        } catch (HandlebarsException ex) {
               logger.error(ex);
               MessageDialog.openError(Display.getCurrent().getActiveShell(), "Handlebars Error",
                       "An error occurred while rendering the Handlebars template: " + ex.getMessage());
           } catch (NullPointerException ex) {
               logger.error(ex);
               MessageDialog.openError(Display.getCurrent().getActiveShell(), "NullPointerException",
                       ex.getStackTrace().toString());
           }
    }

    @objid ("c3e89048-1631-400c-8072-b5a40aa8d57c")
    private void addToscaMetadata(ZipOutputStream zos) throws IOException {
        ZipEntry entry = new ZipEntry("TOSCA-Metadata/TOSCA.meta");
        zos.putNextEntry(entry);
        String metadataContent = "TOSCA-Meta-File-Version: 1.0\n" + "CSAR-Version: 1.1\n"
                + "Created-By: Modelio MYRTUS TOSCA Designer\n" + "Entry-Definitions: _definitions/\n";
        zos.write(metadataContent.getBytes());
        zos.closeEntry();
    }

    @objid ("7897a1e1-90ae-4d93-8b7b-da0fcc8e7b70")
    private void addDefinitions(ZipOutputStream zos, MObject toscaModel) throws IOException {
        if (!(toscaModel instanceof ModelTree)) {
            logger.error("toscaModel is not an instance of ModelTree");
            return;
        }

        ModelTree modelPackage = (ModelTree) toscaModel;

        for (MObject element : modelPackage.getCompositionChildren()) {
            if (new NodeTypeChecker().isTypeOf(element) ||
                    new TopologyTemplateChecker().isTypeOf(element)) {
                String toscaContent = renderTemplate(handlebars, element);
                String fileName = element.getName() + ".tosca";

                ZipEntry entry = new ZipEntry("_definitions/" + fileName);
                zos.putNextEntry(entry);
                zos.write(toscaContent.getBytes());
                zos.closeEntry();
            }
        }
    }

    @objid ("759a4a79-33e7-4f6b-9521-52228b3ffac7")
    private void addNodeTypes(ZipOutputStream zos, MObject toscaModel) throws IOException {
        // Implement logic to add node type files
        // @Juan in the TNO example there are only icons in this part of the file
    }

    @objid ("5e0e1c48-6c65-49af-9875-0e5783a99827")
    private void addServiceTemplates(ZipOutputStream zos, MObject toscaModel) throws IOException {
        // Implement logic to add service template files
        // @Juan in the TNO example there is only an NGIX configuration file
    }

    @objid ("d6d1608f-8735-4683-a506-bd7a3e09b5fb")
    @Override
    protected String getFileType() {
        return "CSAR file";
    }

}
