package org.root.cast.shadow;

public class HidingTest {
    public static void main(String[] args) {
        Parent p = new Parent();
        p.printName();//Parent
        System.out.println(p.name);//Parent

        Child ch = new Child();
        //ch.printName();//Child
        //System.out.println(ch.name);//Child

        // Superclass reference refers to subclass object.
        Parent pc = ch;//we did not create new object with 'new' keyword
        pc.printName();//Child
        //When an instance variable in a subclass has the same name as an instance variable in a super class, then the instance variable is chosen from the reference type.
        System.out.println(pc.name);//Parent
        if(pc == ch) {
            System.out.println("both are same");
        }
    }
}
