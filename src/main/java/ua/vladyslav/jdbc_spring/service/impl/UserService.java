package ua.vladyslav.jdbc_spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ua.vladyslav.jdbc_spring.model.Car;
import ua.vladyslav.jdbc_spring.model.User;
import ua.vladyslav.jdbc_spring.service.CrudService;

@Service
@RequiredArgsConstructor
public class UserService implements CrudService<User, Integer> {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public User getById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public void save(User t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void update(Integer id, User t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @SuppressWarnings("deprecation")
    public Optional<User> findByUsername(String username) {
        return jdbcTemplate.query("SELECT * FROM usr WHERE username=?", new Object[] { username },
                new BeanPropertyRowMapper<>(User.class))
                .stream()
                .findAny();

    }@SuppressWarnings("deprecation")
    public Optional<User> findByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM usr WHERE username=?", new Object[] { email },
                new BeanPropertyRowMapper<>(User.class))
                .stream()
                .findAny();

    }

}
