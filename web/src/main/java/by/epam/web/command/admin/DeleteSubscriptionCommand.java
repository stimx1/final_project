package by.epam.web.command.admin;

import by.epam.web.command.*;
import by.epam.web.exception.CommandException;
import by.epam.web.exception.ServiceException;
import by.epam.web.resource.ConfigurationManager;
import by.epam.web.service.SubscriptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteSubscriptionCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(DeleteSubscriptionCommand.class);

    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String page = ConfigurationManager.getProperty(PageName.SERVICES);
        int subscriptionId = Integer.parseInt(sessionRequestContent.getParameter(AttributeName.SUBSCRIPTION_ID));
        String name = sessionRequestContent.getParameter(AttributeName.SUBSCRIPTION_NAME);
        int price = Integer.parseInt(sessionRequestContent.getParameter(AttributeName.SUBSCRIPTION_PRICE));
        int duration = Integer.parseInt(sessionRequestContent.getParameter(AttributeName.SUBSCRIPTION_DURATION));
        SubscriptionService subscriptionService = SubscriptionService.getInstance();
        try {
            subscriptionService.deleteSubscription(subscriptionId, name, price, duration);
            sessionRequestContent.setAttribute(AttributeName.REDIRECT, RedirectName.SERVICES);
        } catch (ServiceException e) {
            logger.catching(e);
            throw new CommandException("Subscription delete error", e);
        }
        return page;
    }
}
