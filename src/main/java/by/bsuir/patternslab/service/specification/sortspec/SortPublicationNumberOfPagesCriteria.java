package by.bsuir.patternslab.service.specification.sortspec;

import by.bsuir.patternslab.entity.Publication;
import by.bsuir.patternslab.entity.comparison.PublicationNumberOfPagesComparator;
import by.bsuir.patternslab.entity.comparison.PublicationTitleComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SortPublicationNumberOfPagesCriteria implements SortCriteria<Publication> {
    @Override
    public List<Publication> sort(Set<Publication> objs) {
        List<Publication> sortedBooks = new ArrayList<>(objs);
        sortedBooks.sort(new PublicationNumberOfPagesComparator().thenComparing(new PublicationTitleComparator()));
        return sortedBooks;
    }
}
