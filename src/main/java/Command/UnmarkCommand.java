package Command;
import Task.*;
import UI.Ui;
import Storage.Storage;
import JarvisException.*;

public class UnmarkCommand extends Command {
    private String input;

    public UnmarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        int index = Integer.parseInt(this.input.substring(7));
        try {
            Task target = taskList.unmarkTask(index);
            target.unmark();
            storage.update(taskList);
            ui.reportTaskUnmarked(target);
        } catch (JarvisException e) {
            ui.reportError(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
