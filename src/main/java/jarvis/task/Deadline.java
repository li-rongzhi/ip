package jarvis.task;

import java.time.LocalDateTime;

/**
 * Deadline class is a subclass of Task class.
 * Deadline is a type of Task with a due time (due).
 *
 * @author Rongzhi
 */
public class Deadline extends Task {
    private final LocalDateTime due;

    /**
     * Constructs a Deadline task instance without state.
     *
     * @param content Task content.
     * @param due The due time.
     */
    public Deadline(String content, LocalDateTime due) {
        super(content);
        this.due = due;
    }

    /**
     * Constructs a Deadline task instance with state.
     *
     * @param content Task content.
     * @param due The due time.
     * @param state Done or not done yet.
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
