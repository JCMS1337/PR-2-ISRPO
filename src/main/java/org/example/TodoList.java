package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * In-memory list of todo tasks.
 */
public class TodoList {

    private final List<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the list. The task text is trimmed first; {@code null},
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
        return tasks.add(new Task(normalized));
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
     * Marks the task at the given zero-based index as completed.
     *
     * @param index the index of the task to mark
     * @return {@code true} if the task was marked, {@code false} if the index
     *         is out of bounds
     */
    public boolean markDone(int index) {
        if (index < 0 || index >= tasks.size()) {
            return false;
        }
        tasks.get(index).markDone();
        return true;
    }

    /**
     * Removes all tasks from the list.
     */
    public void clear() {
        tasks.clear();
    }

    /**
     * Returns the tasks whose text contains the given query, ignoring case.
     * A {@code null} or blank query matches nothing.
     *
     * @param query the text to search for
     * @return a new list with the matching tasks
     */
    public List<Task> search(String query) {
        List<Task> matches = new ArrayList<>();
        if (query == null) {
            return matches;
        }
        String needle = query.trim().toLowerCase(Locale.ROOT);
        if (needle.isEmpty()) {
            return matches;
        }
        for (Task task : tasks) {
            if (task.getText().toLowerCase(Locale.ROOT).contains(needle)) {
                matches.add(task);
            }
        }
        return matches;
    }

    /**
     * Returns a copy of the current tasks.
     *
     * @return a new list with the tasks
     */
    public List<Task> getAll() {
        return new ArrayList<>(tasks);
    }

    /**
     * @return the number of tasks currently stored
     */
    public int size() {
        return tasks.size();
    }
}
