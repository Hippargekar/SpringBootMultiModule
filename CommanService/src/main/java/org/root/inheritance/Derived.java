package org.root.inheritance;

public class Derived extends Base{

    public Derived() {
        System.out.println("Derived: "+ index);
        this.incrementIndex();
    }

    @Override
    public void incrementIndex() {
        System.out.println("Derived before: "+ index);
        index = index + 20;
        System.out.println("Derived after: "+ index);
    }


}
