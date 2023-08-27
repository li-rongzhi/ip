package Jarvis.Command;
import Jarvis.Task.*;
import Jarvis.Ui;
import Jarvis.Storage;

/**
 * ExitCommand class is a subclass of Command class
 * It is used for exiting the program.
 *
 * @author Rongzhi
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        return;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
