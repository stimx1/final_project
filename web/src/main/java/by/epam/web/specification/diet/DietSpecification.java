package by.epam.web.specification.diet;

import by.epam.web.connection.DbConnectionPool;
import by.epam.web.specification.EntitySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DietSpecification implements EntitySpecification {
    private static final Logger logger = LogManager.getLogger(DietSpecification.class);
    private static final String SQL_SELECT_DIET = "SELECT id, name, description FROM diet;";
    @Override
    public PreparedStatement specified() {
        PreparedStatement statement = null;
        try(Connection connection = DbConnectionPool.INSTANCE.getConnection()) {
            statement = connection.prepareStatement(SQL_SELECT_DIET);
        } catch (SQLException e) {
            logger.catching(e);
        }
        return statement;
    }
}
