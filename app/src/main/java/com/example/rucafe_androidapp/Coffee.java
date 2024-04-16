package com.example.rucafe_androidapp;

import java.util.ArrayList;

public class Coffee extends MenuItem {

    private String size;
    private ArrayList<String> addons;
    private double basePrice;
    private int quantity;

    private static final double addonPrice = 0.39;

    public Coffee() {

    }
    public Coffee(String size, ArrayList<String> addons, double basePrice, int quantity) {
        this.size = size;
        this.addons = addons;
        this.basePrice = basePrice;
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public ArrayList<String> getAddons() {
        return addons;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public double price() {

        return this.basePrice + this.addons.size() * addonPrice;
    }

    @Override
    public String toString() {

        return this.quantity+" "+this.size+" coffees";
    }
}
