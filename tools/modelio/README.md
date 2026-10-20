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

## Notes

- These scripts are transaction-safe: they rollback on exceptions.
- They use ToscaDesigner API proxies for linking service and topology templates.
- Inputs/outputs are attached to `TTopologyTemplate` only (TOSCA v1.3 compliant).
