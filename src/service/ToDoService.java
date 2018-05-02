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

    List<ToDo> getAll() {
        return repository.getAll();
    }

    public void remove(String toDoToRemove) {
        repository.remove(toDoToRemove);
    }

    public List<ToDo> findByDescription(String description) {
        return repository.findByDescription(description);
    }

    public List<ToDo> findByStatus(Status status) {
        return repository.findByStatus(status);
    }

    //kirjutada meetod, mis v]tab sisse stringi ja vaatab, kas sellest saab teha vastava enumi, ja kui saab teha, siis teeb,
    // kui ei saa siis annab veateate.
}


