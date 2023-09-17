package jarvis.task;

import java.time.LocalDateTime;
import java.time.Month;

/**
 * Task is a parent class for all types of task defined for Jarvis.
 *
 * @author Rongzhi
 */
public class Task {
    private boolean isDone;
    private final String content;
    /**
     * Constructs a new Task with the specified content.
     *
     * @param content The content or description of the task.
     */
    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    /**
     * Constructs a new Task with the specified content.
     *
     * @param content The content or description of the task.
     * @param state Done or not done yet.
     */
    public Task(String content, Boolean state) {
        this.content = content;
        this.isDone = state;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as not done yet.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Gets the status icon of the task.
     * "X" stands for done while " " stands for not done yet.
     *
     * @return The status icon in String.
     */
    private String get_status_icon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + get_status_icon() + "] " + this.content;
    }

    /**
     * Transforms the given time into a specific format.
     *
     * @param time The time to be printed.
     * @return Time in a specific format in String.
     */
    protected String time_printer(LocalDateTime time) {
        Month month = time.getMonth();
        int day = time.getDayOfMonth();
        int year = time.getYear();
        int hour = time.getHour();
        int minute = time.getMinute();
        return month.toString() + " " + day + " " + year + " " + hour + ":" + minute;
    }

    /**
     * Gets the format for record storage.
     *
     * @return Task in format for record storage.
     */
    public String toRecord() {
        return "[" + get_status_icon() + "] " + this.content;
    }

    /**
     * Gets time components contained in the task.
     *
     * @return An array of time components.
     */
    public LocalDateTime[] get_time_components() {
        return new LocalDateTime[0];
    }

    public boolean checkContent(String input) {
        return this.content.contains(input);
    }
}
