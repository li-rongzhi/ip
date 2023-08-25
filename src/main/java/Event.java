import java.time.LocalDateTime;
import java.time.Month;

public class Event extends Task{
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String content, LocalDateTime from, LocalDateTime to) {
        super(content);
        this.from = from;
        this.to = to;
    }

    @Override
    public LocalDateTime[] get_time_components() {
        return new LocalDateTime[]{this.from, this.to};
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                time_printer(this.from) + " to: " + time_printer(this.to) + ")";
    }
}
