package by.epam.web.resource;

import java.util.ResourceBundle;

public class DBConfigurationManger {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("DBconfig");
    private DBConfigurationManger(){ }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
