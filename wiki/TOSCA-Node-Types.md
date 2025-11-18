# MYRTUS TOSCA Node Types

This page documents the TOSCA node type definitions developed as part of the MYRTUS project and contributed to the TOSCA community for standardization.

## Overview

The MYRTUS node types are designed for edge computing and IoT scenarios, particularly focusing on containerized software deployments in distributed systems.

## Node Types

### `example.eu.myrtus.MyrtusSWComponent`

A specialized node type representing containerized MYRTUS software components.

**Purpose**: Represents software modules packaged as Docker containers that can be deployed in edge/IoT environments.

**Key Properties**:
- `dockerImage` (string, optional): URI pointing to the Docker image to be deployed
- `port` (integer, optional): Port to be opened in the container (default: 1234)

**Requirements**:
- `connects`: Can connect to any node offering a `tosca.capabilities.Endpoint` capability (0 to unbounded occurrences)

**Capabilities**:
- `service`: Provides an endpoint capability for other components to connect to (0 to unbounded occurrences)

**Example Usage**:
```yaml
topology_template:
  node_templates:
    my_face_detection_service:
      type: example.eu.myrtus.MyrtusSWComponent
      properties:
        dockerImage: "registry.example.com/myrtus/face-detection:latest"
        port: 8080
```

### `example.eu.myrtus.Myrtus-Compute`

A specialized compute node type representing computing infrastructure in MYRTUS systems.

**Purpose**: Represents edge computing nodes or workstations that host MYRTUS components.

**Derived From**: `tosca.nodes.Compute`

**Example Usage**:
```yaml
topology_template:
  node_templates:
    edge_camera_node:
      type: example.eu.myrtus.Myrtus-Compute
```

## Complete TOSCA Definition

```yaml
tosca_definitions_version: tosca_simple_yaml_1_3

metadata:
  template_name: MYRTUS Node Types
  template_author: MYRTUS Project Contributors
  template_version: 1.0.0

description: >
  MYRTUS custom node types for containerized software components
  and computing infrastructure in edge/IoT scenarios.
  
  These types have been developed as part of the MYRTUS project
  and are contributed to the TOSCA community for standardization.

node_types:
  example.eu.myrtus.MyrtusSWComponent:
    description: This is an example of a specialization made to represent Myrtus SW components. This component assumes the SW modules are containerized in Docker containers.
    metadata:
      targetNamespace: "example.eu.myrtus.nodetypes"
      abstract: "false"
      final: "false"
    properties:
      dockerImage:
        type: string
        description: An URI pointing to the docker image to be deployed.
        required: false
      port:
        type: integer
        description: The port to be opened in the container
        required: false
        default: 1234
    requirements:
      - connects:
          capability: tosca.capabilities.Endpoint
          relationship: tosca.relationships.ConnectsTo
          occurrences: [ 0, UNBOUNDED ]
    capabilities:
      service:
        occurrences: [ 0, UNBOUNDED ]
        type: tosca.capabilities.Endpoint
        
  example.eu.myrtus.Myrtus-Compute:
    description: Representation of a computing node in the Myrtus systems.
    metadata:
      targetNamespace: "example.eu.myrtus.nodetypes"
      abstract: "false"
      final: "false"
    derived_from: tosca.nodes.Compute
```

## Usage in Service Templates

To use these node types in your TOSCA service templates:

1. Import the node type definitions:
```yaml
imports:
  - myrtus/node-types.yaml
```

2. Reference the types in your topology template as shown in the examples above.

## Contributing to TOSCA Standardization

These node types are being proposed for inclusion in TOSCA community standards. If you have suggestions or improvements:

1. Review the type definitions above
2. Submit feedback or proposed changes through the appropriate TOSCA community channels
3. Reference this work in standardization discussions

## Version History

- **1.0.0** (2025-11-18): Initial release with MyrtusSWComponent and Myrtus-Compute node types

## Source Files

The complete type definitions can be found in the repository at:
- `tosca-types/myrtus/node-types.yaml`

## License

See the main project LICENSE file for licensing information.
