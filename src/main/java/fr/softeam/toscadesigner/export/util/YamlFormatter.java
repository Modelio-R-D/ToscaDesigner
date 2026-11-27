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
        String escaped = trimmed.replace("\\", "\\\\").replace("\"", "\\\"").replace("\r", "")
                .replace("\n", "\\n");
        return "\"" + escaped + "\"";
    }

    /**
     * Format a TOSCA occurrences range as "[ lower, upper ]".
     * An empty lower bound becomes 1 (TOSCA default), an empty upper bound becomes {@code defaultUpper},
     * and a negative upper bound (Modelio's "unbounded") becomes UNBOUNDED.
     */
    public static String formatOccurrences(Object lower, Object upper, String defaultUpper) {
        String lo = lower == null ? "" : lower.toString().trim();
        String hi = upper == null ? "" : upper.toString().trim();
        if (lo.isEmpty()) {
            lo = "1";
        }
        if (hi.isEmpty()) {
            hi = defaultUpper;
        } else if (hi.startsWith("-") || "*".equals(hi) || "unbounded".equalsIgnoreCase(hi)) {
            hi = "UNBOUNDED";
        }
        return "[ " + lo + ", " + hi + " ]";
    }

    private static boolean looksLikeBoolean(String value) {
        return "true".equalsIgnoreCase(value) || "false".equalsIgnoreCase(value);
    }

    private static boolean looksLikeNumber(String value) {
        return value.matches("-?\\d+(\\.\\d+)?");
    }
}
