package service;

import dto.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;


class ToDoServiceTest {

    private ToDoService service;

    @BeforeEach
    void setUp() {
        service = new ToDoService();
    }

    @Test
    void createNewToDo() {
        ToDo result = service.create("toDoToTest");

        assertEquals("toDoToTest", result.getDescription());
    }

    @Test
    void saveAndGetAllToDos() {
        ToDo toDo1 = new ToDo("toDo1");
        service.save(toDo1);
        ToDo toDo2 = new ToDo("toDo2");
        service.save(toDo2);

        List<ToDo> allToDos = service.getAll();

        assertEquals(asList(toDo1, toDo2), allToDos);
    }

    @Test
    void removeSelectedToDo() {
        ToDo toDo1 = new ToDo("toDo1");
        service.save(toDo1);
        ToDo toDo2 = new ToDo("todo2");
        service.save(toDo2);
        ToDo toDo3 = new ToDo("todo3");
        service.save(toDo3);

        service.remove("todo2");

        assertEquals(asList(toDo1, toDo3), service.getAll());
    }

    @Test
    void findByExactDescription() {
        ToDo toDo1 = new ToDo("toDo1");
        service.save(toDo1);
        ToDo toDo2 = new ToDo("todo2");
        service.save(toDo2);
        ToDo toDo3 = new ToDo("todo3");
        service.save(toDo3);

        assertEquals(singletonList(toDo2), service.findByDescription("toDo2"));
    }

    @Test
    void findByExactDescriptionUpperCase() {
        ToDo toDo1 = new ToDo("toDo1");
        service.save(toDo1);
        ToDo toDo2 = new ToDo("todo2");
        service.save(toDo2);
        ToDo toDo3 = new ToDo("todo3");
        service.save(toDo3);

        assertEquals(singletonList(toDo2), service.findByDescription("TODO2"));
    }

    @Test
    void findByDescriptionSomeLetters() {
        ToDo toDo1 = new ToDo("toDo1");
        service.save(toDo1);
        ToDo toDo2 = new ToDo("todo2");
        service.save(toDo2);
        ToDo toDo3 = new ToDo("todo3");
        service.save(toDo3);

        assertEquals(asList(toDo1, toDo2, toDo3), service.findByDescription("tod"));
    }
}