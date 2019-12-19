package by.epam.web.command.user;

import by.epam.web.command.*;
import by.epam.web.entity.User;
import by.epam.web.exception.CommandException;
import by.epam.web.exception.ServiceException;
import by.epam.web.resource.ConfigurationManager;
import by.epam.web.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BuySubscriptionCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(BuySubscriptionCommand.class);
    private static final int ADMIN_ID = 1;

    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String page = ConfigurationManager.getProperty(PageName.SERVICES);
        AccountService accountService = AccountService.getInstance();
        User user = (User) (sessionRequestContent.getSessionAttribute(AttributeName.CURRENT_USER));
        int userId = user.getId();
        int subscriptionId = Integer.parseInt(sessionRequestContent.getParameter(AttributeName.SUBSCRIPTION_ID));
        int price = Integer.parseInt(sessionRequestContent.getParameter(AttributeName.SUBSCRIPTION_PRICE));
        int duration = Integer.parseInt(sessionRequestContent.getParameter(AttributeName.SUBSCRIPTION_DURATION));
        try {
            accountService.transaction(userId, subscriptionId, price, duration);
            sessionRequestContent.setAttribute(AttributeName.REDIRECT, RedirectName.SERVICES);
        } catch (ServiceException e) {
            logger.catching(e);
            throw new CommandException("Transaction error", e);
        }
        return page;
    }
}
