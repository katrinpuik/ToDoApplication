package service;

import dto.ToDo;
import enums.Status;
import exception.ServiceException;

import java.util.List;
import java.util.stream.Collectors;

class ToDoMapper {

    private ToDoService service = new ToDoService();


    List<ToDo> deserialize(List<String> stringList) {
        return stringList.stream()
                .map((String row) -> {
                    String[] split = row.split(",", -1);
                    String description = split[0].isEmpty() ? null : split[0];
                    String status = split[1].isEmpty() ? null : split[1];
                    return createToDo(description, status);
                })
                .collect(Collectors.toList());
    }

    private ToDo createToDo(String description, String status) {
        ToDo toDo = new ToDo(description);
        toDo.setStatus(mapEnumValue(status));
        return toDo;
    }

    private Status mapEnumValue(String status) {
        try {
            return service.validateAndCreateStatus(status);
        } catch (ServiceException e) {
            System.out.println("Unable to create toDo with status: " + status);
            return null;
        }
    }

    List<String> serialize(List<ToDo> toDoList) {
        return toDoList.stream()
                .map(toDo -> {
                    String description = toDo.getDescription() == null ? "" : toDo.getDescription();
                    String status = toDo.getStatus() == null ? "" : String.valueOf(toDo.getStatus());
                    return description + "," + status;
                })
                .collect(Collectors.toList());
    }
}
