package by.bsuir.patternslab.service.specification.sortspec;

import by.bsuir.patternslab.entity.Book;
import by.bsuir.patternslab.entity.comparison.BookAuthorComparator;
import by.bsuir.patternslab.entity.comparison.PublicationTitleComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SortBookAuthorCriteria implements SortCriteria<Book> {
    @Override
    public List<Book> sort(Set<Book> objs) {
        List<Book> sortedBooks = new ArrayList<>(objs);
        sortedBooks.sort(new BookAuthorComparator().thenComparing(new PublicationTitleComparator()));
        return sortedBooks;
    }
}
