package by.epam.web.command.user;

import by.epam.web.command.ActionCommand;
import by.epam.web.content.AttributeName;
import by.epam.web.content.PageName;
import by.epam.web.content.SessionRequestContent;
import by.epam.web.entity.Instructor;
import by.epam.web.entity.User;
import by.epam.web.exception.CommandException;
import by.epam.web.exception.ServiceException;
import by.epam.web.resource.ConfigurationManager;
import by.epam.web.service.InstructorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.List;

public class GetInstructorsCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(GetInstructorsCommand.class);
    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String page = ConfigurationManager.getProperty(PageName.INSTRUCTORS);
        User user = (User)sessionRequestContent.getSessionAttribute(AttributeName.CURRENT_USER);
        int userId = user.getId();
        InstructorService service = InstructorService.getInstance();
        List<Instructor> instructors = null;
        List<Instructor> selectedInstructor = null;
        try {
            instructors = service.findInstructors();
            selectedInstructor = service.findSelectedInstructorByUserId(userId);
        } catch (ServiceException e) {
            logger.catching(e);
            throw new CommandException("Instructors get error", e);
        }
        sessionRequestContent.setAttribute(AttributeName.INSTRUCTORS, instructors);
        Iterator<Instructor> iterator = selectedInstructor.iterator();
        if(iterator.hasNext()) {
            int selectedId = iterator.next().getId();
            logger.info(selectedId);
            sessionRequestContent.setAttribute("selectedId", selectedId);
        }
        return page;
    }
}
