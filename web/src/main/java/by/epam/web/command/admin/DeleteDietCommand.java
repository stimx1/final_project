package by.epam.web.command.admin;

import by.epam.web.command.*;
import by.epam.web.exception.CommandException;
import by.epam.web.exception.ServiceException;
import by.epam.web.resource.ConfigurationManager;
import by.epam.web.service.DietService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteDietCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(DeleteDietCommand.class);

    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        int id = Integer.valueOf(sessionRequestContent.getParameter(AttributeName.DIET_ID));
        String name = sessionRequestContent.getParameter(AttributeName.DIET_NAME);
        String description = sessionRequestContent.getParameter(AttributeName.DIET_DESCRIPTION);
        DietService service = DietService.getInstance();
        try {
            service.deleteDiet(id, name, description);
            sessionRequestContent.setAttribute(AttributeName.REDIRECT, RedirectName.DIETS);
        } catch (ServiceException e) {
            logger.catching(e);
            throw new CommandException("Diet delete error", e);
        }

        String page = ConfigurationManager.getProperty(PageName.DIET);
        return page;
    }
}
