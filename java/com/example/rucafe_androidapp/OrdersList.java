package com.example.rucafe_androidapp;

import java.util.ArrayList;

public final class OrdersList {

    private static OrdersList ordersList;
    private ArrayList<ArrayList<MenuItem>> orders;

    private OrdersList() {
        orders = new ArrayList<>();
    }
    public static synchronized OrdersList getInstance() {
        if (ordersList == null) {
            ordersList = new OrdersList();
        }
        return ordersList;
    }
    public void addOrder(ArrayList<MenuItem> order) {
        this.orders.add(order);
    }
    public ArrayList<ArrayList<MenuItem>> getOrders() {
        return this.orders;
    }

}
