package by.epam.web.command.admin;

import by.epam.web.command.ActionCommand;
import by.epam.web.content.AttributeName;
import by.epam.web.content.PageName;
import by.epam.web.content.SessionRequestContent;
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
        InstructorService service = InstructorService.getInstance();
        try {
            service.deleteInstructor(id);
        } catch (ServiceException e) {
            logger.catching(e);
            throw new CommandException("Instructor delete error",e);
        }
        return page;
    }
}
