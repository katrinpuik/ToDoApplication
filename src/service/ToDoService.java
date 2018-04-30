package service;

import dto.ToDo;
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

    List<ToDo> getAll() {
        return repository.getAll();
    }

    public void remove(String toDoToRemove) {
        repository.remove(toDoToRemove);
    }

    public List<ToDo> findByDescription(String description) {
        return repository.findByDescription(description);
    }
}
