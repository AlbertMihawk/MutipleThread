package com.test;

public class Product {

    private String name;
    private static int number = 0;

    public Product(String name) {
        this.name = name;
        number++;
    }

    public String getName() {
        return name + number;
    }

}
