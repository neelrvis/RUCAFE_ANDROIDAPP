package com.example.rucafe_androidapp;

import java.util.ArrayList;

public class Coffee extends MenuItem {

    private String size;
    private ArrayList<String> addons;
    private double basePrice;
    private int quantity;

    public static final double addonPrice = 0.30;

    public Coffee() {

    }

    public Coffee(String size, ArrayList<String> addons, double basePrice, int quantity) {
        this.size = size;
        this.addons = addons;
        this.basePrice = basePrice;
        this.quantity = quantity;
    }


    public Coffee(String size, ArrayList<String> addons, int quantity) {
        this.size = size;
        this.addons = addons;
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

        return this.quantity * (this.basePrice + this.addons.size() * addonPrice);
    }

    @Override
    public String toString() {

        String addonscsv = "";
        if (!this.addons.isEmpty()) {
            addonscsv += " with ";
            for (int i = 0; i < this.addons.size(); i++) {
                if (i < (this.addons.size()-2)) {
                    addonscsv += this.addons.get(i).toLowerCase() + ", ";
                }
                else if (i < (this.addons.size()-1)) {
                    if (this.addons.size() > 2) {
                        addonscsv += this.addons.get(i).toLowerCase() + ", & ";
                    }
                    else {
                        addonscsv += this.addons.get(i).toLowerCase() + " & ";
                    }
                }
                else {
                    addonscsv += this.addons.get(i).toLowerCase();
                }
            }
        }
        return this.quantity + " " + this.size + " coffee" + ((this.quantity == 1) ? "" : "s") + addonscsv;




    }
}
