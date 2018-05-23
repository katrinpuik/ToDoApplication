package service;

import dto.ToDo;
import enums.Status;
import repository.ToDoRepository;

import java.util.List;

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


}