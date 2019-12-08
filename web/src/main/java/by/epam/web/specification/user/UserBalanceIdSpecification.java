package by.epam.web.specification.user;

import by.epam.web.connection.DbConnectionPool;
import by.epam.web.specification.EntitySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserBalanceIdSpecification implements EntitySpecification {
    private static final Logger logger = LogManager.getLogger(UserBalanceIdSpecification.class);
    private static final String SQL_SELECT_BALANCE = "SELECT user_id,amount FROM user_balance WHERE user_id = ?;";

    private int userId;

    public UserBalanceIdSpecification(int userId) {
        this.userId = userId;
    }

    @Override
    public PreparedStatement specified() {
        PreparedStatement statement = null;
        try(Connection connection = DbConnectionPool.INSTANCE.getConnection()) {
            statement = connection.prepareStatement(SQL_SELECT_BALANCE);
            statement.setInt(1,userId);
        } catch (SQLException e) {
            logger.catching(e);
        }
        return statement;
    }
}
