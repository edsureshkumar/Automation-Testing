package framework.config;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class ConfigManager {
    private static final Properties properties = new Properties();
    static {
        try (FileInputStream fis =
                     new FileInputStream("src/test/resources/config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }
    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Key not found in config: " + key);
        }
        return value.trim();
    }
}
