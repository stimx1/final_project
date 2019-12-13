package by.epam.web.specification.subscription;

import by.epam.web.connection.DbConnectionPool;
import by.epam.web.specification.EntitySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SubscriptionSpecification implements EntitySpecification {
    private static final Logger logger = LogManager.getLogger(SubscriptionSpecification.class);
    private static final String SQL_SELECT_SUBSCRIPTION = "SELECT id, name, price, duration,start_day,end_day,state FROM subscriptions;";

    @Override
    public PreparedStatement specified() {
        PreparedStatement statement = null;
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection()) {
            statement = connection.prepareStatement(SQL_SELECT_SUBSCRIPTION);
        } catch (SQLException e) {
            logger.catching(e);
        }
        return statement;
    }
}
