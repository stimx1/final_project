package by.epam.web.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CommandProvider {
    private static final Logger logger = LogManager.getLogger(CommandProvider.class);

    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
        String action = request.getParameter(AttributeName.COMMAND);
        logger.info(action);
        try {
            if (action == null || action.isEmpty()) {
                throw new IllegalArgumentException();
            }
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            logger.catching(e);
            return current;
        }
        return current;
    }
}
