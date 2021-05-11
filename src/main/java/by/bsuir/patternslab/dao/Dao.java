package by.bsuir.patternslab.dao;

import java.util.Set;

public interface Dao<T> {
    boolean save(Set<T> objs);

    Set<T> load();

    boolean delete(Set<T> objs);
}
