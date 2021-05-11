package by.bsuir.patternslab.view;

import by.bsuir.patternslab.controller.PublicationController;
import by.bsuir.patternslab.entity.PublicationType;
import by.bsuir.patternslab.entity.PublishingHouse;

import java.util.*;

public class AddPublicationCommand implements Command {
    private final ResourceBundle resourceBundle;
    private final PublicationController controller;

    public AddPublicationCommand(ResourceBundle resourceBundle, PublicationController controller) {
        this.resourceBundle = resourceBundle;
        this.controller = controller;
    }

    public String execute() {
        int choice;
        String title;
        List<String> authors = new ArrayList<>();
        int numberOfPages;
        String publishingHouse;
        int yearOfPublishing;
        PublicationType type;

        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        System.out.println(resourceBundle.getString("typeOfPublicationEntryMessage"));
        choice = scanner.nextInt();
        if (choice == 1) {
            type = PublicationType.BOOK;
        } else if (choice == 2) {
            type = PublicationType.MAGAZINE;
        } else {
            return resourceBundle.getString("isNoItemMessage");
        }
        System.out.println(resourceBundle.getString("bookTitleEntryMessage"));
        title = scanner.next();
        if (type == PublicationType.BOOK)
            System.out.println(resourceBundle.getString("bookAuthorsEntryMessage"));
        else
            System.out.println(resourceBundle.getString("magazineEditorsEntryMessage"));
        String[] auth = scanner.next().split(",");
        Collections.addAll(authors, auth);
        System.out.println(resourceBundle.getString("bookNumberOfPagesEntryMessage"));
        numberOfPages = scanner.nextInt();

        System.out.println(resourceBundle.getString("bookPublishingHouseEntryMessage") + "\n" + PublishingHouse.printAllName());
        publishingHouse = scanner.next();

        System.out.println(resourceBundle.getString("bookYearOfPublishingEntryMessage"));
        yearOfPublishing = scanner.nextInt();

        return controller.addPublication(type, title, numberOfPages, publishingHouse, yearOfPublishing, authors);
    }
}
