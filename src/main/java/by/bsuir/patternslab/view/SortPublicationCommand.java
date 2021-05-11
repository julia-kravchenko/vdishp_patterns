package by.bsuir.patternslab.view;

import by.bsuir.patternslab.controller.PublicationController;
import by.bsuir.patternslab.controller.Validator;

import java.util.ResourceBundle;
import java.util.Scanner;

public class SortPublicationCommand implements Command {
    private final ResourceBundle resourceBundle;
    private final PublicationController controller;

    public SortPublicationCommand(ResourceBundle resourceBundle, PublicationController controller) {
        this.resourceBundle = resourceBundle;
        this.controller = controller;
    }

    public String execute() {
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println(resourceBundle.getString("sortMenuMessage"));
            String possibleChoice = sc.next();
            if (Validator.isInt(possibleChoice)) {
                choice = Integer.parseInt(possibleChoice);
            } else {
                System.out.println("Invalid input!");
            }
            switch (choice) {
                case 1:
                    System.out.println(controller.sortByTitle());
                    break;
                case 2:
                    System.out.println("BOOKS:\n" + controller.sortBookByAuthor());
                    System.out.println("MAGAZINES:\n" + controller.sortMagazinesByEditor());
                    break;
                case 3:
                    System.out.println(controller.sortByPublishingHouse());
                    break;
                case 4:
                    System.out.println(controller.sortByNumberOfPages());
                    break;
                case 5:
                    System.out.println(controller.sortByYearOfPublishing());
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
