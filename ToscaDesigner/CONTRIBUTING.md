CONTRIBUTING

Development setup
-----------------
This repository builds a Modelio 5.4.1 module (ToscaDesigner) and requires Java 8 for runtime compatibility with Modelio.

Requirements
- Java 8 JDK (for running Modelio and for building modules targeting Modelio)
- Maven 3.6+

Build instructions
------------------
From the project root:

```powershell
mvn -B -DskipTests package
```

This produces `target/toscadesigner-<version>.jar` and `target/ToscaDesigner_<version>.jmdac`.

Notes
-----
- The project compiles with `maven-compiler-plugin` configured to `<release>8` to ensure bytecode compatibility with Modelio 5.4.1.
- The `MDAKit` dependency is provided by the Modelio runtime; it is declared with `scope=provided` in `pom.xml`.
- If you need to run checks that require the Modelio API, install Modelio and run integration steps inside its environment.

Contributing
------------
- Fork the repo and create a feature branch.
- Run the build locally and open a pull request.
- For major changes, open an issue first to discuss the design.

CI
--
A GitHub Actions workflow (`.github/workflows/maven-java8.yml`) is included. Note:

- The workflow runs Maven under Java 11 so Modelio-related Maven plugins (which require
	a newer JDK) can execute. The project is still compiled to Java 8 bytecode via the
	`maven-compiler-plugin` configuration (`<release>8`).

This preserves runtime compatibility with Modelio 5.4.1 while allowing plugin execution
that needs a newer JDK.
