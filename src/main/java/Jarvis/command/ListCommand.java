package Jarvis.command;
import Jarvis.task.*;
import Jarvis.Ui;
import Jarvis.Storage;

/**
 * ListCommand class is a subclass of Command class.
 * It is used for listing all tasks on the taskList.
 *
 * @author Rongzhi
 */
public class ListCommand extends Command {
    /**
     * Execute the ListCommand to display all tasks on the taskList.
     * @param taskList the list of tasks currently held
     * @param ui ui for interaction with user
     * @param storage backup storage of the taskList
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        ui.reportList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
