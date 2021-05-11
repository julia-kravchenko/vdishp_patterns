package by.bsuir.patternslab.entity.comparison;

import by.bsuir.patternslab.entity.Magazine;

import java.io.Serializable;
import java.util.Comparator;

public class MagazineEditorComparator implements Comparator<Magazine>, Serializable {
    @Override
    public int compare(Magazine o1, Magazine o2) {
        return o1.getChiefEditors().get(0).compareTo(o2.getChiefEditors().get(0));
    }
}
