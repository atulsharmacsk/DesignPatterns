package Utils;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FileUtils {

    @SneakyThrows
    public static String readFile(String path) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);
        return new String(Objects.requireNonNull(inputStream).readAllBytes(), StandardCharsets.UTF_8);
    }
}
