package by.bsuir.patternslab.service.specification.searchspec;

import by.bsuir.patternslab.entity.Magazine;
import by.bsuir.patternslab.entity.Publication;

import java.util.List;

public class MatchMagazineEditorCriteria implements SearchCriteria<Publication> {
    private final List<String> searchEditors;

    public MatchMagazineEditorCriteria(List<String> editors) {
        this.searchEditors = editors;
    }

    @Override
    public boolean search(Publication obj) {
        Magazine magazine;
        if (!(obj instanceof Magazine)) {
            return false;
        }
        magazine = (Magazine) obj;
        for (String editor : magazine.getChiefEditors()) {
            for (String searchEditor : searchEditors) {
                if (editor.toUpperCase().contains(searchEditor.toUpperCase())) {
                    return true;
                }
            }
        }
        return false;
    }
}
