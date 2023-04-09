package com.test.jie.learn;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorLearn {
    public static void main(String[] args) {
//        ExecutorService pool = Executors.newFixedThreadPool(20);
//            pool.execute(new Runnable() {
//                int count2 =0;
//                @Override
//                public void run() {
//                    int count =0;
//                    while (true) {
//                        System.out.println(count++);
//                        count2++;
//                        if (count>=20){
//                            System.out.println("结束"+count2);
//                            break;
//                        }
//                    }
//                }
//            });
//        pool.shutdown();

        ExeThread et = new ExeThread();
        Thread t1 = new Thread(et);
        Thread t2 = new Thread(et);
        t1.start();
        t2.start();
    }
}
class ExeThread implements Runnable{
//    static int count3 = 0;
    int count2 =0;
    @Override
    public void run() {
        int count =0;
        while (true) {
            System.out.println(count++);
            count2++;
//            count3++;
            new Person().buy();
            if (count>=5){
                System.out.println("结束"+count2);
                Person p = new Person();
                p.buy();
                System.out.println(p.getPiecesAndPrice());
                break;
            }
        }
    }
}
class Person {
    private int pieces;

    public void buy(){
        pieces++;
    }
    public int getPiecesAndPrice() {
        return pieces;
    }
}
