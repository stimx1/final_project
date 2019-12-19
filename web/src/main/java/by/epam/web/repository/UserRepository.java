package by.epam.web.repository;

import by.epam.web.connection.DbConnectionPool;
import by.epam.web.entity.User;
import by.epam.web.entity.UserRole;
import by.epam.web.exception.EntityRepositoryException;
import by.epam.web.specification.EntitySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements EntityRepository<User> {
    private static final Logger logger = LogManager.getLogger(UserRepository.class);
    private static final String SQL_INSERT_USER = "INSERT INTO users (email,password,first_name,last_name) VALUES (?,?, ?,?);";
    private static final String SQL_DELETE_USER = "DELETE FROM users WHERE id = ? ;";
    private static final String SQL_UPDATE_USER = "UPDATE users SET email = ?, password = ?, first_name = ?, last_name = ? WHERE id = ?";

    @Override
    public void addEntity(User user) throws EntityRepositoryException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPass());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.execute();
        } catch (SQLException e) {
            logger.catching(e);
            throw new EntityRepositoryException("User add error", e);
        }
    }

    @Override
    public void removeEntity(User user) throws EntityRepositoryException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER)) {
            statement.setInt(1, user.getId());
            statement.execute();
        } catch (SQLException e) {
            logger.throwing(e);
            throw new EntityRepositoryException("User remove error", e);
        }
    }

    @Override
    public void updateEntity(User user) throws EntityRepositoryException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER);) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPass());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setInt(5, user.getId());
            statement.execute();
        } catch (SQLException e) {
            logger.throwing(e);
            throw new EntityRepositoryException("Incorrect query", e);
        }
    }

    @Override
    public List<User> query(EntitySpecification specification) throws EntityRepositoryException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = specification.specified();
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(ColumnName.ID));
                user.setFirstName(resultSet.getString(ColumnName.FIRST_NAME));
                user.setLastName(resultSet.getString(ColumnName.LAST_NAME));
                user.setPass(resultSet.getString(ColumnName.PASSWORD));
                user.setEmail(resultSet.getString(ColumnName.EMAIL));
                user.setRole(UserRole.valueOf(resultSet.getString(ColumnName.ROLE).toUpperCase()));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.throwing(e);
            throw new EntityRepositoryException("Incorrect query", e);
        }
        return users;
    }
}
