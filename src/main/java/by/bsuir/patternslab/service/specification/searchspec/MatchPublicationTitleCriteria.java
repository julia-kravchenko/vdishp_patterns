package by.bsuir.patternslab.service.specification.searchspec;


import by.bsuir.patternslab.entity.Publication;

public class MatchPublicationTitleCriteria implements SearchCriteria<Publication> {
    private final String searchTitle;

    public MatchPublicationTitleCriteria(String searchTitle) {
        this.searchTitle = searchTitle;
    }

    @Override
    public boolean search(Publication obj) {
        return obj.getTitle().toUpperCase().contains(searchTitle.toUpperCase());
    }
}
