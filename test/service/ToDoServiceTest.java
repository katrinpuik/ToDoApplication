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

    @Test
    void find (){
        ToDoService service = new ToDoService();
        service.saveToDoToRepository(new ToDo("toDo"));
        service.saveToDoToRepository(new ToDo("toDoSecond"));
        service.saveToDoToRepository(new ToDo("toDoThird"));
        service.saveToDoToRepository(new ToDo("toDoFourth"));

        assertEquals(asList(new ToDo("toDoThird")), service.findToDo("Third"));
        assertEquals(asList(new ToDo("toDoThird")), service.findToDo("third"));
        assertEquals(asList(new ToDo("toDo"), new ToDo("toDoSecond"), new ToDo("toDoThird"),new ToDo("toDoFourth")),
                service.findToDo("Tod"));
    }

    @Test
    void testIdNumber(){

        ToDo first = new ToDo("toDo");
        ToDo second = new ToDo("toDoSecond");
        ToDo third = new ToDo("toDoThird");
        ToDo fourth = new ToDo("toDoFourth");

        assertEquals(1, first.getId());
        assertEquals(2, second.getId());
        assertEquals(3, third.getId());
        assertEquals(4, fourth.getId());
    }


}