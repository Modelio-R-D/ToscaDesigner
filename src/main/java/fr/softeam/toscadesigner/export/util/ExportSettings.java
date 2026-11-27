package fr.softeam.toscadesigner.export.util;

import org.modelio.api.module.context.configuration.IModuleUserConfiguration;

import fr.softeam.toscadesigner.export.ToscaVersion;
import fr.softeam.toscadesigner.impl.ToscaDesignerModule;

/**
 * Reads the export settings from the ToscaDesigner module parameters (Configuration &gt; Modules &gt; TOSCA
 * Designer &gt; Parameters).
 * <p>
 * Never throws: when the module, its configuration or a parameter is not available (for instance when the
 * export classes are driven by reflection from a script), the 1.3 defaults apply.
 */
public final class ExportSettings {

    /** Module parameter selecting the TOSCA version written by the export. */
    public static final String PARAM_TOSCA_VERSION = "ToscaVersion";

    /** Module parameter naming the profile each TOSCA 2.0 file imports; empty means no profile import. */
    public static final String PARAM_TOSCA2_SIMPLE_PROFILE = "Tosca2SimpleProfile";

    /** Default for {@link #PARAM_TOSCA2_SIMPLE_PROFILE}, as given in the TOSCA 2.0 specification (6.8.3). */
    public static final String DEFAULT_TOSCA2_SIMPLE_PROFILE = "org.oasis-open.tosca.simple:2.0";

    private ExportSettings() {
        // Utility class
    }

    /** The TOSCA version selected in the module parameters, or {@link ToscaVersion#DEFAULT}. */
    public static ToscaVersion getToscaVersion() {
        return ToscaVersion.fromParameter(readParameter(PARAM_TOSCA_VERSION));
    }

    /**
     * The profile each TOSCA 2.0 file imports so that the normative {@code tosca.*} types resolve. Returns the
     * default when the parameter does not exist yet (projects where an older module version was installed), and
     * an empty string when the user cleared it.
     */
    public static String getTosca2SimpleProfile() {
        String value = readParameter(PARAM_TOSCA2_SIMPLE_PROFILE);
        return value == null ? DEFAULT_TOSCA2_SIMPLE_PROFILE : value.trim();
    }

    private static String readParameter(String name) {
        try {
            ToscaDesignerModule module = ToscaDesignerModule.getInstance();
            if (module == null || module.getModuleContext() == null) {
                return null;
            }
            IModuleUserConfiguration configuration = module.getModuleContext().getConfiguration();
            return configuration == null ? null : configuration.getParameterValue(name);
        } catch (RuntimeException | LinkageError e) {
            return null;
        }
    }
}
