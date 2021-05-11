package by.bsuir.patternslab.service.specification.searchspec;

import java.util.List;

public class ConjunctionCriteria<T> implements SearchCriteria<T> {
    private final List<SearchCriteria> criteriaList;

    public ConjunctionCriteria(List<SearchCriteria> criteriaList) {
        this.criteriaList = criteriaList;
    }

    @Override
    public boolean search(T obj) {
        for (SearchCriteria criteria : criteriaList) {
            if (!criteria.search(obj)) {
                return false;
            }
        }
        return true;
    }
}
