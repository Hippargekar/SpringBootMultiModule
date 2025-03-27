package org.root.cast;

public class HoverEvent extends Event{
    void hoverAction() {
        System.out.println("Performing hover action");
    }

    @Override
    void trigger() {
        System.out.println("Performing hover trigger");
    }
}
