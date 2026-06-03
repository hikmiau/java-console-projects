import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private final String identifier;
    private final String description;
    private boolean isCompleted;
    private final LocalDateTime createdAt;

    public Task(String identifier, String description) {
        this.identifier = identifier;
        this.description = description;
        this.isCompleted = false;
        this.createdAt = LocalDateTime.now();
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public void markAsIncomplete() {
        this.isCompleted = false;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getFormattedCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return createdAt.format(formatter);
    }

    public String getStatusSymbol() {
        return isCompleted ? "[✓]" : "[ ]";
    }

    @Override
    public String toString() {
        return String.format("%s %s - %s (Created: %s)",
            getStatusSymbol(),
            identifier,
            description,
            getFormattedCreatedAt()
        );
    }
}
