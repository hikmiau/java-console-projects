import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final TaskManager taskManager;
    private final Scanner scanner;
    private boolean isRunning;

    public UserInterface(TaskManager taskManager) {
        this.taskManager = taskManager;
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
    }

    public void start() {
        displayWelcomeMessage();

        while (isRunning) {
            displayMenu();
            String userChoice = getUserInput("Choose an option: ");
            processUserChoice(userChoice);
        }

        displayGoodbyeMessage();
        scanner.close();
    }

    private void displayWelcomeMessage() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║        Todo List Manager v1.0          ║");
        System.out.println("╚════════════════════════════════════════╝\n");
    }

    private void displayMenu() {
        System.out.println("\n┌─ Main Menu ─────────────────────────────┐");
        System.out.println("│ 1. Add task                             │");
        System.out.println("│ 2. List all tasks                       │");
        System.out.println("│ 3. List incomplete tasks                │");
        System.out.println("│ 4. List completed tasks                 │");
        System.out.println("│ 5. Mark task as completed               │");
        System.out.println("│ 6. Mark task as incomplete              │");
        System.out.println("│ 7. Remove task                          │");
        System.out.println("│ 8. Clear completed tasks                │");
        System.out.println("│ 9. View statistics                      │");
        System.out.println("│ 0. Exit                                 │");
        System.out.println("└─────────────────────────────────────────┘");
    }

    private void processUserChoice(String choice) {
        switch (choice.trim()) {
            case "1" -> handleAddTask();
            case "2" -> handleListAllTasks();
            case "3" -> handleListIncompleteTasks();
            case "4" -> handleListCompletedTasks();
            case "5" -> handleCompleteTask();
            case "6" -> handleIncompleteTask();
            case "7" -> handleRemoveTask();
            case "8" -> handleClearCompletedTasks();
            case "9" -> handleViewStatistics();
            case "0" -> isRunning = false;
            default -> System.out.println("❌ Invalid option. Please try again.");
        }
    }

    private void handleAddTask() {
        String description = getUserInput("\nEnter task description: ");
        
        try {
            taskManager.addTask(description);
            System.out.println("✓ Task added successfully!");
        } catch (IllegalArgumentException exception) {
            System.out.println("❌ Error: " + exception.getMessage());
        }
    }

    private void handleListAllTasks() {
        List<Task> allTasks = taskManager.getAllTasks();
        displayTaskList("All Tasks", allTasks);
    }

    private void handleListIncompleteTasks() {
        List<Task> incompleteTasks = taskManager.getIncompleteTasks();
        displayTaskList("Incomplete Tasks", incompleteTasks);
    }

    private void handleListCompletedTasks() {
        List<Task> completedTasks = taskManager.getCompletedTasks();
        displayTaskList("Completed Tasks", completedTasks);
    }

    private void handleCompleteTask() {
        displayAllTasksForSelection();
        String taskIdentifier = getUserInput("\nEnter task ID to mark as complete: ");
        
        try {
            taskManager.completeTask(taskIdentifier);
            System.out.println("✓ Task marked as completed!");
        } catch (IllegalArgumentException exception) {
            System.out.println("❌ Error: " + exception.getMessage());
        }
    }

    private void handleIncompleteTask() {
        displayAllTasksForSelection();
        String taskIdentifier = getUserInput("\nEnter task ID to mark as incomplete: ");
        
        try {
            taskManager.incompleteTask(taskIdentifier);
            System.out.println("✓ Task marked as incomplete!");
        } catch (IllegalArgumentException exception) {
            System.out.println("❌ Error: " + exception.getMessage());
        }
    }

    private void handleRemoveTask() {
        displayAllTasksForSelection();
        String taskIdentifier = getUserInput("\nEnter task ID to remove: ");
        
        try {
            taskManager.removeTask(taskIdentifier);
            System.out.println("✓ Task removed successfully!");
        } catch (IllegalArgumentException exception) {
            System.out.println("❌ Error: " + exception.getMessage());
        }
    }

    private void handleClearCompletedTasks() {
        String confirmation = getUserInput("\nAre you sure you want to delete all completed tasks? (yes/no): ");
        
        if (confirmation.equalsIgnoreCase("yes")) {
            taskManager.clearCompletedTasks();
            System.out.println("✓ Completed tasks cleared!");
        } else {
            System.out.println("Operation cancelled.");
        }
    }

    private void handleViewStatistics() {
        int totalTasks = taskManager.getTotalTaskCount();
        int completedTasks = taskManager.getCompletedTaskCount();
        int incompleteTasks = totalTasks - completedTasks;
        double completionPercentage = totalTasks == 0 ? 0 : (completedTasks * 100.0) / totalTasks;

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║          Statistics                    ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.printf("║ Total Tasks:        %2d                   ║%n", totalTasks);
        System.out.printf("║ Completed:          %2d                   ║%n", completedTasks);
        System.out.printf("║ Incomplete:         %2d                   ║%n", incompleteTasks);
        System.out.printf("║ Completion Rate:    %.1f%%               ║%n", completionPercentage);
        System.out.println("╚════════════════════════════════════════╝");
    }

    private void displayTaskList(String title, List<Task> tasks) {
        System.out.println("\n┌─ " + title + " ─────────────────���───┐");
        
        if (tasks.isEmpty()) {
            System.out.println("│ No tasks found.                         │");
        } else {
            for (Task task : tasks) {
                String formattedTask = String.format("│ %s │", task);
                
                if (formattedTask.length() > 42) {
                    System.out.println(formattedTask.substring(0, 41) + "│");
                } else {
                    System.out.printf("│ %-40s │%n", task);
                }
            }
        }
        
        System.out.println("└─────────────────────────────────────────┘");
    }

    private void displayAllTasksForSelection() {
        List<Task> allTasks = taskManager.getAllTasks();
        System.out.println();
        displayTaskList("Available Tasks", allTasks);
    }

    private void displayGoodbyeMessage() {
        System.out.println("\n👋 Goodbye! Your tasks have been saved in memory.");
        System.out.println("   (Note: Tasks are not persisted to disk in this version)\n");
    }

    private String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
