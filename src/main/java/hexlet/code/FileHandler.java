package hexlet.code;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class FileHandler {
    public static String getContent(String filepath) throws Exception {
        return Files.readString(Paths.get(filepath));
    }

    public static String getExtension(String filepath) {
        File file = new File(filepath);
        String fileName = file.toString();

        int index = fileName.lastIndexOf('.');

        return fileName.substring(index + 1);
    }
}
