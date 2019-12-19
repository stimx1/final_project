package by.epam.web.command.admin;

import by.epam.web.command.ActionCommand;
import by.epam.web.command.AttributeName;
import by.epam.web.command.PageName;
import by.epam.web.command.SessionRequestContent;
import by.epam.web.entity.Instructor;
import by.epam.web.exception.CommandException;
import by.epam.web.exception.ServiceException;
import by.epam.web.resource.ConfigurationManager;
import by.epam.web.service.InstructorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CoachingRoomCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(CoachingRoomCommand.class);

    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String page = ConfigurationManager.getProperty(PageName.COACHING_ROOM);
        InstructorService instructorService = InstructorService.getInstance();
        try {
            List<Instructor> instructors = instructorService.findSelectedInstructor();
            sessionRequestContent.setAttribute(AttributeName.INSTRUCTORS, instructors);
        } catch (ServiceException e) {
            logger.catching(e);
            throw new CommandException("Find error", e);
        }
        return page;
    }
}
