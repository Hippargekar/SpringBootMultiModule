package org.root.cast.shadow;

public class Child extends Parent{
    String name  = "Child";

    @Override
    void printName(){
        System.out.println(name);
    }
}
