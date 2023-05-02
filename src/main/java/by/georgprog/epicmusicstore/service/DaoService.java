package by.georgprog.epicmusicstore.service;

import java.util.List;
import java.util.Optional;

public interface DaoService<T> {

    List<T> findAll();

    Optional<T> findById(long id);

    void deleteById(long id);

    void save(T entity);
}
