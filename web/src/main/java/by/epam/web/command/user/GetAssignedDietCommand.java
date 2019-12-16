package by.epam.web.command.user;

import by.epam.web.command.ActionCommand;
import by.epam.web.command.AttributeName;
import by.epam.web.command.PageName;
import by.epam.web.command.SessionRequestContent;
import by.epam.web.entity.Diet;
import by.epam.web.entity.User;
import by.epam.web.entity.UserRole;
import by.epam.web.exception.CommandException;
import by.epam.web.exception.ServiceException;
import by.epam.web.resource.ConfigurationManager;
import by.epam.web.service.DietService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GetAssignedDietCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(GetAssignedDietCommand.class);

    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String page = ConfigurationManager.getProperty(PageName.ASSIGNED_DIET);
        DietService dietService = DietService.getInstance();
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
        List<Diet> dietList;
        try {
            assignedDietList = dietService.findAssignedDiets(id);
            dietList = dietService.findDiets();
        } catch (ServiceException e) {
            logger.catching(e);
            throw new CommandException("Assignment get error");
        }
        sessionRequestContent.setAttribute(AttributeName.ASSIGNED_DIET_LIST,assignedDietList);
        sessionRequestContent.setAttribute(AttributeName.DIET_LIST,dietList);
        return page;
    }
}
