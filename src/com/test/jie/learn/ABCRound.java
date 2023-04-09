package com.test.jie.learn;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ABCRound {
    /*
     0-A, 1-B, 2-C
     */

    private static int count = 0;
    private static final Integer target = 100;
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Condition CA = lock.newCondition();
        Condition CB = lock.newCondition();
        Condition CC = lock.newCondition();
        PrintRunner PA = new PrintRunner(lock, "A", CA, CB);
        PrintRunner PB = new PrintRunner(lock, "B", CB, CC);
        PrintRunner PC = new PrintRunner(lock, "C", CC, CA);
        new Thread(PA).start();
        new Thread(PB).start();
        new Thread(PC).start();
//
//        Thread t1 = new Thread(()->{
//           while (true){
//               lock.lock();
//               if (++count<=100){
//                   System.out.println("A");
//               }else break;
//               CB.signal();
//               try {
//                   CA.await();//与此Condition关联的锁被自动释放
//               } catch (InterruptedException e) {
//                   e.printStackTrace();
//               }
////               System.out.println("A before lock.unlock()");
//               lock.unlock();
////               System.out.println("A after lock.unlock()");
//           }
//
//        });
//        Thread t2 = new Thread(()->{
//           while (true){
//               lock.lock();
//               if (++count<=100){
//                   System.out.println("B");
//               }else break;
//               CC.signal();
//               try {
//                   CB.await();
//               } catch (InterruptedException e) {
//                   e.printStackTrace();
//               }
//               lock.unlock();
//           }
//
//        });
//        Thread t3 = new Thread(()->{
//           while (true){
//               lock.lock();
//               if (++count<=100){
//                   System.out.println("C");
//               }else break;
//               CA.signal();
//               try {
//                   CC.await();
//               } catch (InterruptedException e) {
//                   e.printStackTrace();
//               }
//               lock.unlock();
//           }
//
//        });
//
//        t1.start();
//        t2.start();
//        t3.start();

    }

    static class PrintRunner implements Runnable {
        private static Integer threadNums = 0;
        private ReentrantLock reentrantLock;
        private Condition curCondition;
        private Condition nextCondition;
        private String name;
        private Integer index;

        @Override
        public void run() {
            while (true) {
                lock.lock();
                if (++count <= target) {
                    System.out.println(name);
                } else break;
                nextCondition.signal();
                try {
                    curCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
            threadNums--;
        }

        public PrintRunner(ReentrantLock reentrantLock, String name, Condition curCondition, Condition nextCondition) {
            threadNums++;
            this.reentrantLock = reentrantLock;
            this.curCondition = curCondition;
            this.nextCondition = nextCondition;
            this.name = name;
        }
    }
}
