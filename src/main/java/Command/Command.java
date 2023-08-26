package Command;
import Task.TaskList;
import UI.Ui;
import Storage.Storage;
import JarvisException.*;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage)
            throws InvalidCommandException, ContentMissingException, InvalidTimeFormatException;
    public abstract boolean isExit();
}
