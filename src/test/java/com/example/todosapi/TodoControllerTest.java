package com.example.todosapi;

import com.example.todosapi.controller.TodoController;
import com.example.todosapi.exception.TodoNotFoundException;
import com.example.todosapi.model.Todo;
import com.example.todosapi.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TodoControllerTest {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoController todoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTodos() {
        List<Todo> todos = new ArrayList<>();
        todos.add(new Todo(1L, "Test Todo 1", false));
        todos.add(new Todo(2L, "Test Todo 2", true));

        when(todoService.findAll()).thenReturn(todos);

        List<Todo> result = todoController.getAllTodos();

        assertEquals(2, result.size());
        assertEquals("Test Todo 1", result.get(0).getDescription());
        assertEquals("Test Todo 2", result.get(1).getDescription());

        verify(todoService, times(1)).findAll();
    }

    @Test
    public void testGetTodoById() {
        Todo todo = new Todo(1L, "Test Todo", false);

        when(todoService.findById(1L)).thenReturn(Optional.of(todo));

        ResponseEntity<Todo> responseEntity = todoController.getTodoById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Test Todo", responseEntity.getBody().getDescription());

        verify(todoService, times(1)).findById(1L);
    }

    @Test
    public void testGetTodoById_NotFound() {
        when(todoService.findById(1L)).thenReturn(Optional.empty());

        assertThrows(TodoNotFoundException.class, () -> {
            todoController.getTodoById(1L);
        });

        verify(todoService, times(1)).findById(1L);
    }

    // Similar tests for createTodo, updateTodo, and deleteTodo methods
}
