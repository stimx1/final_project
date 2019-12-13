package by.epam.web.specification.instructor;

import by.epam.web.connection.DbConnectionPool;
import by.epam.web.specification.EntitySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InstructorSelectedInstructorSpecification implements EntitySpecification {
    private static final Logger logger = LogManager.getLogger(InstructorSelectedInstructorSpecification.class);
    private static final String SQL_SELECT_INSTRUCTORS = "SELECT I.first_name, I.last_name, I.info, U.id,I.state FROM instructors AS I " +
            "FULL JOIN selected_instructor AS SI ON I.id = SI.instructor_id LEFT JOIN users AS U ON U.id = SI.user_id;";
    @Override
    public PreparedStatement specified() {
        PreparedStatement statement = null;
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection()) {
            statement = connection.prepareStatement(SQL_SELECT_INSTRUCTORS);
        } catch (SQLException e) {
            logger.catching(e);
        }
        return statement;
    }
}
