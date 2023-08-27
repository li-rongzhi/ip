package Jarvis.task;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void toRecordTest() {
        assertEquals("[ ] read", new Task("read").toRecord());
        Task temp = new Task("read", true);
        assertEquals("[X] read", temp.toRecord());
        temp.unmark();
        assertEquals("[ ] read", temp.toRecord());
        temp.mark();
        assertEquals("[X] read", temp.toRecord());
    }

    @Test
    public void toStringTest() {
        assertEquals("[ ] read", new Task("read").toString());
        Task temp = new Task("read", true);
        assertEquals("[X] read", temp.toString());
        temp.unmark();
        assertEquals("[ ] read", temp.toString());
        temp.mark();
        assertEquals("[X] read", temp.toString());
    }


    @Test
    public void time_printerTest() {
        assertEquals("AUGUST 27 2023 12:0",
                new Task("read").time_printer(
                        LocalDateTime.of(2023, 8, 27, 12, 0)));
    }

    @Test
    public void get_time_componentsTest() {
        Task temp = new Task("read book");
        assertArrayEquals(new LocalDateTime[0],
                temp.get_time_components());
    }
}
