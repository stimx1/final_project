package by.epam.web.command.admin;

import by.epam.web.command.*;
import by.epam.web.exception.CommandException;
import by.epam.web.exception.ServiceException;
import by.epam.web.resource.ConfigurationManager;
import by.epam.web.service.InstructorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddInstructorCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AddInstructorCommand.class);

    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String page = ConfigurationManager.getProperty(PageName.INSTRUCTORS);
        String firstName = sessionRequestContent.getParameter(AttributeName.FIRST_NAME);
        String lastName = sessionRequestContent.getParameter(AttributeName.LAST_NAME);
        String info = sessionRequestContent.getParameter(AttributeName.INFO);
        logger.info(firstName);
        InstructorService service = InstructorService.getInstance();
        try {
            service.addInstructor(firstName, lastName, info);
            sessionRequestContent.setAttribute(AttributeName.REDIRECT, RedirectName.INSTRUCTORS);
        } catch (ServiceException e) {
            logger.catching(e);
            throw new CommandException("Instructor add error", e);
        }
        return page;
    }
}
