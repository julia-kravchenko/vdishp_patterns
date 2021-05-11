package by.bsuir.patternslab.entity;

public enum PublishingHouse {
    PETER("Питер"),
    EXMO("Эксмо"),
    ROSMAN("Росмэн"),
    ACT("АСТ"),
    MACHAON("Махаон");

    private final String name;

    private PublishingHouse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static PublishingHouse getByName(String aName) {
        for (PublishingHouse current : PublishingHouse.values()) {
            if (current.getName().equalsIgnoreCase(aName.trim())) {
                return current;
            }
        }
        return null;
    }

    public static String printAllName() {
        StringBuilder result = new StringBuilder();
        for (PublishingHouse current : PublishingHouse.values()) {
            result.append(current.name).append("\n");
        }
        return result.toString();
    }
}
