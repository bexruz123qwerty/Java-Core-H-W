package com.example.lesson3.part2;

import java.util.ArrayList;
import java.util.List;

public class OnlineStore {
    private final String code;
    private final String name;
    private final double price;
    private int count;

    private static final List<OnlineStore> products = new ArrayList<>();

    public OnlineStore(String code, String name, double price, int count) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public static void addProduct(OnlineStore product) {
        products.add(product);
    }

    public boolean buyProduct(int quantity) {
        if (count >= quantity) {
            count -= quantity;
            return true;
        }
        return false;
    }

    public String getProductInfo() {
        return code + ": " + name + " - $" + price + " [" + count + " pcs]";
    }

    public static List<OnlineStore> getProducts() {
        return products;
    }
}

