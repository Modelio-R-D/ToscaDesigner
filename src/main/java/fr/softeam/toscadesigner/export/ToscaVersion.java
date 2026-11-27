package fr.softeam.toscadesigner.export;

/**
 * TOSCA versions the exporter can target.
 * <p>
 * {@link #V1_3} is the default and renders the original templates unchanged. {@link #V2_0} renders the templates
 * under {@code tosca_2_0/} first and falls back to the 1.3 templates for anything not overridden there.
 */
public enum ToscaVersion {
    /** TOSCA Simple Profile in YAML 1.3. */
    V1_3("1.3", "tosca_simple_yaml_1_3", null),
    /** OASIS TOSCA 2.0. */
    V2_0("2.0", "tosca_2_0", "tosca_2_0/");

    /** Version used when no module parameter is available or its value is not recognised. */
    public static final ToscaVersion DEFAULT = V1_3;

    private final String parameterValue;
    private final String definitionsVersion;
    private final String templateOverrideDir;

    ToscaVersion(String parameterValue, String definitionsVersion, String templateOverrideDir) {
        this.parameterValue = parameterValue;
        this.definitionsVersion = definitionsVersion;
        this.templateOverrideDir = templateOverrideDir;
    }

    /** Value of this version in the {@code ToscaVersion} module parameter, e.g. {@code "2.0"}. */
    public String getParameterValue() {
        return this.parameterValue;
    }

    /** Value written to {@code tosca_definitions_version}. */
    public String getDefinitionsVersion() {
        return this.definitionsVersion;
    }

    /**
     * Sub-directory of the template path holding the templates that replace the 1.3 ones for this version, or
     * {@code null} when the 1.3 templates are used as they are.
     */
    public String getTemplateOverrideDir() {
        return this.templateOverrideDir;
    }

    /**
     * Parse a module parameter value. Accepts {@code "1.3"}, {@code "2.0"}, the enum names and the
     * {@code tosca_definitions_version} values; anything else (including {@code null}) gives {@link #DEFAULT}.
     */
    public static ToscaVersion fromParameter(String value) {
        if (value == null) {
            return DEFAULT;
        }
        String candidate = value.trim();
        for (ToscaVersion version : values()) {
            if (version.parameterValue.equals(candidate) || version.name().equalsIgnoreCase(candidate)
                    || version.definitionsVersion.equalsIgnoreCase(candidate)) {
                return version;
            }
        }
        return DEFAULT;
    }
}
