package jarvis.task;

import java.time.LocalDateTime;

/**
 * Deadline class is a subclass of Task class.
 * Deadline is a type of Task with a due time (due).
 *
 * @author Rongzhi
 */
public class Deadline extends Task {
    private LocalDateTime due;

    /**
     * Create a Deadline task instance without state.
     * @param content task content.
     * @param due the due time.
     */
    public Deadline(String content, LocalDateTime due) {
        super(content);
        this.due = due;
    }

    /**
     * Create a Deadline task instance with state.
     * @param content task content.
     * @param due the due time.
     * @param state done or not done yet
     */
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
