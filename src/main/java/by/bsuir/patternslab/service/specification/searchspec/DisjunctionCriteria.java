package by.bsuir.patternslab.service.specification.searchspec;

import java.util.List;

public class DisjunctionCriteria<T> implements SearchCriteria<T> {
    private final List<SearchCriteria> criteriaList;

    public DisjunctionCriteria(List<SearchCriteria> criteriaList) {
        this.criteriaList = criteriaList;
    }

    @Override
    public boolean search(T obj) {
        for (SearchCriteria criteria : criteriaList) {
            if (criteria.search(obj)) {
                return true;
            }
        }
        return false;
    }
}
