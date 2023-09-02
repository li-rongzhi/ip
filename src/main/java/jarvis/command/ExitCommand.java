package jarvis.command;

import jarvis.Storage;
import jarvis.gui.Ui;
import jarvis.task.TaskList;



/**
 * ExitCommand class is a subclass of Command class.
 * It is used for exiting the program.
 *
 * @author Rongzhi
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return "";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
