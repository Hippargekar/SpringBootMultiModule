package org.root.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        final int threadCount = 5;
        final ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);

        final CountDownLatch startSignal = new CountDownLatch(1);
        for (int i = 0; i < threadCount; i++) {
            threadPool.execute(new CountDownLatchWorker(startSignal));
        }

        TimeUnit.SECONDS.sleep(1);
        startSignal.countDown();      // Let all workers proceed
        threadPool.shutdown();
    }
}
