package fr.softeam.toscadesigner.impl;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.softeam.toscadesigner.api.ToscaDesignerProxyFactory;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.modelio.api.modelio.mc.IModelComponentDescriptor;
import org.modelio.api.modelio.mc.IModelComponentService;
import org.modelio.api.module.lifecycle.DefaultModuleLifeCycleHandler;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.vbasic.version.Version;

@objid ("365c000d-ee50-4570-a858-4916436eefa8")
public class ToscaDesignerLifeCycleHandler extends DefaultModuleLifeCycleHandler {
    @objid ("b939d73f-d878-40c1-825b-ae7f77d28f04")
    private String _ramcVersion = "0.0.01";

    @objid ("4425dd59-664e-4147-82d4-0a71e1d42ff3")
    private String _toscaLibrary = "ToscaLibrary";

    @objid ("76af05c6-475e-42b7-b1bc-d7b9921a5dc1")
    public  ToscaDesignerLifeCycleHandler(final ToscaDesignerModule module) {
        super(module);
    }

    @objid ("4d7e0ff3-c99c-429d-93e2-47bac9ef947c")
    @Override
    public boolean start() throws ModuleException {
        try {
            ToscaDesignerProxyFactory
                    .initialize(ToscaDesignerModule.getInstance().getModuleContext().getModelingSession());
        } catch (fr.softeam.toscadesigner.impl.MdaProxyException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }

        installRamc();
        return super.start();
    }

    @objid ("3a18097b-3c02-435f-80a6-36347f71725f")
    @Override
    public void stop() throws ModuleException {
        super.stop();
    }

    /**
     * @return
     */
    @objid ("3031965b-1588-4f7d-b0c7-089ceb883f9d")
    public static boolean install(final String modelioPath, final String mdaPath) throws ModuleException {
        return DefaultModuleLifeCycleHandler.install(modelioPath, mdaPath);
    }

    @objid ("da65896b-7b30-4d3c-9ad5-f44df0e4ae11")
    @Override
    public boolean select() throws ModuleException {
        return super.select();
    }

    @objid ("381ad9d0-ae66-4108-a844-926dcb3daf05")
    @Override
    public void upgrade(final Version oldVersion, final Map<String, String> oldParameters) throws ModuleException {
        super.upgrade(oldVersion, oldParameters);
    }

    @objid ("91521d00-965a-456c-8016-69eb3c0245e8")
    @Override
    public void unselect() throws ModuleException {
        super.unselect();
    }

    @objid ("579b391d-2d31-4ba5-b5f4-ed5984e7df7d")
    private void installRamc() {
        Path mdaplugsPath = this.module.getModuleContext().getConfiguration().getModuleResourcesPath();

        final IModelComponentService modelComponentService = ToscaDesignerModule.getInstance().getModuleContext()
                .getModelioServices().getModelComponentService();
        for (IModelComponentDescriptor mc : modelComponentService.getModelComponents()) {
            if (mc.getName().equals(_toscaLibrary)) {
                if (new Version(mc.getVersion()).isOlderThan(new Version(this._ramcVersion))) {
                    modelComponentService.deployModelComponent(new File(mdaplugsPath
                            .resolve("res" + File.separator + "ramc" + File.separator + _toscaLibrary + ".ramc")
                            .toString()), new NullProgressMonitor());
                } else {
                    // Ramc already deployed...
                    return;
                }
            }
        }

        // No ramc found, deploy it
        modelComponentService.deployModelComponent(new File(mdaplugsPath
                .resolve("res" + File.separator + "ramc" + File.separator + _toscaLibrary + ".ramc").toString()),
                new NullProgressMonitor());
    }

}
