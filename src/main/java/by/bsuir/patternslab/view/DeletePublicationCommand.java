package by.bsuir.patternslab.view;

import by.bsuir.patternslab.controller.PublicationController;

import java.util.ResourceBundle;
import java.util.Scanner;

public class DeletePublicationCommand implements Command {
    private final ResourceBundle resourceBundle;
    private final PublicationController controller;

    public DeletePublicationCommand(ResourceBundle resourceBundle, PublicationController controller) {
        this.resourceBundle = resourceBundle;
        this.controller = controller;
    }

    public String execute() {
        System.out.println(resourceBundle.getString("bookIsbnEntryMessage"));
        Integer isbn = new Scanner(System.in).nextInt();
        return controller.deletePublication(isbn);
    }
}
