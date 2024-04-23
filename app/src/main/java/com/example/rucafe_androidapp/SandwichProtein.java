package com.example.rucafe_androidapp;

/**
 * Enum class that describes protein selection of sandwich
 * 
 * @author Tadhg
 */
public enum SandwichProtein {

    /**
     * Beef: $10.99
     */
    BEEF(10.99),

    /**
     * Chicken: $8.99
     */
    CHICKEN(8.99),

    /**
     * Fish: $9.99
     */
    FISH(9.99);

    /**
     * Declaration of price of customized sandwich
     */
    private final double price;

    /**
     * Enum constructor. Initializes the price for particular SandwichProtein enum
     * 
     * @param price price of sandwich protein
     */
    private SandwichProtein(double price) {
        this.price = price;
    }

    /**
     * Returns the price of selected sandwich protein
     * 
     * @return price of sandwich protein
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Returns the matching enum given a string.
     * 
     * @param input string matching on of the enums
     * @return matching Sandwich enum or null if not found
     */
    public static SandwichProtein fromString(String input) {
        if (input != null) {
            String lowerInput = input.toLowerCase();
            if (lowerInput.equals("beef")) {
                return SandwichProtein.BEEF;
            } else if (lowerInput.equals("chicken")) {
                return SandwichProtein.CHICKEN;
            } else if (lowerInput.equals("fish")) {
                return SandwichProtein.FISH;
            }
        }
        return null;
    }

    /**
     * Returns formatted string of SandwichProtein enum
     * 
     * @return string representation of SandwichProtein enum
     */
    @Override
    public String toString() {
        return this.name().substring(0, 1).toUpperCase() + this.name().substring(1).toLowerCase();
    }
}
