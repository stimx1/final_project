package by.epam.web.command.user;

import by.epam.web.command.ActionCommand;
import by.epam.web.command.AttributeName;
import by.epam.web.command.PageName;
import by.epam.web.command.SessionRequestContent;
import by.epam.web.entity.Subscription;
import by.epam.web.entity.User;
import by.epam.web.exception.CommandException;
import by.epam.web.exception.ServiceException;
import by.epam.web.resource.ConfigurationManager;
import by.epam.web.service.SubscriptionService;
import by.epam.web.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.List;

public class LoginCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String page = null;
        String email = sessionRequestContent.getParameter(AttributeName.EMAIL);
        String pass = sessionRequestContent.getParameter(AttributeName.PASSWORD);
        UserService userService = UserService.getINSTANCE();
        try {
            List<User> userList = userService.findUserByEmailAndPassword(email, pass);
            Iterator<User> iterator = userList.iterator();
            if (iterator.hasNext()) {
                User user = iterator.next();
                page = ConfigurationManager.getProperty(PageName.SERVICES);
                logger.info(user.getRole());
                sessionRequestContent.setSessionAttribute(AttributeName.CURRENT_USER, user);
                SubscriptionService subscriptionService = SubscriptionService.getInstance();
                List<Subscription> subscriptions = subscriptionService.findSubscription();
                sessionRequestContent.setAttribute(AttributeName.SUBSCRIPTIONS, subscriptions);
            } else {
                sessionRequestContent.setAttribute(AttributeName.LOGIN_ERROR, true);
                page = ConfigurationManager.getProperty(PageName.LOGIN);
            }
        } catch (ServiceException e) {
            logger.catching(e);
            throw new CommandException("Login error", e);
        }
        return page;
    }
}
