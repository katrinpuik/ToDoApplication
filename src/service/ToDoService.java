package service;

import dto.ToDo;
import enums.Status;
import exception.ServiceException;

import java.util.List;

public class ToDoService {

    public ToDo create(String description) {
        return null;
    }

    public void save(ToDo todo) {
    }

    public List<ToDo> getAll() {
        return null;
    }

    public void remove(String toDoToRemove) {
    }

    public List<ToDo> findByDescription(String description) {
        return null;
    }

    public List<ToDo> findByStatus(Status status) {
        return null;
    }

    public Status validateAndCreateStatus(String enumToCreate) throws ServiceException {
        return null;
    }

}