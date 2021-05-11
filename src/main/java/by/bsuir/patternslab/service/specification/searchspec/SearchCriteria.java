package by.bsuir.patternslab.service.specification.searchspec;

public interface SearchCriteria<T> {
    boolean search(T obj);
}
