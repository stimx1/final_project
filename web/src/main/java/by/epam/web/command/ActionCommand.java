package by.epam.web.command;

import by.epam.web.content.SessionRequestContent;
import by.epam.web.exception.CommandException;

public interface ActionCommand {
    String execute(SessionRequestContent sessionRequestContent) throws CommandException;
}
