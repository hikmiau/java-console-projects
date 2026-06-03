import java.util.*;
import java.util.stream.Collectors;

public class TaskManager {
    private final List<Task> tasks;
    private int nextTaskNumber;

    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.nextTaskNumber = 1;
    }

    public void addTask(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Task description cannot be empty");
        }

        String taskIdentifier = generateTaskIdentifier();
        Task newTask = new Task(taskIdentifier, description.trim());
        tasks.add(newTask);
    }

    public void completeTask(String identifier) {
        Task task = findTaskByIdentifier(identifier);
        
        if (task == null) {
            throw new IllegalArgumentException("Task not found: " + identifier);
        }

        task.markAsCompleted();
    }

    public void incompleteTask(String identifier) {
        Task task = findTaskByIdentifier(identifier);
        
        if (task == null) {
            throw new IllegalArgumentException("Task not found: " + identifier);
        }

        task.markAsIncomplete();
    }

    public void removeTask(String identifier) {
        Task task = findTaskByIdentifier(identifier);
        
        if (task == null) {
            throw new IllegalArgumentException("Task not found: " + identifier);
        }

        tasks.remove(task);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public List<Task> getCompletedTasks() {
        return tasks.stream()
            .filter(Task::isCompleted)
            .collect(Collectors.toList());
    }

    public List<Task> getIncompleteTasks() {
        return tasks.stream()
            .filter(task -> !task.isCompleted())
            .collect(Collectors.toList());
    }

    public int getTotalTaskCount() {
        return tasks.size();
    }

    public int getCompletedTaskCount() {
        return (int) tasks.stream().filter(Task::isCompleted).count();
    }

    public void clearCompletedTasks() {
        tasks.removeIf(Task::isCompleted);
    }

    private Task findTaskByIdentifier(String identifier) {
        return tasks.stream()
            .filter(task -> task.getIdentifier().equals(identifier))
            .findFirst()
            .orElse(null);
    }

    private String generateTaskIdentifier() {
        return "#" + nextTaskNumber++;
    }
}
