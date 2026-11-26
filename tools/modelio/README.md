# Modelio Automation Scripts

This folder contains Jython scripts intended to run inside Modelio with the ToscaDesigner module loaded.

## Tosca Template Generators

### 1) Inline configuration generator

Script: `create_tosca_service_topology_templates.py`

What it creates:
- one `ToscaModel` package
- one `TServiceTemplate` package
- one `TTopologyTemplate` class
- optional topology `inputs` and `outputs` as `TParameter`

How to use:
1. Open Modelio with ToscaDesigner enabled.
2. Select a target package in the model browser.
3. Edit the `CONFIG` dictionary in the script.
4. Run the script from Modelio's script runner.

### 2) JSON-driven generator

Script: `create_tosca_templates_from_json.py`
Sample spec: `tosca_template_spec.sample.json`

How to use:
1. Copy `tosca_template_spec.sample.json` to a project-specific JSON file.
2. Edit names, inputs, and outputs in your JSON.
3. In the script, set `SPEC_PATH` to your JSON file.
4. Select a target package in Modelio and run the script.

Example JSON:

```json
{
  "tosca_model_name": "TrafficToscaModel",
  "service_template_name": "TrafficServiceTemplate",
  "topology_template_name": "TrafficTopologyTemplate",
  "inputs": [
    { "name": "cameraStreamUrl", "type": "string", "required": true, "default": "" },
    { "name": "replicas", "type": "integer", "required": false, "default": "1" }
  ],
  "outputs": [
    { "name": "inferenceEndpoint", "type": "string", "required": true, "default": "" }
  ]
}
```

## MYRTUS node types library (RAMC)

Script: `build_myrtus_node_types.py`

Builds the five MYRTUS node types from [`tosca-types/myrtus/node-types.yaml`](../../tosca-types/myrtus/node-types.yaml)
(`eu.myrtus.MyrtusCompute`, `MyrtusEdgeDevice`, `MyrtusNetwork`, `MyrtusAccelerator`, `MyrtusSWComponent`)
as TOSCA Designer elements, with their properties and requirements, plus a `MyrtusNodeTypes` model
component artifact (version 2.0.00).

How to use:
1. Create a dedicated UML project with TOSCA Designer 0.6.00 or later (ToscaLibrary is deployed by the module).
2. Run the script from Modelio's script runner, or through ScriptServer.
3. Save the project, then package the `MyrtusNodeTypes` artifact as a model component (`.ramc`).
4. Deploy the `.ramc` in the projects that need the MYRTUS types.

Notes:
- Property constraints (`valid_values`, `in_range`, `greater_or_equal`) are written into the property
  descriptions, because TOSCA Designer does not export property constraints yet.
- `MyrtusNetwork` exports `derived_from: tosca.nodes.network.Network`; ToscaLibrary 0.0.05 has no such node
  type, so in the model it links to `tosca.nodes.Root`.
- Set `DRY_RUN = True` to check the build without changing the model.
- Package with TOSCA Designer 0.6.00 or later: earlier versions drop the links between TOSCA elements
  (derivedFrom, capability, nodeType, relationshipType) from model components.

## Notes

- These scripts are transaction-safe: they rollback on exceptions.
- They use ToscaDesigner API proxies for linking service and topology templates.
- Inputs/outputs are attached to `TTopologyTemplate` only (TOSCA v1.3 compliant).
