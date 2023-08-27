package Jarvis.command;
import Jarvis.task.*;
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
