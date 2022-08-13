package hexlet.code;

import hexlet.code.formatters.FormatterInterface;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FormatterTest {
    @Test
    void testGetFormatter() {
        FormatterInterface result1 = Formatter.getFormatter(Formatter.PLAINT_FORMAT);

        assertThat(result1).isInstanceOf(Plain.class);

        FormatterInterface result2 = Formatter.getFormatter(Formatter.STYLISH_FORMAT);
        assertThat(result2).isInstanceOf(Stylish.class);

        FormatterInterface result3 = Formatter.getFormatter("random class");
        assertThat(result3).isInstanceOf(Stylish.class);
    }
}
