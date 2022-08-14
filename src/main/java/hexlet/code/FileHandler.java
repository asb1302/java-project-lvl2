package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.nio.file.Paths;
import java.util.Map;

public final class FileHandler implements FileHandlerInterface {
    @Override
    public Map<String, Object> handle(String filepath) throws Exception {
        File file = new File(filepath);
        String fileName = file.toString();

        int index = fileName.lastIndexOf('.');

        String fileExtension = fileName.substring(index + 1);

        ObjectMapper mapper = createObjectMapper(fileExtension);

        TypeReference<Map<String, Object>> type = new TypeReference<>() {
        };

        return mapper.readValue(Paths.get(filepath).toFile(), type);
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
