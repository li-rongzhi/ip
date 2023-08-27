package Jarvis.command;
import Jarvis.task.TaskList;
import Jarvis.Ui;
import Jarvis.Storage;
import Jarvis.jarvisexception.*;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage)
            throws InvalidCommandException, ContentMissingException, InvalidTimeFormatException;
    public abstract boolean isExit();
}
