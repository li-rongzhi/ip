package jarvis.command;

import jarvis.Parser;
import jarvis.Storage;
import jarvis.gui.Ui;
import jarvis.jarvisexception.JarvisException;
import jarvis.task.Task;
import jarvis.task.TaskList;

/**
 * UnmarkCommand class is a subclass of Command class.
 * It is used for unmarking a specific task as done.
 *
 * @author Rongzhi
 */
public class UnmarkCommand extends Command {
    private final String input;

    /**
     * Constructs an UnmarkCommand.
     *
     * @param input The user input.
     */
    public UnmarkCommand(String input) {
        assert input.startsWith(Parser.Keyword.UNMARK.getKeyword() + " ") && input.length() > 7
                && input.substring(7).matches("-?\\d+")
                : "Invalid input for UnmarkCommand";
        this.input = input;
    }

    /**
     * Executes the UnmarkCommand to unmark the task with given index.
     *
     * @param taskList the list of tasks currently held.
     * @param ui ui for interaction with user.
     * @param storage backup storage of the taskList.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        int index = Integer.parseInt(this.input.substring(7));
        try {
            Task target = taskList.unmarkTask(index);
            target.unmark();
            storage.update(taskList);
            return ui.reportTaskUnmarked(target);
        } catch (JarvisException e) {
            return ui.reportError(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
