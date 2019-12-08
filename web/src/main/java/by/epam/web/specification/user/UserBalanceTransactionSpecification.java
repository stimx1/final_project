package by.epam.web.specification.user;

import by.epam.web.connection.DbConnectionPool;
import by.epam.web.specification.EntitySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserBalanceTransactionSpecification implements EntitySpecification {
    private static final Logger logger = LogManager.getLogger(UserBalanceTransactionSpecification.class);
    private static final String SQL_SELECT_BALANCES = "SELECT user_id, amount FROM user_balance WHERE user_id = ? or user_id = ?;";

    private int fromId;
    private int toId;

    public UserBalanceTransactionSpecification(int fromIdId, int toId) {
        this.fromId = fromId;
        this.toId = toId;
    }

    @Override
    public PreparedStatement specified() {
        PreparedStatement statement = null;
        try(Connection connection = DbConnectionPool.INSTANCE.getConnection()) {
            statement = connection.prepareStatement(SQL_SELECT_BALANCES);
            statement.setInt(1,fromId);
            statement.setInt(2,toId);
        } catch (SQLException e) {
            logger.catching(e);
        }
        return statement;
    }
}
