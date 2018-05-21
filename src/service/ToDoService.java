package service;

import dto.ToDo;
import enums.Status;
import exception.ServiceException;
import repository.ToDoRepository;

import java.util.List;

import static enums.Status.valueOf;

public class ToDoService {

    private ToDoRepository repository = new ToDoRepository();

    public ToDo create(String description) {
        return new ToDo(description);
    }

    public void save(ToDo todo) {
        repository.save(todo);
    }

    public List<ToDo> getAll() {
        return repository.getAll();
    }

    public void remove(String description) {
        repository.remove(description);
    }

    public List<ToDo> findByDescription(String description) {
        return repository.findByDescription(description);
    }

    public List<ToDo> findByStatus(Status status) {
        return repository.findByStatus(status);
    }

    public Status validateAndCreateStatus(String enumToCreate) throws ServiceException {
        if (enumToCreate == null) {
            return null;
        }
        try {
            return valueOf(enumToCreate.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ServiceException("Invalid input");
        }
    }
}