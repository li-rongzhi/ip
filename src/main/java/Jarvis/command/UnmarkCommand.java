package Jarvis.command;
import Jarvis.task.*;
import Jarvis.Ui;
import Jarvis.Storage;
import Jarvis.jarvisexception.*;

/**
 * UnmarkCommand class is a subclass of Command class.
 * It is used for unmarking a specific task as done.
 *
 * @author Rongzhi
 */
public class UnmarkCommand extends Command {
    private String input;

    public UnmarkCommand(String input) {
        this.input = input;
    }

    /**
     * Execute the UnmarkCommand to unmark the task with given index.
     * @param taskList the list of tasks currently held.
     * @param ui ui for interaction with user.
     * @param storage backup storage of the taskList.
     */
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
