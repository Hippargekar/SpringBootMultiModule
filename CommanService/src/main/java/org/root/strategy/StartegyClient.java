package org.root.strategy;

public class StartegyClient {
    public static void main(String[] args) {
        CompressContext context;
        context = new CompressContext(new ZipStrategy());
        context.compress("c:\\file", "d:\\file.zip");
        context.uncompress("c:\\file.zip", "d:\\file");
    }
}
