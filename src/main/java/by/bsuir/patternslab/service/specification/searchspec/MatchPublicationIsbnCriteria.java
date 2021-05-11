package by.bsuir.patternslab.service.specification.searchspec;

import by.bsuir.patternslab.entity.Publication;

public class MatchPublicationIsbnCriteria implements SearchCriteria<Publication> {
    private final Integer searchIsbn;

    public MatchPublicationIsbnCriteria(Integer searchIsbn) {
        this.searchIsbn = searchIsbn;
    }

    @Override
    public boolean search(Publication obj) {
        return searchIsbn.equals(obj.getIsbn());
    }
}
