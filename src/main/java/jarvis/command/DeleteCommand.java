package jarvis.command;

import jarvis.Storage;
import jarvis.gui.Ui;
import jarvis.jarvisexception.JarvisException;
import jarvis.task.Task;
import jarvis.task.TaskList;


/**
 * DeleteCommand class is a subclass of Command class.
 * It is used for deleting task with a specific index in the given taskList.
 *
 * @author Rongzhi
 */
public class DeleteCommand extends Command {
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Execute the ExecuteCommand to delete the target task.
     * @param taskList the list of tasks currently held.
     * @param ui ui for interaction with user.
     * @param storage backup storage of the taskList.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        int index = Integer.parseInt(this.input.substring(7));
        try {
            Task target = taskList.deleteTask(index - 1);
            storage.update(taskList);
            return ui.reportTaskDeleted(target);
        } catch (JarvisException e) {
            return ui.reportError(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
