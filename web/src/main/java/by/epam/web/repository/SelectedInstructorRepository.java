package by.epam.web.repository;

import by.epam.web.connection.DbConnectionPool;
import by.epam.web.content.ColumnName;
import by.epam.web.entity.SelectedInstructor;
import by.epam.web.exception.EntityRepositoryException;
import by.epam.web.specification.EntitySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SelectedInstructorRepository implements EntityRepository<SelectedInstructor> {
    private static final Logger logger = LogManager.getLogger(SelectedInstructorRepository.class);
    private static final String SQL_INSERT_INSTRUCTOR = "INSERT INTO selected_instructor (instructor_id,user_id) VALUES (?,?)";
    private static final String SQL_DELETE_INSTRUCTOR = "DELETE FROM selected_instructor WHERE id = ? ;";
    private static final String SQL_UPDATE_INSTRUCTOR = "UPDATE selected_instructor SET instructor_id = ?, user_id = ? WHERE id = ? ;";
    @Override
    public void addEntity(SelectedInstructor selectedInstructor) throws EntityRepositoryException {
        try(Connection connection = DbConnectionPool.INSTANCE.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_INSTRUCTOR)) {
            statement.setInt(1,selectedInstructor.getInstructorId());
            statement.setInt(2,selectedInstructor.getUserId());
            statement.execute();
        } catch (SQLException e) {
            logger.catching(e);
            throw new EntityRepositoryException("Selected instructor add error",e);
        }
    }

    @Override
    public void removeEntity(SelectedInstructor selectedInstructor) throws EntityRepositoryException {
        try(Connection connection = DbConnectionPool.INSTANCE.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_INSTRUCTOR)){
            statement.setInt(1,selectedInstructor.getId());
            statement.execute();
        } catch (SQLException e) {
            logger.catching(e);
            throw new EntityRepositoryException("Selected instructor remove error",e);
        }
    }

    @Override
    public void updateEntity(SelectedInstructor selectedInstructor) throws EntityRepositoryException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_INSTRUCTOR)){
            statement.setInt(1,selectedInstructor.getInstructorId());
            statement.setInt(2,selectedInstructor.getUserId());
            statement.setInt(3,selectedInstructor.getId());
            statement.execute();
        } catch (SQLException e) {
            logger.catching(e);
            throw new EntityRepositoryException("Selected instructor update error",e);
        }
    }

    @Override
    public List<SelectedInstructor> query(EntitySpecification specification) throws EntityRepositoryException {
        List<SelectedInstructor> instructors = new LinkedList<>();
        try(PreparedStatement statement = specification.specified();
        ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                SelectedInstructor instructor = new SelectedInstructor();
                instructor.setId(resultSet.getInt(ColumnName.ID));
                instructor.setInstructorId(resultSet.getInt(ColumnName.INSTRUCTOR_ID));
                instructor.setUserId(resultSet.getInt(ColumnName.USER_ID));
                instructors.add(instructor);
            }
        } catch (SQLException e) {
            logger.catching(e);
            throw new EntityRepositoryException("Incorrect query error",e);
        }
        return instructors;
    }
}
