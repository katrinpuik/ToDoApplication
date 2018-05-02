import dto.ToDo;
import enums.Status;
import service.ToDoService;

import java.util.List;

public class Application {

    private static ToDoService toDoService = new ToDoService();

    public static void main(String[] args) {
        toDoService.save(toDoService.create("ToDoToTest"));
        toDoService.save(toDoService.create("ToDoToTestSecond"));
        toDoService.save(toDoService.create("ToDoToTestThird"));

        toDoService.remove("ToDoToTestSecond");

        List<ToDo> result = toDoService.findByDescription("third");
        ToDo third = result.get(0);

        System.out.println(third);

        third.setDescription("new description");

        toDoService.save(third);

        third.setStatus(Status.DONE);
        toDoService.save(third);
        System.out.println(third);

        List<ToDo> results2 = toDoService.findByStatus(Status.DONE);

        ToDo toDoDone = results2.get(0);
        System.out.println(toDoDone);
    }
}
