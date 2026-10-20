# MYRTUS TOSCA Node Types

This page documents the five custom node types in the MYRTUS
cloud-fog-edge profile. The definitions are in
[`tosca-types/myrtus/node-types.yaml`](../tosca-types/myrtus/node-types.yaml)
and use the `eu.myrtus.*` namespace.

## Type hierarchy

| Type | Parent | Purpose |
| --- | --- | --- |
| `eu.myrtus.MyrtusCompute` | `tosca.nodes.Compute` | Compute resource at the edge, fog, or cloud tier |
| `eu.myrtus.MyrtusEdgeDevice` | `tosca.nodes.Root` | Physical data-producing or data-consuming endpoint |
| `eu.myrtus.MyrtusNetwork` | `tosca.nodes.network.Network` | Network link between continuum tiers |
| `eu.myrtus.MyrtusAccelerator` | `tosca.nodes.Abstract.Compute` | Hardware accelerator hosted by a compute node |
| `eu.myrtus.MyrtusSWComponent` | `tosca.nodes.SoftwareComponent` | Containerized software component |

## Type properties

### `eu.myrtus.MyrtusCompute`

Models a compute node at any continuum layer. `edge_zone` makes tier
assignment explicit and supports reassignment during design-space exploration.

| Property | Type | Description |
| --- | --- | --- |
| `edge_zone` | string | Required tier: `edge`, `fog`, or `cloud` |
| `location` | string | Optional physical or logical location |
| `docker_runtime` | string | Optional container runtime |
| `monitoring_enabled` | boolean | Optional monitoring-agent setting |
| `tdp_watts` | float | Optional vendor-rated peak power draw in watts |
| `renewable_energy_ratio` | float | Optional renewable energy fraction, from 0 to 1 |
| `cooling_pue` | float | Optional Power Usage Effectiveness; at least 1.0 |

### `eu.myrtus.MyrtusEdgeDevice`

Models a physical endpoint such as a sensor, camera, or VR headset. It
produces or consumes data and is not itself a compute hosting target.

| Property | Type | Description |
| --- | --- | --- |
| `protocol` | string | Communication protocol: `MQTT`, `CoAP`, `HTTP`, `LoRaWAN`, or `custom` |
| `data_rate_mbps` | float | Peak data rate in megabits per second |
| `mobility` | string | `static` or `mobile` |
| `power_source` | string | `battery`, `solar`, `grid`, or `harvested` |
| `max_power_w` | float | Peak power consumption in watts |
| `idle_power_w` | float | Idle power consumption in watts |
| `duty_cycle` | float | Fraction of time active, from 0 to 1 |
| `sleep_latency_ms` | integer | Wake-up time from deep sleep in milliseconds |

### `eu.myrtus.MyrtusNetwork`

Models a link between continuum tiers and exposes its performance and
sustainability characteristics.

| Property | Type | Description |
| --- | --- | --- |
| `bandwidth_mbps` | float | Link capacity in megabits per second |
| `latency_ms` | float | One-way propagation latency in milliseconds |
| `tier_src` | string | Source tier: `device`, `edge`, `fog`, or `cloud` |
| `tier_dst` | string | Destination tier: `device`, `edge`, `fog`, or `cloud` |
| `link_type` | string | `fiber`, `5G`, `WiFi6`, `LoRaWAN`, or `ethernet` |
| `energy_per_gb_kwh` | float | Energy to transfer one gigabyte over the link |
| `carbon_intensity_gco2_kwh` | float | Link infrastructure carbon intensity in gCO2/kWh |

### `eu.myrtus.MyrtusAccelerator`

Models a dedicated accelerator hosted by a `MyrtusCompute`, such as a GPU,
FPGA, CGRA, NPU, or RISC-V core. Software components can express accelerator
affinity so hardware dependencies are visible in the topology.

| Property | Type | Description |
| --- | --- | --- |
| `accelerator_type` | string | `GPU`, `FPGA`, `CGRA`, `NPU`, or `RISC-V` |
| `compute_units` | integer | Number of compute units |
| `memory_gb` | float | Accelerator memory in gigabytes |
| `tdp_watts` | float | Peak power draw under full load in watts |
| `gflops_per_watt` | float | Floating-point throughput per watt |
| `idle_power_ratio` | float | Idle power as a fraction of peak, from 0 to 1 |
| `reconfigurable` | boolean | Whether the accelerator can be reconfigured at runtime |

### `eu.myrtus.MyrtusSWComponent`

Models a containerized microservice. `docker_image` is an OCI-compatible
image reference; the runtime is supplied by its hosting `MyrtusCompute`.
The `energy_profile` value `adaptive` indicates that a component can reduce
quality or accuracy to operate under power constraints.

| Property | Type | Description |
| --- | --- | --- |
| `docker_image` | string | Required OCI container image reference |
| `resource_limits` | map | Optional CPU and memory bounds (`cpu`, `mem`) |
| `health_check_endpoint` | string | Optional HTTP health-check path |
| `auto_restart` | boolean | Optional automatic recovery after failure |
| `energy_profile` | string | Optional energy class: `high`, `medium`, `low`, or `adaptive` |

## Example

```yaml
tosca_definitions_version: tosca_simple_yaml_1_3

imports:
  - myrtus/node-types.yaml

topology_template:
  node_templates:
    edge_gateway:
      type: eu.myrtus.MyrtusCompute
      properties:
        edge_zone: edge
        location: building-a
        tdp_watts: 45.0
        renewable_energy_ratio: 0.4
        cooling_pue: 1.0

    camera:
      type: eu.myrtus.MyrtusEdgeDevice
      properties:
        protocol: MQTT
        mobility: static
        power_source: grid
        duty_cycle: 1.0

    edge_link:
      type: eu.myrtus.MyrtusNetwork
      properties:
        bandwidth_mbps: 100.0
        latency_ms: 2.0
        tier_src: device
        tier_dst: edge
        link_type: ethernet

    inference_service:
      type: eu.myrtus.MyrtusSWComponent
      properties:
        docker_image: registry.example.com/myrtus/inference:1.0
        resource_limits:
          cpu: "2"
          mem: "2Gi"
        health_check_endpoint: /health
        auto_restart: true
        energy_profile: adaptive
      requirements:
        - host: edge_gateway
```

The definitions make tier placement, cross-tier network cost, device energy
constraints, and accelerator characteristics explicit for downstream
deployment and design-space exploration tools.

## Source and license

The complete TOSCA definitions are maintained in
[`tosca-types/myrtus/node-types.yaml`](../tosca-types/myrtus/node-types.yaml).
See the repository [LICENSE](../LICENSE) for licensing information.
