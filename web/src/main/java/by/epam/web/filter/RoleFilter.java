package by.epam.web.filter;

import by.epam.web.command.AttributeName;
import by.epam.web.command.PageName;
import by.epam.web.entity.User;
import by.epam.web.entity.UserRole;
import by.epam.web.resource.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/controller"})
public class RoleFilter implements Filter {

    private static final String DELETE_SUBSCRIPTION = "delete_subscription";
    private static final String ADD_DIET = "add_diet";
    private static final String GET_USERS = "get_users";
    private static final String DELETE_DIET = "delete_diet";
    private static final String DELETE_USER = "delete_user";
    private static final String ADD_EXERCISE = "add_exercise";
    private static final String COACHING_ROOM = "coaching_room";
    private static final String DELETE_EXERCISE = "delete_exercise";
    private static final String ADD_INSTRUCTOR = "add_instructor";
    private static final String ADD_SUBSCRIPTION = "add_subscription";
    private static final String DELETE_INSTRUCTOR = "delete_instructor";
    private static final String LOGIN = "login";
    private static final String REGISTRATION = "registration";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String command = servletRequest.getParameter(AttributeName.COMMAND);
        if (command != null) {
            HttpSession session = ((HttpServletRequest) servletRequest).getSession();
            User user = (User) session.getAttribute(AttributeName.CURRENT_USER);
            if (user == null) {
                if (!command.equals(LOGIN) && !command.equals(REGISTRATION)) {
                    RequestDispatcher requestDispatcher = servletRequest.getServletContext().getRequestDispatcher(ConfigurationManager.getProperty(PageName.INDEX));
                    requestDispatcher.forward(servletRequest, servletResponse);
                    return;
                }
            } else {
                if (user.getRole() == UserRole.USER) {
                    switch (command) {
                        case DELETE_SUBSCRIPTION:
                            ;
                        case ADD_DIET:
                            ;
                        case GET_USERS:
                            ;
                        case DELETE_USER:
                            ;
                        case DELETE_DIET:
                            ;
                        case ADD_EXERCISE:
                            ;
                        case COACHING_ROOM:
                            ;
                        case ADD_INSTRUCTOR:
                            ;
                        case DELETE_EXERCISE:
                            ;
                        case ADD_SUBSCRIPTION:
                            ;
                        case DELETE_INSTRUCTOR:
                            RequestDispatcher requestDispatcher = servletRequest.getServletContext().getRequestDispatcher(ConfigurationManager.getProperty(PageName.INDEX));
                            requestDispatcher.forward(servletRequest, servletResponse);
                            return;
                    }
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
