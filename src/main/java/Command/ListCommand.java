package Command;
import Task.*;
import UI.Ui;
import Storage.Storage;

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
