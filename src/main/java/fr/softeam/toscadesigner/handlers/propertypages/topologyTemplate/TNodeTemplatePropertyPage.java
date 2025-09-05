package fr.softeam.toscadesigner.handlers.propertypages.topologyTemplate;

import java.util.Arrays;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.softeam.toscadesigner.api.tosca.standard.class_.RequirementsType;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TDeploymentArtifacts;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeTemplate;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeType;
import fr.softeam.toscadesigner.handlers.propertypages.core.ToscaElementPropertyPage;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.Metamodel;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("d7f945d4-ad69-488b-ab02-d36bf143c67f")
public class TNodeTemplatePropertyPage<T extends TNodeTemplate> extends ToscaElementPropertyPage<T> {
    @objid ("e542b2a7-de8e-4e55-81e7-9f0348a4c406")
    public  TNodeTemplatePropertyPage(T elt) {
        super(elt);
        // TODO Auto-generated constructor stub
    }

    @objid ("0346c114-bc05-41b6-b1c1-ce7480851d3c")
    @Override
    public void changeProperty(int row, String value) {
        switch (row) {
        case 1:
            this._element.getElement().setName(value);
            break;
        case 2:
            this._element.setMinInstances(Integer.valueOf(value));
            break;
        case 3:
            this._element.setMaxInstances(Integer.valueOf(value));
            break;

        case 4:
            for (ModelElement dep : TDeploymentArtifacts.MdaTypes.STEREOTYPE_ELT.getExtendedElement()) {
                if (value.contains(dep.getUuid())) {
                    this._element.setDeploymentArtifacts(
                            TDeploymentArtifacts.instantiate((org.modelio.metamodel.uml.statik.Class) dep));
                    break;
                } else {
                    this._element.setDeploymentArtifacts(null);
                }
            }
            break;

        case 5:
            for (ModelElement req : RequirementsType.MdaTypes.STEREOTYPE_ELT.getExtendedElement()) {
                if (value.contains(req.getUuid())) {
                    this._element.setRequirements(
                            RequirementsType.instantiate((org.modelio.metamodel.uml.statik.Class) req));
                    break;
                } else {
                    this._element.setRequirements(null);
                }
            }

            break;
        case 6:
            for (ModelElement nodet : TNodeType.MdaTypes.STEREOTYPE_ELT.getExtendedElement()) {
                if (value.contains(nodet.getUuid())) {
                    this._element.setNodeType(TNodeType.instantiate((org.modelio.metamodel.uml.statik.Class) nodet));
                    break;
                } else {
                    this._element.setNodeType(null);
                }
            }
            break;

        }
    }

    @objid ("6899bd7c-4a2d-4c66-aa85-6f7a8cb2467e")
    @SuppressWarnings("deprecation")
    @Override
    public void update(IModulePropertyTable table) {
        super.update(table);
        table.addProperty("Name", _element.getElement().getName());
        table.addProperty("Min Instance", String.valueOf(_element.getMinInstances()));
        table.addProperty("Max Instance", String.valueOf(_element.getMaxInstances()));

        // DeploymentArtifacts
        table.addProperty("Deployment Artifacts",
                this._element.getDeploymentArtifacts() != null ? this._element.getDeploymentArtifacts().getElement()
                        : null,
                Arrays.asList(Metamodel.getMClass("Class")), new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return TDeploymentArtifacts.canInstantiate(element);
                    }
                });

        // Requirements
        table.addProperty("Requirements",
                this._element.getRequirements() != null ? this._element.getRequirements().getElement() : null,
                Arrays.asList(Metamodel.getMClass("Class")), new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return RequirementsType.canInstantiate(element);
                    }
                });

        // Node Type
        table.addProperty("Node Type",
                this._element.getNodeType() != null ? this._element.getNodeType().getElement() : null,
                Arrays.asList(Metamodel.getMClass("Class")), new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return TNodeType.canInstantiate(element);
                    }
                });
    }

}
