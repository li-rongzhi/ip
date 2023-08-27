package Jarvis.command;
import Jarvis.task.*;
import Jarvis.Ui;
import Jarvis.Storage;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        ui.reportList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
