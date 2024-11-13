package ua.vladyslav.jdbc_spring.service;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ua.vladyslav.jdbc_spring.model.Person;

@RequiredArgsConstructor
@Service
public class PersonService implements CrudService<Person, Integer> {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Person> getAll() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    @Override
    @SuppressWarnings("deprecation")
    public Person getById(Integer id) {
        return jdbcTemplate
                .query("SELECT * FROM person WHERE id=?", new Object[] { id },
                        new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findAny()
                .orElseThrow();
    }

    @Override
    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person (name, age) VALUES (?, ?)", person.getName(), person.getAge());
    }

    @Override
    public void update(Integer id, Person person) {
        jdbcTemplate.update("UPDATE person SET name=?, age=? WHERE id=?", person.getName(), person.getAge(), id);
    }

    @Override
    public void deleteById(Integer id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }
}
