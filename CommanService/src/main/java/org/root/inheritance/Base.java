package org.root.inheritance;

public class Base {
    int index = 10;

    public Base() {
        System.out.println("Base: "+ index +" "+this.getClass());
        this.incrementIndex();
    }

    public void incrementIndex(){
        System.out.println("Base before: "+ index);
        index = index + 10;
        System.out.println("Base after: "+ index);
    }

    public int getIndex() {
        return index;
    }
}
