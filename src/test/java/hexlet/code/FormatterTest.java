package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FormatterTest {
    @Test
    void testGetFormatter() {
        Formatter result1 = FormatterFactory.getFormatter(FormatterFactory.PLAINT_FORMAT);

        assertThat(result1).isInstanceOf(Plain.class);

        Formatter result2 = FormatterFactory.getFormatter(FormatterFactory.STYLISH_FORMAT);
        assertThat(result2).isInstanceOf(Stylish.class);

        Formatter result3 = FormatterFactory.getFormatter("random class");
        assertThat(result3).isInstanceOf(Stylish.class);
    }
}
