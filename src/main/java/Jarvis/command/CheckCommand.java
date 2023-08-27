package Jarvis.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import Jarvis.task.*;
import Jarvis.Ui;
import Jarvis.Storage;
import Jarvis.jarvisexception.*;

/**
 * CheckCommand class is a subclass of Command class.
 * It is used for checking tasks in the list on a specific date
 *
 * @author Rongzhi
 */
public class CheckCommand extends Command {
    private String input;
    private static DateTimeFormatter formatter_without_time = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
            LocalDate formatted_time = LocalDate.parse(time, formatter_without_time);
            String output = taskList.checkTask(formatted_time);
            ui.reportCheckDate(output, formatted_time);
        } catch (DateTimeParseException e) {
            ui.reportError(new InvalidTimeFormatException(formatter_without_time));
        } catch (StringIndexOutOfBoundsException e) {
            ui.reportError(new ContentMissingException("check"));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
