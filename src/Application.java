import dto.ToDo;
import enums.Status;
import exception.ServiceException;
import helper.ReadingAndWritingToFileHelper;
import service.ToDoMapper;
import service.ToDoService;

import java.io.IOException;
import java.util.List;

public class Application {

    private static ToDoService toDoService = new ToDoService();
    private static ReadingAndWritingToFileHelper helper = new ReadingAndWritingToFileHelper();
    private static ToDoMapper mapper = new ToDoMapper();


    public static void main(String[] args) throws IOException {
        mapper.deserialize(helper.initStringsFromFile())
                .forEach(toDoFromFile -> toDoService.save(toDoFromFile));
        toDoService.getAll().forEach(System.out::println);
        runProgram();
        toDoService.getAll().forEach(System.out::println);
        helper.writeRowsToFile(mapper.serialize(toDoService.getAll()));
    }

    private static void runProgram() {
        toDoService.save(toDoService.create("ToDoToTest"));
        toDoService.save(toDoService.create("ToDoToTestSecond"));
        toDoService.getAll().forEach(System.out::println);
        toDoService.remove("stuff");
        toDoService.remove("sth");
        toDoService.getAll().forEach(System.out::println);

        List<ToDo> result = toDoService.findByDescription("ara nae vaeva");
        ToDo toDoFoundByDescription = result.get(0);
        System.out.println(toDoFoundByDescription);
        toDoFoundByDescription.setDescription("new description");
        toDoService.save(toDoFoundByDescription);
        try {
            toDoFoundByDescription.setStatus(toDoService.validateAndCreateStatus("done"));
        } catch (ServiceException e) {
            System.out.println("invalid enum type inserted");
        }
        toDoService.save(toDoFoundByDescription);
        System.out.println(toDoFoundByDescription);

        List<ToDo> results2 = toDoService.findByStatus(Status.DONE);
        ToDo toDoDone = results2.get(0);
        System.out.println(toDoDone);
    }


}
