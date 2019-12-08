package by.epam.web.connection;

import by.epam.web.exception.DbConnectionPoolException;
import by.epam.web.resource.DBConfigurationManger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Enumeration;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public enum DbConnectionPool {
    INSTANCE;

    private final Logger logger = LogManager.getLogger(DbConnectionPool.class);
    private BlockingQueue<Connection> freeConnections;
    private Queue<Connection> givenAwayConnections;

    private static final int DEFAULT_POOL_SIZE = 32;

    DbConnectionPool(){
        try {
            String URL = DBConfigurationManger.getProperty("db.host");
            String USER = DBConfigurationManger.getProperty("db.login");
            String PASS = DBConfigurationManger.getProperty("db.password");
            DriverManager.registerDriver(new org.postgresql.Driver());
            freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
            givenAwayConnections = new ArrayDeque<>();
            Connection connection;
            for(int i =0; i < DEFAULT_POOL_SIZE; i++){
                connection = new ProxyConnection(DriverManager.getConnection(URL,USER,PASS));
                freeConnections.offer(connection);
            }
        } catch (SQLException e) {
            logger.catching(e);
        }
    }

    public Connection getConnection(){
        Connection connection = null;
        try {
            connection = freeConnections.take();
            givenAwayConnections.offer(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) throws DbConnectionPoolException {
        if(connection.getClass() == ProxyConnection.class) {
            givenAwayConnections.remove(connection);
            freeConnections.offer(connection);
        } else {
            throw new DbConnectionPoolException("Incorrect connection type");
        }
    }

    public void destroyPool(){
        for(int i =0; i < DEFAULT_POOL_SIZE; i++){
            try {
                ((ProxyConnection)freeConnections.take()).reallyClose();
            } catch (InterruptedException e) {
                logger.catching(e);
            }
        }
        deregisterDrivers();
    }
    private void deregisterDrivers(){
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()){
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException e) {
                logger.catching(e);
            }
        }
    }

}
