package Jarvis.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toRecordTest() {
        assertEquals("[T][ ] read book", new ToDo("read book").toRecord());
        ToDo temp = new ToDo("read book", true);
        assertEquals("[T][X] read book", temp.toRecord());
        temp.unmark();
        assertEquals("[T][ ] read book",temp.toRecord());
        temp.mark();
        assertEquals("[T][X] read book", temp.toRecord());
    }

    @Test
    public void toStringTest() {
        assertEquals("[T][ ] read book", new ToDo("read book").toString());
        ToDo temp = new ToDo("read book", true);
        assertEquals("[T][X] read book", temp.toString());
        temp.unmark();
        assertEquals("[T][ ] read book",temp.toString());
        temp.mark();
        assertEquals("[T][X] read book", temp.toString());
    }

    @Test
    public void get_time_componentsTest() {
        ToDo temp = new ToDo("read book");
        assertArrayEquals(new LocalDateTime[0],
                temp.get_time_components());
    }
}
