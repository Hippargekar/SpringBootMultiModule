package org.root.decorator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DecoratorTest {

    public static void main(String[] args) {
        String text = "Hello, world!";
        TextFormatter plainFormatter = new PlainTextFormatter();
        System.out.println(plainFormatter.format(text));

        TextFormatter boldFormatter = new BoldTextDecorator(plainFormatter);
        String boldText = boldFormatter.format(text);
        System.out.println(boldText);

        Map<String, String> data  = new HashMap<>();
    }
}
