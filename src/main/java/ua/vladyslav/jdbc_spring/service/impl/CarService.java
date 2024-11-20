package ua.vladyslav.jdbc_spring.service.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ua.vladyslav.jdbc_spring.model.Car;
import ua.vladyslav.jdbc_spring.service.CrudService;

@Service
@RequiredArgsConstructor
public class CarService implements CrudService<Car, Integer> {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Car> getAll() {
        return jdbcTemplate.query("SELECT * FROM car", new BeanPropertyRowMapper<>(Car.class));
    }

    @Override
    @SuppressWarnings("deprecation")
    public Car getById(Integer id) {
        return jdbcTemplate
                .query("SELECT * FROM car WHERE id=?", new Object[] { id },
                        new BeanPropertyRowMapper<>(Car.class))
                .stream()
                .findAny()
                .orElseThrow();
    }

    @Override
    public void save(Car car) {
        jdbcTemplate.update("INSERT INTO car (producer, model, manufacture_year, origin) " +
                "VALUES (?, ?, ?, ?)",
                car.getProducer(),
                car.getModel(),
                car.getManufactureYear(),
                car.getOrigin());
    }

    @Override
    public void update(Integer id, Car car) {
        jdbcTemplate.update("UPDATE car SET producer=?, model=?, manufacture_year=?, origin=? " +
                "WHERE id=?",
                car.getProducer(),
                car.getModel(),
                car.getManufactureYear(),
                car.getOrigin(),
                id);
    }

    @Override
    public void deleteById(Integer id) {
        jdbcTemplate.update("DELETE FROM car WHERE id=?", id);
    }

}
