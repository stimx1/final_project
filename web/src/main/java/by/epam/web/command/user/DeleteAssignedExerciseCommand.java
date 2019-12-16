package by.epam.web.command.user;

import by.epam.web.command.ActionCommand;
import by.epam.web.command.AttributeName;
import by.epam.web.command.PageName;
import by.epam.web.command.RedirectName;
import by.epam.web.command.SessionRequestContent;
import by.epam.web.exception.CommandException;
import by.epam.web.exception.ServiceException;
import by.epam.web.resource.ConfigurationManager;
import by.epam.web.service.AssignedExerciseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteAssignedExerciseCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(DeleteAssignedExerciseCommand.class);
    private AssignedExerciseService assignedExerciseService = AssignedExerciseService.getInstance();

    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String page = ConfigurationManager.getProperty(PageName.ASSIGNED_EXERCISE);
        int id = Integer.valueOf(sessionRequestContent.getParameter(AttributeName.EXERCISE_ID));
        try {
            assignedExerciseService.deleteExercise(id);
            sessionRequestContent.setAttribute(AttributeName.REDIRECT, RedirectName.ASSIGNED_EXERCISE);
        } catch (ServiceException e) {
            logger.catching(e);
            throw new CommandException("Assigned exercise error",e);
        }
        return page;
    }
}
