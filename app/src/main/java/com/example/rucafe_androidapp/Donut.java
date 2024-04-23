package com.example.rucafe_androidapp;

/**
 * Describes a Donut MenuItem
 * 
 * @author Neel
 */
public class Donut extends MenuItem {

    /**
     * type of donut
     */
    private String type;

    /**
     * flavor of donut
     */
    private String flavor;
    /**
     * image of donut
     */
    private int image;
    /**
     * base price of donut
     */
    private double basePrice;
    /**
     * quantity of donuts
     */
    private int quantitySelected = 1;

    /**
     * default constructor
     * 
     * @param type      type of donut
     * @param flavor    flavor of donut
     * @param image     image of donut
     * @param basePrice base price of donut
     */
    public Donut(String type, String flavor, int image, double basePrice) {
        this.type = type;
        this.flavor = flavor;
        this.image = image;
        this.basePrice = basePrice;
    }

    /**
     * constructor with quantity
     * 
     * @param type             type of donut
     * @param flavor           flavor of donut
     * @param basePrice        base price of donut
     * @param quantitySelected quantity of donuts
     */
    public Donut(String type, String flavor, double basePrice, int quantitySelected) {
        this.type = type;
        this.flavor = flavor;
        this.basePrice = basePrice;
        this.quantitySelected = quantitySelected;
    }

    /**
     * getter for type
     * 
     * @return type of donut (String)
     */
    public String getType() {
        return type;
    }

    /**
     * getter for flavor
     * 
     * @return flavor of donut (String)
     */
    public String getFlavor() {
        return flavor;
    }

    /**
     * getter for image
     * 
     * @return image of donut (int)
     */
    public int getImage() {
        return image;
    }

    /**
     * getter for base price
     * 
     * @return base price of donut (double)
     */
    public double getBasePricePrice() {
        return basePrice;
    }

    /**
     * getter for quantity
     * 
     * @return quantity of donuts (int)
     */
    @Override
    public String toString() {
        return this.quantitySelected + " " + this.flavor + " " + this.type + " donuts";
    }

    /**
     * getter for price
     * 
     * @return price of donuts (double)
     */
    @Override
    public double price() {
        return this.basePrice * this.quantitySelected;
    }
}
