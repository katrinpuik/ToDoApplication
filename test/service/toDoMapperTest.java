package service;

import dto.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static enums.Status.DONE;
import static enums.Status.NOT_DONE;
import static java.util.Collections.singletonList;
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
        controlList.add("Read a book,DONE");
        controlList.add("Take a break,NOT_DONE");

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
        controlList.add(",DONE");

        List<ToDo> toDos = mapper.deserialize(controlList);

        assertNull(toDos.get(0).getDescription());
        assertEquals(DONE, toDos.get(0).getStatus());
    }

    @Test
    void deserializeIfStatusIsNull() {
        List<String> controlList = new ArrayList<>();
        controlList.add("Read a book,");

        List<ToDo> toDos = mapper.deserialize(controlList);

        assertEquals("Read a book", toDos.get(0).getDescription());
        assertNull(toDos.get(0).getStatus());
    }

    @Test
    void deserializeIfStatusIsInvalid() {
        List<String> controlList = new ArrayList<>();
        controlList.add("Read a book,wrg");

        List<ToDo> toDos = mapper.deserialize(controlList);

        assertEquals("Read a book", toDos.get(0).getDescription());
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

    @Test
    void serialize() {
        List<ToDo> controlList = new ArrayList<>();

        ToDo toDo1 = new ToDo("Read a book");
        toDo1.setStatus(DONE);
        controlList.add(toDo1);

        ToDo toDo2 = new ToDo("Take a break");
        toDo2.setStatus(NOT_DONE);
        controlList.add(toDo2);

        List<String> toDosAsStrings = mapper.serialize(controlList);

        assertEquals(2, toDosAsStrings.size());
        assertEquals("Read a book,DONE", toDosAsStrings.get(0));
        assertEquals("Take a break,NOT_DONE", toDosAsStrings.get(1));
    }

    @Test
    void serializeIfDescriptionIsNull() {
        ToDo toDo = new ToDo(null);
        toDo.setStatus(DONE);

        List<String> toDosAsStrings = mapper.serialize(singletonList(toDo));

        assertEquals(",DONE", toDosAsStrings.get(0));
    }

    @Test
    void serialiseIfStatusIsNull() {
        ToDo toDo = new ToDo("Read a book");
        toDo.setStatus(null);

        List<String> toDosAsStrings = mapper.serialize(singletonList(toDo));

        assertEquals("Read a book,", toDosAsStrings.get(0));
    }

    @Test
    void serializeIfDescriptionAndStatusAreNull() {
        ToDo toDo = new ToDo(null);
        toDo.setStatus(null);

        List<String> toDosAsStrings = mapper.serialize(singletonList(toDo));

        assertEquals(",", toDosAsStrings.get(0));
    }
}
