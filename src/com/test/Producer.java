package com.test;

public class Producer implements Level, Runnable {

    private int level = 1;
    private int duration = 500;
    private static int number = 0;
    private int employeeNum;


    public Producer() {
        employeeNum = ++number;
    }

    public Producer(int level) {
        this.level = level;
        this.duration /= this.level;
        employeeNum = ++number;
    }

    @Override
    public void run() {
        while (true) {
//            System.out.println(" (" + getProducerName() + number + "号 准备生产产品) ");
            Product p = new Product("汽车零件");
            try {
                Thread.sleep(this.duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" (" + getProducerName() + employeeNum + "号 生产产品 " + p.getName() + ") ");
            synchronized (ProductStore.class) {
                ProductStore ps = ProductStore.getInstance();
                while (ps.isFull()) {
                    try {
                        System.out.println("库存已满");
                        ProductStore.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                ProductStore.getInstance().addProduct(p);
                System.out.println(" <" + getProducerName() + employeeNum + "号 入库成功> ");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~入库完成 库存为：" + ps.getStock());
                ProductStore.class.notifyAll();
            }

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
