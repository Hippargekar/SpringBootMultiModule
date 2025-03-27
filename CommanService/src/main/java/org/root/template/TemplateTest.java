package org.root.template;

public class TemplateTest {
    public static void main(String[] args) {
        var thief = new HalflingThief(new HitAndRunMethod());
        thief.steal();
//        thief.changeMethod(new SubtleMethod());
//        thief.steal();
    }
}
