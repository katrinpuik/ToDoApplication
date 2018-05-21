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

    public void remove(String description) {
        toDos.removeIf(toDo -> areToDosDescriptionAndQuerySame(description, toDo));
    }

    public List<ToDo> findByDescription(String description) {
        return toDos.stream()
                .filter(toDo ->
                        areToDosDescriptionAndQuerySame(description, toDo))
                .collect(Collectors.toList());
    }

    public List<ToDo> findByStatus(Status status) {
        return toDos.stream()
                .filter(toDo -> toDo.getStatus() == null || toDo.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    private boolean areToDosDescriptionAndQuerySame(String description, ToDo toDo) {
        return description == null && toDo.getDescription() == null
                || (description != null && toDo.getDescription() != null
                && haveSameLowerCaseValue(description, toDo.getDescription()));
    }

    private boolean haveSameLowerCaseValue(String value1, String value2) {
        return value2.toLowerCase().contains(value1.toLowerCase());
    }
}
