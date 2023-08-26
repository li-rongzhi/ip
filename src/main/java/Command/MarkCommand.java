package Command;
import Task.*;
import UI.Ui;
import Storage.Storage;
import JarvisException.*;

public class MarkCommand extends Command {
    private String input;

    public MarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        int index = Integer.parseInt(this.input.substring(5));
        try {
            Task target = taskList.markTask(index);
            target.mark();
            storage.update(taskList);
            ui.reportTaskMarked(target);
        } catch (JarvisException e) {
            ui.reportError(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
