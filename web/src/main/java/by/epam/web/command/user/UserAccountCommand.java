package by.epam.web.command.user;

import by.epam.web.command.ActionCommand;
import by.epam.web.command.AttributeName;
import by.epam.web.command.PageName;
import by.epam.web.command.SessionRequestContent;
import by.epam.web.entity.Subscription;
import by.epam.web.entity.User;
import by.epam.web.entity.UserBalance;
import by.epam.web.exception.CommandException;
import by.epam.web.exception.ServiceException;
import by.epam.web.resource.ConfigurationManager;
import by.epam.web.service.AccountService;
import by.epam.web.service.SubscriptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.List;

public class UserAccountCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(UserAccountCommand.class);

    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String page = ConfigurationManager.getProperty(PageName.ACCOUNT);
        User user = (User) (sessionRequestContent.getSessionAttribute(AttributeName.CURRENT_USER));
        int userId = user.getId();
        AccountService accountService = AccountService.getInstance();
        SubscriptionService subscriptionService = SubscriptionService.getInstance();
        try {
            List<UserBalance> balanceList = accountService.findByUserId(userId);
            Iterator<UserBalance> iterator = balanceList.iterator();
            if (iterator.hasNext()) {
                int amount = iterator.next().getAmount();
                sessionRequestContent.setAttribute(AttributeName.AMOUNT, amount);
            } else {
                sessionRequestContent.setAttribute(AttributeName.AMOUNT, 0);
            }
            List<Subscription> subscriptions = subscriptionService.findBoughtSubscriptionByUserId(userId);
            sessionRequestContent.setAttribute(AttributeName.SUBSCRIPTIONS, subscriptions);
            logger.info(subscriptions);
        } catch (ServiceException e) {
            logger.catching(e);
            throw new CommandException("Balance not found", e);
        }
        return page;
    }
}
