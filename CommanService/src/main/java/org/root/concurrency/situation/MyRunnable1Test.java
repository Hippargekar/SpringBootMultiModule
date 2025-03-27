package org.root.concurrency.situation;

public class MyRunnable1Test {
    public static void main(String[] args) {

        //Suppose you have 2 threads (Thread-1 on object1 and Thread-2 on object2).
        new Thread(new MyRunnable1(),"Thread-1").start();
       // Thread.sleep(10);//Just to ensure Thread-1 starts before Thread-2
        new Thread(new MyRunnable1(),"Thread-2").start();

        //Suppose you have 2 threads (Thread-1 and Thread-2) on same object
//        MyRunnable1 myRunnable1=new MyRunnable1();
//        new Thread(myRunnable1,"Thread-1").start();
//        new Thread(myRunnable1,"Thread-2").start();

    }
}
