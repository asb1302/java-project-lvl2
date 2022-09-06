package hexlet.code.formatters;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class JsonTest extends AbstractFormatterTestCase {
    private static Json json;

    @BeforeAll
    public static void beforeAll() {
        json = new Json();
    }

    @Test
    void testFormatSimpleValues() throws Exception {
        String result = json.format(getSimpleDiffMap());
        String expected = Files.readString(Paths.get(getFixturePath("simple-diff.json")));

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testFormatNestedValues() throws Exception {
        String result = json.format(getNestedDiffMap());
        String expected = Files.readString(Paths.get(getFixturePath("nested-diff.json")));

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testFormatNullValues() throws Exception {
        String result = json.format(getNullDiffMap());
        String expected = Files.readString(Paths.get(getFixturePath("null-diff.json")));

        assertThat(result).isEqualTo(expected);
    }
}
