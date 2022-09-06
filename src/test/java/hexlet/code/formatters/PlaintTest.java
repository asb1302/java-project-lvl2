package hexlet.code.formatters;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class PlaintTest extends AbstractFormatterTestCase {
    private static Plain plain;

    @BeforeAll
    public static void beforeAll() {
        plain = new Plain();
    }

    @Test
    void testFormatSimpleValues() throws IOException {
        String result = plain.format(getSimpleDiffMap());
        String expected = Files.readString(Paths.get(getFixturePath("simple-diff-plain.txt")));

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testFormatNestedValues() throws IOException {
        String result = plain.format(getNestedDiffMap());
        String expected = Files.readString(Paths.get(getFixturePath("nested-diff-plain.txt")));

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testFormatNullValues() throws IOException {
        String result = plain.format(getNullDiffMap());
        String expected = Files.readString(Paths.get(getFixturePath("null-diff-plain.txt")));

        assertThat(result).isEqualTo(expected);
    }
}
