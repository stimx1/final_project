package by.epam.web.command.user;

import by.epam.web.command.ActionCommand;
import by.epam.web.content.AttributeName;
import by.epam.web.content.PageName;
import by.epam.web.content.RedirectName;
import by.epam.web.content.SessionRequestContent;
import by.epam.web.entity.User;
import by.epam.web.exception.CommandException;
import by.epam.web.exception.ServiceException;
import by.epam.web.resource.ConfigurationManager;
import by.epam.web.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class UpdateUserCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(UpdateUserCommand.class);

    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String page = ConfigurationManager.getProperty(PageName.ACCOUNT);
        String pass = sessionRequestContent.getParameter(AttributeName.PASSWORD);
        String repeatPass = sessionRequestContent.getParameter(AttributeName.REPEATED_PASSWORD);
        String lastName = sessionRequestContent.getParameter(AttributeName.LAST_NAME);
        String firstName = sessionRequestContent.getParameter(AttributeName.FIRST_NAME);
        User user = (User)sessionRequestContent.getSessionAttribute(AttributeName.CURRENT_USER);
        UserService userService = UserService.getINSTANCE();
        logger.info(user.getEmail());
        try {
            Map<String,Object> map = userService.updateUser(user.getId(),user.getEmail(),pass,repeatPass,firstName,lastName);
            if(map.containsKey(AttributeName.FLAG)){
                sessionRequestContent.setAttribute(map);
            }else {
                user.setLastName(lastName);
                user.setFirstName(firstName);
                sessionRequestContent.setSessionAttribute(AttributeName.CURRENT_USER,user);
                sessionRequestContent.setAttribute(AttributeName.REDIRECT, RedirectName.ACCOUNT);
            }
        } catch (ServiceException e) {
            logger.catching(e);
            throw new CommandException("Update error",e);
        }
        return page;
    }
}
