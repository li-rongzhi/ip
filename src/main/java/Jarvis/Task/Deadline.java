package Jarvis.Task;

import java.time.LocalDateTime;

/**
 * Deadline class is a subclass of Task class.
 * Deadline is a type of Task with a due time (due).
 *
 * @author Rongzhi
 */
public class Deadline extends Task {
    private LocalDateTime due;

    public Deadline(String content, LocalDateTime due) {
        super(content);
        this.due = due;
    }

    public Deadline(String content, LocalDateTime due, Boolean state) {
        super(content, state);
        this.due = due;
    }

    @Override
    public LocalDateTime[] get_time_components() {
        return new LocalDateTime[]{this.due};
    }

    @Override
    public String toRecord() {
        return "[D]" + super.toRecord() + " (by: " + this.due + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.time_printer(due) + ")";
    }
}
