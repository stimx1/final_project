package test.epam.web.repository;

import by.epam.web.repository.ColumnName;
import by.epam.web.entity.Subscription;
import by.epam.web.exception.EntityRepositoryException;
import by.epam.web.repository.SubscriptionRepository;
import by.epam.web.resource.DBConfigurationManger;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.*;
import java.util.Enumeration;
import java.util.Random;

public class SubscriptionRepositoryTest {
    static final String SQL_SELECT_SUBSCRIPTION = "SELECT id, name, price, duration FROM subscriptions WHERE name = ?";
    static final String SQL_DELETE_SUBSCRIPTION = "DELETE FROM subscriptions WHERE id = ?";
    static final String HOST = "db.host";
    static final String LOGIN = "db.login";
    static final String PASSWORD = "db.password";

    SubscriptionRepository repository = new SubscriptionRepository();
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
        Subscription actual = new Subscription("subscription", 10,30);
        repository.addEntity(actual);
        statement = connection.prepareStatement(SQL_SELECT_SUBSCRIPTION);
        statement.setString(1,"subscription");
        resultSet = statement.executeQuery();
        Subscription expected = new Subscription();
        while (resultSet.next()) {
            expected.setId(resultSet.getInt(ColumnName.ID));
            expected.setName(resultSet.getString(ColumnName.NAME));
            expected.setPrice(resultSet.getInt(ColumnName.PRICE));
            expected.setDuration(resultSet.getInt(ColumnName.DURATION));
        }
        statement = connection.prepareStatement(SQL_DELETE_SUBSCRIPTION);
        statement.setInt(1,expected.getId());
        statement.execute();
        Assert.assertEquals(actual,expected);
    }

    @Test
    void testRemoveEntity() throws SQLException, EntityRepositoryException {
        statement = connection.prepareStatement(SQL_SELECT_SUBSCRIPTION);
        statement.setString(1,"test");
        resultSet = statement.executeQuery();
        Subscription subscription = new Subscription();
        while (resultSet.next()) {
            subscription.setId(resultSet.getInt(ColumnName.ID));
            subscription.setName(resultSet.getString(ColumnName.NAME));
            subscription.setPrice(resultSet.getInt(ColumnName.PRICE));
            subscription.setDuration(resultSet.getInt(ColumnName.DURATION));
        }
        repository.removeEntity(subscription);
        statement = connection.prepareStatement(SQL_SELECT_SUBSCRIPTION);
        statement.setString(1,"test");
        resultSet = statement.executeQuery();
        Subscription actual = new Subscription();
        while (resultSet.next()) {
            actual.setId(resultSet.getInt(ColumnName.ID));
            actual.setName(resultSet.getString(ColumnName.NAME));
            actual.setPrice(resultSet.getInt(ColumnName.PRICE));
            actual.setDuration(resultSet.getInt(ColumnName.DURATION));
        }
        repository.addEntity(subscription);
        Subscription expected = new Subscription();
        Assert.assertEquals(actual,expected);
    }

    @Test
    void testUpdateEntity() throws SQLException, EntityRepositoryException {
        statement = connection.prepareStatement(SQL_SELECT_SUBSCRIPTION);
        statement.setString(1,"test");
        resultSet = statement.executeQuery();
        Subscription actual = new Subscription();
        while (resultSet.next()) {
            actual.setId(resultSet.getInt(ColumnName.ID));
            actual.setName(resultSet.getString(ColumnName.NAME));
            actual.setPrice(resultSet.getInt(ColumnName.PRICE));
            actual.setDuration(resultSet.getInt(ColumnName.DURATION));
        }
        actual.setPrice(new Random().nextInt(1000));
        repository.updateEntity(actual);
        statement = connection.prepareStatement(SQL_SELECT_SUBSCRIPTION);
        statement.setString(1,"test");
        resultSet = statement.executeQuery();
        Subscription expected = new Subscription();
        while (resultSet.next()) {
            expected.setId(resultSet.getInt(ColumnName.ID));
            expected.setName(resultSet.getString(ColumnName.NAME));
            expected.setPrice(resultSet.getInt(ColumnName.PRICE));
            expected.setDuration(resultSet.getInt(ColumnName.DURATION));
        }
        Assert.assertEquals(actual,expected);
    }
}
