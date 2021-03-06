package by.epam.web.repository;

import by.epam.web.connection.DbConnectionPool;
import by.epam.web.entity.Exercise;
import by.epam.web.entity.State;
import by.epam.web.exception.EntityRepositoryException;
import by.epam.web.specification.EntitySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExerciseRepository implements EntityRepository<Exercise> {
    private static final Logger logger = LogManager.getLogger(ExerciseRepository.class);
    private static final String SQL_INSERT_EXERCISE = "INSERT INTO exercise (name, description,state) VALUES (?,?,CAST (? AS status));";
    private static final String SQL_DELETE_EXERCISE = "DELETE FROM exercise WHERE id = ? ;";
    private static final String SQL_UPDATE_EXERCISE = "UPDATE exercise SET name = ?, description = ?, state = CAST (? AS status) WHERE id = ? ;";

    @Override
    public void addEntity(Exercise exercise) throws EntityRepositoryException {

        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_EXERCISE)) {
            statement.setString(1, exercise.getName());
            statement.setString(2, exercise.getDescription());
            statement.setString(3, exercise.getState().toString().toLowerCase());
            statement.execute();
        } catch (SQLException e) {
            logger.catching(e);
            throw new EntityRepositoryException("Exercise add error", e);
        }
    }

    @Override
    public void removeEntity(Exercise exercise) throws EntityRepositoryException {
        try (
                Connection connection = DbConnectionPool.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_DELETE_EXERCISE)) {
            statement.setInt(1, exercise.getId());
            statement.execute();
        } catch (SQLException e) {
            logger.catching(e);
            throw new EntityRepositoryException("Exercise remove error", e);
        }
    }

    @Override
    public void updateEntity(Exercise exercise) throws EntityRepositoryException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_EXERCISE)) {
            statement.setString(1, exercise.getName());
            statement.setString(2, exercise.getDescription());
            statement.setString(3, exercise.getState().toString().toLowerCase());
            statement.setInt(4, exercise.getId());
            statement.execute();
        } catch (SQLException e) {
            logger.catching(e);
            throw new EntityRepositoryException("Update error", e);
        }
    }

    @Override
    public List<Exercise> query(EntitySpecification specification) throws EntityRepositoryException {
        List<Exercise> exercises = new ArrayList<>();
        try (PreparedStatement statement = specification.specified();
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getInt(ColumnName.ID));
                exercise.setName(resultSet.getString(ColumnName.NAME));
                exercise.setDescription(resultSet.getString(ColumnName.DESCRIPTION));
                exercise.setState(State.valueOf(resultSet.getString(ColumnName.STATE).toUpperCase()));
                exercises.add(exercise);
            }
        } catch (SQLException e) {
            logger.catching(e);
            throw new EntityRepositoryException("Exercise query incorrect", e);
        }
        return exercises;
    }
}
