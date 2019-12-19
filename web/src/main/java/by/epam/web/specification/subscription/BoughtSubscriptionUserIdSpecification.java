package by.epam.web.specification.subscription;

import by.epam.web.connection.DbConnectionPool;
import by.epam.web.specification.EntitySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BoughtSubscriptionUserIdSpecification implements EntitySpecification {
    private static final Logger logger = LogManager.getLogger(BoughtSubscriptionUserIdSpecification.class);
    private static final String SQL_SELECT_SUBSCRIPTION = "SELECT id, subscription_id, user_id, price,start_day,end_day FROM bought_subscription WHERE user_id =?;";
    private int userId;

    public BoughtSubscriptionUserIdSpecification(int userId) {
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
