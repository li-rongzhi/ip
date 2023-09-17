package jarvis.command;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jarvis.Parser;
import jarvis.Storage;
import jarvis.gui.Ui;
import jarvis.jarvisexception.ContentMissingException;
import jarvis.jarvisexception.InvalidCommandException;
import jarvis.jarvisexception.InvalidTimeFormatException;
import jarvis.jarvisexception.InvalidTimePeriodException;
import jarvis.jarvisexception.JarvisException;
import jarvis.jarvisexception.TimeClashException;
import jarvis.task.Deadline;
import jarvis.task.Event;
import jarvis.task.Task;
import jarvis.task.TaskList;
import jarvis.task.ToDo;



/**
 * AddCommand class is a subclass of Command class.
 * It is used for adding tasks into taskList.
 *
 * @author Rongzhi
 */
public class AddCommand extends Command {
    private static final DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private final String input;

    /**
     * Create an AddCommand.
     * @param input The user input.
     */
    public AddCommand(String input) {
        assert input.startsWith(Parser.Keyword.TODO.getKeyword())
                || input.startsWith(Parser.Keyword.DEADLINE.getKeyword())
                || input.startsWith(Parser.Keyword.EVENT.getKeyword())
                : "Invalid input for AddCommand";
        this.input = input;
    }
    /**
     * Execute the AddCommand to add task into taskList.
     * @param taskList the list of tasks currently held, where the new task is to be added.
     * @param ui ui for interaction with user.
     * @param storage backup storage of the taskList.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String keyword = input.split(" ")[0];
        String content;
        Task task;
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
                LocalDateTime formattedTime = LocalDateTime.parse(time, formatterWithTime);
                task = new Deadline(content, formattedTime);
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
                LocalDateTime formattedFrom = LocalDateTime.parse(from, formatterWithTime);
                LocalDateTime formattedTo = LocalDateTime.parse(to, formatterWithTime);
                if (!taskList.detectAnomalies(new LocalDateTime[]{formattedFrom, formattedTo}).equals("")) {
                    throw new TimeClashException();
                } else if (formattedFrom.isAfter(formattedTo)) {
                    throw new InvalidTimePeriodException();
                }
                task = new Event(content, formattedFrom, formattedTo);
                taskList.addTask(task);
                break;
            default:
                throw new InvalidCommandException();
            }
            storage.update(taskList);
            return ui.reportTaskAdded(taskList, task);
        } catch (IndexOutOfBoundsException e) {
            return ui.reportError(new ContentMissingException(keyword));
        } catch (DateTimeParseException e) {
            return ui.reportError(new InvalidTimeFormatException(formatterWithTime));
        } catch (JarvisException e) {
            return ui.reportError(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
