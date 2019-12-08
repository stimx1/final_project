package by.epam.web.specification.user;

import by.epam.web.connection.DbConnectionPool;
import by.epam.web.specification.EntitySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserSpecification implements EntitySpecification {
    private static final Logger logger = LogManager.getLogger(UserSpecification.class);
    private static final String SQL_SELECT_USER = "SELECT id,email, password, last_name, first_name, role FROM users;";

    @Override
    public PreparedStatement specified() {
        PreparedStatement statement = null;
        try(Connection connection = DbConnectionPool.INSTANCE.getConnection()) {
            statement = connection.prepareStatement(SQL_SELECT_USER);
        } catch (SQLException e) {
            logger.catching(e);
        }
        return statement;
    }
}
