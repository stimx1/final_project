package by.epam.web.command.admin;

import by.epam.web.command.ActionCommand;
import by.epam.web.content.AttributeName;
import by.epam.web.content.PageName;
import by.epam.web.content.SessionRequestContent;
import by.epam.web.entity.Exercise;
import by.epam.web.exception.CommandException;
import by.epam.web.exception.ServiceException;
import by.epam.web.resource.ConfigurationManager;
import by.epam.web.service.ExerciseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GetExercisesCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(GetExercisesCommand.class);
    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String page = ConfigurationManager.getProperty(PageName.EXERCISE);
        ExerciseService exerciseService = ExerciseService.getInstance();
        List<Exercise> exerciseList = null;
        try {
            exerciseList = exerciseService.findExercises();
        } catch (ServiceException e) {
            logger.catching(e);
            throw new CommandException("Admin panel error",e);
        }
        sessionRequestContent.setAttribute(AttributeName.EXERCISE_LIST,exerciseList);
        return page;
    }
}
