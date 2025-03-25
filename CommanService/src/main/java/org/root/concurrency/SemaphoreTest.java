package org.root.concurrency;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

//Practical Use Cases
//Semaphores are particularly useful in scenarios where you need to limit the number of concurrent accesses to a particular resource, such as:
//------------------------------
//Limiting database connections
//Controlling access to a shared file
//Managing network connections in a server
public class SemaphoreTest {



    // Creating a semaphore with 3 permits
    private static final Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) {
        // Creating and starting 4 threads
        int totalJobs = 4;
        for (int i = 1; i <= totalJobs; i++) {
            new WorkerThread("Worker " + i).start();
        }
    }

    static class WorkerThread extends Thread {
        private String name;

        WorkerThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
//            if(semaphore.tryAcquire(1000, TimeUnit.MILLISECONDS)) {
                try {
                    System.out.println(name + " is trying to acquire a permit...");
                    // Acquiring the semaphore
                    semaphore.acquire();
                    System.out.println(name + " acquired a permit.");

                    // Simulating work by sleeping
                    Thread.sleep(2000);

                    System.out.println(name + " is releasing a permit.");
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } finally {
                    // Releasing the semaphore
                    semaphore.release();
                }
//            }
        }
    }
}
//+ Char[] is a mutable data array. You can clear the password from the char array when you no longer need it.
//
//+ String is immutable, meaning that once you create a String object containing a password, you cannot change it. Any modification will create a new String object.


//        final int numberOfThreads = 3;
//        CyclicBarrier barrier = new CyclicBarrier(numberOfThreads, new BarrierAction());
//        class BarrierAction implements Runnable {
//            @Override
//            public void run() {
//                System.out.println("Barrier Action executed. All threads are released.");
//            }
//        }
//        Common Use Cases
//        Batch Processing: When processing data in batches, threads can use a CyclicBarrier to synchronize at the end of each batch.
//        Parallel Algorithms: In parallel algorithms, threads often need to synchronize after completing certain phases of computation.