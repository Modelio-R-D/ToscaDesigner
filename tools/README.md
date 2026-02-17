# Tools

This folder contains helper scripts used during development and Modelio model maintenance.

## Modelio Jython Scripts

Location: `tools/modelio`

These scripts are intended to be run inside Modelio's Jython environment and operate on the currently selected model elements.

- `delete_association_by_name.py`: Delete an association by name within the selected package.
- `duplicate_fog_nodes.py`: Duplicate a Fog node with properties and tagged values.
- `generate_random_edges.py`: Create randomized associations between edge, fog, and cloud nodes.
- `name_association_ends.py`: Name association ends based on source/target nodes.
- `remove_duplicate_association_ends.py`: Remove duplicate association ends per class.

## Dev Utilities

Location: `tools/dev`

- `correct_imports.py`: Fix generated Java imports in `src/main/java` when needed.
