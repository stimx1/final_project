package by.epam.web.repository;

import by.epam.web.connection.DbConnectionPool;
import by.epam.web.entity.Diet;
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

public class DietRepository implements EntityRepository<Diet> {
    private static final Logger logger = LogManager.getLogger(DietRepository.class);
    private static final String SQL_INSERT_DIET = "INSERT INTO diet (name, description,state) VALUES (?, ?,CAST (? AS status));";
    private static final String SQL_DELETE_DIET = "DELETE FROM diet WHERE id = ? ;";
    private static final String SQL_UPDATE_DIET = "UPDATE diet SET name = ?, description = ?, state = CAST (? AS status) WHERE id = ? ;";

    @Override
    public void addEntity(Diet diet) throws EntityRepositoryException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_DIET)) {
            statement.setString(1, diet.getName());
            statement.setString(2, diet.getDescription());
            statement.setString(3,diet.getState().toString().toLowerCase());
            statement.execute();
        } catch (SQLException e) {
            logger.catching(e);
            throw new EntityRepositoryException("Diet add error", e);
        }
    }

    @Override
    public void removeEntity(Diet diet) throws EntityRepositoryException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_DIET)) {
            statement.setInt(1, diet.getId());
            statement.execute();
        } catch (SQLException e) {
            logger.catching(e);
            throw new EntityRepositoryException("Diet remove error", e);
        }
    }

    @Override
    public void updateEntity(Diet diet) throws EntityRepositoryException {
        try(Connection connection = DbConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_DIET)){
            statement.setString(1,diet.getName());
            statement.setString(2,diet.getDescription());
            statement.setString(3, diet.getState().toString().toLowerCase());
            statement.setInt(4,diet.getId());
            statement.execute();
        }catch (SQLException e){
            logger.catching(e);
            throw new EntityRepositoryException("Update error",e);
        }
    }

    @Override
    public List<Diet> query(EntitySpecification specification) throws EntityRepositoryException {
        List<Diet> dietList = new ArrayList<>();
        try (PreparedStatement statement = specification.specified();
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Diet diet = new Diet();
                diet.setId(resultSet.getInt(ColumnName.ID));
                diet.setName(resultSet.getString(ColumnName.NAME));
                diet.setDescription(resultSet.getString(ColumnName.DESCRIPTION));
                diet.setState(State.valueOf(resultSet.getString(ColumnName.STATE).toUpperCase()));
                dietList.add(diet);
            }
        } catch (SQLException e) {
            logger.catching(e);
            throw new EntityRepositoryException("Diet query incorrect", e);
        }
        return dietList;
    }
}
