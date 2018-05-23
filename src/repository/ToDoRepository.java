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
        toDos.removeIf(toDo -> areEqual(description, toDo.getDescription()));
    }

    public List<ToDo> findByDescription(String description) {
        return toDos.stream()
                .filter(toDo -> areEqual(description, toDo.getDescription()))
                .collect(Collectors.toList());
    }

    public List<ToDo> findByStatus(Status status) {
        return toDos.stream()
                .filter(toDo -> areEqualStatus(status, toDo))
                .collect(Collectors.toList());
    }

    private boolean areEqualStatus(Status queryStatus, ToDo toDo) {
        return toDo.getStatus() == null && queryStatus == null
                || queryStatus != null && toDo.getStatus() != null
                && toDo.getStatus().equals(queryStatus);
    }

    private boolean areEqual(String value1, String value2) {
        return value1 == null && value2 == null
                || (value1 != null && value2 != null
                && haveSameLowerCaseValue(value1, value2));
    }

    private boolean haveSameLowerCaseValue(String value1, String value2) {
        return value2.toLowerCase().contains(value1.toLowerCase());
    }
}
