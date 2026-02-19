package fr.softeam.toscadesigner.export.lakeside.registry;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

import org.modelio.api.module.context.log.ILogService;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.helper.ConditionalHelpers;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;

import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeTemplate;
import fr.softeam.toscadesigner.export.util.ImportCollector;
import fr.softeam.toscadesigner.export.util.NodeTemplateCollector;
import fr.softeam.toscadesigner.export.util.PropertyResolver;
import fr.softeam.toscadesigner.export.util.StereotypeHelper;
import fr.softeam.toscadesigner.export.util.TemplateClassifier;
import fr.softeam.toscadesigner.export.util.TemplateConstants;
import fr.softeam.toscadesigner.export.util.YamlFormatter;
import fr.softeam.toscadesigner.export.lakeside.renderer.ArchitectureRenderer;
import fr.softeam.toscadesigner.export.lakeside.renderer.RequirementsResolver;
import fr.softeam.toscadesigner.export.lakeside.renderer.SystemParametersRenderer;

/**
 * Lakeside-specific Handlebars helper registry that includes all base helpers plus Lakeside DSE helpers.
 */
public final class LakesideHandlebarsHelperRegistry {

    private final TemplateClassifier classifier;
    private final NodeTemplateCollector collector;
    private final SystemParametersRenderer systemParamsRenderer;
    private final ArchitectureRenderer architectureRenderer;
    private final RequirementsResolver requirementsResolver;
    private final StereotypeHelper stereotypeHelper;

    public LakesideHandlebarsHelperRegistry(ILogService logger) {
        this.classifier = new TemplateClassifier();
        this.collector = new NodeTemplateCollector(classifier);
        this.systemParamsRenderer = new SystemParametersRenderer(classifier, collector);
        this.architectureRenderer = new ArchitectureRenderer(logger);
        this.requirementsResolver = new RequirementsResolver(logger);
        this.stereotypeHelper = new StereotypeHelper();
    }

    /**
     * Create and configure a new Handlebars instance with all helpers registered (base + Lakeside).
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
        // Register base helpers
        registerPropertyHelper(handlebars);
        registerStereotypeHelpers(handlebars);
        registerImportHelpers(handlebars);
        registerBaseTemplateHelpers(handlebars);
        registerYamlHelpers(handlebars);
        
        // Register Lakeside-specific helpers
        registerLakesideTemplateHelpers(handlebars);
        registerLakesideSystemParametersHelpers(handlebars);
        registerLakesideArchitectureHelpers(handlebars);
        
        handlebars.registerHelpers(ConditionalHelpers.class);
    }

    // Base helpers (copied from HandlebarsHelperRegistry)
    
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

    private void registerBaseTemplateHelpers(Handlebars handlebars) {
        handlebars.registerHelper("nodeTypeLocalName",
                (Object candidate, Options options) -> classifier.nodeTypeLocalName(candidate));
    }

    private void registerYamlHelpers(Handlebars handlebars) {
        handlebars.registerHelper("formatYamlValue",
                (Object value, Options options) -> YamlFormatter.formatScalar(value));
    }

    // Lakeside-specific helpers
    
    private void registerLakesideTemplateHelpers(Handlebars handlebars) {
        handlebars.registerHelper("lakesideTemplateName",
                (Object candidate, Options options) -> classifier.templateDisplayName(candidate));
        handlebars.registerHelper("lakesidePropertyAllowed", (Object context, Options options) -> {
            Object template = options.params.length > 0 ? options.params[0] : null;
            Object propertyName = options.params.length > 1 ? options.params[1] : null;
            return classifier.lakesidePropertyAllowed(template, propertyName);
        });
        handlebars.registerHelper("lakesideNodeTemplates", (ModelElement context, Options options) -> {
            return renderTemplates(context, options, template -> true);
        });
        handlebars.registerHelper("lakesideComputeTemplates", (ModelElement context, Options options) -> {
            return renderTemplates(context, options, classifier::isComputeTemplate);
        });
        handlebars.registerHelper("lakesidePodTemplates", (ModelElement context, Options options) -> {
            return renderTemplates(context, options, classifier::isPodTemplate);
        });
    }

    private void registerLakesideSystemParametersHelpers(Handlebars handlebars) {
        handlebars.registerHelper("isSystemParameters",
                (Object candidate, Options options) -> classifier.isSystemParameters(candidate));
        handlebars.registerHelper("lakesideSystemParams", (ModelElement context, Options options) -> {
            String indent = options.params.length > 0 ? Objects.toString(options.param(0), "  ") : "  ";
            return systemParamsRenderer.renderSystemParametersBlock(context, indent);
        });
    }

    private void registerLakesideArchitectureHelpers(Handlebars handlebars) {
        handlebars.registerHelper("lakesideArchitecture", (ModelElement context, Options options) -> {
            String indent = options.params.length > 0 ? Objects.toString(options.param(0), "  ") : "  ";
            ArchitectureRenderer.ArchitectureData architecture = architectureRenderer.buildArchitectureData(context);
            if (architecture.isEmpty()) {
                return "";
            }
            return ArchitectureRenderer.renderArchitectureBlock(architecture, indent);
        });
        handlebars.registerHelper("lakesideRequirements", (ModelElement context, Options options) -> {
            String indent = options.params.length > 0 ? Objects.toString(options.param(0), "  ") : "  ";
            return requirementsResolver.renderRequirementsBlock(context, indent);
        });
    }

    private String renderTemplates(ModelElement context, Options options,
            java.util.function.Predicate<TNodeTemplate> predicate) {
        java.util.List<TNodeTemplate> nodeTemplates = collector.collect(context, predicate);
        StringBuilder builder = new StringBuilder();
        for (TNodeTemplate template : nodeTemplates) {
            // Partial templates expect the raw Modelio element (ModelTree) so they can
            // navigate owned attributes while helpers can still cast back to
            // TNodeTemplate when needed.
            try {
                builder.append(options.fn(template.getElement()));
            } catch (IOException e) {
                throw new RuntimeException("Failed to render template block", e);
            }
        }
        return builder.toString();
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
