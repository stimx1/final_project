package by.epam.web.command.user;

import by.epam.web.command.ActionCommand;
import by.epam.web.command.AttributeName;
import by.epam.web.command.PageName;
import by.epam.web.command.RedirectName;
import by.epam.web.command.SessionRequestContent;
import by.epam.web.entity.User;
import by.epam.web.entity.UserRole;
import by.epam.web.exception.CommandException;
import by.epam.web.exception.ServiceException;
import by.epam.web.resource.ConfigurationManager;
import by.epam.web.service.AssignedExerciseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AssignExerciseCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AssignExerciseCommand.class);
    private AssignedExerciseService assignedDietService = AssignedExerciseService.getInstance();

    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String page = ConfigurationManager.getProperty(PageName.ASSIGNED_EXERCISE);
        int userId;
        int exerciseId = Integer.parseInt(sessionRequestContent.getParameter(AttributeName.EXERCISE_ID));
        User user = (User)(sessionRequestContent.getSessionAttribute(AttributeName.CURRENT_USER));
        if(user.getRole() == UserRole.ADMIN){
            userId = (Integer) (sessionRequestContent.getSessionAttribute(AttributeName.USER_ID));
        }else {
            userId = user.getId();
        }
        try {
            assignedDietService.assignExercise(userId,exerciseId);
            sessionRequestContent.setAttribute(AttributeName.REDIRECT, RedirectName.ASSIGNED_EXERCISE);
        } catch (ServiceException e) {
            logger.catching(e);
            throw new CommandException("Exercise assign error",e);
        }
        logger.info(page);
        return page;
    }
}
