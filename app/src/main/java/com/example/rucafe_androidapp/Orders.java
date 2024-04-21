package com.example.rucafe_androidapp;

import java.util.ArrayList;

/**
 * Describes all the current orders
 *
 * @author Neel,Tadhg
 */
public class Orders {
    // singleton...
    // manages orders in general...

    /**
     * Declaration of selected order
     */
    public Order currentOrder;

    /**
     * Declaration of list of previous orders
     */
    public ArrayList<Order> previousOrders;

    /**
     * Constant for first order number
     */
    public static int firstID = 1;

    /**
     * Declaration of next order number
     */
    private int nextOrderID;

    private static Orders ordersList;


    /**
     * Default constructor. Initializes order list and creates first order.
     */
    private Orders() {
        createFirstOrder();
        previousOrders = new ArrayList<>();
    }

    public static synchronized Orders getInstance() {
        if (ordersList == null) {
            ordersList = new Orders();
        }
        return ordersList;
    }

    /**
     * Creates first order of order list
     */
    private void createFirstOrder() {
        currentOrder = new Order(firstID);
        nextOrderID = firstID+1;
    }

    /**
     * Sets up new order to add in order list
     */
    private void setupNewOrder() {
        currentOrder = new Order(nextOrderID);
        nextOrderID += 1;
    }

    /**
     * Adds order to order list
     */
    public void placeCurrentOrder() {
        // adds currentOrder to prevOrders list
        previousOrders.add(currentOrder);
        // sets currentOrder to a new order (AND CHANGE THE CURRENT ORDER ID)
        setupNewOrder();
    }

    /**
     * Returns order of specific order number passed.
     * @param orderID order number of an order
     * @return Order given order number
     */
    public Order fetchOrderID(int orderID) {
        for (Order o : previousOrders) {
            if (o.getOrderID() == orderID) {
                return o;
            }
        }
        return null;
    }

    /**
     * Returns list of all previous orders
     * @return ArrayList of all previous orders
     */
    public ArrayList<Order> getPreviousOrders() {
        return this.previousOrders;
    }

    /**
     * Returns true if order is removed, and false if it does not exist
     * @param orderID order number of an order
     * @return {@code true} if order removed
     *         {@code false} if order does not exist
     */
    public boolean removeOrderID(int orderID) {
        for (Order o : previousOrders) {
            if (o.getOrderID() == orderID) {
                return previousOrders.remove(o); // returns true if ArrayList could remove this object
            }
        }
        return false; // never found order to remove, did not remove this order ID
    }
}
