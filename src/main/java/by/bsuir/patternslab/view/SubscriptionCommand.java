package by.bsuir.patternslab.view;

import by.bsuir.patternslab.controller.SubscriberController;
import by.bsuir.patternslab.controller.Validator;
import by.bsuir.patternslab.entity.PublicationType;

import java.util.ResourceBundle;
import java.util.Scanner;

public class SubscriptionCommand implements Command {
    private final ResourceBundle resourceBundle;
    private final SubscriberController controller;

    public SubscriptionCommand(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
        controller = new SubscriberController(resourceBundle);
    }

    public String execute() {
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        do {
            System.out.println(resourceBundle.getString("subscriptionMenuMessage"));
            String possibleChoice = sc.next();
            if (Validator.isInt(possibleChoice)) {
                choice = Integer.parseInt(possibleChoice);
            } else {
                System.out.println("Invalid input!");
            }
            switch (choice) {
                case 1:
                    System.out.println(subscribe());
                    break;
                case 2:
                    System.out.println(resourceBundle.getString("nameEntryMessage"));
                    String name = sc.next();
                    System.out.println(controller.unsubscribe(name));
                    break;
                case 3:
                    System.out.println(resourceBundle.getString("nameEntryMessage"));
                    name = sc.next();
                    System.out.println(controller.viewRepositoryStatus(name));
                    break;
                case 0:
                    break;
                default:
                    System.out.println(resourceBundle.getString("isNoItemMessage"));
            }
        } while (choice != 0);
        return "";
    }

    private String subscribe() {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        String name;
        PublicationType type;
        int yearOfPublishing;
        System.out.println(resourceBundle.getString("nameEntryMessage"));
        name = scanner.next();
        System.out.println(resourceBundle.getString("typeOfPublicationSubMessage"));
        int choice = scanner.nextInt();
        if (choice == 1) {
            type = PublicationType.BOOK;
        } else if (choice == 2) {
            type = PublicationType.MAGAZINE;
        } else {
            return resourceBundle.getString("isNoItemMessage");
        }
        System.out.println(resourceBundle.getString("yearOfPublishingSubMessage"));
        yearOfPublishing = scanner.nextInt();

        return controller.subscribe(name, type, yearOfPublishing);
    }
}
