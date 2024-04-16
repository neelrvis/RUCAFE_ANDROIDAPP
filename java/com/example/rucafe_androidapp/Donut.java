package com.example.rucafe_androidapp;

import androidx.annotation.NonNull;

public class Donut extends MenuItem{

    private String type;
    private String flavor;
    private int image;
    private double basePrice;
    private int quantitySelected = 1;

    public Donut(String type, String flavor, int image,double basePrice){
        this.type = type;
        this.flavor = flavor;
        this.image = image;
        this.basePrice = basePrice;
    }
    public Donut(String type, String flavor, int image,double basePrice,int quantitySelected){
        this.type = type;
        this.flavor = flavor;
        this.image = image;
        this.basePrice = basePrice;
        this.quantitySelected = quantitySelected;
    }

    public Donut(String type, String flavor, double basePrice,int quantitySelected) {
        this.type = type;
        this.flavor = flavor;
        this.basePrice = basePrice;
        this.quantitySelected = quantitySelected;
    }


    public String getType() {
        return type;
    }

    public String getFlavor() {
        return flavor;
    }

    public int getImage() {
        return image;
    }

    public double getBasePricePrice() {
        return basePrice;
    }

    public int getQuantity(){return this.quantitySelected;}

    public void setQuantity(int quantitySelected) {
        this.quantitySelected = quantitySelected;
    }

    @Override
    public String toString() {

        return this.quantitySelected+" "+this.flavor+" "+this.type+" donuts";
    }

    @Override
    public double price() {
        return this.basePrice * this.quantitySelected;
    }
}
