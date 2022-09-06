package hexlet.code.formatters;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class StylishTest extends AbstractFormatterTestCase {
    private static Stylish stylish;

    @BeforeAll
    public static void beforeAll() {
        stylish = new Stylish();
    }

    @Test
    void testFormatSimpleValues() throws IOException {
        String result = stylish.format(getSimpleDiffMap());
        String expected = Files.readString(Paths.get(getFixturePath("simple-diff-stylish.txt")));

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testFormatNestedValues() throws IOException {
        String result = stylish.format(getNestedDiffMap());
        String expected = Files.readString(Paths.get(getFixturePath("nested-diff-stylish.txt")));

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testFormatNullValues() throws IOException {
        String result = stylish.format(getNullDiffMap());
        String expected = Files.readString(Paths.get(getFixturePath("null-diff-stylish.txt")));

        assertThat(result).isEqualTo(expected);
    }
}
