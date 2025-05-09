package org.root.strategy;

public class CompressContext {
    private CompressStrategy compressStrategy;

    public CompressContext(CompressStrategy compressStrategy) {
        this.compressStrategy = compressStrategy;
    }

    public boolean compress(String source, String to) {
        return compressStrategy.compress(source, to);
    }

    public boolean uncompress(String source, String to) {
        return compressStrategy.uncompress(source, to);
    }

    public void changeCompressStrategy(CompressStrategy compressStrategy) {
        this.compressStrategy = compressStrategy;
    }
}
