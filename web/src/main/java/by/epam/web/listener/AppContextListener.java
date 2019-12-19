package by.epam.web.listener;

import by.epam.web.connection.DbConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger(AppContextListener.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("DbConnectionPool",DbConnectionPool.INSTANCE);
        logger.info("Connection pool initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DbConnectionPool.INSTANCE.destroyPool();
        logger.info("Connection pool destroyed");
    }
}
