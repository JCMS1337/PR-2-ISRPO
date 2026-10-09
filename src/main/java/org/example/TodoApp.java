package org.example;

import java.util.List;
import java.util.Scanner;

/**
 * Simple console shell for {@link TodoList}.
 *
 * <p>Supported commands: {@code add <task>}, {@code remove <index>},
 * {@code done <index>}, {@code search <text>}, {@code clear}, {@code list},
 * {@code exit}.</p>
 */
public class TodoApp {

    private final TodoList todoList = new TodoList();

    public static void main(String[] args) {
        new TodoApp().run(new Scanner(System.in));
    }

    /**
     * Runs the command loop until the user types {@code exit} or the input ends.
     *
     * @param scanner the input source
     */
    public void run(Scanner scanner) {
        System.out.println("Todo app. Commands: add <task>, remove <index>, done <index>, search <text>, clear, list, exit");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            if (!handle(line)) {
                break;
            }
        }
    }

    private boolean handle(String line) {
        String[] parts = line.split("\\s+", 2);
        String command = parts[0].toLowerCase();

        switch (command) {
            case "add":
                handleAdd(parts.length > 1 ? parts[1] : null);
                break;
            case "remove":
                handleRemove(parts.length > 1 ? parts[1] : null);
                break;
            case "done":
                handleDone(parts.length > 1 ? parts[1] : null);
                break;
            case "search":
                handleSearch(parts.length > 1 ? parts[1] : null);
                break;
            case "clear":
                handleClear();
                break;
            case "list":
                handleList();
                break;
            case "exit":
                System.out.println("Bye.");
                return false;
            default:
                System.out.println("Unknown command: " + command);
        }
        return true;
    }

    private void handleAdd(String task) {
        if (todoList.add(task)) {
            System.out.println("Added: " + task.trim());
        } else {
            System.out.println("Task is empty, nothing to add.");
        }
    }

    private void handleRemove(String rawIndex) {
        Integer index = parseIndex(rawIndex);
        if (index == null || !todoList.remove(index)) {
            System.out.println("Invalid index.");
        } else {
            System.out.println("Removed task at index " + index + ".");
        }
    }

    private void handleDone(String rawIndex) {
        Integer index = parseIndex(rawIndex);
        if (index == null || !todoList.markDone(index)) {
            System.out.println("Invalid index.");
        } else {
            System.out.println("Marked task at index " + index + " as done.");
        }
    }

    private void handleSearch(String query) {
        if (query == null) {
            System.out.println("Usage: search <text>");
            return;
        }
        List<Task> matches = todoList.search(query);
        if (matches.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        for (Task task : matches) {
            System.out.println(task);
        }
    }

    private void handleClear() {
        todoList.clear();
        System.out.println("All tasks cleared.");
    }

    private void handleList() {
        List<Task> tasks = todoList.getAll();
        if (tasks.isEmpty()) {
            System.out.println("No tasks.");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + ": " + tasks.get(i));
        }
    }

    private Integer parseIndex(String rawIndex) {
        if (rawIndex == null) {
            return null;
        }
        try {
            return Integer.parseInt(rawIndex.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
