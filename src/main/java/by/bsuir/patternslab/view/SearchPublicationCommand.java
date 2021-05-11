package by.bsuir.patternslab.view;

import by.bsuir.patternslab.controller.PublicationController;
import by.bsuir.patternslab.controller.Validator;
import by.bsuir.patternslab.entity.PublishingHouse;

import java.util.*;

public class SearchPublicationCommand implements Command {
    private final ResourceBundle resourceBundle;
    private final PublicationController controller;

    public SearchPublicationCommand(ResourceBundle resourceBundle, PublicationController controller) {
        this.resourceBundle = resourceBundle;
        this.controller = controller;
    }

    public String execute() {
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        do {
            System.out.println(resourceBundle.getString("searchMenuMessage"));
            String possibleChoice = sc.next();
            if (Validator.isInt(possibleChoice)) {
                choice = Integer.parseInt(possibleChoice);
            } else {
                System.out.println("Invalid input!");
            }
            switch (choice) {
                case 1:
                    System.out.println(resourceBundle.getString("bookIsbnEntryMessage"));
                    Integer isbn = sc.nextInt();
                    System.out.println(controller.findByIsbn(isbn));
                    break;
                case 2:
                    System.out.println(resourceBundle.getString("bookTitleEntryMessage"));
                    String title = sc.next();
                    System.out.println(controller.findByTitle(title));
                    break;
                case 3:
                    System.out.println(resourceBundle.getString("bookAuthorsEntryMessage"));
                    String[] auth = sc.next().split(",");
                    List<String> authors = new ArrayList<>();
                    Collections.addAll(authors, auth);
                    System.out.println(controller.findByAuthor(authors));
                    break;
                case 4:
                    System.out.println(resourceBundle.getString("bookPublishingHouseEntryMessage") + "\n" + PublishingHouse.printAllName());
                    String publishingHouse = sc.next();
                    System.out.println(controller.findByPublishingHouse(publishingHouse));
                    break;
                case 5:
                    System.out.println(resourceBundle.getString("bookTitleEntryMessage"));
                    title = sc.next();
                    System.out.println(resourceBundle.getString("bookPublishingHouseEntryMessage"));
                    publishingHouse = sc.next();
                    System.out.println(controller.findByTitleAndPublishingHouse(title, publishingHouse));
                    break;
                case 6:
                    System.out.println(resourceBundle.getString("bookTitleEntryMessage"));
                    title = sc.next();
                    System.out.println(resourceBundle.getString("bookYearOfPublishingEntryMessage"));
                    Integer year = sc.nextInt();
                    System.out.println(controller.findByTitleAndYearOfPublishing(title, year));
                    break;
                case 0:
                    break;
                default:
                    System.out.println(resourceBundle.getString("isNoItemMessage"));
            }
        } while (choice != 0);
        return "";
    }
}
