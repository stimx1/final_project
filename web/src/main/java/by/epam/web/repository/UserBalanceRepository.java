package by.epam.web.repository;

import by.epam.web.connection.DbConnectionPool;
import by.epam.web.entity.BoughtSubscription;
import by.epam.web.entity.UserBalance;
import by.epam.web.exception.EntityRepositoryException;
import by.epam.web.specification.EntitySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserBalanceRepository implements EntityRepository<UserBalance> {
    private static final Logger logger = LogManager.getLogger(UserBalanceRepository.class);
    private static final String SQL_UPDATE_BALANCE = "UPDATE user_balance SET amount = ? WHERE user_id = ?;";
    private static final String SQL_INSERT_BOUGHT_SUBSCRIPTION = "INSERT INTO bought_subscription (subscription_id,user_id, price, start_day, end_day)" +
            " VALUES(?,?,?,?,?);";

    @Override
    public void addEntity(UserBalance userBalance) throws EntityRepositoryException {

    }

    @Override
    public void removeEntity(UserBalance userBalance) throws EntityRepositoryException {

    }

    @Override
    public void updateEntity(UserBalance userBalance) throws EntityRepositoryException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_BALANCE)) {
            statement.setInt(1, userBalance.getAmount());
            statement.setInt(2, userBalance.getUserId());
            statement.execute();
        } catch (SQLException e) {
            logger.catching(e);
            throw new EntityRepositoryException("Balance update error", e);
        }
    }

    @Override
    public List<UserBalance> query(EntitySpecification specification) throws EntityRepositoryException {
        List<UserBalance> balances = new LinkedList<>();
        try (PreparedStatement statement = specification.specified();
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                UserBalance userBalance = new UserBalance();
                userBalance.setUserId(resultSet.getInt(ColumnName.USER_ID));
                userBalance.setAmount(resultSet.getInt(ColumnName.AMOUNT));
                balances.add(userBalance);
            }
        } catch (SQLException e) {
            logger.catching(e);
            throw new EntityRepositoryException("Query error", e);
        }
        return balances;
    }

    public void transaction(BoughtSubscription boughtSubscription, UserBalance from, UserBalance to) throws EntityRepositoryException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement fromStatement = connection.prepareStatement(SQL_UPDATE_BALANCE);
                 PreparedStatement toStatement = connection.prepareStatement(SQL_UPDATE_BALANCE);
                 PreparedStatement statement = connection.prepareStatement(SQL_INSERT_BOUGHT_SUBSCRIPTION)) {
                fromStatement.setInt(1, from.getAmount() - boughtSubscription.getPrice());
                fromStatement.setInt(2, from.getId());
                fromStatement.executeUpdate();
                toStatement.setInt(1, to.getAmount() + boughtSubscription.getPrice());
                toStatement.setInt(2, to.getUserId());
                toStatement.executeUpdate();
                statement.setInt(1, boughtSubscription.getSubscriptionId());
                statement.setInt(2, boughtSubscription.getUserId());
                statement.setInt(3, boughtSubscription.getPrice());
                statement.setDate(4, Date.valueOf(boughtSubscription.getStartDay()));
                statement.setDate(5, Date.valueOf(boughtSubscription.getEndDay()));
                statement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                logger.catching(e);
                connection.rollback();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            logger.catching(e);
            throw new EntityRepositoryException("Transaction error", e);
        }
    }
}
