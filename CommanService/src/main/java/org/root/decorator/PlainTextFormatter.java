package org.root.decorator;

public class PlainTextFormatter implements TextFormatter {

    @Override
    public String format(String text) {
        return "plain";
    }
}
