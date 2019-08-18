package com.test;

public class Product {

    private String name;
    private static int count = 0;
    private  int number;

    public Product(String name) {
        this.name = name;
        number = ++count;
    }

    public String getName() {
        return name + number;
    }

}
