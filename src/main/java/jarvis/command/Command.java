package jarvis.command;

import jarvis.Storage;
import jarvis.Ui;
import jarvis.jarvisexception.ContentMissingException;
import jarvis.jarvisexception.InvalidCommandException;
import jarvis.jarvisexception.InvalidTimeFormatException;
import jarvis.task.TaskList;


/**
 * Command class is an abstract class for all commands defined for Jarvis.
 *
 * @author Rongzhi
 */
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage)
            throws InvalidCommandException, ContentMissingException, InvalidTimeFormatException;

    public abstract boolean isExit();
}
