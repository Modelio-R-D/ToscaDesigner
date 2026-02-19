package fr.softeam.toscadesigner.export.util;

/**
 * Constants for Handlebars template names used in TOSCA export.
 */
public final class TemplateConstants {

    private TemplateConstants() {
        // Utility class
    }

    /**
     * Main template for standard TOSCA file generation.
     */
    public static final String MAIN_TEMPLATE = "_mainTemplate";

    /**
     * Main template for Lakeside Labs DSE file generation.
     */
    public static final String LAKESIDE_DSE_TEMPLATE = "_mainTemplate_lakeside_dse";

    /**
     * Template path where Handlebars templates are located.
     */
    public static final String TEMPLATE_PATH = "/fr/softeam/templates/";

    /**
     * Template file extension.
     */
    public static final String TEMPLATE_EXTENSION = ".hbs";
}
