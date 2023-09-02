package jarvis.command;

import jarvis.Storage;
import jarvis.gui.Ui;
import jarvis.task.TaskList;



/**
 * ListCommand class is a subclass of Command class.
 * It is used for listing all tasks on the taskList.
 *
 * @author Rongzhi
 */
public class ListCommand extends Command {
    /**
     * Execute the ListCommand to display all tasks on the taskList.
     * @param taskList the list of tasks currently held.
     * @param ui ui for interaction with user.
     * @param storage backup storage of the taskList.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.reportList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
