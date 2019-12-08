package by.epam.web.service;

import by.epam.web.entity.BoughtSubscription;
import by.epam.web.exception.EntityRepositoryException;
import by.epam.web.exception.ServiceException;
import by.epam.web.repository.BoughtSubscriptionRepository;
import by.epam.web.specification.subscription.BoughtSubscriptionUserIdSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

public class BoughtSubscriptionService {
    private static final Logger logger = LogManager.getLogger(BoughtSubscriptionService.class);
    private static final BoughtSubscriptionService INSTANCE = new BoughtSubscriptionService();
    private BoughtSubscriptionRepository repository = new BoughtSubscriptionRepository();

    private BoughtSubscriptionService(){}

    public static BoughtSubscriptionService getInstance() {
        return INSTANCE;
    }

    public void addBoughtSubscription(int subscriptionId, int userId, int price, LocalDate startDay, LocalDate endDay) throws ServiceException {
        BoughtSubscription subscription = new BoughtSubscription(subscriptionId,userId,price,startDay,endDay);
        try {
            repository.addEntity(subscription);
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Bought subscription add error",e);
        }
    }
    public List<BoughtSubscription> findByUserId(int userId) throws ServiceException {
        try {
            return repository.query(new BoughtSubscriptionUserIdSpecification(userId));
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Bought subscription find error",e);
        }
    }
}
