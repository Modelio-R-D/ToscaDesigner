package fr.softeam.toscadesigner.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.modelio.api.modelio.mc.AbstractModelComponentContributor;
import org.modelio.api.modelio.model.IMetamodelExtensions;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.module.IModule;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.vcore.smkernel.mapi.MObject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import fr.softeam.toscadesigner.api.IToscaDesignerPeerModule;
import fr.softeam.toscadesigner.api.ToscaDesignerStereotypes;

@objid ("07f2d991-e0ea-40fa-bc40-885b76eb1dac")
public class ToscaDesignerComponentContributor extends AbstractModelComponentContributor {
    @objid ("0b5255cd-9324-4f13-9cba-464cb041ca15")
    private IModelingSession session = ToscaDesignerModule.getInstance().getModuleContext().getModelingSession();

    @objid ("e937ff61-03ed-4274-bfe1-6158a2d24fb2")
    public  ToscaDesignerComponentContributor(IModule module) {
        super(module);
    }

    @objid ("94fb7ece-8d2b-4010-b25f-0055876a3a06")
    private TagType getTagType(java.lang.Class<? extends MObject> metaclass, String tagTypeName) {
        IMetamodelExtensions metamodel = this.session.getMetamodelExtensions();
        return metamodel.getTagType(IToscaDesignerPeerModule.MODULE_NAME, tagTypeName, ToscaDesignerModule.getInstance()
                .getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(metaclass));
    }

    @objid ("a8a87fd1-aa52-43aa-8871-fdb2f32516ee")
    private Stereotype getStereotype(java.lang.Class<? extends MObject> metaclass, String stereotypeName) {
        IMetamodelExtensions metamodel = this.session.getMetamodelExtensions();
        return metamodel.getStereotype(IToscaDesignerPeerModule.MODULE_NAME, stereotypeName,
                ToscaDesignerModule.getInstance().getModuleContext().getModelioServices().getMetamodelService()
                        .getMetamodel().getMClass(metaclass));
    }

    @objid ("d6f21bd8-a8f6-40df-b266-38393fe33d2a")
    private NoteType getNoteType(java.lang.Class<? extends MObject> metaclass, String noteTypeName) {
        IMetamodelExtensions metamodel = this.session.getMetamodelExtensions();
        return metamodel.getNoteType(IToscaDesignerPeerModule.MODULE_NAME, noteTypeName,
                ToscaDesignerModule.getInstance().getModuleContext().getModelioServices().getMetamodelService()
                        .getMetamodel().getMClass(metaclass));
    }

    @objid ("a7011630-cf07-4dfb-8558-39d776a5fa9a")
    @Override
    public Set<Stereotype> getDependencyStereotypes() {
        Set<Stereotype> stereotypes = new HashSet<>();
        stereotypes.add(getStereotype(Class.class, ToscaDesignerStereotypes.TNODETYPE));
        stereotypes.add(getStereotype(Class.class, ToscaDesignerStereotypes.TRELATIONSHIPTYPE));
        stereotypes.add(getStereotype(Class.class, ToscaDesignerStereotypes.TARTIFACTTYPE));
        stereotypes.add(getStereotype(ModelElement.class, ToscaDesignerStereotypes.TINTERFACE));
        stereotypes.add(getStereotype(ModelElement.class, ToscaDesignerStereotypes.TOPERATION));
        stereotypes.add(getStereotype(Package.class, ToscaDesignerStereotypes.TGROUP));
        stereotypes.add(getStereotype(Class.class, ToscaDesignerStereotypes.TPOLICY));
        stereotypes.add(getStereotype(Class.class, ToscaDesignerStereotypes.TPOLICY_TYPE));
        stereotypes.add(getStereotype(Class.class, ToscaDesignerStereotypes.TCAPABILITY_DEFINITION));
        stereotypes.add(getStereotype(Class.class, ToscaDesignerStereotypes.TCAPABILITY_TYPE));
        stereotypes.add(getStereotype(Class.class, ToscaDesignerStereotypes.CAPABILITY_DEFINITIONS_TYPE));
        
        return stereotypes;
    }

    @objid ("db6572fe-3ed8-4b17-af59-9bd6e6f3d881")
    @Override
    public Set<MObject> getElements() {
        return Collections.emptySet();
    }

    @objid ("cc9f0c0b-8117-4bd5-b3f1-1d2657b5edea")
    @Override
    public Set<ExportedFileEntry> getFiles() {
        return Collections.emptySet();
    }

    @objid ("061c25af-a99d-4925-ba65-348e2fa5aa84")
    @Override
    public Set<NoteType> getNoteTypes() {
        // TODO Auto-generated method stub
        return Collections.emptySet();
    }

    @objid ("0520ef61-88e2-4d60-a161-4e2c6bd4aae1")
    @Override
    public Set<TagType> getTagTypes() {
        // TODO Auto-generated method stub
        return Collections.emptySet();
    }

}
