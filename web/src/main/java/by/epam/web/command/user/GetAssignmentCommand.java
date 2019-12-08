package by.epam.web.command.user;

import by.epam.web.command.ActionCommand;
import by.epam.web.content.AttributeName;
import by.epam.web.content.PageName;
import by.epam.web.content.SessionRequestContent;
import by.epam.web.entity.Diet;
import by.epam.web.entity.Exercise;
import by.epam.web.entity.User;
import by.epam.web.entity.UserRole;
import by.epam.web.exception.CommandException;
import by.epam.web.exception.ServiceException;
import by.epam.web.resource.ConfigurationManager;
import by.epam.web.service.DietService;
import by.epam.web.service.ExerciseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GetAssignmentCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(GetAssignmentCommand.class);

    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String page = ConfigurationManager.getProperty(PageName.ASSIGNMENT);
        DietService dietService = DietService.getInstance();
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
        List<Diet> assignedDietList;
        List<Exercise> assignedExerciseList;
        List<Diet> dietList;
        List<Exercise> exerciseList;
        try {
            assignedDietList = dietService.findAssignedDiets(id);
            assignedExerciseList = exerciseService.findAssignedExercisesByUserId(id);
            dietList = dietService.findDiets();
            exerciseList = exerciseService.findExercises();
        } catch (ServiceException e) {
            logger.catching(e);
            throw new CommandException("Assignment get error");
        }
        sessionRequestContent.setAttribute(AttributeName.ASSIGNED_DIET_LIST,assignedDietList);
        sessionRequestContent.setAttribute(AttributeName.ASSIGNED_EXERCISE_LIST,assignedExerciseList);
        sessionRequestContent.setAttribute(AttributeName.DIET_LIST,dietList);
        sessionRequestContent.setAttribute(AttributeName.EXERCISE_LIST,exerciseList);
        return page;
    }
}
