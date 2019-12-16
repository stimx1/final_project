package by.epam.web.command.admin;

import by.epam.web.command.ActionCommand;
import by.epam.web.command.AttributeName;
import by.epam.web.command.PageName;
import by.epam.web.command.RedirectName;
import by.epam.web.command.SessionRequestContent;
import by.epam.web.exception.CommandException;
import by.epam.web.exception.ServiceException;
import by.epam.web.resource.ConfigurationManager;
import by.epam.web.service.ExerciseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteExerciseCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(DeleteExerciseCommand.class);

    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String page = ConfigurationManager.getProperty(PageName.EXERCISE);
        ExerciseService service = ExerciseService.getInstance();
        int id = Integer.valueOf(sessionRequestContent.getParameter(AttributeName.EXERCISE_ID));
        String name = sessionRequestContent.getParameter(AttributeName.EXERCISE_NAME);
        String description = sessionRequestContent.getParameter(AttributeName.EXERCISE_DESCRIPTION);
        try {
            service.deleteExercise(id,name,description);
            sessionRequestContent.setAttribute(AttributeName.REDIRECT, RedirectName.EXERCISES);
        } catch (ServiceException e) {
            logger.catching(e);
            throw new CommandException("Exercise delete error",e);
        }
        return page;
    }
}
