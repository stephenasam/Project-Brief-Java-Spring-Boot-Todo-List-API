package com.example.todosapi.controller;

import com.example.todosapi.model.Todo;
import com.example.todosapi.service.TodoService;

import com.example.todosapi.exception.TodoNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        return todoService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new TodoNotFoundException(id));
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.save(todo);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        return todoService.findById(id)
                .map(existingTodo -> {
                    existingTodo.setDescription(todo.getDescription());
                    existingTodo.setCompleted(todo.isCompleted());
                    todoService.update(existingTodo);
                    return ResponseEntity.ok(existingTodo);
                })
                .orElseThrow(() -> new TodoNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        if (todoService.findById(id).isPresent()) {
            todoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new TodoNotFoundException(id);
        }
    }

    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<String> handleTodoNotFound(TodoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}