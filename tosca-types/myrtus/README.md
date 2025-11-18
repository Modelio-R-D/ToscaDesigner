# MYRTUS TOSCA Node Types

This directory contains TOSCA node type definitions developed as part of the MYRTUS project and contributed to the TOSCA community for standardization.

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

## Usage

To use these node types in your TOSCA service templates:

1. Import the node type definitions:
```yaml
imports:
  - myrtus/node-types.yaml
```

2. Reference the types in your topology template as shown in the examples above.

## Contributing to TOSCA Standardization

These node types are being proposed for inclusion in TOSCA community standards. If you have suggestions or improvements:

1. Review the type definitions in `node-types.yaml`
2. Submit feedback or proposed changes through the appropriate TOSCA community channels
3. Reference this work in standardization discussions

## Version History

- **1.0.0** (2025-11-18): Initial release with MyrtusSWComponent and Myrtus-Compute node types

## License

See the main project LICENSE file for licensing information.

## Contact

For questions or contributions, please refer to the main ToscaDesigner project documentation.
