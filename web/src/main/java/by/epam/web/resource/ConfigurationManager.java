package by.epam.web.resource;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private static final String CONFIG = "config";

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(CONFIG);

    private ConfigurationManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
