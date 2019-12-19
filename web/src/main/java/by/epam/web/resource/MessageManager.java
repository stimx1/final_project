package by.epam.web.resource;

import java.util.ResourceBundle;

public class MessageManager {
    private static final String MESSAGE = "message";
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(MESSAGE);


    private MessageManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
