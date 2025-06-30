package fr.softeam.toscadesigner.impl;

import org.modelio.api.module.AbstractJavaModule;
import org.modelio.api.module.IPeerModule;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.module.lifecycle.IModuleLifeCycleHandler;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.api.module.parameter.IParameterEditionModel;
import org.modelio.gproject.ramc.core.model.IModelComponent;
import org.modelio.gproject.ramc.core.packaging.IModelComponentContributor;
import org.modelio.metamodel.uml.infrastructure.Stereotype;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("f74fe6e0-48ad-4b16-9b56-5d97e8be9707")
public class ToscaDesignerModule extends AbstractJavaModule {
    @objid ("559a534f-a51e-4774-9c8b-048f214a3ec8")
    private static final String MODULE_IMAGE = "/res/icon/module.png";

    @objid ("394abf63-fb00-4a8d-af19-d4feb8beb4a9")
    private ToscaDesignerPeerModule peerModule = null;

    @objid ("1fcf7be8-4727-4133-88ea-4f79377a1e4d")
    private ToscaDesignerLifeCycleHandler lifeCycleHandler = null;

    @objid ("44aaffb0-c843-4e14-a1f7-957dc0d06675")
    private static ToscaDesignerModule instance;

    @objid ("2dcf4e29-1c02-4f12-8c85-c5b1fec36df3")
    public  ToscaDesignerModule(final IModuleContext moduleContext) {
        super(moduleContext);
        
        ToscaDesignerModule.instance = this;
        
        this.lifeCycleHandler = new ToscaDesignerLifeCycleHandler(this);
        this.peerModule = new ToscaDesignerPeerModule(this, moduleContext.getPeerConfiguration());
        init();
    }

    @objid ("53e797d9-cfad-4d2d-aaba-367a04dca423")
    @Override
    public IPeerModule getPeerModule() {
        return this.peerModule;
    }

    /**
     * Return the LifeCycleHandler attached to the current module. This handler is
     * used to manage the module lifecycle by declaring the desired implementation
     * for the start, select... methods.
     */
    @objid ("22bfe6b0-4809-4e4e-b225-ed850cd5b818")
    @Override
    public IModuleLifeCycleHandler getLifeCycleHandler() {
        return this.lifeCycleHandler;
    }

    /**
     * Method automatically called just after the creation of the module. The module
     * is automatically instanciated at the beginning of the MDA lifecycle and
     * constructor implementation is not accessible to the module developer. The
     * <code>init</code> method allows the developer to execute the desired
     * initialization.
     */
    @objid ("c92a2978-a3b9-4a8c-925b-21a6f42299b9")
    @Override
    public IParameterEditionModel getParametersEditionModel() {
        return super.getParametersEditionModel();
    }

    @objid ("50f01248-0b2c-4df0-b9b0-5313b21a0ae9")
    @Override
    public String getModuleImagePath() {
        return ToscaDesignerModule.MODULE_IMAGE;
    }

    @objid ("26f0eb41-d105-4c41-a30d-8a578687d728")
    public static ToscaDesignerModule getInstance() {
        // Automatically generated method. Please delete this comment before entering
        // specific code.
        return instance;
    }

    @objid ("2ef5b75f-ce7a-4699-a4d0-5ed3c8bc4800")
    @Override
    public IMdaExpert getMdaExpert(final Stereotype st) {
        // Generated code, please do not remove it
        IMdaExpert expert = getGeneratedMdaExpert(st);
        if (expert != null) {
            return expert;
        } // End generated code
        return null;
    }

    /**
     * Generated expert looking for a MDA expert in the generated MDA API.
     * @param st a stereotype owned by the current module.
     * @return a MDA expert belonging to the MDA API or <code>null</code>.
     */
    @objid ("f7d4d813-d18b-4e5f-b257-25fdf86ad84e")
    private IMdaExpert getGeneratedMdaExpert(final Stereotype st) {
        switch (st.getUuid()) {
        	default: return null;
        }
    }

    @objid ("ea1a50e7-3853-4792-8bb2-9c97a024dfa6")
    @Override
    public IModelComponentContributor getModelComponentContributor(IModelComponent mc) {
        return new ToscaDesignerComponentContributor(this);
    }

}
