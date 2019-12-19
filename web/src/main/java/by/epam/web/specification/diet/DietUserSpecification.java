package by.epam.web.specification.diet;

import by.epam.web.connection.DbConnectionPool;
import by.epam.web.specification.EntitySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DietUserSpecification implements EntitySpecification {
    private static final Logger logger = LogManager.getLogger(DietUserSpecification.class);
    private static final String SQL_SELECT_DIET = "SELECT assigned_diet.id ,diet.name, diet.description, diet.state FROM users " +
            "RIGHT JOIN assigned_diet ON users.id=assigned_diet.user_id " +
            "LEFT JOIN diet ON diet.id=assigned_diet.diet_id WHERE users.id = ?;";

    private int id;

    public DietUserSpecification(int id) {
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
