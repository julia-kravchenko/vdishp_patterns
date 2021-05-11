package by.bsuir.patternslab.entity.observer;

import by.bsuir.patternslab.entity.PublicationType;

public class SubscriberDecorator extends Subscriber {
    private static final String GIFT = "Поздравляю! Вы получаете книгу бесплатно!\n";

    public SubscriberDecorator(String name) {
        super(name);
        System.out.println(name + "! " + GIFT);
    }

    public SubscriberDecorator(String name, PublicationType publicationType, Integer yearOfPublishing) {
        super(name, publicationType, yearOfPublishing);
        System.out.println(name + "! " + GIFT);
    }

    public SubscriberDecorator(String name, Integer numberOfPublications, PublicationType publicationType, Integer yearOfPublishing) {
        super(name, numberOfPublications, publicationType, yearOfPublishing);
        System.out.println(name + "! " + GIFT);
    }
}
