package ru.itis.rasimusv.repositories;

import ru.itis.rasimusv.models.User;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {
    void save(T entity);
    void delete(ID id);
    void update(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    List<User> findAll(int page, int size);
}
