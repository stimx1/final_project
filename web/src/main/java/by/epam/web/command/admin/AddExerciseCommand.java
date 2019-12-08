package by.epam.web.command.admin;

import by.epam.web.command.ActionCommand;
import by.epam.web.content.AttributeName;
import by.epam.web.content.PageName;
import by.epam.web.content.SessionRequestContent;
import by.epam.web.exception.CommandException;
import by.epam.web.exception.ServiceException;
import by.epam.web.resource.ConfigurationManager;
import by.epam.web.service.ExerciseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddExerciseCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AddExerciseCommand.class);
    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String page = ConfigurationManager.getProperty(PageName.ADMIN);
        ExerciseService service = ExerciseService.getInstance();
        String name = sessionRequestContent.getParameter(AttributeName.EXERCISE_NAME);
        String description = sessionRequestContent.getParameter(AttributeName.EXERCISE_DESCRIPTION);
        try {
            service.addExercise(name,description);
        } catch (ServiceException e) {
            logger.catching(e);
            throw new CommandException("Exercise add error",e);
        }
        return page;
    }
}
