package fr.softeam.toscadesigner.api;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.IPeerModule;

@objid ("0b3043c0-695b-42e5-a69a-8298dc63b89c")
public interface IToscaDesignerPeerModule extends IPeerModule {
    @objid ("40e56225-331d-4513-ba49-a71b06c20f83")
    public static final String MODULE_NAME = "ToscaDesigner";

    @objid ("c4c43a05-9347-486a-856d-1adce593d9d7")
    public static final String TOSCA_STYLE = "ToscaStyle";
}

