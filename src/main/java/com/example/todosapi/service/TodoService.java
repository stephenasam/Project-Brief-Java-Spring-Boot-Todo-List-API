package com.example.todosapi.service;

import com.example.todosapi.model.Todo;


import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<Todo> findAll();
    Optional<Todo> findById(Long id);
    Todo save(Todo todo);
    void update(Todo todo);
    void deleteById(Long id);
}