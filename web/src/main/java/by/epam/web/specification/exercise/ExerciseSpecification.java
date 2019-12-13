package by.epam.web.specification.exercise;

import by.epam.web.connection.DbConnectionPool;
import by.epam.web.specification.EntitySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExerciseSpecification implements EntitySpecification {
    private static final Logger logger = LogManager.getLogger(ExerciseSpecification.class);
    private static final String SQL_SELECT_EX = "SELECT id, name, description,state FROM exercise;";
    @Override
    public PreparedStatement specified() {
        PreparedStatement statement = null;
        try(Connection connection = DbConnectionPool.INSTANCE.getConnection()) {
            statement = connection.prepareStatement(SQL_SELECT_EX);
        } catch (SQLException e) {
            logger.catching(e);
        }
        return statement;
    }
}
