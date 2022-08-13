package hexlet.code;

import hexlet.code.formatters.FormatterInterface;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

public class Formatter {
    public static final String STYLISH_FORMAT = "stylish";

    public static final String PLAINT_FORMAT = "plain";
    public static FormatterInterface getFormatter(String format) {
        switch (format) {
            case PLAINT_FORMAT -> {
                return new Plain();
            }

            default -> {
                return new Stylish();
            }
        }
    }
}
