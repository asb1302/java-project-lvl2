package hexlet.code;

import java.nio.file.Paths;

public abstract class TestCase {
    protected static final Integer EXAMPLE_INT_1 = 123;
    protected static final Integer EXAMPLE_INT_2 = 345;
    protected static final Integer EXAMPLE_INT_3 = 567;
    protected static final Integer EXAMPLE_INT_4 = 789;

    protected static final String JSON_1_PATH = getFixturePath("file1.json");
    protected static final String YAML_2_PATH = getFixturePath("file2.yaml");
    protected static final String YAML_3_PATH = getFixturePath("file3.yaml");
    protected static final String YAML_4_PATH = getFixturePath("file4.yaml");

    protected static String getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName).toAbsolutePath().toString();
    }
}
