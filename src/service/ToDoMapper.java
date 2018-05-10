package service;

import dto.ToDo;
import exception.ServiceException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class ToDoMapper {

    private ToDoService service = new ToDoService();

    List<ToDo> deserialize(List<String> stringList) {
        return stringList.stream()
                .map(row -> {
                    String[] split = row.split(",", -1);
                    if (split[0].equals("") && split[1].equals("")) {
                        return new ToDo(null);
                    } else if (split[0].equals("")) {
                        String status = split[1];
                        return createToDo(null, status);
                    } else if (split[1].equals("")) {
                        return new ToDo(split[0]);
                    } else {
                        String description = split[0];
                        String status = split[1];
                        return createToDo(description, status);
                    }
                }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    private ToDo createToDo(String description, String status) {
        try {
            ToDo toDo = new ToDo(description);
            toDo.setStatus(service.validateAndCreateStatus(status.trim()));
            return toDo;
        } catch (ServiceException e) {
            return null;
        }
    }
}
