package by.epam.web.command.user;

import by.epam.web.command.ActionCommand;
import by.epam.web.command.AttributeName;
import by.epam.web.command.PageName;
import by.epam.web.command.SessionRequestContent;
import by.epam.web.entity.Subscription;
import by.epam.web.exception.CommandException;
import by.epam.web.exception.ServiceException;
import by.epam.web.resource.ConfigurationManager;
import by.epam.web.service.SubscriptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GetServicesCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(GetServicesCommand.class);

    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String page = ConfigurationManager.getProperty(PageName.SERVICES);
        SubscriptionService subscriptionService = SubscriptionService.getInstance();
        logger.info("lol");
        try {
            List<Subscription> subscriptions = subscriptionService.findSubscription();
            sessionRequestContent.setAttribute(AttributeName.SUBSCRIPTIONS,subscriptions);
        } catch (ServiceException e) {
            logger.catching(e);
            throw new CommandException("Subscriptions not found",e);
        }
        return page;
    }
}
