package by.epam.web.specification.instructor;

import by.epam.web.connection.DbConnectionPool;
import by.epam.web.specification.EntitySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InstructorUserSpecification implements EntitySpecification {
    private static final Logger logger = LogManager.getLogger(InstructorUserSpecification.class);
    private static final String SQL_SELECT_DIET = "SELECT instructors.id, instructors.info,instructors.last_name, instructors.first_name,instructors.state FROM users " +
            "RIGHT JOIN selected_instructor ON users.id=selected_instructor.user_id " +
            "LEFT JOIN instructors ON instructors.id=selected_instructor.instructor_id WHERE users.id = ?;";

    private int userId;

    public InstructorUserSpecification(int userId) {
        this.userId = userId;
    }

    @Override
    public PreparedStatement specified() {
        PreparedStatement statement = null;
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection()) {
            statement = connection.prepareStatement(SQL_SELECT_DIET);
            statement.setInt(1, userId);
        } catch (SQLException e) {
            logger.catching(e);
        }
        return statement;
    }
}
