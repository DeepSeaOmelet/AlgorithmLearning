package com.test.jie.learn.threadLearn;

import java.util.Random;

public class SimpleMultipleThing {
    public static void main(String[] args) {
        Object locker = new Object();
        SimpleThreading myThread1 = new SimpleThreading("q1", locker);
        myThread1.start();
        Thread myThread2 = new Thread(new AnotherSimpleThread("John Wish", locker));
        for (int i = 0; i < 3; i++) {
            myThread2 = new Thread(new AnotherSimpleThread("John Wish", locker));
            myThread2.start();
            try {
                myThread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread myThread3 = new Thread(new AnotherWaitingSimpleThread(myThread2));
        AnotherWaitingSimpleThread myThread4 = new AnotherWaitingSimpleThread(myThread2);
//        System.out.println(Thread.currentThread().getName());
        myThread3.start();
        Random ran = new Random();
        StringBuilder waitingWord = new StringBuilder("say:");
        while (myThread3.isAlive()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitingWord.append((char) ran.nextInt());
        }
        myThread4.setSayWord(waitingWord.toString());
        myThread4.start();

//        throw new RuntimeException("yeee");
    }
}

class SimpleThreading extends Thread {
    static int count;
    Object lock;

    public SimpleThreading(String name, Object lock) {
        super(name);
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            for (int i = 5; i > 0; i--) {
                System.out.println(i + " in " + Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}


class AnotherSimpleThread implements Runnable {
    String name;
    Object lock;

    public AnotherSimpleThread(String name, Object lock) {
        this.name = name;
        this.lock = lock;
    }

    @Override
    public void run() {
        Random ran = new Random();
        System.out.println("waiting for lock");
        synchronized (lock) {
            System.out.print("[");
            for (int i = 0; i < 35; i++) {
                System.out.print("■");
                try {
                    Thread.sleep(ran.nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("]");
        }
    }
}

class AnotherWaitingSimpleThread extends Thread {
    private Thread waitingFor;
    private String sayWord = "HELLO?";

    public AnotherWaitingSimpleThread(Thread thread) {
        this.waitingFor = thread;
    }

    public void setSayWord(String word) {
        this.sayWord = word;
    }

    @Override
    public void run() {
        try {
            waitingFor.join();
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sayWord);
    }
}