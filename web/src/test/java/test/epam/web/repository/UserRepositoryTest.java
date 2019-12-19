package test.epam.web.repository;

import by.epam.web.repository.ColumnName;
import by.epam.web.entity.User;
import by.epam.web.entity.UserRole;
import by.epam.web.exception.EntityRepositoryException;
import by.epam.web.repository.UserRepository;
import by.epam.web.resource.DBConfigurationManger;
import by.epam.web.specification.user.UserEmailSpecification;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;

public class UserRepositoryTest {
    static final String SQL_SELECT_USER = "SELECT id,email,password,last_name,first_name,role FROM users WHERE email = ?";
    static final String SQL_DELETE_USER = "DELETE FROM users WHERE id = ?";
    static final String HOST = "db.host";
    static final String LOGIN = "db.login";
    static final String PASSWORD = "db.password";

    UserRepository userRepository = new UserRepository();
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
        User actual = new User("1234", "gmail@gmail.com", "Last name", "First name", UserRole.USER);
        userRepository.addEntity(actual);
        statement = connection.prepareStatement(SQL_SELECT_USER);
        statement.setString(1, "gmail@gmail.com");
        resultSet = statement.executeQuery();
        User expected = new User();
        while (resultSet.next()) {
            expected.setFirstName(resultSet.getString(ColumnName.FIRST_NAME));
            expected.setLastName(resultSet.getString(ColumnName.LAST_NAME));
            expected.setId(resultSet.getInt(ColumnName.ID));
            expected.setPass(resultSet.getString(ColumnName.PASSWORD));
            expected.setEmail(resultSet.getString(ColumnName.EMAIL));
            expected.setRole(UserRole.valueOf(resultSet.getString(ColumnName.ROLE).toUpperCase()));
        }
        statement = connection.prepareStatement(SQL_DELETE_USER);
        statement.setInt(1, expected.getId());
        statement.execute();
        Assert.assertEquals(actual, expected);
    }

    @Test
    void testRemoveEntity() throws SQLException, EntityRepositoryException {
        statement = connection.prepareStatement(SQL_SELECT_USER);
        statement.setString(1, "mail@gmail.com");
        resultSet = statement.executeQuery();
        User user = new User();
        while (resultSet.next()) {
            user.setFirstName(resultSet.getString(ColumnName.FIRST_NAME));
            user.setLastName(resultSet.getString(ColumnName.LAST_NAME));
            user.setId(resultSet.getInt(ColumnName.ID));
            user.setPass(resultSet.getString(ColumnName.PASSWORD));
            user.setEmail(resultSet.getString(ColumnName.EMAIL));
            user.setRole(UserRole.valueOf(resultSet.getString(ColumnName.ROLE).toUpperCase()));
        }
        userRepository.removeEntity(user);
        statement = connection.prepareStatement(SQL_SELECT_USER);
        statement.setString(1, "mail@gmail.com");
        resultSet = statement.executeQuery();
        User actual = new User();
        while (resultSet.next()) {
            actual.setFirstName(resultSet.getString(ColumnName.FIRST_NAME));
            actual.setLastName(resultSet.getString(ColumnName.LAST_NAME));
            actual.setId(resultSet.getInt(ColumnName.ID));
            actual.setPass(resultSet.getString(ColumnName.PASSWORD));
            actual.setEmail(resultSet.getString(ColumnName.EMAIL));
            actual.setRole(UserRole.valueOf(resultSet.getString(ColumnName.ROLE).toUpperCase()));
        }
        userRepository.addEntity(user);
        User expected = new User();
        Assert.assertEquals(actual, expected);
    }

    @Test
    void testQuery() throws EntityRepositoryException, SQLException {
        List<User> actual = userRepository.query(new UserEmailSpecification("mail@gmail.com"));
        List<User> expected = new ArrayList<>();
        statement = connection.prepareStatement(SQL_SELECT_USER);
        statement.setString(1, "mail@gmail.com");
        resultSet = statement.executeQuery();
        User user = new User();
        while (resultSet.next()) {
            user.setFirstName(resultSet.getString(ColumnName.FIRST_NAME));
            user.setLastName(resultSet.getString(ColumnName.LAST_NAME));
            user.setId(resultSet.getInt(ColumnName.ID));
            user.setPass(resultSet.getString(ColumnName.PASSWORD));
            user.setEmail(resultSet.getString(ColumnName.EMAIL));
            user.setRole(UserRole.valueOf(resultSet.getString(ColumnName.ROLE).toUpperCase()));
            expected.add(user);
        }
        Assert.assertEquals(actual, expected);
    }

    @Test
    void testUpdateEntity() throws SQLException, EntityRepositoryException {
        statement = connection.prepareStatement(SQL_SELECT_USER);
        statement.setString(1, "mail@gmail.com");
        resultSet = statement.executeQuery();
        User actual = new User();
        while (resultSet.next()) {
            actual.setFirstName(resultSet.getString(ColumnName.FIRST_NAME));
            actual.setLastName(resultSet.getString(ColumnName.LAST_NAME));
            actual.setId(resultSet.getInt(ColumnName.ID));
            actual.setPass(resultSet.getString(ColumnName.PASSWORD));
            actual.setEmail(resultSet.getString(ColumnName.EMAIL));
            actual.setRole(UserRole.valueOf(resultSet.getString(ColumnName.ROLE).toUpperCase()));
        }
        actual.setLastName("last" + new Random().nextInt(10000));
        actual.setFirstName("first" + new Random().nextInt(10000));
        userRepository.updateEntity(actual);
        statement = connection.prepareStatement(SQL_SELECT_USER);
        statement.setString(1, "mail@gmail.com");
        resultSet = statement.executeQuery();
        User expected = new User();
        while (resultSet.next()) {
            expected.setFirstName(resultSet.getString(ColumnName.FIRST_NAME));
            expected.setLastName(resultSet.getString(ColumnName.LAST_NAME));
            expected.setId(resultSet.getInt(ColumnName.ID));
            expected.setPass(resultSet.getString(ColumnName.PASSWORD));
            expected.setEmail(resultSet.getString(ColumnName.EMAIL));
            expected.setRole(UserRole.valueOf(resultSet.getString(ColumnName.ROLE).toUpperCase()));
        }
        Assert.assertEquals(actual, expected);
    }
}
