package by.epam.web.command.user;

import by.epam.web.command.*;
import by.epam.web.entity.Subscription;
import by.epam.web.entity.User;
import by.epam.web.exception.CommandException;
import by.epam.web.exception.ServiceException;
import by.epam.web.resource.ConfigurationManager;
import by.epam.web.service.AccountService;
import by.epam.web.service.SubscriptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DepositAccountCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(DepositAccountCommand.class);
    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String page = ConfigurationManager.getProperty(PageName.ACCOUNT);
        User user = (User)(sessionRequestContent.getSessionAttribute(AttributeName.CURRENT_USER));
        int userId = user.getId();
        String amount = sessionRequestContent.getParameter(AttributeName.AMOUNT);
        AccountService accountService = AccountService.getInstance();
        try {
            boolean correct = accountService.updateBalance(userId,amount);
            if(!correct) {
                SubscriptionService subscriptionService = SubscriptionService.getInstance();
                List<Subscription> subscriptions = subscriptionService.findBoughtSubscriptionByUserId(userId);
                int currentAmount = Integer.parseInt(sessionRequestContent.getParameter(AttributeName.CURRENT_AMOUNT));
                sessionRequestContent.setAttribute(AttributeName.AMOUNT,currentAmount);
                sessionRequestContent.setAttribute(AttributeName.SUBSCRIPTIONS,subscriptions);
                sessionRequestContent.setAttribute(AttributeName.DEPOSIT_BALANCE_ERROR, true);
            }else {
                sessionRequestContent.setAttribute(AttributeName.REDIRECT, RedirectName.ACCOUNT);
            }
        } catch (ServiceException e) {
            logger.catching(e);
            throw new CommandException("Balance update error",e);
        }
        return page;
    }
}
