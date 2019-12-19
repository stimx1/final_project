package by.epam.web.resource;

import java.util.ResourceBundle;

public class DBConfigurationManger {
    private static final String DB_CONFIG = "DbConfig";
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(DB_CONFIG);

    private DBConfigurationManger() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
