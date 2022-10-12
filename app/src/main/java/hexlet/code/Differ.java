package hexlet.code;

import java.util.Map;

/**
 * The service for comparing two yaml/json files and displaying their differences in different formats.
 */
public class Differ {
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
        Map<String, Object> content1 = CONTENT_HANDLER.handle(
            FileHandler.getContent(filepath1),
            FileHandler.getExtension(filepath1)
        );

        Map<String, Object> content2 = CONTENT_HANDLER.handle(
            FileHandler.getContent(filepath2),
            FileHandler.getExtension(filepath2)
        );

        return FormatterFactory.getFormatter(format).format(DIFF_MAKER.make(content1, content2));
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
        return Differ.generate(filepath1, filepath2, FormatterFactory.STYLISH_FORMAT);
    }
}
