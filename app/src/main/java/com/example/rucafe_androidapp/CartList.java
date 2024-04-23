package com.example.rucafe_androidapp;

import java.util.ArrayList;

/**
 * sets up CartList singleton object
 * 
 * @author Neel
 */
public final class CartList {
    /**
     * instance of CartList
     */
    private static CartList cartList;

    /**
     * list of items in the cart
     */
    private ArrayList<MenuItem> items;

    /**
     * private constructor for CartList
     */
    private CartList() {
        items = new ArrayList<>();
    }

    /**
     * gets the instance of CartList
     * 
     * @return cartList instance
     */
    public static synchronized CartList getInstance() {
        if (cartList == null) {
            cartList = new CartList();
        }
        return cartList;
    }

    /**
     * adds an item to the cart
     * 
     * @param item to be added
     */
    public void addItem(MenuItem item) {
        this.items.add(item);
    }

    /**
     * sets the cart contents
     * 
     * @param input arraylist of MenuItems
     */
    public void setCartContents(ArrayList<MenuItem> input) {
        this.items = input;
    }

    /**
     * gets the cart contents
     * 
     * @return
     */
    public ArrayList<MenuItem> getItems() {
        return this.items;
    }
}
