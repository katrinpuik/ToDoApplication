package dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    @Test
    void testIdNumber() {

        ToDo first = new ToDo("toDoFirst");
        ToDo second = new ToDo("toDoSecond");
        ToDo third = new ToDo("toDoThird");
        ToDo fourth = new ToDo("toDoFourth");

        assertEquals(1, first.getId());
        assertEquals(2, second.getId());
        assertEquals(3, third.getId());
        assertEquals(4, fourth.getId());
    }

    @Test
    void testDescription() {

        ToDo first = new ToDo("toDoFirst");
        ToDo second = new ToDo("toDoSecond");

        assertEquals("toDoFirst", first.getDescription());
        assertEquals("toDoSecond", second.getDescription());
    }

    @Test
    void setStatusTrue() {
        ToDo first = new ToDo("toDoFirst");
        first.setStatus(true);

        assertTrue(first.getStatus());
    }

    @Test
    void setStatusFalse() {
        ToDo first = new ToDo("toDoFirst");
        first.setStatus(false);

        assertFalse(first.getStatus());
    }

    @Test
    void setStatusFalseByDefault() {
        ToDo first = new ToDo("toDoFirst");

        assertFalse(first.getStatus());
    }

}