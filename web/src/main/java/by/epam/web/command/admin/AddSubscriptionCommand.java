package by.epam.web.command.admin;

import by.epam.web.command.ActionCommand;
import by.epam.web.command.AttributeName;
import by.epam.web.command.PageName;
import by.epam.web.command.RedirectName;
import by.epam.web.command.SessionRequestContent;
import by.epam.web.exception.CommandException;
import by.epam.web.exception.ServiceException;
import by.epam.web.resource.ConfigurationManager;
import by.epam.web.service.SubscriptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddSubscriptionCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AddSubscriptionCommand.class);

    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String page = ConfigurationManager.getProperty(PageName.SERVICES);
        SubscriptionService subscriptionService = SubscriptionService.getInstance();
        String name = sessionRequestContent.getParameter(AttributeName.SUBSCRIPTION_NAME);
        int price = Integer.parseInt(sessionRequestContent.getParameter(AttributeName.SUBSCRIPTION_PRICE));
        int duration = Integer.parseInt(sessionRequestContent.getParameter(AttributeName.SUBSCRIPTION_DURATION));
        try {
            subscriptionService.addSubscription(name,price,duration);
            sessionRequestContent.setAttribute(AttributeName.REDIRECT, RedirectName.SERVICES);
        } catch (ServiceException e) {
            logger.catching(e);
            throw new CommandException("Subscription add error",e);
        }
        return page;
    }
}
