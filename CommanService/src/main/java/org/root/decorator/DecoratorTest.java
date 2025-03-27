package org.root.decorator;

public class DecoratorTest {

    public static void main(String[] args) {
        String text = "Hello, world!";
        TextFormatter plainFormatter = new PlainTextFormatter();
        System.out.println(plainFormatter.format(text));

        TextFormatter boldFormatter = new BoldTextDecorator(plainFormatter);
        String boldText = boldFormatter.format(text);
        System.out.println(boldText);
    }
}
