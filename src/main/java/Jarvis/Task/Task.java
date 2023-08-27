package Jarvis.Task;

import java.time.LocalDateTime;
import java.time.Month;

/**
 * Task is a parent class for all types of task defined for Jarvis.
 *
 * @author Rongzhi
 */
public class Task {
    private boolean isDone;
    private String content;



    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    public Task(String content, Boolean state) {
        this.content = content;
        this.isDone = state;
    }

    /**
     * Mark the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmark the task as not done yet.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Get the status icon of the task.
     * "X" stands for done while " " stands for not done yet.
     * @return the status icon in String.
     */
    private String get_status_icon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public  String toString() {
        return "[" + get_status_icon() + "] " + this.content;
    }

    /**
     * Transform the given time into a specific format.
     * @param time the time to be printed.
     * @return time in a specific format in String.
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
     * Get the format for record storage.
     * @return task in format for record storage.
     */
    public String toRecord() {
        return "[" + get_status_icon() + "] " + this.content;
    }

    /**
     * Get time components contained in the task.
     * @return an array of time components.
     */
    public LocalDateTime[] get_time_components() {
        return new LocalDateTime[0];
    }

    public boolean checkContent(String input) {
        return  this.content.contains(input);
    }
}
