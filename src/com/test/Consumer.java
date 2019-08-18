package com.test;

public class Consumer implements Level, Runnable {

    private int level = 1;
    private int duration = 500;
    private static int number = 0;

    public Consumer() {
        number++;
    }

    public Consumer(int Level) {
        this.duration /= level;
        number++;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("[ " + getConsumerName() + number + "号 开始出库 ]");
            try {
                Thread.sleep(this.duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ProductStore.getInstance().removeProduct();
            System.out.println(" [" + getConsumerName() + number + "号 出库成功] ");
        }
    }

    private String getConsumerName() {
        switch (this.level) {
            case Level.SENIOR:
                return "专家Consumer";
            case Level.NORMAL:
                return "一般Consumer";
            case Level.NEWBIE:
                return "菜鸟Consumer";
            default:
                return "菜鸟Consumer";
        }
    }

}
