package dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}