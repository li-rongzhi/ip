package Command;
import Task.*;
import UI.Ui;
import Storage.Storage;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        return;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
