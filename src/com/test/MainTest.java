package com.test;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainTest {

    private static long startTime = 0;

    /**
     * Thread.yield()临时让权，在公平竞争情况下
     * Thread.sleep()不释放锁
     * Thread.wait()必须用在synchronized代码块中
     * Thread.join(),先执行start()在运行状态下，加入其它线程，优先执行
     * static 静态变量 count++ 也会出现线程重复的现象，需要加synchronize，同步锁
     */

    private static boolean isRuning = false;

    static int count = 0;

    public static void main(String[] args) {
        Lock l1 = new ReentrantLock(true);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                }
            }
        }, "T1");
//        t1.setPriority(Thread.MAX_PRIORITY);

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        if (i > 3 && !isRuning) {
                            isRuning = true;
                            System.out.println("T1加入");
                            t1.start();
                            t1.join();

                        }
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                }
            }
        }, "T2");

//        t2.setPriority(Thread.MIN_PRIORITY);

//        t1.start();
//        t2.start();

        startTime = System.currentTimeMillis();
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                compute();
            }
        };

        Thread th1 = new Thread(r1);
        Thread th2 = new Thread(r1);
        Thread th3 = new Thread(r1);
        Thread th4 = new Thread(r1);
        Thread th5 = new Thread(r1);

        th1.start();
        th2.start();
        th3.start();
        th4.start();
        th5.start();


        try {
            th1.join();
            th2.join();
            th3.join();
            th4.join();
            th5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);
    }

    private static void compute() {
//        synchronized (MainTest.class) {
            for (int i = 0; i < 10000; i++) {
//                Random random = new Random();
//                    int time = random.nextInt(1) * 300;
                count++;
//            if (count == 50000) {
//                System.out.println("经历时间：" + (System.currentTimeMillis() - startTime));
//            }

//            }
        }
    }


}
