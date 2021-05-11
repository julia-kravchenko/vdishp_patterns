package by.bsuir.patternslab.service.specification.searchspec;


import by.bsuir.patternslab.entity.Publication;

public class MatchPublicationYearOfPublishingCriteria implements SearchCriteria<Publication> {
    private final Integer searchYearOfPublishing;

    public MatchPublicationYearOfPublishingCriteria(Integer searchYearOfPublishing) {
        this.searchYearOfPublishing = searchYearOfPublishing;
    }

    @Override
    public boolean search(Publication obj) {
        return searchYearOfPublishing.equals(obj.getYearOfPublishing());
    }
}
