package Jarvis.Task;

import java.time.LocalDateTime;
import java.time.Month;

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

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    private String get_status_icon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public  String toString() {
        return "[" + get_status_icon() + "] " + this.content;
    }

    protected String time_printer(LocalDateTime time) {
        Month month = time.getMonth();
        int day = time.getDayOfMonth();
        int year = time.getYear();
        int hour = time.getHour();
        int minute = time.getMinute();
        return month.toString() + " " + day + " " + year + " " + hour + ":" + minute;
    }

    public String toRecord() {
        return "[" + get_status_icon() + "] " + this.content;
    }

    public LocalDateTime[] get_time_components() {
        return new LocalDateTime[0];
    }
}
