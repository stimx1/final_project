package by.epam.web.command;

import by.epam.web.resource.ConfigurationManager;

public class EmptyCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
}
