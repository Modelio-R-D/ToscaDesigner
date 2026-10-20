# MYRTUS TOSCA Node Types

This directory contains the MYRTUS cloud-fog-edge node type definitions
for TOSCA Simple YAML 1.3.

The type library in [`node-types.yaml`](node-types.yaml) defines:

- `eu.myrtus.MyrtusCompute` for edge, fog, and cloud compute resources
- `eu.myrtus.MyrtusEdgeDevice` for physical data endpoints
- `eu.myrtus.MyrtusNetwork` for links between continuum tiers
- `eu.myrtus.MyrtusAccelerator` for compute-node hardware accelerators
- `eu.myrtus.MyrtusSWComponent` for containerized software components

The types expose placement, network, hardware, and energy properties for
deployment modeling and design-space exploration. See the
[MYRTUS TOSCA Node Types documentation](../../docs/TOSCA-Node-Types.md)
for the property reference and a topology example.

## Usage

Import the definitions into a service template:

```yaml
imports:
  - myrtus/node-types.yaml
```

Then refer to a type using its `eu.myrtus.*` name in a topology template.
