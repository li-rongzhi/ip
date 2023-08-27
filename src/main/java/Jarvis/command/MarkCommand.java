package Jarvis.command;
import Jarvis.task.*;
import Jarvis.Ui;
import Jarvis.Storage;
import Jarvis.jarvisexception.*;

/**
 * MarkCommand class is a subclass of Command class.
 * It is used for marking a specific task as done.
 *
 * @author Rongzhi
 */
public class MarkCommand extends Command {
    private String input;

    public MarkCommand(String input) {
        this.input = input;
    }


    /**
     * Execute the MarkCommand to mark the task with given index.
     * @param taskList the list of tasks currently held
     * @param ui ui for interaction with user
     * @param storage backup storage of the taskList
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        int index = Integer.parseInt(this.input.substring(5));
        try {
            Task target = taskList.markTask(index);
            target.mark();
            storage.update(taskList);
            ui.reportTaskMarked(target);
        } catch (JarvisException e) {
            ui.reportError(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
