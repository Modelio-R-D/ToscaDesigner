# Usage

## Launching TOSCA Designer

1. Open Modelio 5.4.1.
2. Open or create a UML project.
3. Ensure the TOSCA Designer module is installed for the project (`Configuration > Install a module in this project`).

## Creating a Topology Template

- Use the module's graphical editors to define node types, relationship types, and policies.
- Save exports via the module menus to `.tosca` or `.csar`.

## Choosing the TOSCA Version

Exports target TOSCA Simple Profile in YAML 1.3 by default. To export OASIS TOSCA 2.0 instead, open `Configuration > Modules`, select TOSCA Designer, and set **Target TOSCA version** to `2.0` in the module parameters. The setting is per project, so projects delivered to existing clients keep producing 1.3.

In 2.0 mode the export:

- writes `tosca_definitions_version: tosca_2_0` and `service_template` instead of `topology_template`;
- imports the profile named in **TOSCA 2.0 profile to import** (default `org.oasis-open.tosca.simple:2.0`), because TOSCA 2.0 no longer loads the `tosca.*` normative types implicitly. Set it to the profile name your orchestrator ships, or leave it empty for none;
- writes imports as `url` entries, requirement `count_range` instead of `occurrences`, drops capability `occurrences`, and lists policies as a sequence;
- moves node templates owned by a group under `node_templates` and lists them as the group's `members` (group type `tosca.groups.Root`);
- writes triggers with `event`, `condition` and `action` only. A trigger's `condition` must hold a TOSCA 2.0 boolean expression and its `action` an activity definition (for example `call_operation: scaling.scale_out`); `schedule` and `target_filter` no longer exist in 2.0 and are not exported;
- writes a CSAR `TOSCA.meta` with `CSAR-Version: 2.0` and the first topology template as `Entry-Definitions`.

The Lakeside Labs DSE export is not affected by this setting.

## Importing Example Projects

- `File > Import project` then select one of the example zips included in the release.

Generated `.tosca` and `.csar` outputs can be found in [examples/generated-tosca](../examples/generated-tosca).

## Troubleshooting

- If the module doesn't appear in the module catalog, ensure your `.jmdac` is compatible with Modelio 5.4.1 and that Java 8 is active.
