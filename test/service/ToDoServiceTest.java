package service;

import dto.ToDo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoServiceTest {

    @Test
    void createNewToDo() {
        ToDoService service = new ToDoService();

        ToDo todo = new ToDo("toDoToTest");

        assertEquals(todo, service.createNewToDo("toDoToTest"));
    }

    @Test
    void getAllToDos() {
        ToDoService service = new ToDoService();
        service.saveToDoToRepository(new ToDo("toDo"));
        service.saveToDoToRepository(new ToDo("toDoSecond"));

        List<ToDo> allToDos = service.getAllToDos();

        assertEquals(asList(new ToDo("toDo"), new ToDo("toDoSecond")), allToDos);
    }

    @Test
    void remove() {
        ToDoService service = new ToDoService();
        service.saveToDoToRepository(new ToDo("toDo"));
        service.saveToDoToRepository(new ToDo("toDoSecond"));
        service.saveToDoToRepository(new ToDo("toDoThird"));

        service.removeSelectedToDo("toDoSecond");

        assertEquals(asList(new ToDo("toDo"), new ToDo ("toDoThird")), service.getAllToDos());
    }
}