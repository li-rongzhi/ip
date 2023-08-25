import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;

public class Deadline extends Task{
    private LocalDateTime due;

    public Deadline(String content, LocalDateTime due) {
        super(content);
        this.due = due;
    }

    @Override
    public LocalDateTime[] get_time_components() {
        return new LocalDateTime[]{this.due};
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.time_printer(due) + ")";
    }
}
