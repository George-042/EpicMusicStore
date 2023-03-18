package by.georgprog.epicmusicstore.service;

import java.util.List;
import java.util.Optional;

public interface DaoService<T> {

    List<T> getAll();

    Optional<T> getById(long id);

    void deleteById(T dto);

    void save(T dto);
}
