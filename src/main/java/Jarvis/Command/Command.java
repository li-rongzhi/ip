package Jarvis.Command;
import Jarvis.Task.TaskList;
import Jarvis.Ui;
import Jarvis.Storage;
import Jarvis.JarvisException.*;

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
