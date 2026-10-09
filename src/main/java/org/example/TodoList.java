package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * In-memory list of todo tasks.
 */
public class TodoList {

    private final List<String> tasks = new ArrayList<>();

    /**
     * Adds a task to the list. The task is trimmed first; {@code null},
     * empty and blank values are ignored.
     *
     * @param task the task text
     * @return {@code true} if the task was added
     */
    public boolean add(String task) {
        if (task == null) {
            return false;
        }
        String normalized = task.trim();
        if (normalized.isEmpty()) {
            return false;
        }
        return tasks.add(normalized);
    }

    /**
     * Removes the task at the given zero-based index.
     *
     * @param index the index of the task to remove
     * @return {@code true} if the task was removed, {@code false} if the index
     *         is out of bounds
     */
    public boolean remove(int index) {
        if (index < 0 || index >= tasks.size()) {
            return false;
        }
        tasks.remove(index);
        return true;
    }

    /**
     * Returns a copy of the current tasks.
     *
     * @return a new list with the tasks
     */
    public List<String> getAll() {
        return new ArrayList<>(tasks);
    }

    /**
     * @return the number of tasks currently stored
     */
    public int size() {
        return tasks.size();
    }
}
