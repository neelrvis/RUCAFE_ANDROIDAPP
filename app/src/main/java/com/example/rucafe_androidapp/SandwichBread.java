package com.example.rucafe_androidapp;

/**
 * Describes SandwichBread Enum Type of Sandwich Item
 *
 * @author Neel, Tadhg
 */
public enum SandwichBread {

    /**
     * Bagel
     */
    BAGEL("Bagel"),

    /**
     * Wheat
     */
    WHEAT("Wheat toast"),

    /**
     * Sour
     */
    SOUR("Sour dough");

    /**
     * name of SandwichBread Enum Type as string
     */
    private String prettyName;

    /**
     * Private default constructor. Initializes string format name for enum object
     * @param prettyName name of SandwichBread Enum Type
     */
    private SandwichBread(String prettyName) {
        this.prettyName = prettyName;
    }

    /**
     * Returns the matching enum given a string.
     * @param input string matching on of the enums
     * @return matching SandwichBread enum or null if not found
     */
    public static SandwichBread fromString(String input) {
        if (input != null) {
            String lowerInput = input.toLowerCase();
            if (lowerInput.equals("bagel")) {
                return SandwichBread.BAGEL;
            } else if (lowerInput.equals("wheat")) {
                return SandwichBread.WHEAT;
            } else if (lowerInput.equals("sour")) {
                return SandwichBread.SOUR;
            }
        }
        return null;
    }

    /**
     * Returns name attribute of SandwichBread Enum Type
     * @return string representation of name of SandwichBread Enum type
     */
    @Override
    public String toString() {
        return prettyName;
    }
}
