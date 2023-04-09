package com.test.jie.learn.threadLearn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class VolatoleAtomicityDemo {
    public AtomicInteger inc = new AtomicInteger();
    public void increase() {
        inc.getAndIncrement();
    }
//    private ReentrantLock lock = new ReentrantLock();
//    public void increase() {
//        lock.lock();
//        try {
//            inc++;
//        } finally {
//            lock.unlock();
//        }
//    }

//    public volatile static int inc = 0;
//    public volatile static int inc = 0;
//    public synchronized void increase(){
//        inc++;
//    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        VolatoleAtomicityDemo volatoleAtomicityDemo = new VolatoleAtomicityDemo();
        for (int i = 0; i < 5; i++) {
            threadPool.execute(() -> {
                for (int j = 0; j < 500; j++) {
                    volatoleAtomicityDemo.increase();
                }

            });
        }
        //等待1.5秒
        Thread.sleep(1500);
        System.out.println(volatoleAtomicityDemo.inc.get());
        threadPool.shutdown();
    }
}
