package ua.vladyslav.jdbc_spring.service;

import java.util.List;

public interface CrudService<T, ID> {
    public List<T> getAll();

    public T getById(ID id);

    public void save(T t);

    public void update(ID id, T t);

    public void deleteById(ID id);
}