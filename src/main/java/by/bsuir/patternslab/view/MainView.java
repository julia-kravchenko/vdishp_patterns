package by.bsuir.patternslab.view;

import by.bsuir.patternslab.controller.PublicationController;
import by.bsuir.patternslab.controller.Validator;
import by.bsuir.patternslab.entity.Publication;
import by.bsuir.patternslab.service.adapter.Converter;
import by.bsuir.patternslab.service.adapter.XmlToJsonAdapter;
import by.bsuir.patternslab.utils.Constants;
import by.bsuir.patternslab.service.abstractfactory.DOMParser;
import by.bsuir.patternslab.service.abstractfactory.DomBookParser;
import by.bsuir.patternslab.service.abstractfactory.DomMagazineParser;
import by.bsuir.patternslab.service.abstractfactory.XMLHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MainView {
    private ResourceBundle resourceBundle;

    public void changeLocal() {
        Locale locale;
        if (resourceBundle.getLocale().getCountry().equals("")) {
            locale = new Locale("en", "US");
        } else {
            locale = new Locale("", "");
        }
        resourceBundle = ResourceBundle.getBundle("info", locale);
    }

    public void mainMenu() {
        Locale locale = new Locale("", "");
        resourceBundle = ResourceBundle.getBundle("info", locale);
        final PublicationController controller = new PublicationController(resourceBundle);

        int choice = -1;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println(resourceBundle.getString("mainMenuMessage"));
            String possibleChoice = sc.next();
            if (Validator.isInt(possibleChoice)) {
                choice = Integer.parseInt(possibleChoice);
            } else {
                System.out.println("Invalid input!");
            }
            Command command = null;
            switch (choice) {
                case 1:
                    command = new AddPublicationCommand(resourceBundle, controller);
                    break;
                case 2:
                    command = new DeletePublicationCommand(resourceBundle, controller);
                    break;
                case 3:
                    command = new UpdatePublicationCommand(resourceBundle, controller);
                    break;
                case 4:
                    printAll(resourceBundle, controller);
                    break;
                case 5:
                    command = new SearchPublicationCommand(resourceBundle, controller);
                    break;
                case 6:
                    command = new SortPublicationCommand(resourceBundle, controller);
                    break;
                case 7:
                    command = new SubscriptionCommand(resourceBundle);
                    break;
                case 8:
                    changeLocal();
                    break;
                case 9:
                    workWithXml();
                    Converter converter = new XmlToJsonAdapter();
                    converter.convertXmlToJson();
                    break;
                case 0:
                    break;
                default:
                    System.out.println(resourceBundle.getString("isNoItemMessage"));
            }
            if (command != null)
                System.out.print(command.execute());
            System.out.println();
        } while (choice != 0);
    }

    private void workWithXml() {
        DOMParser domBookXmlParser;
        DOMParser domMagazineXmlParser;
        List<Publication> domBookProducts;
        List<Publication> domMagazineProducts;

        try {
            domBookXmlParser = new DomBookParser(Constants.FILEDOM);
            domBookProducts = domBookXmlParser.parseProducts();
            if (domBookProducts != null) {
                System.out.println("DOMParser is worked");
            } else
                System.out.println("Возникла ошибка");
            domMagazineXmlParser = new DomMagazineParser(Constants.FILEDOM);
            domMagazineProducts = domMagazineXmlParser.parseProducts();
            if (domMagazineProducts != null) {
                System.out.println("DOMParser2 is worked");
            } else
                System.out.println("Возникла ошибка2");
            List<Publication> publications = domBookProducts;
            publications.addAll(domMagazineProducts);


            List<Publication> saxProducts;
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            XMLHandler handler = new XMLHandler();
            parser.parse((Constants.FILESAX), handler);
            saxProducts = handler.parseProducts();
            if (saxProducts != null) {
                System.out.println("\nSAXParser is worked");
                if (saxProducts.size() != 0) {
                    publications.addAll(saxProducts);
                }
            } else
                System.out.println("Возникла ошибка");

            for (Publication publication : publications)
                System.out.println(publication.toString());

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    private void printAll(ResourceBundle resourceBundle, PublicationController controller) {
        int choice = -1;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println(resourceBundle.getString("printAllMessage"));
            String possibleChoice = sc.next();
            if (Validator.isInt(possibleChoice)) {
                choice = Integer.parseInt(possibleChoice);
            } else {
                System.out.println("Invalid input!");
            }

            switch (choice) {
                case 1:
                    System.out.println(controller.printAllPublication());
                    break;
                case 2:
                    System.out.println(controller.printAllBooks());
                    break;
                case 3:
                    System.out.println(controller.printAllMagazines());
                    break;
                case 0:
                    break;
                default:
                    System.out.println(resourceBundle.getString("isNoItemMessage"));
            }
            System.out.println();
        } while (choice != 0);
    }
}
