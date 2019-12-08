package by.epam.web.command;

import by.epam.web.resource.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CommandProvider {
    private static final Logger logger = LogManager.getLogger(CommandProvider.class);
    public ActionCommand defineCommand(HttpServletRequest request){
        ActionCommand current = new EmptyCommand();
        String action = request.getParameter("command");
        logger.info(action);
        if(action == null || action.isEmpty()){
            throw new IllegalArgumentException();
    }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e){
            request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}
