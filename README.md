# TOSCA Designer

**TOSCA Designer** is an open-source module for **Modelio 5.4.1**, enabling graphical modeling of cloud-based applications using the [OASIS TOSCA Standard](https://www.oasis-open.org/committees/tosca/). It supports modeling topology templates, types, and policies, and exports models as `.tosca` and (experimental) `.csar` files.

TOSCA Designer is developed by the **Softeam R&D Department**, as part of the [MYRTUS Horizon Europe project](https://myrtus-project.eu/) (Grant No. 101135183).

## Key Features

- Supports **TOSCA Standard v1.3**
- Graphical modeling of topology templates, node types, relationship types, policies, and requirements
- Export to `.tosca` and experimental `.csar` files

## Requirements

- [Java 8 JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Modelio Open Source 5.4.1](https://github.com/ModelioOpenSource/Modelio/releases/tag/v5.4.1)
- [Maven 3.6+](https://maven.apache.org/) (for building)

## Build

```powershell
mvn -B -DskipTests package
```

Build outputs:

- `target/toscadesigner-<version>.jar`
- `target/ToscaDesigner_<version>.jmdac`

## Documentation

- [Docs landing page](docs/README.md)
- [Installation](docs/Installation.md)
- [Usage](docs/Usage.md)
- [Development](docs/Development.md)
- [MYRTUS TOSCA node types](docs/TOSCA-Node-Types.md)

## Examples and Samples

- Generated TOSCA exports: [examples/generated-tosca](examples/generated-tosca)
- MYRTUS node type definitions: [tosca-types/myrtus](tosca-types/myrtus)

## Repository Layout

- [src](src): module sources and resources
- [docs](docs): documentation pages
- [tools](tools): helper scripts (Modelio Jython and dev utilities)
- [examples](examples): example outputs
- [tosca-types](tosca-types): TOSCA type definitions

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md). By participating, you agree to the [Code of Conduct](CODE_OF_CONDUCT.md).

## License

Licensed under the [MIT License](LICENSE).
