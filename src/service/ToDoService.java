package service;

import dto.ToDo;
import repository.ToDoRepository;

import java.util.List;

public class ToDoService {

    private ToDoRepository repository = new ToDoRepository();

    public ToDo createNewToDo(String name) {
        return new ToDo(name);
    }

    public void saveToDoToRepository(ToDo todo) {
        repository.save(todo);
    }

    public List<ToDo> getAllToDos() {
        return repository.getAll();
    }

    public void removeSelectedToDo(String toDoToRemove) {
        repository.removeUsingStreams(toDoToRemove);
    }

}
