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

        assertEquals(1, allToDos.size());
        ToDo updatedToDo = allToDos.get(0);
        assertEquals("new description", updatedToDo.getDescription());
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
        service.save(createToDo(Status.NOT_DONE));

        // TODO one call to testable method

        assertEquals(1, service.findByStatus(Status.DONE).size());
        assertEquals(toDo, service.findByStatus(Status.DONE).get(0));
    }

    @Test
    void findByStatusNotDone() {
        service.save(createToDo(Status.DISCARDED));
        ToDo toDo = createToDo(Status.NOT_DONE);
        service.save(toDo);
        service.save(createToDo(Status.DONE));

        // TODO one call to testable method

        assertEquals(1, service.findByStatus(Status.NOT_DONE).size());
        assertEquals(toDo, service.findByStatus(Status.NOT_DONE).get(0));
    }


    @Test
    void findByStatusDiscarded() {
        service.save(createToDo(Status.NOT_DONE));
        ToDo toDo = createToDo(Status.DISCARDED);
        service.save(toDo);
        service.save(createToDo(Status.DONE));

        // TODO one call to testable method

        assertEquals(1, service.findByStatus(Status.DISCARDED).size());
        assertEquals(toDo, service.findByStatus(Status.DISCARDED).get(0));
    }

    @Test
    void findIfStatusIsNull() {
        service.save(createToDo(Status.DISCARDED));
        ToDo toDo = createToDo(null);
        service.save(toDo);

        // TODO one call to testable method

        assertEquals(1, service.findByStatus(null).size());
        assertEquals(toDo, service.findByStatus(null).get(0));
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