package org.root.cast.covariant;

public class Subclass extends Superclass{
    @Override
    public Subclass getMe() {
        return this;
    }
}
