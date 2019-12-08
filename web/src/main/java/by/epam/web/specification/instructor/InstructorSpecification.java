package by.epam.web.specification.instructor;

import by.epam.web.connection.DbConnectionPool;
import by.epam.web.specification.EntitySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InstructorSpecification implements EntitySpecification {
    private static final Logger logger = LogManager.getLogger(InstructorSpecification.class);
    private static final String SQL_SELECT_INSTRUCTOR = "SELECT  last_name, first_name, id, info FROM instructors;";

    @Override
    public PreparedStatement specified() {
        PreparedStatement statement = null;
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection()) {
            statement = connection.prepareStatement(SQL_SELECT_INSTRUCTOR);
        } catch (SQLException e) {
            logger.catching(e);
        }
        return statement;
    }
}
