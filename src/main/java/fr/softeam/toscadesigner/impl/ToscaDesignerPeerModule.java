package fr.softeam.toscadesigner.impl;

import org.modelio.api.module.IPeerModule;
import org.modelio.api.module.context.configuration.IModuleAPIConfiguration;
import org.modelio.vbasic.version.Version;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("483febc5-912f-4855-87ab-978908d6e762")
public class ToscaDesignerPeerModule implements IPeerModule {
    @objid ("523199a1-6406-4802-b3c1-4844597e8b3e")
    private IModuleAPIConfiguration peerConfiguration;

    @objid ("cd208989-cd06-44fe-a4d5-6525cec196f3")
    private ToscaDesignerModule module = null;

    @objid ("66bbce75-6ed9-4b6c-9942-5d563d773b94")
    public  ToscaDesignerPeerModule(final ToscaDesignerModule module, final IModuleAPIConfiguration peerConfiguration) {
        this.module = module;
        this.peerConfiguration = peerConfiguration;
    }

    @objid ("0d69d19f-dc8e-4651-aa8a-edab3ad5cc3e")
    public IModuleAPIConfiguration getConfiguration() {
        return this.peerConfiguration;
    }

    @objid ("31d25c75-ec7c-4667-bc2d-57c1f83f62f1")
    public String getDescription() {
        return this.module.getDescription();
    }

    @objid ("aec01f1b-9749-4c2f-ae39-ea4afdd8f7c8")
    public String getName() {
        return this.module.getName();
    }

    @objid ("8a27d4ef-ff55-42cd-9929-1c163e695ef9")
    public Version getVersion() {
        return this.module.getVersion();
    }

    @objid ("5c2b8879-737f-432d-8551-83e6dfe2b29b")
    void init() {
        
    }

}
