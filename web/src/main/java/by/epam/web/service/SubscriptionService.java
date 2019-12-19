package by.epam.web.service;

import by.epam.web.entity.State;
import by.epam.web.entity.Subscription;
import by.epam.web.exception.EntityRepositoryException;
import by.epam.web.exception.ServiceException;
import by.epam.web.repository.SubscriptionRepository;
import by.epam.web.specification.subscription.SubscriptionSpecification;
import by.epam.web.specification.subscription.SubscriptionUserIdSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class SubscriptionService {
    private static final Logger logger = LogManager.getLogger(SubscriptionService.class);
    private static final SubscriptionService INSTANCE = new SubscriptionService();
    private SubscriptionRepository repository = new SubscriptionRepository();

    private SubscriptionService() {
    }

    public static SubscriptionService getInstance() {
        return INSTANCE;
    }

    public void addSubscription(String name, int price, int duration) throws ServiceException {
        Subscription subscription = new Subscription(name, price, duration);
        try {
            repository.addEntity(subscription);
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Subscription add error", e);
        }
    }

    public void deleteSubscription(int id, String name, int price, int duration) throws ServiceException {
        Subscription subscription = new Subscription(id, State.BLOCKED, name, price, duration);
        try {
            repository.updateEntity(subscription);
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Subscription remove error", e);
        }
    }

    public List<Subscription> findSubscription() throws ServiceException {
        try {
            return repository.query(new SubscriptionSpecification()).stream()
                    .filter(o -> o.getState() == State.UNBLOCKED)
                    .collect(Collectors.toList());
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Subscription find error", e);
        }
    }

    public List<Subscription> findBoughtSubscriptionByUserId(int userId) throws ServiceException {
        try {
            logger.info("tut");
            return repository.query(new SubscriptionUserIdSpecification(userId)).stream()
                    .filter(o -> o.getState() == State.UNBLOCKED)
                    .collect(Collectors.toList());
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Subscription find error", e);
        }
    }
}
