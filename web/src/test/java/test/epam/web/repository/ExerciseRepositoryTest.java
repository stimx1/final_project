package test.epam.web.repository;

import by.epam.web.repository.ColumnName;
import by.epam.web.entity.Exercise;
import by.epam.web.exception.EntityRepositoryException;
import by.epam.web.repository.ExerciseRepository;
import by.epam.web.resource.DBConfigurationManger;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.*;
import java.util.Enumeration;
import java.util.Random;

public class ExerciseRepositoryTest {
    static final String SQL_SELECT_EXERCISE = "SELECT id, name, description FROM exercise WHERE name = ?";
    static final String SQL_DELETE_EXERCISE = "DELETE FROM exercise WHERE id = ?";
    static final String HOST = "db.host";
    static final String LOGIN = "db.login";
    static final String PASSWORD = "db.password";

    ExerciseRepository repository = new ExerciseRepository();
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
        while (drivers.hasMoreElements()){
            DriverManager.deregisterDriver(drivers.nextElement());
        }
    }

    @BeforeClass
    void beforeClass() throws SQLException {
        String url = DBConfigurationManger.getProperty(HOST);
        String user = DBConfigurationManger.getProperty(LOGIN);
        String pass = DBConfigurationManger.getProperty(PASSWORD);
        connection = DriverManager.getConnection(url,user,pass);
    }

    @AfterClass
    void afterClass() throws SQLException {
        if(resultSet !=null){
            resultSet.close();
        }
        if(statement != null){
            statement.close();
        }
        if(connection != null){
            connection.close();
        }
    }

    @Test
    public void testAddEntity() throws EntityRepositoryException, SQLException {
        Exercise actual = new Exercise("ex","info");
        repository.addEntity(actual);
        statement = connection.prepareStatement(SQL_SELECT_EXERCISE);
        statement.setString(1,"ex");
        resultSet = statement.executeQuery();
        Exercise expected = new Exercise();
        while (resultSet.next()) {
            expected.setId(resultSet.getInt(ColumnName.ID));
            expected.setName(resultSet.getString(ColumnName.NAME));
            expected.setDescription(resultSet.getString(ColumnName.DESCRIPTION));
        }
        statement = connection.prepareStatement(SQL_DELETE_EXERCISE);
        statement.setInt(1,expected.getId());
        statement.execute();
        Assert.assertEquals(actual,expected);
    }

    @Test
    void testRemoveEntity() throws SQLException, EntityRepositoryException {
        statement = connection.prepareStatement(SQL_SELECT_EXERCISE);
        statement.setString(1,"test");
        resultSet = statement.executeQuery();
        Exercise exercise = new Exercise();
        while (resultSet.next()) {
            exercise.setId(resultSet.getInt(ColumnName.ID));
            exercise.setName(resultSet.getString(ColumnName.NAME));
            exercise.setDescription(resultSet.getString(ColumnName.DESCRIPTION));
        }
        repository.removeEntity(exercise);
        statement = connection.prepareStatement(SQL_SELECT_EXERCISE);
        statement.setString(1,"test");
        resultSet = statement.executeQuery();
        Exercise actual = new Exercise();
        while (resultSet.next()) {
            actual.setId(resultSet.getInt(ColumnName.ID));
            actual.setName(resultSet.getString(ColumnName.NAME));
            actual.setDescription(resultSet.getString(ColumnName.DESCRIPTION));
        }
        repository.addEntity(exercise);
        Exercise expected = new Exercise();
        Assert.assertEquals(actual,expected);
    }

    @Test
    void testUpdateEntity() throws SQLException, EntityRepositoryException {
        statement = connection.prepareStatement(SQL_SELECT_EXERCISE);
        statement.setString(1,"test");
        resultSet = statement.executeQuery();
        Exercise actual = new Exercise();
        while (resultSet.next()) {
            actual.setId(resultSet.getInt(ColumnName.ID));
            actual.setName(resultSet.getString(ColumnName.NAME));
            actual.setDescription(resultSet.getString(ColumnName.DESCRIPTION));
        }
        actual.setDescription("info" + new Random().nextInt(1000));
        repository.updateEntity(actual);
        statement = connection.prepareStatement(SQL_SELECT_EXERCISE);
        statement.setString(1,"test");
        resultSet = statement.executeQuery();
        Exercise expected = new Exercise();
        while (resultSet.next()) {
            expected.setId(resultSet.getInt(ColumnName.ID));
            expected.setName(resultSet.getString(ColumnName.NAME));
            expected.setDescription(resultSet.getString(ColumnName.DESCRIPTION));
        }
        Assert.assertEquals(actual,expected);
    }
}
