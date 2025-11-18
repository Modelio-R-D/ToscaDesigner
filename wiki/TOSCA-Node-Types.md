# MYRTUS TOSCA Node Types

This page documents the TOSCA node type definitions developed as part of the MYRTUS project and contributed to the TOSCA community for standardization.

## Overview

The MYRTUS node types are designed for edge computing and IoT scenarios, particularly focusing on containerized software deployments in distributed systems. These definitions provide a standardized way to model and orchestrate edge applications.

## Node Types

### `eu.myrtus.MyrtusSWComponent`

A specialized node type representing containerized MYRTUS software components.

**Purpose**: Represents software modules packaged as Docker containers that can be deployed in edge/IoT environments.

**Derived From**: `tosca.nodes.SoftwareComponent`

**Key Properties**:
- `docker_image` (string, **required**): URI pointing to the Docker image (e.g., `registry.example.com/myrtus/service:1.0`)
- `container_port` (integer, optional): Primary port exposed by the container (default: 8080)
- `environment_variables` (map, optional): Environment variables to set in the container
- `resource_limits` (map, optional): Resource limits for CPU and memory
- `auto_restart` (boolean, optional): Enable automatic restart on failure (default: true)
- `health_check_endpoint` (string, optional): HTTP endpoint path for health checks

**Attributes**:
- `container_id`: Runtime identifier of the deployed container
- `service_url`: Full URL where the service is accessible

**Requirements**:
- `host`: Must be hosted on a node providing container capability (required, 1 occurrence)
- `connects`: Can connect to any node offering an endpoint capability (0 to unbounded occurrences)

**Capabilities**:
- `service_endpoint`: Provides an HTTP/HTTPS endpoint for other components to connect to

**Example Usage**:
```yaml
topology_template:
  node_templates:
    face_detection_service:
      type: eu.myrtus.MyrtusSWComponent
      properties:
        docker_image: "registry.example.com/myrtus/face-detection:2.1.0"
        container_port: 8080
        environment_variables:
          LOG_LEVEL: "INFO"
          MAX_WORKERS: "4"
        resource_limits:
          cpu: "2.0"
          memory: "4Gi"
        auto_restart: true
        health_check_endpoint: "/health"
      requirements:
        - host: edge_camera_node
```

### `eu.myrtus.MyrtusCompute`

A specialized compute node type representing computing infrastructure in MYRTUS systems.

**Purpose**: Represents edge computing nodes, workstations, or IoT gateways that host MYRTUS containerized components.

**Derived From**: `tosca.nodes.Compute`

**Key Properties**:
- `location` (string, optional): Physical or logical location of the compute node
- `edge_zone` (string, optional): Edge computing zone or region identifier
- `docker_runtime` (string, optional): Docker runtime version (default: "latest")
- `monitoring_enabled` (boolean, optional): Enable monitoring agents (default: true)

**Attributes**:
- `node_id`: Unique identifier for this compute node
- `available_resources`: Currently available resources (CPU, memory, storage)
- `deployment_timestamp`: When this node was provisioned

**Capabilities**:
- `host`: Can host containerized applications
- `compute`: Provides computational capabilities
- `endpoint`: Administrative endpoint for node management

**Example Usage**:
```yaml
topology_template:
  node_templates:
    edge_camera_node:
      type: eu.myrtus.MyrtusCompute
      properties:
        location: "Building A - Floor 2"
        edge_zone: "zone-paris-north"
        docker_runtime: "24.0"
        monitoring_enabled: true
      capabilities:
        compute:
          properties:
            num_cpus: 4
            mem_size: 8 GB
            disk_size: 100 GB
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
  eu.myrtus.MyrtusSWComponent:
    description: >
      Represents a MYRTUS software component deployed as a containerized application.
      This node type is designed for edge computing scenarios where software modules
      are packaged and deployed as Docker containers in distributed IoT environments.
    derived_from: tosca.nodes.SoftwareComponent
    metadata:
      targetNamespace: "eu.myrtus.nodetypes"
      abstract: "false"
      final: "false"
    properties:
      docker_image:
        type: string
        description: URI pointing to the Docker image to be deployed
        required: true
      container_port:
        type: integer
        description: Primary port exposed by the container
        required: false
        default: 8080
      environment_variables:
        type: map
        description: Environment variables to be set in the container
        required: false
        entry_schema:
          type: string
      resource_limits:
        type: map
        description: Resource limits for the container (cpu, memory)
        required: false
        entry_schema:
          type: string
      auto_restart:
        type: boolean
        description: Whether the container should automatically restart on failure
        required: false
        default: true
      health_check_endpoint:
        type: string
        description: HTTP endpoint path for health checks
        required: false
    attributes:
      container_id:
        type: string
        description: Runtime identifier of the deployed container
      service_url:
        type: string
        description: Full URL where the service is accessible
    requirements:
      - host:
          capability: tosca.capabilities.Container
          relationship: tosca.relationships.HostedOn
          occurrences: [ 1, 1 ]
      - connects:
          capability: tosca.capabilities.Endpoint
          relationship: tosca.relationships.ConnectsTo
          occurrences: [ 0, UNBOUNDED ]
    capabilities:
      service_endpoint:
        type: tosca.capabilities.Endpoint
        description: Endpoint capability provided by this software component
        occurrences: [ 0, UNBOUNDED ]
    interfaces:
      Standard:
        type: tosca.interfaces.node.lifecycle.Standard
        
  eu.myrtus.MyrtusCompute:
    description: >
      Represents a computing node in MYRTUS edge/IoT infrastructure.
      This can be a workstation, edge server, or IoT gateway capable of hosting
      containerized MYRTUS software components.
    derived_from: tosca.nodes.Compute
    metadata:
      targetNamespace: "eu.myrtus.nodetypes"
      abstract: "false"
      final: "false"
    properties:
      location:
        type: string
        description: Physical or logical location of the compute node
        required: false
      edge_zone:
        type: string
        description: Edge computing zone or region identifier
        required: false
      docker_runtime:
        type: string
        description: Docker runtime version installed on this node
        required: false
        default: "latest"
      monitoring_enabled:
        type: boolean
        description: Whether monitoring agents are deployed on this node
        required: false
        default: true
    attributes:
      node_id:
        type: string
        description: Unique identifier for this compute node
      available_resources:
        type: map
        description: Currently available resources (cpu, memory, storage)
      deployment_timestamp:
        type: timestamp
        description: When this node was provisioned
    capabilities:
      host:
        type: tosca.capabilities.Container
        description: Ability to host containerized applications
      compute:
        type: tosca.capabilities.Compute
        description: Computational capabilities of this node
      endpoint:
        type: tosca.capabilities.Endpoint.Admin
        description: Administrative endpoint for node management
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

- **1.0.0** (2025-11-18): Initial release with enriched node type definitions for MyrtusSWComponent and MyrtusCompute

## Source Files

The complete type definitions can be found in the repository at:
- `tosca-types/myrtus/node-types.yaml`

## License

See the main project LICENSE file for licensing information.
