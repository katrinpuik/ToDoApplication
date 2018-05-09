package service;

import dto.ToDo;
import exception.ServiceException;

import java.util.List;
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
                        ToDo toDo = new ToDo(null);
                        try {
                            toDo.setStatus(service.validateAndCreateStatus(split[1].trim()));
                        } catch (ServiceException e) {
                            throw new RuntimeException("");
                        }
                        return toDo;
                    } else if (split[1].equals("")) {
                        return new ToDo(split[0]);
                    } else {
                        ToDo toDo = new ToDo(split[0]);
                        try {
                            toDo.setStatus(service.validateAndCreateStatus(split[1].trim()));
                        } catch (ServiceException e) {
                            e.printStackTrace();
                        }
                        return toDo;
                    }
                }).collect(Collectors.toList());
    }
}
