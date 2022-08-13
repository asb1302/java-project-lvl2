package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.Callable;
@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.1",
        description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<Integer> {
    @Option(
        names = { "-f", "--format" },
        paramLabel = "format",
        description = "output format [default: stylish]",
        defaultValue = "stylish"
    )
    private String format;

    @Parameters(
        paramLabel = "filepath1",
        description = "path to first file"
    )
    private String filepath1;

    @Parameters(
        paramLabel = "filepath2",
        description = "path to second file"
    )
    private String filepath2;

    private final DifferInterface differ;
    App() {
        this.differ = new Differ();
    }
    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);

        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        try {
            File file1 = new File(this.filepath1);
            String file1Name = file1.toString();

            File file2 = new File(this.filepath2);
            String file2Name = file2.toString();

            int index = file1Name.lastIndexOf('.');

            String file1Extension = file1Name.substring(index + 1);
            String file2Extension = file2Name.substring(index + 1);

            ObjectMapper mapper1 = createObjectMapper(file1Extension);
            ObjectMapper mapper2 = createObjectMapper(file2Extension);
            TypeReference<Map<String, Object>> type = new TypeReference<>() {
            };

            Map<String, Object> mapFile1 = mapper1.readValue(Paths.get(this.filepath1).toFile(), type);
            Map<String, Object> mapFile2 = mapper2.readValue(Paths.get(this.filepath2).toFile(), type);

            String diff = this.differ.generate(mapFile1, mapFile2, this.format);

            System.out.println(diff);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    private ObjectMapper createObjectMapper(String extension) throws Exception {
        switch (extension) {
            case "json" -> {
                return new ObjectMapper();
            }
            case "yaml" -> {
                return new ObjectMapper(new YAMLFactory());
            }

            default -> throw new Exception("Invalid files format");
        }
    }
}
