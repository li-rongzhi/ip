package Command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import Task.*;
import UI.Ui;
import Storage.Storage;
import JarvisException.*;

public class CheckCommand extends Command {
    private String input;
    private static DateTimeFormatter formatter_without_time = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public CheckCommand(String input) {
        this.input = input;
    }

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
