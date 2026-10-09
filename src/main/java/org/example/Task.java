package org.example;

/**
 * A single todo item with its text and completion state.
 */
public class Task {

    private final String text;
    private boolean done;

    public Task(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public boolean isDone() {
        return done;
    }

    /**
     * Marks this task as completed.
     */
    public void markDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        return (done ? "[x] " : "[ ] ") + text;
    }
}
