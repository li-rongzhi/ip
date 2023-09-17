package jarvis.command;

import jarvis.Parser;
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
    private final String input;

    /**
     * Constructs a DeleteCommand.
     *
     * @param input The user input.
     */
    public DeleteCommand(String input) {
        assert input.startsWith(Parser.Keyword.DELETE.getKeyword() + " ") && input.length() > 7
                && input.substring(7).matches("-?\\d+")
                : "Invalid input for DeleteCommand";
        this.input = input;
    }

    /**
     * Executes the ExecuteCommand to delete the target task.
     *
     * @param taskList The list of tasks currently held.
     * @param ui The ui for interaction with user.
     * @param storage Backup storage of the taskList.
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
