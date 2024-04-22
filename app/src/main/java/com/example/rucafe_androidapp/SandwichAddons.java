package com.example.rucafe_androidapp;

/**
 * Describes the addons for Sandwich Item
 *
 * @author Neel, Tadhg
 */
public enum SandwichAddons {

    
    /**
     * Lettuce: $0.30
     */
    LETTUCE(0.30),

    /**
     * Tomatoes: $0.30
     */
    TOMATOES(0.30),

    /**
     * Onions: $0.30
     */
    ONIONS(0.30),

    /**
     * Cheese: $1.00
     */
    CHEESE(1.0);


    /**
     * Constant of price of an addon
     */
    private final double price;

    /**
     * Private default constructor of SandwichAddons Enum Type
     * @param price price of addon
     */
    private SandwichAddons(double price) {
        this.price = price;
    }

    /**
     * Returns the price of specific addon
     * @return price of addon
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Returns the matching enum given a string.
     * @param input string matching on of the enums
     * @return matching SandwichAddons enum or null if not found
     */
    public static SandwichAddons fromString(String input) {
        if (input != null) {
            input = input.toLowerCase();
            if (input.equals("lettuce")) {
                return LETTUCE;
            }
            if (input.equals("tomatoes")) {
                return TOMATOES;
            }
            if (input.equals("onions")) {
                return ONIONS;
            }
            if (input.equals("cheese")) {
                return CHEESE;
            }
            return null;
        }
        return null;
    }

    /**
     * Returns formatted string of SandwichAddons Enum
     * @return string representation of SandwichAddons Enum
     */
    @Override
    public String toString() {
        return this.name().substring(0,1).toUpperCase() + this.name().substring(1).toLowerCase();
    }
}
