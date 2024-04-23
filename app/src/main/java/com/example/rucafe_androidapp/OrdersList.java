package com.example.rucafe_androidapp;

import java.util.ArrayList;

/**
 * sets up OrdersList singleton object
 * 
 * @author Tadhg
 */
public final class OrdersList {

    /**
     * internal instance of OrdersList
     */
    private static OrdersList ordersList;

    /**
     * list of orders
     */
    private ArrayList<ArrayList<MenuItem>> orders;

    /**
     * private constructor for OrdersList
     */
    private OrdersList() {
        orders = new ArrayList<>();
    }

    /**
     * gets the instance of OrdersList
     * 
     * @return ordersList instance
     */
    public static synchronized OrdersList getInstance() {
        if (ordersList == null) {
            ordersList = new OrdersList();
        }
        return ordersList;
    }

    /**
     * adds an order to the list of orders
     * 
     * @param order list of MenuItems
     */
    public void addOrder(ArrayList<MenuItem> order) {
        this.orders.add(order);
    }

    /**
     * returns the list of orders
     * 
     * @return list of orders
     */
    public ArrayList<ArrayList<MenuItem>> getOrders() {
        return this.orders;
    }

}
