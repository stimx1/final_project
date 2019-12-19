package by.epam.web.command.admin;

import by.epam.web.command.ActionCommand;
import by.epam.web.command.AttributeName;
import by.epam.web.command.PageName;
import by.epam.web.command.SessionRequestContent;
import by.epam.web.exception.CommandException;
import by.epam.web.exception.ServiceException;
import by.epam.web.resource.ConfigurationManager;
import by.epam.web.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteUserCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(DeleteUserCommand.class);

    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String page = ConfigurationManager.getProperty(PageName.ADMIN);
        int id = Integer.valueOf(sessionRequestContent.getParameter(AttributeName.USER_ID));
        UserService userService = UserService.getINSTANCE();
        try {
            userService.deleteUser(id);
        } catch (ServiceException e) {
            logger.catching(e);
            throw new CommandException("User delete error", e);
        }
        return page;
    }
}
