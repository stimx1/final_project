package by.epam.web.command.user;

import by.epam.web.command.ActionCommand;
import by.epam.web.command.PageName;
import by.epam.web.command.SessionRequestContent;
import by.epam.web.resource.ConfigurationManager;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = ConfigurationManager.getProperty(PageName.INDEX);
        sessionRequestContent.setInvalidate(true);
        return page;
    }
}
