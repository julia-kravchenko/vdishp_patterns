package by.bsuir.patternslab.service.specification.sortspec;

import java.util.List;
import java.util.Set;

public interface SortCriteria<T> {
    List<T> sort(Set<T> objs);
}
