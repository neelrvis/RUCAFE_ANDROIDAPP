package com.example.rucafe_androidapp;


import java.util.ArrayList;

public final class CartList{
    private static CartList cartList;

    private ArrayList<MenuItem> items;

    private CartList() {
        items = new ArrayList<>();
    }
    public static synchronized CartList getInstance() {
        if (cartList == null) {
            cartList = new CartList();
        }
        return cartList;
    }

    public void addItem(MenuItem item) {
        this.items.add(item);
    }

    public void setCartContents(ArrayList<MenuItem> input) {
        this.items = input;
    }

    public ArrayList<MenuItem> getItems() {
        return this.items;
    }
}
