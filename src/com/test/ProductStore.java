package com.test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProductStore {

    private static ProductStore productStore;


    public static ProductStore init(int capacity) {
        if (productStore == null) {
            productStore = new ProductStore(capacity);
        } else {
            throw new RuntimeException("产品仓库已经初始化完成");
        }
        return productStore;
    }

    public static ProductStore init(int capacity, int stock) {
        if (productStore == null) {
            productStore = new ProductStore(capacity, stock);
        } else {
            throw new RuntimeException("产品仓库已经初始化完成");
        }
        return productStore;
    }

    public static ProductStore getInstance() {
        if (productStore == null) {
            throw new RuntimeException("产品仓库没有初始化，请先调用init()");
        }
        return productStore;
    }

    public void clearStore() {
        if (productStore != null) {
            queue.clear();
            queue = null;
            stock = 0;
            productStore = null;
        }
    }

    private Queue<Product> queue;
    private int stock;
    public final int CAPACITY;

    private Lock addLock = new ReentrantLock();
    private Lock removeLock = new ReentrantLock();

    private ProductStore(int capacity) {
        clearStore();
        queue = new LinkedList<>();
        this.stock = 0;
        this.CAPACITY = capacity;
    }

    private ProductStore(int capacity, int stock) {
        clearStore();
        queue = new LinkedList<>();
        this.CAPACITY = capacity;
        this.stock = stock;
        for (int i = 0; i < stock; i++) {
            queue.add(new Product("库存零件"));
        }
    }

    public void addProduct(Product p) {
//        addLock.lock();
        if (stock == CAPACITY) {
            throw new RuntimeException("库存已满，不能入库");
        }
        stock++;
        queue.add(p);
//        System.out.println("入库：" + p.getName());
//        addLock.unlock();
    }

    public void removeProduct() {
//        removeLock.lock();
        if (stock == 0) {
            throw new RuntimeException("库存为空，不能出库");
        }
        stock--;
        Product p = queue.remove();
//        System.out.println("出库：" + p.getName());
//        removeLock.unlock();
    }

    public int getStock() {
        return this.stock;
    }

    public int getCAPACITY() {
        return this.CAPACITY;
    }

    public boolean isFull() {
        return this.stock == this.CAPACITY;
    }

    public boolean isEmpty() {
        return this.stock == 0;
    }
}
