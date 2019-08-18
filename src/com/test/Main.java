package com.test;

public class Main {
    public static void main(String[] args) {

        /**
         * 库存0，上线为10个
         */
//        ProductStore.init(10);
//        Thread t1 = new Thread(new Consumer());
//        Thread t2 = new Thread(new Producer(Level.SENIOR));
//
//        t1.start();
//        t2.start();


        /**
         * 库存为20，上线也为20
         */
        ProductStore.init(10, 0);
        Thread t1 = new Thread(new Consumer(Level.NEWBIE));
        Thread t11 = new Thread(new Consumer(Level.SENIOR));
        Thread t2 = new Thread(new Producer(Level.NORMAL));
        Thread t3 = new Thread(new Producer(Level.NORMAL));
        Thread t4 = new Thread(new Producer(Level.NORMAL));
        Thread t5 = new Thread(new Producer(Level.NEWBIE));

        t1.start();
        t11.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
