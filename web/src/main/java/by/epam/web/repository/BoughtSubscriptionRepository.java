package by.epam.web.repository;

import by.epam.web.command.AttributeName;
import by.epam.web.connection.DbConnectionPool;
import by.epam.web.entity.BoughtSubscription;
import by.epam.web.exception.EntityRepositoryException;
import by.epam.web.specification.EntitySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class BoughtSubscriptionRepository implements EntityRepository<BoughtSubscription> {
    private static final Logger logger = LogManager.getLogger(BoughtSubscriptionRepository.class);
    private static final String SQL_INSERT_SUBSCRIPTION = "INSERT INTO bought_subscription (subscription_id,user_id,price,start_day,end_day) VALUES (?,?,?,?,?);";
    private static final String SQL_DELETE_SUBSCRIPTION = "DELETE FROM bought_subscription WHERE id = ? ;";

    @Override
    public void addEntity(BoughtSubscription boughtSubscription) throws EntityRepositoryException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_SUBSCRIPTION)) {
            statement.setInt(1, boughtSubscription.getSubscriptionId());
            statement.setInt(2, boughtSubscription.getUserId());
            statement.setInt(3, boughtSubscription.getPrice());
            statement.setDate(4, Date.valueOf(boughtSubscription.getStartDay()));
            statement.setDate(5, Date.valueOf(boughtSubscription.getEndDay()));
            statement.execute();
        } catch (SQLException e) {
            logger.catching(e);
            throw new EntityRepositoryException("Bought subscription add error", e);
        }
    }

    @Override
    public void removeEntity(BoughtSubscription boughtSubscription) throws EntityRepositoryException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_SUBSCRIPTION)) {
            statement.setInt(1, boughtSubscription.getId());
            statement.execute();
        } catch (SQLException e) {
            logger.catching(e);
            throw new EntityRepositoryException("Bought subscription remove error", e);
        }
    }

    @Override
    public void updateEntity(BoughtSubscription boughtSubscription) throws EntityRepositoryException {

    }

    @Override
    public List<BoughtSubscription> query(EntitySpecification specification) throws EntityRepositoryException {
        List<BoughtSubscription> subscriptions = new LinkedList<>();
        try (PreparedStatement statement = specification.specified();
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                BoughtSubscription subscription = new BoughtSubscription();
                subscription.setId(resultSet.getInt(AttributeName.ID));
                subscription.setUserId(resultSet.getInt(AttributeName.USER_ID));
                subscription.setPrice(resultSet.getInt(AttributeName.PRICE));
                subscription.setSubscriptionId(resultSet.getInt(AttributeName.SUBSCRIPTION_ID));
                subscription.setStartDay(resultSet.getDate(AttributeName.START_DAY).toLocalDate());
                subscription.setEndDay(resultSet.getDate(AttributeName.END_DAY).toLocalDate());
                subscriptions.add(subscription);
            }
        } catch (SQLException e) {
            logger.catching(e);
            throw new EntityRepositoryException("Incorrect query error", e);
        }
        return subscriptions;
    }
}
