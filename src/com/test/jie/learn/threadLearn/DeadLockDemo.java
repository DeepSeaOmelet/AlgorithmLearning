package com.test.jie.learn.threadLearn;

public class DeadLockDemo {
    private static Object resource1 = new Object();
    private static Object resource2 = new Object();

    /**
     * 下⾯的例⼦符合产⽣死锁的四个必要条件：
     * 1. 互斥条件：该资源任意⼀个时刻只由⼀个线程占⽤。
     * 2. 请求与保持条件：⼀个线程因请求资源⽽阻塞时，对已获得的资源保持不放。
     * 3. 不剥夺条件:线程已获得的资源在未使⽤完之前不能被其他线程强⾏剥夺，只有⾃⼰使⽤完毕后
     * 才释放资源。
     * 4. 循环等待条件:若⼲线程之间形成⼀种头尾相接的循环等待资源关系。
     *
     * @param args
     */
    public static void main(String[] args) {
        new Thread(()->{
            synchronized (resource1){
                System.out.println(Thread.currentThread()+"get resource1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread()+"waitting for get resource2");
                synchronized (resource2){
                    System.out.println(Thread.currentThread()+"get resource2");
                }
            }
        },"线程1").start();
        new Thread(()->{
            synchronized (resource2){
                System.out.println(Thread.currentThread()+"get resource2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread()+"waitting for get resource1");
                synchronized (resource1){
                    System.out.println(Thread.currentThread()+"get resource1");
                }
            }
        },"线程2").start();
        //sleep() ⽅法没有释放锁，⽽ wait() ⽅法释放了锁

        //如何预防死锁？ 破坏死锁的产⽣的必要条件即可：
        //1. 破坏请求与保持条件 ：⼀次性申请所有的资源。
        //2. 破坏不剥夺条件 ：占⽤部分资源的线程进⼀步申请其他资源时，如果申请不到，可以主动释放
        //它占有的资源。
        //3. 破坏循环等待条件 ：靠按序申请资源来预防。按某⼀顺序申请资源，释放资源则反序释放。破
        //坏循环等待条件。

        //银⾏家算法
    }
}
