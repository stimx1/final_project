package by.epam.web.command.admin;

import by.epam.web.command.ActionCommand;
import by.epam.web.command.AttributeName;
import by.epam.web.command.PageName;
import by.epam.web.command.SessionRequestContent;
import by.epam.web.entity.User;
import by.epam.web.exception.CommandException;
import by.epam.web.exception.ServiceException;
import by.epam.web.resource.ConfigurationManager;
import by.epam.web.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GetUsersCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(GetUsersCommand.class);

    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String page = ConfigurationManager.getProperty(PageName.USERS);
        UserService service = UserService.getINSTANCE();
        List<User> list = null;
        try {
            list = service.findUsers();
        } catch (ServiceException e) {
            logger.catching(e);
            throw new CommandException("Users get error",e);
        }
        sessionRequestContent.setAttribute(AttributeName.USER_LIST, list);

        return page;
    }
}
