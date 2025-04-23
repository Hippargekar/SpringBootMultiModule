package org.root.strategy;

public interface CompressStrategy {
    boolean compress(String source, String to);
    boolean uncompress(String source, String to);
}
