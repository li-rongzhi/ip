package jarvis.task;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toRecordTest() {
        assertEquals("[D][ ] quiz (by: 2023-08-27T12:00)",
                new Deadline("quiz", LocalDateTime.of(2023, 8, 27, 12, 00)).toRecord());
        Deadline temp = new Deadline("quiz",
                LocalDateTime.of(2023, 8, 27, 12, 00), true);
        assertEquals("[D][X] quiz (by: 2023-08-27T12:00)", temp.toRecord());
        temp.unmark();
        assertEquals("[D][ ] quiz (by: 2023-08-27T12:00)",temp.toRecord());
        temp.mark();
        assertEquals("[D][X] quiz (by: 2023-08-27T12:00)", temp.toRecord());
    }

    @Test
    public void toStringTest() {
        assertEquals("[D][ ] quiz (by: AUGUST 27 2023 12:0)",
                new Deadline("quiz", LocalDateTime.of(2023, 8, 27, 12, 00)).toString());
        Deadline temp = new Deadline("quiz",
                LocalDateTime.of(2023, 8, 27, 12, 00), true);
        assertEquals("[D][X] quiz (by: AUGUST 27 2023 12:0)", temp.toString());
        temp.unmark();
        assertEquals("[D][ ] quiz (by: AUGUST 27 2023 12:0)",temp.toString());
        temp.mark();
        assertEquals("[D][X] quiz (by: AUGUST 27 2023 12:0)", temp.toString());
    }

    @Test
    public void get_time_componentsTest() {
        Deadline temp = new Deadline("quiz",
                LocalDateTime.of(2023, 8, 27, 12, 00), true);
        assertArrayEquals(new LocalDateTime[]{LocalDateTime.of(2023, 8, 27, 12, 00)},
                temp.get_time_components());
    }
}
