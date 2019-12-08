package by.epam.web.command.admin;

import by.epam.web.command.ActionCommand;
import by.epam.web.content.AttributeName;
import by.epam.web.content.PageName;
import by.epam.web.content.SessionRequestContent;
import by.epam.web.entity.Diet;
import by.epam.web.exception.CommandException;
import by.epam.web.exception.ServiceException;
import by.epam.web.resource.ConfigurationManager;
import by.epam.web.service.DietService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GetDietsCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(GetDietsCommand.class);
    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String page = ConfigurationManager.getProperty(PageName.DIET);
        DietService dietService = DietService.getInstance();
        List<Diet> dietList = null;
        try {
            dietList = dietService.findDiets();
        } catch (ServiceException e) {
            logger.catching(e);
            throw new CommandException("Admin panel error",e);
        }
        sessionRequestContent.setAttribute(AttributeName.DIET_LIST,dietList);
        return page;
    }
}
