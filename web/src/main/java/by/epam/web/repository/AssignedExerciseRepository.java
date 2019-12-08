package by.epam.web.repository;

import by.epam.web.connection.DbConnectionPool;
import by.epam.web.entity.AssignedExercise;
import by.epam.web.exception.EntityRepositoryException;
import by.epam.web.specification.EntitySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AssignedExerciseRepository implements EntityRepository<AssignedExercise> {
    private static final Logger logger = LogManager.getLogger(AssignedExerciseRepository.class);
    private static final String SQL_INSERT_EXERCISE = "INSERT INTO assigned_exercise (ex_id,user_id) VALUES (?,?);";
    private static final String SQL_DELETE_EXERCISE = "DELETE FROM assigned_exercise WHERE id = ? ;";

    @Override
    public void addEntity(AssignedExercise assignedExercise) throws EntityRepositoryException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_EXERCISE)){
            statement.setInt(1,assignedExercise.getExerciseId());
            statement.setInt(2,assignedExercise.getUserId());
            statement.execute();
        } catch (SQLException e) {
            logger.catching(e);
            throw new EntityRepositoryException("Assigned exercise remove error",e);
        }
    }

    @Override
    public void removeEntity(AssignedExercise assignedExercise) throws EntityRepositoryException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_EXERCISE)){
            statement.setInt(1,assignedExercise.getId());
            statement.execute();
        } catch (SQLException e) {
            logger.catching(e);
            throw new EntityRepositoryException("Assigned exercise remove error",e);
        }
    }

    @Override
    public void updateEntity(AssignedExercise assignedExercise) {
    }

    @Override
    public List<AssignedExercise> query(EntitySpecification specification) {
        return null;
    }
}
