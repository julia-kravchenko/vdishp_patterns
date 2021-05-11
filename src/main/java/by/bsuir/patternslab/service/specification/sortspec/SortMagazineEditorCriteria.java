package by.bsuir.patternslab.service.specification.sortspec;

import by.bsuir.patternslab.entity.Magazine;
import by.bsuir.patternslab.entity.comparison.MagazineEditorComparator;
import by.bsuir.patternslab.entity.comparison.PublicationTitleComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SortMagazineEditorCriteria implements SortCriteria<Magazine> {
    @Override
    public List<Magazine> sort(Set<Magazine> objs) {
        List<Magazine> sortedBooks = new ArrayList<>(objs);
        sortedBooks.sort(new MagazineEditorComparator().thenComparing(new PublicationTitleComparator()));
        return sortedBooks;
    }
}
