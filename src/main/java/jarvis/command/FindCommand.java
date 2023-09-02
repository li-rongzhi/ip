package jarvis.command;

import jarvis.Storage;
import jarvis.gui.Ui;
import jarvis.jarvisexception.ContentMissingException;
import jarvis.task.TaskList;


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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            String target = this.input.substring(6).trim();
            String output = taskList.findTask(target);
            return ui.reportFindContent(output, target);
        } catch (StringIndexOutOfBoundsException e) {
            return ui.reportError(new ContentMissingException("find"));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
