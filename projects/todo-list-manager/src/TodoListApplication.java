public class TodoListApplication {
    public static void main(String[] arguments) {
        TaskManager taskManager = new TaskManager();
        UserInterface userInterface = new UserInterface(taskManager);
        userInterface.start();
    }
}
