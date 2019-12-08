package by.epam.web.specification.instructor;

import by.epam.web.connection.DbConnectionPool;
import by.epam.web.specification.EntitySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectedInstructorUserIdSpecification implements EntitySpecification {
    private static final Logger logger = LogManager.getLogger(SelectedInstructorUserIdSpecification.class);
    private static final String SQL_SELECT_INSTRUCTOR = "SELECT id, instructor_id, user_id FROM selected_instructor WHERE user_id = ?;";
    private int userId;

    public SelectedInstructorUserIdSpecification(int userId){
        this.userId = userId;
    }
    @Override
    public PreparedStatement specified() {
        PreparedStatement statement = null;
        try(Connection connection = DbConnectionPool.INSTANCE.getConnection()) {
            statement = connection.prepareStatement(SQL_SELECT_INSTRUCTOR);
            statement.setInt(1,userId);
        } catch (SQLException e) {
            logger.catching(e);
        }
        return statement;
    }
}
