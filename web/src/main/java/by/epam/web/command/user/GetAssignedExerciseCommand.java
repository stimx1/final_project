package by.epam.web.command.user;

import by.epam.web.command.ActionCommand;
import by.epam.web.command.AttributeName;
import by.epam.web.command.PageName;
import by.epam.web.command.SessionRequestContent;
import by.epam.web.entity.Exercise;
import by.epam.web.entity.User;
import by.epam.web.entity.UserRole;
import by.epam.web.exception.CommandException;
import by.epam.web.exception.ServiceException;
import by.epam.web.resource.ConfigurationManager;
import by.epam.web.service.ExerciseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GetAssignedExerciseCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(GetAssignedExerciseCommand.class);
    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String page = ConfigurationManager.getProperty(PageName.ASSIGNED_EXERCISE);
        ExerciseService exerciseService = ExerciseService.getInstance();
        int id;
        User user = (User)(sessionRequestContent.getSessionAttribute(AttributeName.CURRENT_USER));
        if(user.getRole() == UserRole.ADMIN){
            String userId = sessionRequestContent.getParameter(AttributeName.USER_ID);
            if(userId==null){
                id = (Integer)(sessionRequestContent.getSessionAttribute(AttributeName.USER_ID));
            }else {
                id = Integer.parseInt(userId);
                sessionRequestContent.setSessionAttribute(AttributeName.USER_ID, id);
            }
        }else {
            id = user.getId();
        }
        List<Exercise> assignedExerciseList;
        List<Exercise> exerciseList;
        try {
            assignedExerciseList = exerciseService.findAssignedExercisesByUserId(id);
            exerciseList = exerciseService.findExercises();
        } catch (ServiceException e) {
            logger.catching(e);
            throw new CommandException("Assignment get error");
        }
        sessionRequestContent.setAttribute(AttributeName.ASSIGNED_EXERCISE_LIST,assignedExerciseList);
        sessionRequestContent.setAttribute(AttributeName.EXERCISE_LIST,exerciseList);
        return page;
    }
}
