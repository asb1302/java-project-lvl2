package hexlet.code;

import java.util.Map;

/**
 * The service for comparing two yaml/json files and displaying their differences in different formats.
 */
public class Differ {
    public static final String OLD_VALUE_KEY = "oldValue";
    public static final String NEW_VALUE_KEY = "newValue";
    public static final ContentHandler CONTENT_HANDLER = new ContentHandlerImp();
    private static final DiffMaker DIFF_MAKER = new DiffMakerImp();

    /**
     * Method for generating and displaying differences.
     *
     * @param filepath1 path to first file
     * @param filepath2 path to second file
     * @param format format of output result
     *
     * @return string result with difference
     */
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> map1 = CONTENT_HANDLER.handle(
            FileHandler.getContent(filepath1),
            FileHandler.getExtension(filepath1)
        );

        Map<String, Object> map2 = CONTENT_HANDLER.handle(
            FileHandler.getContent(filepath2),
            FileHandler.getExtension(filepath2)
        );

        return FormatterFactory.getFormatter(format).format(DIFF_MAKER.make(map1, map2));
    }

    /**
     * Method for generating and displaying differences.
     *
     * @param filepath1 path to first file
     * @param filepath2 path to second file
     *
     * @return string result with difference
     */
    public static String generate(String filepath1, String filepath2) throws Exception {
        Map<String, Object> map1 = CONTENT_HANDLER.handle(
                FileHandler.getContent(filepath1),
                FileHandler.getExtension(filepath1)
        );

        Map<String, Object> map2 = CONTENT_HANDLER.handle(
                FileHandler.getContent(filepath2),
                FileHandler.getExtension(filepath2)
        );

        return FormatterFactory.getFormatter(FormatterFactory.STYLISH_FORMAT).format(DIFF_MAKER.make(map1, map2));
    }
}
