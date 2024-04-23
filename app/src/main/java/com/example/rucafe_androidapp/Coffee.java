package com.example.rucafe_androidapp;

import java.util.ArrayList;

/**
 * Coffee class that extends MenuItem
 * 
 * @author Neel
 */
public class Coffee extends MenuItem {

    /**
     * size of coffee (short, tall, grande, venti)
     */
    private String size;

    /**
     * list of addons for coffee
     */
    private ArrayList<String> addons;

    /**
     * base price of coffee
     */
    private double basePrice;

    /**
     * quantity of coffees
     */
    private int quantity;

    /**
     * price of each addon
     */
    public static final double addonPrice = 0.30;

    /**
     * default constructor
     */
    public Coffee() {

    }

    /**
     * normal constructor
     * 
     * @param size      of coffee
     * @param addons    for coffee
     * @param basePrice of coffee
     * @param quantity  of coffees
     */
    public Coffee(String size, ArrayList<String> addons, double basePrice, int quantity) {
        this.size = size;
        this.addons = addons;
        this.basePrice = basePrice;
        this.quantity = quantity;
    }

    /**
     * constructor without base price
     * 
     * @param size     of coffee
     * @param addons   for coffee
     * @param quantity of coffees
     */
    public Coffee(String size, ArrayList<String> addons, int quantity) {
        this.size = size;
        this.addons = addons;
        this.quantity = quantity;
    }

    /**
     * returns the size of the coffee
     * 
     * @return size of coffee (String)
     */
    public String getSize() {
        return size;
    }

    /**
     * returns the addons of the coffee
     * 
     * @return ArrayList of addons
     */
    public ArrayList<String> getAddons() {
        return addons;
    }

    /**
     * returns the base price of the coffee
     * 
     * @return base price of coffee
     */
    public double getBasePrice() {
        return basePrice;
    }

    /**
     * returns the quantity of the coffee
     * 
     * @return quantity of coffee
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * returns the price of the coffee object
     * 
     * @return double price
     */
    @Override
    public double price() {
        return this.quantity * (this.basePrice + this.addons.size() * addonPrice);
    }

    /**
     * returns the string representation of the coffee object (for cart)
     * 
     * @return string representation of coffee object
     */
    @Override
    public String toString() {

        String addonscsv = "";
        if (!this.addons.isEmpty()) {
            addonscsv += " with ";
            for (int i = 0; i < this.addons.size(); i++) {
                if (i < (this.addons.size() - 2)) {
                    addonscsv += this.addons.get(i).toLowerCase() + ", ";
                } else if (i < (this.addons.size() - 1)) {
                    if (this.addons.size() > 2) {
                        addonscsv += this.addons.get(i).toLowerCase() + ", & ";
                    } else {
                        addonscsv += this.addons.get(i).toLowerCase() + " & ";
                    }
                } else {
                    addonscsv += this.addons.get(i).toLowerCase();
                }
            }
        }
        return this.quantity + " " + this.size + " coffee" + ((this.quantity == 1) ? "" : "s") + addonscsv;
    }
}
