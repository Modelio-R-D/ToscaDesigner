"""
Modelio Jython script: create ToscaModel + ServiceTemplate + TopologyTemplate.

Usage:
- Select a package in Modelio and run this script.
- Optionally adjust configuration below.
"""

from org.modelio.metamodel.uml.statik import Package
from org.modelio.metamodel.uml.infrastructure import ModelElement

from fr.softeam.toscadesigner.api.tosca.standard.package_ import TServiceTemplate
from fr.softeam.toscadesigner.api.tosca.standard.class_ import TTopologyTemplate
from fr.softeam.toscadesigner.api.tosca.infrastructure.modelelement import TParameter

MODULE_NAME = "ToscaDesigner"

CONFIG = {
    "tosca_model_name": "GeneratedToscaModel",
    "service_template_name": "GeneratedServiceTemplate",
    "topology_template_name": "GeneratedTopologyTemplate",
    "inputs": [
        {"name": "image", "type": "string", "required": True, "default": ""},
    ],
    "outputs": [
        {"name": "endpoint", "type": "string", "required": True, "default": ""},
    ],
}


def resolve_owner_package(selected):
    if selected is None:
        return None
    if isinstance(selected, Package):
        return selected
    if hasattr(selected, "getOwner") and selected.getOwner() is not None:
        owner = selected.getOwner()
        if isinstance(owner, Package):
            return owner
    return None


def apply_stereotype(element, stereotype_name):
    if not element.isStereotyped(MODULE_NAME, stereotype_name):
        element.addStereotype(MODULE_NAME, stereotype_name)


def create_parameter(session, parent_package, data):
    param_elt = session.getModel().createElement("Infrastructure.ModelElement")
    parent_package.getOwnedElement().add(param_elt)

    apply_stereotype(param_elt, TParameter.STEREOTYPE_NAME)

    name_value = data.get("name", "")
    type_value = data.get("type", "string")
    required_value = bool(data.get("required", False))

    param_elt.setProperty(MODULE_NAME, "TParameter", "name", name_value)
    param_elt.setProperty(MODULE_NAME, "TParameter", "type", type_value)
    param_elt.setProperty(MODULE_NAME, "TParameter", "required", "true" if required_value else "false")

    if "default" in data:
        param_elt.setProperty(MODULE_NAME, "TParameter", "default", str(data.get("default")))
    if "value" in data:
        param_elt.setProperty(MODULE_NAME, "TParameter", "value", str(data.get("value")))

    return TParameter.safeInstantiate(param_elt)


owner_package = resolve_owner_package(elt)
if owner_package is None:
    print("Error: Select a Package (or an element owned by a Package) before running this script.")
else:
    tx = session.createTransaction("Create Tosca service/topology templates")
    try:
        tosca_model_pkg = session.getModel().createElement("Standard.Package")
        tosca_model_pkg.setName(CONFIG["tosca_model_name"])
        owner_package.getOwnedElement().add(tosca_model_pkg)
        apply_stereotype(tosca_model_pkg, "ToscaModel")

        service_pkg = TServiceTemplate.create(session).getElement()
        service_pkg.setName(CONFIG["service_template_name"])
        tosca_model_pkg.getOwnedElement().add(service_pkg)
        service_proxy = TServiceTemplate.safeInstantiate(service_pkg)

        topology_cls = TTopologyTemplate.create(session).getElement()
        topology_cls.setName(CONFIG["topology_template_name"])
        tosca_model_pkg.getOwnedElement().add(topology_cls)
        topology_proxy = TTopologyTemplate.safeInstantiate(topology_cls)

        service_proxy.setTopologyTemplate(topology_proxy)

        input_count = 0
        for input_data in CONFIG.get("inputs", []):
            pxy = create_parameter(session, tosca_model_pkg, input_data)
            topology_proxy.addInputs(pxy)
            input_count += 1

        output_count = 0
        for output_data in CONFIG.get("outputs", []):
            pxy = create_parameter(session, tosca_model_pkg, output_data)
            topology_proxy.addOutputs(pxy)
            output_count += 1

        tx.commit()

        print("Success: Tosca model structure created.")
        print("- ToscaModel: " + tosca_model_pkg.getName())
        print("- ServiceTemplate: " + service_pkg.getName())
        print("- TopologyTemplate: " + topology_cls.getName())
        print("- Inputs created: " + str(input_count))
        print("- Outputs created: " + str(output_count))

    except Exception as ex:
        tx.rollback()
        print("Error while creating Tosca templates: " + str(ex))
        import traceback
        traceback.print_exc()
