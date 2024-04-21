package com.example.rucafe_androidapp;

import java.util.ArrayList;

/**
 * Describes Order that a customer can make, containing several items
 *
 * @author Neel, Tadhg
 */
public class Order {

    /**
     * Declaration of unique order number
     */
    private int orderNum;

    /**
     * Declaration of list of items for order
     */
    private ArrayList<MenuItem> items;

    /**
     * Constant for sales tax of order
     */
    public static double NJSALESTAX = 0.06625;


    /**
     * Parametrized constructor of Order. Initializes order number and empty items list
     * @param orderNum order number
     */
    public Order(int orderNum) {
        this.orderNum = orderNum;
        items = new ArrayList<>();
    }

    /**
     * Sets this cart's item contents to match input
     * @param input ArrayList of MenuItems
     */
    public void setCartContents(ArrayList<MenuItem> input) {
        items = input;
    }


    /**
     * Returns MenuItem array containing all items for the order
     * @return MenuItem array that has all the items for the order
     */
    public ArrayList<MenuItem> getItems() {
        return items;
        // return items.toArray(MenuItem[]::new);
    }


    /**
     * Returns true if there are no items in the list, false if otherwise
     * @return {@code true} if no items in list
     *         {@code false} if items in list
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Computes and returns the subtotal of the order without tax
     * @return subtotal of order without tax
     */
    public double getSubtotal() {
        double out = 0.0;
        for (MenuItem m : items) {
            out += m.price();
        }
        return out;
    }

    /**
     * Computes and returns price with tax
     * @return price including tax
     */
    public double getTax() {
        return getSubtotal() * NJSALESTAX;
    }

    /**
     * Computes and returns total price of order
     * @return total price of order
     */
    public double getTotal() {
        return getSubtotal() + getTax();
    }

    /**
     * Returns the order number of an order
     * @return order number
     */
    public int getOrderID() {
        return orderNum;
    }

    /**
     * Returns formatted string of order
     * @return string representation of order
     */
    @Override
    public String toString() {
        String orderString = "";
        for (MenuItem item: this.items) {
            orderString+="- ";
            orderString+=item.toString();
            orderString+="\n";
        }
        return orderString;
    }
}
