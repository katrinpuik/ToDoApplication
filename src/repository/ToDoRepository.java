package repository;

import dto.ToDo;

import java.util.ArrayList;
import java.util.List;


public class ToDoRepository {
    private List<ToDo> toDos = new ArrayList<>();


    public void save(ToDo todo) {
        toDos.add(todo);
    }

    public List<ToDo> getAll() {
        return toDos;
    }

    public void removeUsingStreams(String todoToRemove) {
        toDos.removeIf(toDo -> toDo.getDescription().equals(todoToRemove));
    }
}
