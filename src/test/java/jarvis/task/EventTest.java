package jarvis.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toRecordTest() {
        assertEquals("[E][ ] party (from: 2023-08-27T19:00 to: 2023-08-27T22:00)",
                new Event("party",
                        LocalDateTime.of(2023, 8, 27, 19, 00),
                        LocalDateTime.of(2023, 8, 27, 22, 00)).toRecord());
        Event temp = new Event("party",
                LocalDateTime.of(2023, 8, 27, 19, 00),
                LocalDateTime.of(2023, 8, 27, 22, 00),
                true);
        assertEquals("[E][X] party (from: 2023-08-27T19:00 to: 2023-08-27T22:00)", temp.toRecord());
        temp.unmark();
        assertEquals("[E][ ] party (from: 2023-08-27T19:00 to: 2023-08-27T22:00)",temp.toRecord());
        temp.mark();
        assertEquals("[E][X] party (from: 2023-08-27T19:00 to: 2023-08-27T22:00)", temp.toRecord());
    }

    @Test
    public void toStringTest() {
        assertEquals("[E][ ] party (from: AUGUST 27 2023 19:0 to: AUGUST 27 2023 22:0)",
                new Event("party",
                        LocalDateTime.of(2023, 8, 27, 19, 00),
                        LocalDateTime.of(2023, 8, 27, 22, 00)).toString());
        Event temp = new Event("party",
                LocalDateTime.of(2023, 8, 27, 19, 00),
                LocalDateTime.of(2023, 8, 27, 22, 00),
                true);
        assertEquals("[E][X] party (from: AUGUST 27 2023 19:0 to: AUGUST 27 2023 22:0)", temp.toString());
        temp.unmark();
        assertEquals("[E][ ] party (from: AUGUST 27 2023 19:0 to: AUGUST 27 2023 22:0)",temp.toString());
        temp.mark();
        assertEquals("[E][X] party (from: AUGUST 27 2023 19:0 to: AUGUST 27 2023 22:0)", temp.toString());
    }

    @Test
    public void get_time_componentsTest() {
        Event temp = new Event("party",
                LocalDateTime.of(2023, 8, 27, 19, 00),
                LocalDateTime.of(2023, 8, 27, 22, 00),
                true);
        assertArrayEquals(
                new LocalDateTime[]{
                        LocalDateTime.of(2023, 8, 27, 19, 00),
                        LocalDateTime.of(2023, 8, 27, 22, 00)},
                temp.get_time_components());
    }
}