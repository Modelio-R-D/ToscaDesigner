# Development

This page contains developer-focused information.

## Requirements

- Java 8 JDK
- Maven 3.6+
- Modelio 5.4.1 for integration testing

## Build and Test

```powershell
mvn -B -DskipTests package
```

Notes
- The project compiles to Java 8 bytecode (`<release>8`).
- `MDAKit` is provided by Modelio at runtime and declared as `scope=provided` in `pom.xml`.
- To run integration checks that require Modelio APIs, install Modelio and run tests inside its environment.
