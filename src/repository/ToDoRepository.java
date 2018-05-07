package repository;

import dto.ToDo;
import enums.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ToDoRepository {

    private List<ToDo> toDos = new ArrayList<>();

    public void save(ToDo todo) {
        if (toDos.contains(todo)) {
            toDos.remove(todo);
        }
        toDos.add(todo);
    }

    public List<ToDo> getAll() {
        return toDos;
    }

    public void remove(String toDoToRemove) {
        toDos.removeIf(toDo -> toDo.getDescription().equals(toDoToRemove));
    }

    public List<ToDo> findByDescription(String description) {
        return toDos.stream()
                .filter(toDo -> toDo.getDescription().toLowerCase().contains(description.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<ToDo> findByStatus(Status status) {
        return toDos.stream()
                .filter(toDo -> toDo.getStatus().equals(status))
                .collect(Collectors.toList());
    }
}
