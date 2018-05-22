package service;

import dto.ToDo;
import enums.Status;
import exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


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
    void saveToDoWithDifferentDescription() {
        ToDo toDo = new ToDo("toDo1");
        service.save(toDo);
        toDo.setDescription("new description");
        service.save(toDo);

        List<ToDo> allToDos = service.getAll();
        ToDo updatedToDo = allToDos.get(0);

        assertEquals(1, allToDos.size());
        assertEquals("new description", updatedToDo.getDescription());
    }

    @Test
    void removeSelectedToDo() {
        ToDo toDo1 = new ToDo("toDo1");
        service.save(toDo1);
        ToDo toDo2 = new ToDo(null);
        service.save(toDo2);
        ToDo toDo3 = new ToDo("todo3");
        service.save(toDo3);

        service.remove("todo3");

        assertEquals(asList(toDo1, toDo2), service.getAll());
    }

    @Test
    void removeIfDescriptionIsNull () {
        ToDo toDo1 = new ToDo("toDo1");
        service.save(toDo1);
        ToDo toDo2 = new ToDo(null);
        service.save(toDo2);
        ToDo toDo3 = new ToDo("todo3");
        service.save(toDo3);

        service.remove(null);

        assertEquals(asList(toDo1, toDo3), service.getAll());
    }

    @Test
    void findByExactDescription() {
        ToDo toDo1 = new ToDo("toDo1");
        service.save(toDo1);
        ToDo toDo2 = new ToDo("toDo2");
        service.save(toDo2);
        ToDo toDo3 = new ToDo("toDo3");
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

    @Test
    void findIfDescriptionIsNull() {
        ToDo toDo1 = new ToDo("toDo1");
        service.save(toDo1);
        ToDo toDo2 = new ToDo(null);
        service.save(toDo2);
        ToDo toDo3 = new ToDo("todo3");
        service.save(toDo3);

        assertEquals(singletonList(toDo2), service.findByDescription(null));
    }

    @Test
    void findByStatusDone() {
        service.save(createToDo(Status.DISCARDED));
        ToDo toDo = createToDo(Status.DONE);
        service.save(toDo);
        service.save(createToDo(null));
        service.save(createToDo(Status.NOT_DONE));

        List<ToDo> toDosWithStatusDone = service.findByStatus(Status.DONE);

        assertEquals(1, toDosWithStatusDone.size());
        assertEquals(singletonList(toDo), toDosWithStatusDone);
    }

    @Test
    void findByStatusNotDone() {
        service.save(createToDo(Status.DISCARDED));
        ToDo toDo = createToDo(Status.NOT_DONE);
        service.save(toDo);
        service.save(createToDo(Status.DONE));
        service.save(createToDo(null));

        List<ToDo> toDosWithStatusNotDone = service.findByStatus(Status.NOT_DONE);

        assertEquals(1, toDosWithStatusNotDone.size());
        assertEquals(singletonList(toDo), toDosWithStatusNotDone);
    }


    @Test
    void findByStatusDiscarded() {
        service.save(createToDo(Status.NOT_DONE));
        ToDo toDo = createToDo(Status.DISCARDED);
        service.save(toDo);
        service.save(createToDo(null));
        service.save(createToDo(Status.DONE));

        List<ToDo> toDosWithStatusDiscarded = service.findByStatus(Status.DISCARDED);

        assertEquals(1, toDosWithStatusDiscarded.size());
        assertEquals(singletonList(toDo), toDosWithStatusDiscarded);
    }

    @Test
    void findIfStatusIsNull() {
        service.save(createToDo(Status.DISCARDED));
        ToDo toDo = createToDo(null);
        service.save(toDo);

        List<ToDo> toDosWithStatusNull = service.findByStatus(null);

        assertEquals(1, toDosWithStatusNull.size());
        assertEquals(singletonList(toDo), toDosWithStatusNull);
    }

    @Test
    void validateAndCreateStatusDone() throws ServiceException {
        assertEquals(Status.DONE, service.validateAndCreateStatus("done"));
    }

    @Test
    void validateAndCreateStatusNotDone() throws ServiceException {
        assertEquals(Status.NOT_DONE, service.validateAndCreateStatus("NOT_DONE"));
    }

    @Test
    void validateAndCreateStatusDiscarded() throws ServiceException {
        assertEquals(Status.DISCARDED, service.validateAndCreateStatus("disCARdeD"));
    }

    @Test
    void validateAndCreateThrowsExceptionWhenUnableToMapEnum() {
        Throwable exception = assertThrows(ServiceException.class, () -> service.validateAndCreateStatus("xxx"));
        assertEquals("Invalid input", exception.getMessage());
    }

    @Test
    void validateAndCreateStatusIfInputIsNull() throws ServiceException {
        assertEquals(null, service.validateAndCreateStatus(null));
    }

    private ToDo createToDo(Status status) {
        ToDo toDo = new ToDo("toDo");
        toDo.setStatus(status);
        return toDo;
    }

}