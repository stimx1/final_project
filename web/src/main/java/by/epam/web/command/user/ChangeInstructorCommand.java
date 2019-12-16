package by.epam.web.command.user;

import by.epam.web.command.ActionCommand;
import by.epam.web.command.AttributeName;
import by.epam.web.command.PageName;
import by.epam.web.command.RedirectName;
import by.epam.web.command.SessionRequestContent;
import by.epam.web.entity.User;
import by.epam.web.exception.CommandException;
import by.epam.web.exception.ServiceException;
import by.epam.web.resource.ConfigurationManager;
import by.epam.web.service.SelectedInstructorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeInstructorCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(ChangeInstructorCommand.class);


    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String page = ConfigurationManager.getProperty(PageName.INSTRUCTORS);
        User user = (User)(sessionRequestContent.getSessionAttribute(AttributeName.CURRENT_USER));
        int userId = user.getId();
        int instructorId = Integer.parseInt(sessionRequestContent.getParameter(AttributeName.INSTRUCTOR_ID));
        SelectedInstructorService selectedInstructorService = SelectedInstructorService.getInstance();
        try {
            selectedInstructorService.changeInstructor(userId,instructorId);
            sessionRequestContent.setAttribute(AttributeName.REDIRECT, RedirectName.INSTRUCTORS);
        } catch (ServiceException e) {
            logger.catching(e);
            throw new CommandException("Instructor change error",e);
        }
        return page;
    }
}
