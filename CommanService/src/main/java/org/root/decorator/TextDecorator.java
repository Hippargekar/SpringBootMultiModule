package org.root.decorator;

abstract class TextDecorator implements TextFormatter {
    private TextFormatter textFormatter;

    public TextDecorator(TextFormatter textFormatter) {
        this.textFormatter = textFormatter;
    }

    @Override
    public String format(String text) {
        return textFormatter.format(text);
    }
}
