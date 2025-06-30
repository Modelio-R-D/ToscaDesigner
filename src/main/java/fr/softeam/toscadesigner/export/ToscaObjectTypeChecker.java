package fr.softeam.toscadesigner.export;

import org.modelio.api.module.context.log.ILogService;
import org.modelio.vcore.smkernel.mapi.MObject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("72a0ec44-4986-4d12-9ef3-9986a8c88162")
public interface ToscaObjectTypeChecker {
    @objid ("dd3b6d47-bafd-47e2-930c-16cda7ca2dda")
    boolean isTypeOf(MObject object);

    @objid ("94fb15e7-f607-4abc-b91b-2b7408d99f59")
    AbstractToscaFileGenerator createGenerator(ILogService logger);
}

