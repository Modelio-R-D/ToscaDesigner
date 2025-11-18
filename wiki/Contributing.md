# Contributing

Thanks for considering contributing to TOSCA Designer.

## Development Setup

- Java 8 JDK
- Maven 3.6+

## Build

From the project root run:

```powershell
mvn -B -DskipTests package
```

This produces `target/toscadesigner-<version>.jar` and `target/ToscaDesigner_<version>.jmdac`.

## Workflow

- Fork the repo and create a feature branch.
- Run the build locally and open a pull request.
- For major changes, open an issue to discuss.

## CI

A GitHub Actions workflow builds the project on push and PRs.
