package org.root.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class Singleton implements Serializable, Cloneable{
    private static volatile Singleton instance;
    private Singleton(){
//        if (instance != null) {
            throw new RuntimeException("Instance already created.");
//        }
    }

    public static Singleton getInstance(){
        if (instance == null) {
            synchronized (Singleton.class){
                if (instance==null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    @Override// method to prevent creating a new instance during cloning
    protected Singleton clone() throws CloneNotSupportedException {
        return instance;
    }
    // method to prevent creating a new instance during deserialization
    protected Object readResolve() throws ObjectStreamException {
        return instance;
    }

}
