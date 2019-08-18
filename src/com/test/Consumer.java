package com.test;

public class Consumer implements Level, Runnable {

    private int level = 1;
    private int duration = 500;
    private static int number = 0;
    private int employeeNum;

    public Consumer() {
        employeeNum = ++number;
    }

    public Consumer(int level) {
        this.level = level;
        this.duration /= this.level;
        employeeNum = ++number;
    }

    @Override
    public void run() {
        while (true) {
//            System.out.println(" [" + getConsumerName() + number + "号 准备出库]");
            try {
                Thread.sleep(this.duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ProductStore ps = ProductStore.getInstance();
            synchronized (ProductStore.class) {
                while (ps.isEmpty()) {
                    try {
                        System.out.println("库存已空");
                        ProductStore.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                ProductStore.getInstance().removeProduct();
                System.out.println(" [" + getConsumerName() + employeeNum + "号 出库成功] ");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~出库完成 库存为：" + ps.getStock());
                ProductStore.class.notifyAll();
            }
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
