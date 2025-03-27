package org.root.cast;

public class ClickEvent extends Event{
    void clickAction() {
        System.out.println("Performing click action");
    }

    @Override
    void trigger() {
        System.out.println("Performing click trigger");
    }
}
