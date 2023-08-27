package Jarvis.Command;

import Jarvis.JarvisException.ContentMissingException;
import Jarvis.Storage;
import Jarvis.Task.Task;
import Jarvis.Task.TaskList;
import Jarvis.Ui;

/**
 * FindCommand class is a subclass of Command class.
 * It is used for finding related tasks with a given content.
 *
 * @author Rongzhi
 */
public class FindCommand extends Command {
    private String input;

    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            String target = this.input.substring(6).trim();
            String output = taskList.findTask(target);
            ui.reportFindContent(output, target);
        } catch (StringIndexOutOfBoundsException e) {
            ui.reportError(new ContentMissingException("find"));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
