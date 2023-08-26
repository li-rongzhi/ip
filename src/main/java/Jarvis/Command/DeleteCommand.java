package Jarvis.Command;
import Jarvis.Task.*;
import Jarvis.Ui;
import Jarvis.Storage;
import Jarvis.JarvisException.*;

public class DeleteCommand extends Command {
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        int index = Integer.parseInt(this.input.substring(7));
        try {
            Task target = taskList.deleteTask(index-1);
            storage.update(taskList);
            ui.reportTaskDeleted(target);
        } catch (JarvisException e) {
            ui.reportError(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
