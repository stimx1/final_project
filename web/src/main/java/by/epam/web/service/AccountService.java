package by.epam.web.service;

import by.epam.web.entity.BoughtSubscription;
import by.epam.web.entity.UserBalance;
import by.epam.web.exception.EntityRepositoryException;
import by.epam.web.exception.ServiceException;
import by.epam.web.repository.UserBalanceRepository;
import by.epam.web.specification.user.UserBalanceIdSpecification;
import by.epam.web.validation.BalanceValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

public class AccountService {
    private static final Logger logger = LogManager.getLogger(AccountService.class);
    private static final AccountService INSTANCE = new AccountService();
    private static final int ADMIN_ID = 1;
    private UserBalanceRepository repository = new UserBalanceRepository();

    private AccountService() {
    }

    public static AccountService getInstance() {
        return INSTANCE;
    }

    public boolean updateBalance(int userId, int amount) throws ServiceException {
        try {
            List<UserBalance> balanceList = findByUserId(userId);
            Iterator<UserBalance> iterator = balanceList.iterator();
            UserBalance userBalance;
            if (iterator.hasNext()) {
                userBalance = iterator.next();
                if (BalanceValidator.validateDeposit(userBalance, amount)) {
                    userBalance.setAmount(amount + userBalance.getAmount());
                    repository.updateEntity(userBalance);
                    return true;
                }
                return false;
            }
            throw new ServiceException("Balance not found");
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Balance update error", e);
        }
    }

    public List<UserBalance> findByUserId(int userId) throws ServiceException {
        try {
            return repository.query(new UserBalanceIdSpecification(userId));
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Query error", e);
        }
    }

    public boolean transaction(int userId, int subscriptionId, int price, int duration) throws ServiceException {
        try {
            BoughtSubscription boughtSubscription = new BoughtSubscription(subscriptionId, userId, price, LocalDate.now(), LocalDate.now().plusDays(duration));
            List<UserBalance> fromList = repository.query(new UserBalanceIdSpecification(userId));
            List<UserBalance> toList = repository.query(new UserBalanceIdSpecification(ADMIN_ID));
            Iterator<UserBalance> iteratorFrom = fromList.iterator();
            Iterator<UserBalance> iteratorTo = toList.iterator();
            if (iteratorFrom.hasNext() && iteratorTo.hasNext()) {
                UserBalance from = iteratorFrom.next();
                UserBalance to = iteratorTo.next();
                if (BalanceValidator.validateTransaction(from, boughtSubscription.getPrice())) {
                    repository.transaction(boughtSubscription, from, to);
                    return true;
                }
                return false;
            }
            throw new ServiceException("User balance not found");
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Transaction error", e);
        }
    }
}
