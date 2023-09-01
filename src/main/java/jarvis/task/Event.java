package jarvis.task;

import java.time.LocalDateTime;

/**
 * Event class is a subclass of Task class.
 * Event is a type of Task with a time period (from and to).
 *
 * @author Rongzhi
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Create an Event task instance.
     * @param content the content of the Event.
     * @param from start time of the event.
     * @param to end time of the event.
     */
    public Event(String content, LocalDateTime from, LocalDateTime to) {
        super(content);
        this.from = from;
        this.to = to;
    }

    /**
     * Create an Event task instance with state.
     * @param content the content of the Event.
     * @param from start time of the event.
     * @param to end time of the event.
     * @param state done or not done yet.
     */
    public Event(String content, LocalDateTime from, LocalDateTime to, Boolean state) {
        super(content, state);
        this.from = from;
        this.to = to;
    }

    @Override
    public LocalDateTime[] get_time_components() {
        return new LocalDateTime[]{this.from, this.to};
    }

    @Override
    public String toRecord() {
        return "[E]" + super.toRecord() + " (from: "
               + this.from + " to: " + this.to + ")";
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
              + time_printer(this.from) + " to: " + time_printer(this.to) + ")";
    }
}
