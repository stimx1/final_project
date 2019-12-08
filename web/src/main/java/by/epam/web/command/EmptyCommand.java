package by.epam.web.command;

import by.epam.web.content.SessionRequestContent;
import by.epam.web.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmptyCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
}
