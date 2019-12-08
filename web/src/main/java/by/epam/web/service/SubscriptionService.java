package by.epam.web.service;

import by.epam.web.entity.Subscription;
import by.epam.web.exception.EntityRepositoryException;
import by.epam.web.exception.ServiceException;
import by.epam.web.repository.SubscriptionRepository;
import by.epam.web.specification.subscription.SubscriptionUserIdSpecification;
import by.epam.web.specification.subscription.SubscriptionSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SubscriptionService {
    private static final Logger logger = LogManager.getLogger(SubscriptionService.class);
    private static final SubscriptionService INSTANCE= new SubscriptionService();
    private SubscriptionRepository repository = new SubscriptionRepository();

    private SubscriptionService(){}

    public static SubscriptionService getInstance() {
        return INSTANCE;
    }

    public void addSubscription(String name, int price,int duration) throws ServiceException {
        Subscription subscription = new Subscription(name,price,duration);
        try {
            repository.addEntity(subscription);
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Subscription add error",e);
        }
    }

    public void deleteSubscription(int id) throws ServiceException {
        Subscription subscription = new Subscription();
        subscription.setId(id);
        try {
            repository.removeEntity(subscription);
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Subscription remove error",e);
        }
    }

    public List<Subscription> findSubscription() throws ServiceException {
        try {
            return repository.query(new SubscriptionSpecification());
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Subscription find error",e);
        }
    }

    public List<Subscription> findBoughtSubscriptionByUserId(int userId) throws ServiceException {
        try {
            logger.info("tut");
            return repository.query(new SubscriptionUserIdSpecification(userId));
        } catch (EntityRepositoryException e){
            logger.catching(e);
            throw new ServiceException("Subscription find error",e);
        }
    }
}
