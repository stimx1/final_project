package by.epam.web.repository;

import by.epam.web.connection.DbConnectionPool;
import by.epam.web.entity.AssignedDiet;
import by.epam.web.exception.EntityRepositoryException;
import by.epam.web.specification.EntitySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AssignedDietRepository implements EntityRepository<AssignedDiet> {
    private static final Logger logger = LogManager.getLogger(AssignedDietRepository.class);
    private static final String SQL_INSERT_DIET = "INSERT INTO assigned_diet (diet_id,user_id) VALUES (?,?);";
    private static final String SQL_DELETE_DIET = "DELETE FROM assigned_diet WHERE id = ? ;";

    @Override
    public void addEntity(AssignedDiet assignedDiet) throws EntityRepositoryException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_DIET)){
            statement.setInt(1,assignedDiet.getDietId());
            statement.setInt(2,assignedDiet.getUserId());
            statement.execute();
        } catch (SQLException e) {
            logger.catching(e);
            throw  new EntityRepositoryException("Assigned diet add error",e);
        }
    }

    @Override
    public void removeEntity(AssignedDiet assignedDiet) throws EntityRepositoryException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_DIET)){
            statement.setInt(1,assignedDiet.getId());
            statement.execute();
        } catch (SQLException e) {
            logger.catching(e);
            throw new EntityRepositoryException("Assigned diet remove error",e);
        }
    }

    @Override
    public void updateEntity(AssignedDiet assignedDiet){
    }

    @Override
    public List<AssignedDiet> query(EntitySpecification specification) {
        return null;
    }
}
