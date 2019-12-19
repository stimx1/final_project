package by.epam.web.command.admin;

import by.epam.web.command.*;
import by.epam.web.exception.CommandException;
import by.epam.web.exception.ServiceException;
import by.epam.web.resource.ConfigurationManager;
import by.epam.web.service.InstructorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteInstructorCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(DeleteInstructorCommand.class);

    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String page = ConfigurationManager.getProperty(PageName.INSTRUCTORS);
        int id = Integer.parseInt(sessionRequestContent.getParameter(AttributeName.INSTRUCTOR_ID));
        String firstName = sessionRequestContent.getParameter(AttributeName.FIRST_NAME);
        String lastName = sessionRequestContent.getParameter(AttributeName.LAST_NAME);
        String info = sessionRequestContent.getParameter(AttributeName.INFO);
        InstructorService service = InstructorService.getInstance();
        try {
            service.deleteInstructor(id, firstName, lastName, info);
            sessionRequestContent.setAttribute(AttributeName.REDIRECT, RedirectName.INSTRUCTORS);
        } catch (ServiceException e) {
            logger.catching(e);
            throw new CommandException("Instructor delete error", e);
        }
        return page;
    }
}
