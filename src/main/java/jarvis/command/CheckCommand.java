package jarvis.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jarvis.Storage;
import jarvis.Ui;
import jarvis.jarvisexception.ContentMissingException;
import jarvis.jarvisexception.InvalidTimeFormatException;
import jarvis.task.TaskList;

/**
 * CheckCommand class is a subclass of Command class.
 * It is used for checking tasks in the list on a specific date
 *
 * @author Rongzhi
 */
public class CheckCommand extends Command {
    private static DateTimeFormatter formatterWithoutTime = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String input;
    public CheckCommand(String input) {
        this.input = input;
    }

    /**
     * Execute the AddCommand to add task into taskList.
     * @param taskList the list of tasks where the search or check operation will be conducted.
     * @param ui ui for interaction with user.
     * @param storage backup storage of the taskList.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            String time = this.input.substring(6);
            LocalDate formattedTime = LocalDate.parse(time, formatterWithoutTime);
            String output = taskList.checkTask(formattedTime);
            ui.reportCheckDate(output, formattedTime);
        } catch (DateTimeParseException e) {
            ui.reportError(new InvalidTimeFormatException(formatterWithoutTime));
        } catch (StringIndexOutOfBoundsException e) {
            ui.reportError(new ContentMissingException("check"));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
