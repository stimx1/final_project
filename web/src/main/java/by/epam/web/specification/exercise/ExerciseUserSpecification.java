package by.epam.web.specification.exercise;

import by.epam.web.connection.DbConnectionPool;
import by.epam.web.specification.EntitySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExerciseUserSpecification implements EntitySpecification {
    private static final Logger logger = LogManager.getLogger(ExerciseUserSpecification.class);
    private static final String SQL_SELECT_DIET = "SELECT assigned_exercise.id ,exercise.name, exercise.description,exercise.state FROM users " +
            "RIGHT JOIN assigned_exercise ON users.id=assigned_exercise.user_id " +
            "LEFT JOIN exercise ON exercise.id=assigned_exercise.ex_id WHERE users.id = ?;";
    private int id;

    public ExerciseUserSpecification(int id) {
        this.id = id;
    }

    @Override
    public PreparedStatement specified() {
        PreparedStatement statement = null;
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection()) {
            statement = connection.prepareStatement(SQL_SELECT_DIET);
            statement.setInt(1, id);
        } catch (SQLException e) {
            logger.catching(e);
        }
        return statement;
    }
}
