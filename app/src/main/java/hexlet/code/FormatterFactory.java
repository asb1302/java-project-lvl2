package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

public class FormatterFactory {
    public static final String STYLISH_FORMAT = "stylish";

    public static final String PLAINT_FORMAT = "plain";

    public static final String JSON_FORMAT = "json";

    public static Formatter getFormatter(String format) {
        switch (format) {
            case PLAINT_FORMAT -> {
                return new Plain();
            }

            case JSON_FORMAT -> {
                return new Json();
            }

            default -> {
                return new Stylish();
            }
        }
    }
}
