package dto;

import enums.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ToDoTest {

    @Test
    void testDescription() {
        ToDo first = new ToDo("toDoFirst");
        ToDo second = new ToDo("toDoSecond");

        assertEquals("toDoFirst", first.getDescription());
        assertEquals("toDoSecond", second.getDescription());
    }

    @Test
    void setStatusDone() {
        ToDo first = new ToDo("toDoFirst");
        first.setStatus(Status.DONE);

        assertEquals(Status.DONE, first.getStatus());
    }

    @Test
    void setStatusNotDone() {
        ToDo first = new ToDo("toDoFirst");
        first.setStatus(Status.NOT_DONE);

        assertEquals(Status.NOT_DONE, first.getStatus());
    }

    @Test
    void setStatusDiscarded() {
        ToDo first = new ToDo("toDoFirst");
        first.setStatus(Status.DISCARDED);

        assertEquals(Status.DISCARDED, first.getStatus());
    }

    @Test
    void initialStatusIsNull() {
        ToDo first = new ToDo("toDoFirst");

        assertNull(first.getStatus());
    }

}