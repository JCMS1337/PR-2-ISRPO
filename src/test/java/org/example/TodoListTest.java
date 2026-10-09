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
}
