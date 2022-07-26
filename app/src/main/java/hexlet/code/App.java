package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.1",
        description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<Integer> {
    @Option(
            names = {"-f", "--format"},
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

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);

        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        try {
            String diff = Differ.generate(
                    this.filepath1,
                    this.filepath2,
                    this.format
            );

            System.out.println(diff);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return 0;
    }
}
