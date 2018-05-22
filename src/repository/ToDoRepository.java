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
                .filter(toDo -> {
                    return isToDosStatusAndQueryStatusSame(status, toDo);
                })
                .collect(Collectors.toList());
    }

    private boolean isToDosStatusAndQueryStatusSame(Status queryStatus, ToDo toDo) {
        return toDo.getStatus() == null && queryStatus == null
                || queryStatus != null && toDo.getStatus() != null
                && toDo.getStatus().equals(queryStatus);
    }

    private boolean areToDosDescriptionAndQuerySame(String query, ToDo toDo) {
        return query == null && toDo.getDescription() == null
                || (query != null && toDo.getDescription() != null
                && haveSameLowerCaseValue(query, toDo.getDescription()));
    }

    private boolean haveSameLowerCaseValue(String value1, String value2) {
        return value2.toLowerCase().contains(value1.toLowerCase());
    }
}
