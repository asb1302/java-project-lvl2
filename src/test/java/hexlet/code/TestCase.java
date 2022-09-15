package hexlet.code;

import java.nio.file.Paths;

public abstract class TestCase {
    protected static final Integer EXAMPLE_INT_1 = 123;
    protected static final Integer EXAMPLE_INT_2 = 345;
    protected static final Integer EXAMPLE_INT_3 = 567;
    protected static final Integer EXAMPLE_INT_4 = 789;

    protected static final String BEFORE_YAML = getFixturePath("before.yaml");
    protected static final String BEFORE_JSON = getFixturePath("before.json");

    protected static String getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName).toAbsolutePath().toString();
    }
}
