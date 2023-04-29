package carsharing.dao;

import java.util.List;
import java.util.Optional;

public interface AbstractDAO <T> {
    Optional<T> getByName(String name);

    List<T> getAll();

    T create(T entity);

    boolean delete (Long id);

    boolean update (T entity);
}
