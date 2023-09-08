package jarvis.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jarvis.Parser;
import jarvis.Storage;
import jarvis.gui.Ui;
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

    /**
     * Create a CheckCommand.
     * @param input The user input.
     */
    public CheckCommand(String input) {
        assert input.startsWith(Parser.Keyword.CHECK.getKeyword())
                : "Invalid input for CheckCommand";
        this.input = input;
    }

    /**
     * Execute the AddCommand to add task into taskList.
     * @param taskList the list of tasks where the search or check operation will be conducted.
     * @param ui ui for interaction with user.
     * @param storage backup storage of the taskList.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            String time = this.input.substring(6);
            LocalDate formattedTime = LocalDate.parse(time, formatterWithoutTime);
            String output = taskList.checkTask(formattedTime);
            return ui.reportCheckDate(output, formattedTime);
        } catch (DateTimeParseException e) {
            return ui.reportError(new InvalidTimeFormatException(formatterWithoutTime));
        } catch (StringIndexOutOfBoundsException e) {
            return ui.reportError(new ContentMissingException("check"));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
