package fr.softeam.toscadesigner.export.util;

/**
 * Utility class for formatting values into YAML scalar syntax.
 * Handles quoting, escaping, and type detection for YAML output.
 */
final public class YamlFormatter {
    
    private YamlFormatter() {
        // Utility class - no instances
    }

    /**
     * Format a value as a YAML scalar, applying proper quoting and escaping.
     * Leaves booleans, numbers, and null unquoted; quotes strings with escaped inner quotes.
     */
    public static String formatScalar(Object value) {
        if (value == null) {
            return "\"\"";
        }
        String asString = value.toString();
        if (asString == null) {
            return "\"\"";
        }
        String trimmed = asString.trim();
        if (trimmed.isEmpty()) {
            return "\"\"";
        }
        if (looksLikeBoolean(trimmed) || looksLikeNumber(trimmed) || "null".equalsIgnoreCase(trimmed)) {
            return trimmed;
        }
        String escaped = trimmed.replace("\"", "\\\"");
        return "\"" + escaped + "\"";
    }

    private static boolean looksLikeBoolean(String value) {
        return "true".equalsIgnoreCase(value) || "false".equalsIgnoreCase(value);
    }

    private static boolean looksLikeNumber(String value) {
        return value.matches("-?\\d+(\\.\\d+)?");
    }
}
