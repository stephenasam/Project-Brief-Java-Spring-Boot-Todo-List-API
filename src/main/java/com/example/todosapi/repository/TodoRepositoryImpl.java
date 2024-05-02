package com.example.todosapi.repository;

import com.example.todosapi.model.Todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import com.example.todosapi.repository.TodoRepository;

@Repository
public class TodoRepositoryImpl implements TodoRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TodoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Todo> findAll() {
        String sql = "SELECT id, description, completed FROM todos";
        return jdbcTemplate.query(sql, new TodoRowMapper());
    }

    @Override
    public Optional<Todo> findById(Long id) {
        String sql = "SELECT id, description, completed FROM todos WHERE id = ?";
        List<Todo> todos = jdbcTemplate.query(sql, new TodoRowMapper(), id);
        return todos.stream().findFirst();
    }

    @Override
    public Todo save(Todo todo) {
        String sql = "INSERT INTO todos (description, completed) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, todo.getDescription());
            ps.setBoolean(2, todo.isCompleted());
            return ps;
        }, keyHolder);
        todo.setId(keyHolder.getKey().longValue());
        return todo;
    }

    @Override
    public void update(Todo todo) {
        String sql = "UPDATE todos SET description = ?, completed = ? WHERE id = ?";
        jdbcTemplate.update(sql, todo.getDescription(), todo.isCompleted(), todo.getId());
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM todos WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class TodoRowMapper implements RowMapper<Todo> {
        @Override
        public Todo mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
            Todo todo = new Todo();
            todo.setId(rs.getLong("id"));
            todo.setDescription(rs.getString("description"));
            todo.setCompleted(rs.getBoolean("completed"));
            return todo;
        }
    }
}