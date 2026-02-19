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

    public HandlebarsHelperRegistry(ILogService logger) {
        this.classifier = new TemplateClassifier();
        this.stereotypeHelper = new StereotypeHelper();
    }

    /**
     * Create and configure a new Handlebars instance with all helpers registered.
     */
    public Handlebars createConfiguredHandlebars() {
        Handlebars handlebars = new Handlebars(
                new ClassPathTemplateLoader(TemplateConstants.TEMPLATE_PATH, TemplateConstants.TEMPLATE_EXTENSION));
        handlebars.setPrettyPrint(true);
        registerAllHelpers(handlebars);
        return handlebars;
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
            return ImportCollector.generateImportString(imports, includeHeader, indent);
        });
    }

    private void registerTemplateHelpers(Handlebars handlebars) {
        handlebars.registerHelper("nodeTypeLocalName",
                (Object candidate, Options options) -> classifier.nodeTypeLocalName(candidate));
    }

    private void registerYamlHelpers(Handlebars handlebars) {
        handlebars.registerHelper("formatYamlValue",
                (Object value, Options options) -> YamlFormatter.formatScalar(value));
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
