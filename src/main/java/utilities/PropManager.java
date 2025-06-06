package utilities;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Asli Ekmekci
 */

public class PropManager {
    private static final Properties properties = new Properties();

    public static void load(String environment) {
        try (InputStream input = PropManager.class.getClassLoader()
                .getResourceAsStream(environment + ".properties")) {
            if (input != null) properties.load(input);
            else throw new RuntimeException("Could not load environment: " + environment);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
