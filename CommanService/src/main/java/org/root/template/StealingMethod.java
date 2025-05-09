package org.root.template;

public abstract class StealingMethod {
    protected abstract String pickTarget();
    protected abstract void confuseTarget(String target);
    protected abstract void stealTheItem(String target);

    //the template method should be declared as final
    public final void steal() {
        begin();
        var target = pickTarget();
        confuseTarget(target);
        stealTheItem(target);
        end();
    }
    public void begin() {
        System.out.println("**************************Begin*************************** ");
    }
    public void end() {
        System.out.println("**************************End*************************** ");
    }
}
