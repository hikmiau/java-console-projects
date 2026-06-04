# Todo List Manager

A professional console-based todo list application for managing daily tasks efficiently.

## Features

- Add tasks with descriptions
- Mark tasks as completed or incomplete
- Remove tasks from the list
- View all tasks, completed tasks, or incomplete tasks
- Clear all completed tasks at once
- View completion statistics
- Clean, professional UI with visual indicators
- Timestamps for task creation

## Architecture

- **Task.java** — Represents a single task with status tracking and metadata
- **TaskManager.java** — Manages the collection of tasks with business logic
- **UserInterface.java** — Handles all user interaction and display
- **TodoListApplication.java** — Entry point for the application

## Requirements

- Java 21 or newer

## Running

```sh
cd projects/todo-list-manager/src
javac *.java
java TodoListApplication
```

## Example Usage

```
╔════════════════════════════════════════╗
║        Todo List Manager v1.0          ║
╚════════════════════════════════════════╝

┌─ Main Menu ─────────────────────────────┐
│ 1. Add task                             │
│ 2. List all tasks                       │
│ 3. List incomplete tasks                │
│ 4. List completed tasks                 │
│ 5. Mark task as completed               │
│ 6. Mark task as incomplete              │
│ 7. Remove task                          │
│ 8. Clear completed tasks                │
│ 9. View statistics                      │
│ 0. Exit                                 │
└─────────────────────────────────────────┘

Choose an option: 1
Enter task description: Buy groceries
✓ Task added successfully!
```

## Design Notes

- Uses stream processing for efficient task filtering
- Guard clauses prevent deep nesting
- Descriptive method and variable names eliminate need for comments
- Clear separation of concerns between Task, TaskManager, and UserInterface
- Early returns reduce cognitive complexity
