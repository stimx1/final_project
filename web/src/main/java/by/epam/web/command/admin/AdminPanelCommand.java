package by.epam.web.command.admin;

import by.epam.web.command.ActionCommand;
import by.epam.web.content.AttributeName;
import by.epam.web.content.PageName;
import by.epam.web.content.SessionRequestContent;
import by.epam.web.entity.Diet;
import by.epam.web.entity.Exercise;
import by.epam.web.entity.User;
import by.epam.web.exception.CommandException;
import by.epam.web.exception.ServiceException;
import by.epam.web.resource.ConfigurationManager;
import by.epam.web.service.DietService;
import by.epam.web.service.ExerciseService;
import by.epam.web.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AdminPanelCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AdminPanelCommand.class);

    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String page = ConfigurationManager.getProperty(PageName.ADMIN);
//        DietService dietService = DietService.getInstance();
//        ExerciseService exerciseService = ExerciseService.getInstance();
//        UserService userService = UserService.getINSTANCE();
//        List<Diet> dietList = null;
//        List<Exercise> exerciseList = null;
//        List<User> userList = null;
//        try {
//            dietList = dietService.findDiets();
//            exerciseList = exerciseService.findExercises();
//            userList = userService.findUsers();
//        } catch (ServiceException e) {
//            logger.catching(e);
//            throw new CommandException("Admin panel error",e);
//        }
//
//        sessionRequestContent.setAttribute(AttributeName.DIET_LIST,dietList);
//        sessionRequestContent.setAttribute(AttributeName.EXERCISE_LIST,exerciseList);
//        sessionRequestContent.setAttribute(AttributeName.USER_LIST,userList);
        return page;
    }
}