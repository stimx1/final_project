package test.epam.web.repository;

import by.epam.web.repository.ColumnName;
import by.epam.web.entity.Instructor;
import by.epam.web.exception.EntityRepositoryException;
import by.epam.web.repository.InstructorRepository;
import by.epam.web.resource.DBConfigurationManger;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.*;
import java.util.Enumeration;
import java.util.Random;

public class InstructorRepositoryTest {
    static final String SQL_SELECT_INSTRUCTOR = "SELECT last_name, first_name, id, info,state FROM instructors WHERE last_name = ?";
    static final String SQL_DELETE_INSTRUCTOR = "DELETE FROM instructors WHERE id = ?";
    static final String HOST = "db.host";
    static final String LOGIN = "db.login";
    static final String PASSWORD = "db.password";

    InstructorRepository repository = new InstructorRepository();
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
        Instructor actual = new Instructor("first", "last","info");
        repository.addEntity(actual);
        statement = connection.prepareStatement(SQL_SELECT_INSTRUCTOR);
        statement.setString(1,"last");
        resultSet = statement.executeQuery();
        Instructor expected = new Instructor();
        while (resultSet.next()) {
            expected.setId(resultSet.getInt(ColumnName.ID));
            expected.setFirstName(resultSet.getString(ColumnName.FIRST_NAME));
            expected.setLastName(resultSet.getString(ColumnName.LAST_NAME));
            expected.setInfo(resultSet.getString(ColumnName.INFO));
        }
        statement = connection.prepareStatement(SQL_DELETE_INSTRUCTOR);
        statement.setInt(1,expected.getId());
        statement.execute();
        Assert.assertEquals(actual,expected);
    }

    @Test
    void testRemoveEntity() throws SQLException, EntityRepositoryException {
        statement = connection.prepareStatement(SQL_SELECT_INSTRUCTOR);
        statement.setString(1,"test");
        resultSet = statement.executeQuery();
        Instructor instructor = new Instructor();
        while (resultSet.next()) {
            instructor.setId(resultSet.getInt(ColumnName.ID));
            instructor.setFirstName(resultSet.getString(ColumnName.FIRST_NAME));
            instructor.setLastName(resultSet.getString(ColumnName.LAST_NAME));
            instructor.setInfo(resultSet.getString(ColumnName.INFO));
        }
        repository.removeEntity(instructor);
        statement = connection.prepareStatement(SQL_SELECT_INSTRUCTOR);
        statement.setString(1,"test");
        resultSet = statement.executeQuery();
        Instructor actual = new Instructor();
        while (resultSet.next()) {
            actual.setId(resultSet.getInt(ColumnName.ID));
            actual.setFirstName(resultSet.getString(ColumnName.FIRST_NAME));
            actual.setLastName(resultSet.getString(ColumnName.LAST_NAME));
            actual.setInfo(resultSet.getString(ColumnName.INFO));
        }
        repository.addEntity(instructor);
        Instructor expected = new Instructor();
        Assert.assertEquals(actual,expected);
    }

    @Test
    void testUpdateEntity() throws SQLException, EntityRepositoryException {
        statement = connection.prepareStatement(SQL_SELECT_INSTRUCTOR);
        statement.setString(1,"test");
        resultSet = statement.executeQuery();
        Instructor actual = new Instructor();
        while (resultSet.next()) {
            actual.setId(resultSet.getInt(ColumnName.ID));
            actual.setFirstName(resultSet.getString(ColumnName.FIRST_NAME));
            actual.setLastName(resultSet.getString(ColumnName.LAST_NAME));
            actual.setInfo(resultSet.getString(ColumnName.INFO));
        }
        actual.setInfo("info" + new Random().nextInt(1000));
        repository.updateEntity(actual);
        statement = connection.prepareStatement(SQL_SELECT_INSTRUCTOR);
        statement.setString(1,"test");
        resultSet = statement.executeQuery();
        Instructor expected = new Instructor();
        while (resultSet.next()) {
            expected.setId(resultSet.getInt(ColumnName.ID));
            expected.setFirstName(resultSet.getString(ColumnName.FIRST_NAME));
            expected.setLastName(resultSet.getString(ColumnName.LAST_NAME));
            expected.setInfo(resultSet.getString(ColumnName.INFO));
        }
        Assert.assertEquals(actual,expected);
    }
}
