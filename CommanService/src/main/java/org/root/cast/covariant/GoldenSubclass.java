package org.root.cast.covariant;

public class GoldenSubclass extends Subclass{
    @Override
    public Subclass getMe() {
        return this;
    }
}
