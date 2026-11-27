package fr.softeam.toscadesigner.export.registry;

import java.util.Objects;
import java.util.Set;

import org.modelio.api.module.context.log.ILogService;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.helper.ConditionalHelpers;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.CompositeTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

import fr.softeam.toscadesigner.export.ToscaVersion;
import fr.softeam.toscadesigner.export.util.ExportSettings;
import fr.softeam.toscadesigner.export.util.ImportCollector;
import fr.softeam.toscadesigner.export.util.PropertyResolver;
import fr.softeam.toscadesigner.export.util.StereotypeHelper;
import fr.softeam.toscadesigner.export.util.TemplateClassifier;
import fr.softeam.toscadesigner.export.util.TemplateConstants;
import fr.softeam.toscadesigner.export.util.YamlFormatter;

/**
 * Registers all Handlebars helpers for TOSCA file generation.
 */
public final class HandlebarsHelperRegistry {

    private final TemplateClassifier classifier;
    private final StereotypeHelper stereotypeHelper;
    private final ToscaVersion toscaVersion;
    private final String tosca2Profile;

    /**
     * Registry for the TOSCA version selected in the module parameters (1.3 when they are not available).
     */
    public HandlebarsHelperRegistry(ILogService logger) {
        this(logger, ExportSettings.getToscaVersion());
    }

    /**
     * Registry for an explicit TOSCA version. For 2.0, the profile import comes from the module parameters.
     */
    public HandlebarsHelperRegistry(ILogService logger, ToscaVersion toscaVersion) {
        this.classifier = new TemplateClassifier();
        this.stereotypeHelper = new StereotypeHelper();
        this.toscaVersion = toscaVersion != null ? toscaVersion : ToscaVersion.DEFAULT;
        this.tosca2Profile = this.toscaVersion == ToscaVersion.V2_0 ? ExportSettings.getTosca2SimpleProfile() : "";
    }

    public ToscaVersion getToscaVersion() {
        return this.toscaVersion;
    }

    /**
     * Create and configure a new Handlebars instance with all helpers registered.
     */
    public Handlebars createConfiguredHandlebars() {
        Handlebars handlebars = new Handlebars(createTemplateLoader());
        handlebars.setPrettyPrint(true);
        registerAllHelpers(handlebars);
        return handlebars;
    }

    /**
     * 1.3 uses the base templates only. Other versions look up their override directory first, then fall back to
     * the base templates, so only the templates whose grammar changed need a copy.
     */
    private TemplateLoader createTemplateLoader() {
        TemplateLoader base = new ClassPathTemplateLoader(TemplateConstants.TEMPLATE_PATH,
                TemplateConstants.TEMPLATE_EXTENSION);
        String overrideDir = this.toscaVersion.getTemplateOverrideDir();
        if (overrideDir == null) {
            return base;
        }
        TemplateLoader overrides = new ClassPathTemplateLoader(TemplateConstants.TEMPLATE_PATH + overrideDir,
                TemplateConstants.TEMPLATE_EXTENSION);
        return new CompositeTemplateLoader(overrides, base);
    }

    /**
     * Register all helpers on the provided Handlebars instance.
     */
    private void registerAllHelpers(Handlebars handlebars) {
        registerPropertyHelper(handlebars);
        registerStereotypeHelpers(handlebars);
        registerImportHelpers(handlebars);
        registerTemplateHelpers(handlebars);
        registerYamlHelpers(handlebars);
        handlebars.registerHelpers(ConditionalHelpers.class);
    }

    private void registerPropertyHelper(Handlebars handlebars) {
        handlebars.registerHelper("getProperty", (ModelElement context, Options options) -> {
            String searchedPropertyName = (String) options.params[0];
            return PropertyResolver.resolveProperty(context, searchedPropertyName);
        });
    }

    private void registerStereotypeHelpers(Handlebars handlebars) {
        handlebars.registerHelper("noStereotypeApplications",
                (context, options) -> stereotypeHelper.hasNoStereotypeApplications((ModelTree) context,
                        (String) options.params[0]));
    }

    private void registerImportHelpers(Handlebars handlebars) {
        handlebars.registerHelper("imports", (ModelElement context, Options options) -> {
            boolean includeHeader = options.params.length == 0 || asBoolean(options.param(0), true);
            String indent = options.params.length > 1 ? Objects.toString(options.param(1), "  ") : "  ";
            Set<ImportCollector.Import> imports = ImportCollector.collectImports(context);
            if (this.toscaVersion == ToscaVersion.V2_0) {
                return generateTosca2ImportString(imports, includeHeader, indent);
            }
            return ImportCollector.generateImportString(imports, includeHeader, indent);
        });
    }

    /**
     * TOSCA 2.0 import list (spec 6.8.1): the configured profile first, so the normative tosca.* types resolve,
     * then one {@code url} entry per imported file. No {@code namespace} is written: in 2.0 a namespaced import
     * makes its types reachable only as {@code namespace:Type}, while the exporter references them by their full
     * dotted names.
     */
    private String generateTosca2ImportString(Set<ImportCollector.Import> imports, boolean includeHeader,
            String indent) {
        boolean hasProfile = !this.tosca2Profile.isEmpty();
        if (imports.isEmpty() && !hasProfile) {
            return "";
        }
        StringBuilder importString = new StringBuilder();
        if (includeHeader) {
            importString.append("imports:\n");
        }
        if (hasProfile) {
            importString.append(indent).append("- profile: ").append(this.tosca2Profile).append("\n");
        }
        for (ImportCollector.Import anImport : imports) {
            importString.append(indent).append("- url: ").append(anImport.getFile()).append("\n");
        }
        return importString.toString();
    }

    private void registerTemplateHelpers(Handlebars handlebars) {
        handlebars.registerHelper("nodeTypeLocalName",
                (Object candidate, Options options) -> classifier.nodeTypeLocalName(candidate));
    }

    private void registerYamlHelpers(Handlebars handlebars) {
        handlebars.registerHelper("formatYamlValue",
                (Object value, Options options) -> YamlFormatter.formatScalar(value));
        // {{{occurrences lower upper "defaultUpper"}}} -> [ lower, upper ] with TOSCA defaults
        handlebars.registerHelper("occurrences",
                (Object lower, Options options) -> YamlFormatter.formatOccurrences(lower,
                        options.params.length > 0 ? options.param(0) : null,
                        options.params.length > 1 ? String.valueOf((Object) options.param(1)) : "UNBOUNDED"));
    }

    private boolean asBoolean(Object candidate, boolean defaultValue) {
        if (candidate == null) {
            return defaultValue;
        }
        if (candidate instanceof Boolean) {
            return (Boolean) candidate;
        }
        String value = candidate.toString();
        if (value == null || value.isEmpty()) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value);
    }
}
