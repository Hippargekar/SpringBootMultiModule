package org.root.strategy;

public class GzipStrategy implements CompressStrategy{
    @Override
    public boolean compress(String source, String to) {
        return false;
    }

    @Override
    public boolean uncompress(String source, String to) {
        return false;
    }
}
