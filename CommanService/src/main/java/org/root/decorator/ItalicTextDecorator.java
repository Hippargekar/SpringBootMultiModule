package org.root.decorator;

public class ItalicTextDecorator extends TextDecorator {
    public ItalicTextDecorator(TextFormatter textFormatter) {
        super(textFormatter);
    }

    @Override
    public String format(String text) {
        String formattedText = super.format(text);
        return "<i>" + formattedText + "</i>";
    }
}
