package org.root.concurrency;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchWorker implements Runnable {
    private final CountDownLatch startSignal;

    CountDownLatchWorker(CountDownLatch startSignal) {
        this.startSignal = startSignal;
    }

    @Override
    public void run() {
        try {
            System.out.println("Ready to start.");
            startSignal.await();
            doWork();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupted.");
        }
    }

    public void doWork() {
        System.out.println("Doing work.");
    }
}
