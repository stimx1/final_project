package by.epam.web.command.user;

import by.epam.web.command.ActionCommand;
import by.epam.web.content.AttributeName;
import by.epam.web.content.PageName;
import by.epam.web.content.RedirectName;
import by.epam.web.content.SessionRequestContent;
import by.epam.web.exception.CommandException;
import by.epam.web.exception.ServiceException;
import by.epam.web.resource.ConfigurationManager;
import by.epam.web.service.AssignedDietService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteAssignedDietCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(DeleteAssignedDietCommand.class);
    private AssignedDietService assignedDietService = AssignedDietService.getInstance();

    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String page = ConfigurationManager.getProperty(PageName.ASSIGNED_DIET);
        int id = Integer.valueOf(sessionRequestContent.getParameter(AttributeName.DIET_ID));
        try {
            assignedDietService.deleteDiet(id);
            sessionRequestContent.setAttribute(AttributeName.REDIRECT, RedirectName.ASSIGNED_DIET);
        } catch (ServiceException e) {
            logger.catching(e);
            throw new CommandException("Assigned diet delete error",e);
        }
        return page;
    }
}
