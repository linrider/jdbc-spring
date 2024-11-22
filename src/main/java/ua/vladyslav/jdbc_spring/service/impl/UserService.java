package ua.vladyslav.jdbc_spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ua.vladyslav.jdbc_spring.model.User;
import ua.vladyslav.jdbc_spring.service.CrudService;

@Service
@RequiredArgsConstructor
public class UserService implements CrudService<User, Integer> {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    @SuppressWarnings("deprecation")
    public User getById(Integer id) {
        return jdbcTemplate
                .query("SELECT * FROM users WHERE id=?", new Object[] { id },
                        new BeanPropertyRowMapper<>(User.class))
                .stream()
                .findAny()
                .orElseThrow();
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update("INSERT INTO users (name, username, age, email, address) VALUES (?, ?, ?, ?, ?)",
                user.getName(), user.getUsername(), user.getAge(), user.getEmail(), user.getAddress());
    }

    @Override
    public void update(Integer id, User user) {
        jdbcTemplate.update("UPDATE users SET name=?, username=?, age=?, email=?, address=? WHERE id=?",
                user.getName(), user.getUsername(), user.getAge(), user.getEmail(), user.getAddress(), id);
    }

    @Override
    public void deleteById(Integer id) {
        jdbcTemplate.update("DELETE FROM users WHERE id=?", id);
    }

    @SuppressWarnings("deprecation")
    public Optional<User> findByUsername(String username) {
        return jdbcTemplate.query("SELECT * FROM users WHERE username=?", new Object[] { username },
                new BeanPropertyRowMapper<>(User.class))
                .stream()
                .findAny();

    }

    @SuppressWarnings("deprecation")
    public Optional<User> findByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM users WHERE username=?", new Object[] { email },
                new BeanPropertyRowMapper<>(User.class))
                .stream()
                .findAny();
    }

}
