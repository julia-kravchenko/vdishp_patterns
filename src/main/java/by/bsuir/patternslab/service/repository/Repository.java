package by.bsuir.patternslab.service.repository;

import by.bsuir.patternslab.service.specification.searchspec.SearchCriteria;
import by.bsuir.patternslab.service.specification.sortspec.SortCriteria;

import java.util.List;
import java.util.Set;

public interface Repository<T> {
    boolean save(T obj);

    void load();

    Set<T> findAll();

    Set<T> find(SearchCriteria<T> searchCriteria);

    List<T> sort(SortCriteria<T> sortCriteria);

    boolean delete(T obj);

    boolean update(T obj);
}
