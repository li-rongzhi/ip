package jarvis.command;

import jarvis.Parser;
import jarvis.Storage;
import jarvis.gui.Ui;
import jarvis.jarvisexception.JarvisException;
import jarvis.task.Task;
import jarvis.task.TaskList;




/**
 * MarkCommand class is a subclass of Command class.
 * It is used for marking a specific task as done.
 *
 * @author Rongzhi
 */
public class MarkCommand extends Command {
    private final String input;

    /**
     * Constructs a MarkCommand.
     *
     * @param input The user input.
     */
    public MarkCommand(String input) {
        assert input.startsWith(Parser.Keyword.MARK.getKeyword() + " ") && input.length() > 5
                && input.substring(5).matches("-?\\d+")
                : "Invalid input for MarkCommand";
        this.input = input;
    }


    /**
     * Execute the MarkCommand to mark the task with given index.
     *
     * @param taskList The list of tasks currently held.
     * @param ui The ui for interaction with user.
     * @param storage Backup storage of the taskList.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        int index = Integer.parseInt(this.input.substring(5));
        try {
            Task target = taskList.markTask(index);
            target.mark();
            storage.update(taskList);
            return ui.reportTaskMarked(target);
        } catch (JarvisException e) {
            return ui.reportError(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
