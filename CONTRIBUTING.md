# Contributing

Thanks for considering contributing to TOSCA Designer.

## Development Setup

This repository builds a Modelio 5.4.1 module (ToscaDesigner) and requires Java 8 for runtime compatibility with Modelio.

### Requirements

- Java 8 JDK (for running Modelio and for building modules targeting Modelio)
- Maven 3.6+

### Build

From the project root:

```powershell
mvn -B -DskipTests package
```

This produces `target/toscadesigner-<version>.jar` and `target/ToscaDesigner_<version>.jmdac`.

### Notes

- The project compiles with `maven-compiler-plugin` configured to `<release>8` to ensure bytecode compatibility with Modelio 5.4.1.
- The `MDAKit` dependency is provided by the Modelio runtime; it is declared with `scope=provided` in `pom.xml`.
- If you need to run checks that require the Modelio API, install Modelio and run integration steps inside its environment.

### Scripts and Utilities

- Modelio Jython helper scripts live in [tools/modelio](tools/modelio).
- Dev utilities (like import fixups) live in [tools/dev](tools/dev).

## Contribution Workflow

- Fork the repo and create a feature branch.
- Run the build locally and open a pull request.
- For major changes, open an issue first to discuss the design.

## CI

A GitHub Actions workflow ([.github/workflows/maven-java8.yml](.github/workflows/maven-java8.yml)) builds with Java 8 on push and pull requests.
