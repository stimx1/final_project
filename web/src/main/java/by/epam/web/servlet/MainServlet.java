package by.epam.web.servlet;

import by.epam.web.command.ActionCommand;
import by.epam.web.command.CommandProvider;
import by.epam.web.connection.DbConnectionPool;
import by.epam.web.content.SessionRequestContent;
import by.epam.web.exception.CommandException;
import by.epam.web.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class MainServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(MainServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (CommandException e) {
            logger.error(e);
            response.sendRedirect(ConfigurationManager.getProperty("path.page.error"));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("errorLoginPassMessage", " ");
        try {
            processRequest(request, response);
        } catch (CommandException e) {
            logger.error(e);
            response.sendRedirect(ConfigurationManager.getProperty("path.page.error"));
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        SessionRequestContent sessionRequestContent = new SessionRequestContent();
        sessionRequestContent.extractValues(request);
        String page = null;
        CommandProvider client = new CommandProvider();
        ActionCommand command = client.defineCommand(request);
        logger.info(command);
        try {
            page = command.execute(sessionRequestContent);
            sessionRequestContent.insertAttributes(request);
            String redirect = (String)sessionRequestContent.getAttribute("redirect");
            if (redirect == null) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect(redirect);
            }
        } catch (CommandException e) {
            logger.catching(e);
            throw new ServletException(e);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        DbConnectionPool.INSTANCE.destroyPool();
    }

    @Override
    public void init() throws ServletException {
        super.init();

    }
}
