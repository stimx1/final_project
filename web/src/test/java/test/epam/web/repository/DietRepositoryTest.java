package test.epam.web.repository;

import by.epam.web.repository.ColumnName;
import by.epam.web.entity.Diet;
import by.epam.web.exception.EntityRepositoryException;
import by.epam.web.repository.DietRepository;
import by.epam.web.resource.DBConfigurationManger;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.*;
import java.util.Enumeration;
import java.util.Random;

public class DietRepositoryTest {
    static final String SQL_SELECT_DIET = "SELECT id, name, description FROM diet WHERE name = ?";
    static final String SQL_DELETE_DIET = "DELETE FROM diet WHERE id = ?";
    static final String HOST = "db.host";
    static final String LOGIN = "db.login";
    static final String PASSWORD = "db.password";

    DietRepository repository = new DietRepository();
    Connection connection;
    PreparedStatement statement;
    ResultSet resultSet;

    @BeforeTest
    void beforeTest() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    @AfterTest
    void afterTest() throws SQLException {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            DriverManager.deregisterDriver(drivers.nextElement());
        }
    }

    @BeforeClass
    void beforeClass() throws SQLException {
        String url = DBConfigurationManger.getProperty(HOST);
        String user = DBConfigurationManger.getProperty(LOGIN);
        String pass = DBConfigurationManger.getProperty(PASSWORD);
        connection = DriverManager.getConnection(url, user, pass);
    }

    @AfterClass
    void afterClass() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    public void testAddEntity() throws EntityRepositoryException, SQLException {
        Diet actual = new Diet("diet", "info");
        repository.addEntity(actual);
        statement = connection.prepareStatement(SQL_SELECT_DIET);
        statement.setString(1, "diet");
        resultSet = statement.executeQuery();
        Diet expected = new Diet();
        while (resultSet.next()) {
            expected.setId(resultSet.getInt(ColumnName.ID));
            expected.setName(resultSet.getString(ColumnName.NAME));
            expected.setDescription(resultSet.getString(ColumnName.DESCRIPTION));
        }
        statement = connection.prepareStatement(SQL_DELETE_DIET);
        statement.setInt(1, expected.getId());
        statement.execute();
        Assert.assertEquals(actual, expected);
    }

    @Test
    void testRemoveEntity() throws SQLException, EntityRepositoryException {
        statement = connection.prepareStatement(SQL_SELECT_DIET);
        statement.setString(1, "test");
        resultSet = statement.executeQuery();
        Diet diet = new Diet();
        while (resultSet.next()) {
            diet.setId(resultSet.getInt(ColumnName.ID));
            diet.setName(resultSet.getString(ColumnName.NAME));
            diet.setDescription(resultSet.getString(ColumnName.DESCRIPTION));
        }
        repository.removeEntity(diet);
        statement = connection.prepareStatement(SQL_SELECT_DIET);
        statement.setString(1, "test");
        resultSet = statement.executeQuery();
        Diet actual = new Diet();
        while (resultSet.next()) {
            actual.setId(resultSet.getInt(ColumnName.ID));
            actual.setName(resultSet.getString(ColumnName.NAME));
            actual.setDescription(resultSet.getString(ColumnName.DESCRIPTION));
        }
        repository.addEntity(diet);
        Diet expected = new Diet();
        Assert.assertEquals(actual, expected);
    }

    @Test
    void testUpdateEntity() throws SQLException, EntityRepositoryException {
        statement = connection.prepareStatement(SQL_SELECT_DIET);
        statement.setString(1, "test");
        resultSet = statement.executeQuery();
        Diet actual = new Diet();
        while (resultSet.next()) {
            actual.setId(resultSet.getInt(ColumnName.ID));
            actual.setName(resultSet.getString(ColumnName.NAME));
            actual.setDescription(resultSet.getString(ColumnName.DESCRIPTION));
        }
        actual.setDescription("info" + new Random().nextInt(1000));
        repository.updateEntity(actual);
        statement = connection.prepareStatement(SQL_SELECT_DIET);
        statement.setString(1, "test");
        resultSet = statement.executeQuery();
        Diet expected = new Diet();
        while (resultSet.next()) {
            expected.setId(resultSet.getInt(ColumnName.ID));
            expected.setName(resultSet.getString(ColumnName.NAME));
            expected.setDescription(resultSet.getString(ColumnName.DESCRIPTION));
        }
        Assert.assertEquals(actual, expected);
    }
}
