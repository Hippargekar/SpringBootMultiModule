package org.root.concurrency.condtions;

import org.root.concurrency.colla.HttpRequestTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

record ConditionDemoNotify(Lock lock, Condition condition) implements Runnable {

    @Override
    public void run() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "start ConditionDemoNotify");
        try {
            condition.signal();
            System.out.println(Thread.currentThread().getName() + "sleep ConditionDemoNotify");
            HttpRequestTest.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "end ConditionDemoNotify");
        } finally {
            lock.unlock();
        }
    }
}

