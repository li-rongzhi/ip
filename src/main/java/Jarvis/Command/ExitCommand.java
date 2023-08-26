package Jarvis.Command;
import Jarvis.Task.*;
import Jarvis.Ui;
import Jarvis.Storage;

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
