package by.epam.web.command.user;

import by.epam.web.command.ActionCommand;
import by.epam.web.command.AttributeName;
import by.epam.web.command.PageName;
import by.epam.web.command.SessionRequestContent;
import by.epam.web.resource.ConfigurationManager;

public class ChangeLocaleCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = ConfigurationManager.getProperty(PageName.INDEX);
        String locale = sessionRequestContent.getParameter(AttributeName.LANGUAGE);
        sessionRequestContent.setSessionAttribute(AttributeName.LOCALE, locale);
        return page;
    }
}
