package by.epam.web.command.user;

import by.epam.web.command.ActionCommand;
import by.epam.web.command.AttributeName;
import by.epam.web.command.PageName;
import by.epam.web.command.SessionRequestContent;
import by.epam.web.exception.ServiceException;
import by.epam.web.resource.ConfigurationManager;
import by.epam.web.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class RegistrationCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(RegistrationCommand.class);

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        logger.info("inside");
        String page = null;
        String pass = sessionRequestContent.getParameter(AttributeName.PASSWORD);
        String repeatPass = sessionRequestContent.getParameter(AttributeName.REPEATED_PASSWORD);
        String email = sessionRequestContent.getParameter(AttributeName.EMAIL);
        String lastName = sessionRequestContent.getParameter(AttributeName.LAST_NAME);
        String firstName = sessionRequestContent.getParameter(AttributeName.FIRST_NAME);
        UserService userService = UserService.getINSTANCE();
        try {
            Map<String, Object> attributeMap = userService.register(email, pass, repeatPass, firstName, lastName);
            if (!attributeMap.containsKey(AttributeName.FLAG)) {
                page = ConfigurationManager.getProperty(PageName.INDEX);
            } else {
                logger.info(attributeMap);
                sessionRequestContent.setAttribute(attributeMap);
                page = ConfigurationManager.getProperty(PageName.LOGIN);
            }
        } catch (ServiceException e) {
            logger.catching(e);
            page = ConfigurationManager.getProperty(PageName.LOGIN);
        }
        logger.info(page);
        return page;
    }
}
