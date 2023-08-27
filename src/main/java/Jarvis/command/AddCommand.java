package Jarvis.command;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Jarvis.jarvisexception.InvalidTimeFormatException;
import Jarvis.jarvisexception.JarvisException;
import Jarvis.jarvisexception.ContentMissingException;
import Jarvis.jarvisexception.InvalidCommandException;
import Jarvis.task.*;
import Jarvis.Ui;
import Jarvis.Storage;



/**
 * AddCommand class is a subclass of Command class.
 * It is used for adding tasks into taskList.
 *
 * @author Rongzhi
 */
public class AddCommand extends Command {
    private String input;
    private static DateTimeFormatter formatter_with_time = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public AddCommand(String input) {
        this.input = input;
    }

    /**
     * Execute the AddCommand to add task into taskList.
     * @param taskList the list of tasks currently held, where the new task is to be added.
     * @param ui ui for interaction with user.
     * @param storage backup storage of the taskList.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String keyword = input.split(" ")[0];
        String content = "";
        Task task = null;
        try {
            switch (keyword) {
                case "todo":
                    if (!Character.isWhitespace(input.charAt(4))) {
                        throw new InvalidCommandException();
                    }
                    content = input.substring(4).trim();
                    if (content.equals("")) {
                        throw new ContentMissingException("todo");
                    }
                    task = new ToDo(content);
                    taskList.addTask(task);
                    break;
                case "deadline":
                    int byIndex = input.indexOf("/by");
                    content = input.substring("deadline".length(), byIndex).trim();
                    String time = input.substring(byIndex + 3).trim();
                    if (content.equals("") || time.equals("")) {
                        throw new ContentMissingException("deadline");
                    }
                    LocalDateTime formatted_time = LocalDateTime.parse(time, formatter_with_time);
                    task = new Deadline(content, formatted_time);
                    taskList.addTask(task);
                    break;

                case "event":
                    int fromIndex = input.indexOf("/from");
                    int toIndex = input.indexOf("/to");
                    content = input.substring("event".length(), fromIndex).trim();
                    String from = input.substring(fromIndex + 5, toIndex).trim();
                    String to = input.substring(toIndex + 3).trim();
                    if (content.equals("")
                            || from.equals("") || to.equals("")) {
                        throw new ContentMissingException("event");
                    }
                    LocalDateTime formatted_from = LocalDateTime.parse(from, formatter_with_time);
                    LocalDateTime formatted_to = LocalDateTime.parse(to, formatter_with_time);
                    task = new Event(content, formatted_from, formatted_to);
                    taskList.addTask(task);
                    break;
                default:
                    throw new InvalidCommandException();
            }
            storage.update(taskList);
            ui.reportTaskAdded(taskList, task);
        } catch (IndexOutOfBoundsException e) {
            ui.reportError(new ContentMissingException(keyword));
        } catch (DateTimeParseException e) {
            ui.reportError(new InvalidTimeFormatException(formatter_with_time));
        } catch (JarvisException e) {
            ui.reportError(e);
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
