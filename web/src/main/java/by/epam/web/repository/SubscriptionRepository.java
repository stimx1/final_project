package by.epam.web.repository;

import by.epam.web.connection.DbConnectionPool;
import by.epam.web.content.ColumnName;
import by.epam.web.entity.State;
import by.epam.web.entity.Subscription;
import by.epam.web.exception.EntityRepositoryException;
import by.epam.web.specification.EntitySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class SubscriptionRepository implements EntityRepository<Subscription> {
    private static final Logger logger = LogManager.getLogger(SubscriptionRepository.class);
    private static final String SQL_INSERT_SUBSCRIPTION = "INSERT INTO subscriptions (name,price,duration,state) VALUES (?,?,?,?);";
    private static final String SQL_DELETE_SUBSCRIPTION = "DELETE FROM subscriptions WHERE id = ?;";
    private static final String SQL_UPDATE_SUBSCRIPTION = "UPDATE subscriptions SET name = ?, price = ?, duration = ?, state = CAST (? AS status) WHERE id = ? ;";

    @Override
    public void addEntity(Subscription subscription) throws EntityRepositoryException {
        try(Connection connection = DbConnectionPool.INSTANCE.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_SUBSCRIPTION)){
            statement.setString(1,subscription.getName());
            statement.setInt(2,subscription.getPrice());
            statement.setInt(3,subscription.getDuration());
            statement.setString(4,subscription.getState().toString().toLowerCase());
            statement.execute();
        }catch (SQLException e){
            logger.catching(e);
            throw new EntityRepositoryException("Subscription add error",e);
        }
    }

    @Override
    public void removeEntity(Subscription subscription) throws EntityRepositoryException {
        try(Connection connection = DbConnectionPool.INSTANCE.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_SUBSCRIPTION)){
            statement.setInt(1,subscription.getId());
            statement.execute();
        }catch (SQLException e){
            logger.catching(e);
            throw new EntityRepositoryException("Subscription remove error",e);
        }
    }

    @Override
    public void updateEntity(Subscription subscription) throws EntityRepositoryException {
        try(Connection connection = DbConnectionPool.INSTANCE.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_SUBSCRIPTION)){
            statement.setString(1,subscription.getName());
            statement.setInt(2,subscription.getPrice());
            statement.setInt(3,subscription.getDuration());
            statement.setString(4, subscription.getState().toString().toLowerCase());
            statement.setInt(5,subscription.getId());
            statement.execute();
        }catch (SQLException e){
            logger.catching(e);
            throw new EntityRepositoryException("Update error",e);
        }
    }

    @Override
    public List<Subscription> query(EntitySpecification specification) throws EntityRepositoryException {
        List<Subscription> subscriptions = new LinkedList<>();
        try(PreparedStatement statement = specification.specified();
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Subscription subscription = new Subscription();
                subscription.setId(resultSet.getInt(ColumnName.ID));
                subscription.setName(resultSet.getString(ColumnName.NAME));
                subscription.setPrice(resultSet.getInt(ColumnName.PRICE));
                subscription.setDuration(resultSet.getInt(ColumnName.DURATION));
                subscription.setState(State.valueOf(resultSet.getString(ColumnName.STATE).toUpperCase()));
                Date startDay = resultSet.getDate(ColumnName.START_DAY);
                Date endDay = resultSet.getDate(ColumnName.END_DAY);
                if(startDay != null) {
                    subscription.setStartDay(startDay.toLocalDate());
                }
                if(endDay !=null){
                    subscription.setEndDay(endDay.toLocalDate());
                }
                subscriptions.add(subscription);
            }
            logger.info(subscriptions);
        } catch (SQLException e) {
            logger.catching(e);
            throw new EntityRepositoryException("Incorrect query error",e);
        }
        return subscriptions;
    }
}
