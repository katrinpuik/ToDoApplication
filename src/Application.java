import service.ToDoService;

public class Application {

    private static ToDoService service = new ToDoService();

    public static void main(String[] args) {
        service.saveToDoToRepository(service.createNewToDo("ToDoToTest"));
        service.saveToDoToRepository(service.createNewToDo("ToDoToTestSecond"));
        service.saveToDoToRepository(service.createNewToDo("ToDoToTestThird"));

        service.removeSelectedToDo("ToDoToTestSecond");

        System.out.println(service.getAllToDos());
    }
}
