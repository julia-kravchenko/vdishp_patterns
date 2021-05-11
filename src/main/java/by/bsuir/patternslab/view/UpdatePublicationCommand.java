package by.bsuir.patternslab.view;

import by.bsuir.patternslab.controller.PublicationController;
import by.bsuir.patternslab.controller.Validator;

import java.util.ResourceBundle;
import java.util.Scanner;

public class UpdatePublicationCommand implements Command {
    private final ResourceBundle resourceBundle;
    private final PublicationController controller;

    public UpdatePublicationCommand(ResourceBundle resourceBundle, PublicationController controller) {
        this.resourceBundle = resourceBundle;
        this.controller = controller;
    }

    public String execute() {
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        do {
            System.out.println(resourceBundle.getString("updateMenuMessage"));
            String possibleChoice = sc.next();
            if (Validator.isInt(possibleChoice)) {
                choice = Integer.parseInt(possibleChoice);
            } else {
                System.out.println("Invalid input!");
            }
            int isbn;
            String str;
            int year;
            switch (choice) {
                case 1:
                    System.out.println(resourceBundle.getString("bookIsbnEntryMessage"));
                    isbn = sc.nextInt();
                    System.out.println(resourceBundle.getString("bookTitleEntryMessage"));
                    str = sc.next();
                    System.out.println(controller.updateTitlePublication(isbn, str));
                    break;
                case 2:
                    System.out.println(resourceBundle.getString("bookIsbnEntryMessage"));
                    isbn = sc.nextInt();
                    System.out.println(resourceBundle.getString("bookYearOfPublishingEntryMessage"));
                    year = sc.nextInt();
                    System.out.println(controller.updateYearOfPublication(isbn, year));
                    break;
                case 3:
                    System.out.println(resourceBundle.getString("bookIsbnEntryMessage"));
                    isbn = sc.nextInt();
                    System.out.println(resourceBundle.getString("bookPublishingHouseEntryMessage"));
                    str = sc.next();
                    System.out.println(controller.updatePublishingHouse(isbn, str));
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
