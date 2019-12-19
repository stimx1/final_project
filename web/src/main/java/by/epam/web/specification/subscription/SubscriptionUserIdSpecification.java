package by.epam.web.specification.subscription;

import by.epam.web.connection.DbConnectionPool;
import by.epam.web.specification.EntitySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SubscriptionUserIdSpecification implements EntitySpecification {
    private static final Logger logger = LogManager.getLogger(SubscriptionUserIdSpecification.class);
    private static final String SQL_SELECT_SUBSCRIPTION = " SELECT s.id, s.name,s.price,s.duration,bs.start_day,bs.end_day, s.state" +
            " FROM subscriptions AS s LEFT JOIN bought_subscription AS bs ON s.id = bs.subscription_id WHERE bs.user_id = ?;";
    private int userId;

    public SubscriptionUserIdSpecification(int userId) {
        this.userId = userId;
    }

    @Override
    public PreparedStatement specified() {
        PreparedStatement statement = null;
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection()) {
            statement = connection.prepareStatement(SQL_SELECT_SUBSCRIPTION);
            statement.setInt(1, userId);
        } catch (SQLException e) {
            logger.catching(e);
        }
        return statement;
    }
}
