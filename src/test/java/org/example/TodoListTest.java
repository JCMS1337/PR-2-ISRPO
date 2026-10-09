package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TodoListTest {

    private TodoList todoList;

    @BeforeEach
    void setUp() {
        todoList = new TodoList();
    }

    @Test
    void addTrimsSurroundingWhitespace() {
        assertTrue(todoList.add("   buy milk   "));
        assertEquals(1, todoList.size());
        assertEquals("buy milk", todoList.getAll().get(0).getText());
    }

    @Test
    void addIgnoresNull() {
        assertFalse(todoList.add(null));
        assertEquals(0, todoList.size());
    }

    @Test
    void addIgnoresEmptyAndBlankStrings() {
        assertFalse(todoList.add(""));
        assertFalse(todoList.add("     "));
        assertEquals(0, todoList.size());
    }

    @Test
    void sizeReflectsAddedTasks() {
        todoList.add("first");
        todoList.add("second");
        assertEquals(2, todoList.size());
    }

    @Test
    void removeReturnsTrueAndDeletesExistingTask() {
        todoList.add("first");
        todoList.add("second");

        assertTrue(todoList.remove(0));

        assertEquals(1, todoList.size());
        assertEquals("second", todoList.getAll().get(0).getText());
    }

    @Test
    void removeReturnsFalseForOutOfBoundsIndex() {
        todoList.add("only task");

        assertFalse(todoList.remove(-1));
        assertFalse(todoList.remove(1));
        assertEquals(1, todoList.size());
    }

    @Test
    void removeReturnsFalseOnEmptyList() {
        assertFalse(todoList.remove(0));
    }

    @Test
    void getAllReturnsDefensiveCopy() {
        todoList.add("first");

        List<Task> copy = todoList.getAll();
        copy.clear();

        assertEquals(1, todoList.size());
    }

    @Test
    void clearRemovesAllTasks() {
        todoList.add("first");
        todoList.add("second");

        todoList.clear();

        assertEquals(0, todoList.size());
        assertTrue(todoList.getAll().isEmpty());
    }

    @Test
    void markDoneMarksTaskAsDone() {
        todoList.add("first");

        assertTrue(todoList.markDone(0));

        assertTrue(todoList.getAll().get(0).isDone());
        assertTrue(todoList.getAll().get(0).toString().startsWith("[x]"));
    }

    @Test
    void markDoneReturnsFalseForInvalidIndex() {
        todoList.add("only task");

        assertFalse(todoList.markDone(-1));
        assertFalse(todoList.markDone(1));
        assertFalse(todoList.getAll().get(0).isDone());
    }

    @Test
    void markDoneReturnsFalseOnEmptyList() {
        assertFalse(todoList.markDone(0));
    }

    @Test
    void searchIsCaseInsensitive() {
        todoList.add("Buy Milk");

        assertEquals(1, todoList.search("buy").size());
        assertEquals(1, todoList.search("MILK").size());
        assertEquals("Buy Milk", todoList.search("milk").get(0).getText());
    }

    @Test
    void searchMatchesSubstring() {
        todoList.add("write report");
        todoList.add("read book");

        assertEquals(1, todoList.search("port").size());
        assertEquals("write report", todoList.search("port").get(0).getText());
    }

    @Test
    void searchReturnsEmptyWhenNoMatch() {
        todoList.add("buy milk");

        assertTrue(todoList.search("xyz").isEmpty());
    }

    @Test
    void searchWithBlankQueryReturnsEmpty() {
        todoList.add("buy milk");

        assertTrue(todoList.search("").isEmpty());
        assertTrue(todoList.search("   ").isEmpty());
        assertTrue(todoList.search(null).isEmpty());
    }

    @Test
    void searchReturnsDefensiveCopy() {
        todoList.add("buy milk");

        List<Task> matches = todoList.search("milk");
        matches.clear();

        assertEquals(1, todoList.size());
    }
}
