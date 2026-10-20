---
name: tosca-modelio-templates
description: Create and populate Tosca Designer model elements in Modelio using Jython scripts. Use this skill when the user asks to generate a Tosca model, service template, topology template, or topology inputs/outputs through automation.
---

# Tosca Modelio Template Automation

Use this skill to automate creation of Tosca Designer model structures in Modelio.

## Use When

- The user asks to create a Tosca model structure from prompts.
- The user asks to create a `TServiceTemplate` and/or `TTopologyTemplate`.
- The user asks to create topology inputs/outputs (`TParameter`) in bulk.
- The user wants repeatable Modelio scripting instead of manual modeling.

## Preconditions

1. Modelio 5.4.1 is open with the Tosca Designer module loaded.
2. Script runs in Modelio Jython context where `session` is available.
3. A package is selected as script owner, or script can create one.

## Script Strategy

1. Resolve owner package:
- If `elt` is a package, use it.
- If `elt` has an owner package, use the owner.
- Otherwise stop with an explicit error.

2. Create core elements:
- Create `Standard.Package` with stereotype `ToscaModel`.
- Create `Standard.Package` with stereotype `TServiceTemplate`.
- Create `Standard.Class` with stereotype `TTopologyTemplate`.

3. Link service template to topology template:
- Use generated Tosca API proxy classes:
  - `fr.softeam.toscadesigner.api.tosca.standard.package_.TServiceTemplate`
  - `fr.softeam.toscadesigner.api.tosca.standard.class_.TTopologyTemplate`
- Call `service_proxy.setTopologyTemplate(topology_proxy)`.

4. Optionally create topology parameters:
- Create `Infrastructure.ModelElement` with stereotype `TParameter`.
- Set TParameter properties: `name`, `type`, `required`, default/value when provided.
- Link parameters with:
  - `topology_proxy.addInputs(parameter_proxy)`
  - `topology_proxy.addOutputs(parameter_proxy)`

5. Transaction safety:
- Wrap everything in a single Modelio transaction.
- Commit on success, rollback on failure.

## Output Contract

Always return:

- Names of created ToscaModel, ServiceTemplate, TopologyTemplate.
- Count of inputs and outputs created.
- Any warnings (for example unresolved owner selection).

## Implementation Template

Use this script as the default implementation and adapt names from user input:

- `tools/modelio/create_tosca_service_topology_templates.py`

For externalized specs, use the JSON-driven variant:

- `tools/modelio/create_tosca_templates_from_json.py`
- `tools/modelio/tosca_template_spec.sample.json`

If that file does not exist, create it with equivalent logic.

## Notes

- Do not add inputs/outputs to `TNodeTemplate` (TOSCA v1.3 compliance).
- Keep stereotype names exact: `ToscaModel`, `TServiceTemplate`, `TTopologyTemplate`, `TParameter`.
- Prefer API proxy linking methods over manual dependency creation when available.
