package service;

import dto.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static enums.Status.DONE;
import static enums.Status.NOT_DONE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class toDoMapperTest {

    private ToDoMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ToDoMapper();
    }

    @Test
    void deserialize() {
        List<String> controlList = new ArrayList<>();
        controlList.add("Read a book, DONE");
        controlList.add("Take a break, NOT_DONE");

        List<ToDo> toDos = mapper.deserialize(controlList);

        assertEquals(2, toDos.size());
        assertEquals("Read a book", toDos.get(0).getDescription());
        assertEquals(DONE, toDos.get(0).getStatus());
        assertEquals("Take a break", toDos.get(1).getDescription());
        assertEquals(NOT_DONE, toDos.get(1).getStatus());
    }

    @Test
    void deserializeIfDescriptionIsNull() {
        List<String> controlList = new ArrayList<>();
        controlList.add(", DONE");

        List<ToDo> toDos = mapper.deserialize(controlList);

        assertNull(toDos.get(0).getDescription());
        assertEquals(DONE, toDos.get(0).getStatus());
    }

    @Test
    void deserializeIfStatusIsNull() {
        List<String> controlList = new ArrayList<>();
        controlList.add("Read a book,");

        List<ToDo> toDos = mapper.deserialize(controlList);

        assertEquals("Read a book,", toDos.get(0).getDescription());
        assertNull(toDos.get(0).getStatus());
    }

    @Test
    void deserializeIfDescriptionIsNullAndStatusIsNull() {
        List<String> controlList = new ArrayList<>();
        controlList.add(",");

        List<ToDo> toDos = mapper.deserialize(controlList);

        assertNull(toDos.get(0).getDescription());
        assertNull(toDos.get(0).getStatus());
    }
}
