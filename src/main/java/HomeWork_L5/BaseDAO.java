package HomeWork_L5;

import java.util.List;

public interface BaseDAO<T, K> {
    void insert(T student);
    void delete(Class<T> student, K id);
    void update(T student);
    T findById(Class<T> student, K id);
    List<T> findAll();
}
