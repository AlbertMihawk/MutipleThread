package com.test;

public class Producer implements Level, Runnable {

    private int level = 1;
    private int duration = 500;
    private static int number = 0;

    public Producer() {
        number++;
    }

    public Producer(int Level) {
        this.duration /= level;
        number++;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(" <" + getProducerName() + number + "号 生产产品>");
            try {
                Thread.sleep(this.duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Product p = new Product("汽车零件");
            ProductStore.getInstance().addProduct(p);
            System.out.println(" <" + getProducerName() + number + "号 入库成功> ");
        }
    }

    private String getProducerName() {
        switch (this.level) {
            case Level.SENIOR:
                return "专家Producer";
            case Level.NORMAL:
                return "一般Producer";
            case Level.NEWBIE:
                return "菜鸟Producer";
            default:
                return "菜鸟Producer";
        }
    }

}
