package org.root.decorator;

public class BoldTextDecorator extends TextDecorator {
    public BoldTextDecorator(TextFormatter textFormatter) {
        super(textFormatter);
    }

    @Override
    public String format(String text) {
        String formattedText = super.format(text);
        return "<b>" + formattedText + "</b>";
    }
}
