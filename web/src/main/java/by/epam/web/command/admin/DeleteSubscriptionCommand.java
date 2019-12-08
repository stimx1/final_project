package by.epam.web.command.admin;

import by.epam.web.command.ActionCommand;
import by.epam.web.content.AttributeName;
import by.epam.web.content.PageName;
import by.epam.web.content.SessionRequestContent;
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
        SubscriptionService subscriptionService = SubscriptionService.getInstance();
        try {
            subscriptionService.deleteSubscription(subscriptionId);
            sessionRequestContent.setAttribute("redirect","/controller?command=get_services");
        } catch (ServiceException e) {
            logger.catching(e);
            throw new CommandException("Subscription delete error",e);
        }
        return page;
    }
}
