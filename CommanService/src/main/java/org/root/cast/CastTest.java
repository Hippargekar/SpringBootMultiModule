package org.root.cast;

public class CastTest {

    public static void main(String[] args) {
        // Superclass reference refers to subclass object.
        // Superclass reference refers = subclass object.
        Event event1 = new ClickEvent(); // Upcasting
        Event event2 = new HoverEvent(); // Upcasting
        //processEvent(event1);
        processEvent(event2);
    }

    static void processEvent(Event event) {
        event.trigger();
        if (event instanceof ClickEvent) {
            // Converting super class reference type into subclass reference type.
            ClickEvent clickEvent = (ClickEvent) event; // Downcasting
            // Calling clickAction method using reference variable of subclass.
            clickEvent.clickAction(); // Calls ClickEvent-specific method
        } else if (event instanceof HoverEvent) {
            HoverEvent hoverEvent = (HoverEvent) event; // Downcasting
            hoverEvent.hoverAction(); // Calls HoverEvent-specific method
        }
    }
}
