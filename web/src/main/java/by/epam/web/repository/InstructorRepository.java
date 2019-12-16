package by.epam.web.repository;

import by.epam.web.connection.DbConnectionPool;
import by.epam.web.entity.Instructor;
import by.epam.web.entity.State;
import by.epam.web.exception.EntityRepositoryException;
import by.epam.web.specification.EntitySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class InstructorRepository implements EntityRepository<Instructor> {
    private static final Logger logger = LogManager.getLogger(InstructorRepository.class);
    private static final String SQL_INSERT_INSTRUCTOR = "INSERT INTO instructors (first_name,last_name,info,state) VALUES (?,?,?,CAST (? AS status));";
    private static final String SQL_DELETE_INSTRUCTOR = "DELETE FROM instructors WHERE id = ? ;";
    private static final String SQL_UPDATE_INSTRUCTOR = "UPDATE instructors SET first_name = ?, last_name = ?, info = ?, state = CAST (? AS status) WHERE id = ? ;";

    @Override
    public void addEntity(Instructor instructor) throws EntityRepositoryException {
        try(Connection connection = DbConnectionPool.INSTANCE.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_INSTRUCTOR)){
            statement.setString(1,instructor.getFirstName());
            statement.setString(2,instructor.getLastName());
            statement.setString(3,instructor.getInfo());
            statement.setString(4,instructor.getState().toString().toLowerCase());
            statement.execute();
        } catch (SQLException e) {
            logger.catching(e);
            throw new EntityRepositoryException("Instructor add error",e);
        }
    }

    @Override
    public void removeEntity(Instructor instructor) throws EntityRepositoryException {
        try(Connection connection = DbConnectionPool.INSTANCE.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_INSTRUCTOR)) {
            statement.setInt(1,instructor.getId());
            statement.execute();
        } catch (SQLException e) {
            logger.catching(e);
            throw new EntityRepositoryException("Instructor remove error",e);
        }
    }

    @Override
    public void updateEntity(Instructor instructor) throws EntityRepositoryException {
        try(Connection connection = DbConnectionPool.INSTANCE.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_INSTRUCTOR)){
            statement.setString(1,instructor.getFirstName());
            statement.setString(2,instructor.getLastName());
            statement.setString(3,instructor.getInfo());
            statement.setString(4, instructor.getState().toString().toLowerCase());
            statement.setInt(5,instructor.getId());
            statement.execute();
        }catch (SQLException e){
            logger.catching(e);
            throw new EntityRepositoryException("Update error",e);
        }
    }

    @Override
    public List<Instructor> query(EntitySpecification specification) throws EntityRepositoryException {
        List<Instructor> instructors = new LinkedList<>();
        try (PreparedStatement statement = specification.specified();
             ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()) {
                Instructor instructor = new Instructor();
                instructor.setId(resultSet.getInt(ColumnName.ID));
                instructor.setFirstName(resultSet.getString(ColumnName.FIRST_NAME));
                instructor.setLastName(resultSet.getString(ColumnName.LAST_NAME));
                instructor.setInfo(resultSet.getString(ColumnName.INFO));
                instructor.setState(State.valueOf(resultSet.getString(ColumnName.STATE).toUpperCase()));
                instructors.add(instructor);
            }
        } catch (SQLException e) {
            logger.catching(e);
            throw new EntityRepositoryException("Instructor query error",e);
        }
        return instructors;
    }
}
