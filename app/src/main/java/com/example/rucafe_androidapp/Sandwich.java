package com.example.rucafe_androidapp;

/**
 * Describes a Sandwich Item
 * 
 * @author Tadhg
 */
public class Sandwich extends MenuItem {

    /*
     * A beef sandwich is $10.99,
     * a chicken sandwich is $8.99,
     * and a fish sandwich is $9.99.
     * Customers can choose the add-ons, including cheese, lettuce, tomatoes, and
     * onions.
     * However, each veggie add-on costs $0.30 extra, and the cheese costs $1 extra.
     */

    /**
     * Declaration of bread type as a SandwichBread Enum
     */
    private SandwichBread bread;

    /**
     * Declaration of protein type as SandwichBread Enum
     */
    private SandwichProtein protein;

    /**
     * Declaration of addons type as SandwichBread Enum
     */
    private SandwichAddons[] addons;

    /**
     * Default constructor for Sandwich. Initializes default settings for the UI.
     */
    public Sandwich() {
        this.bread = SandwichBread.BAGEL;
        this.protein = SandwichProtein.BEEF;
        this.addons = new SandwichAddons[0];
    }

    /**
     * Parametrized constructor for Sandwich item
     * Initializes Sandwich item with passed parameters
     * 
     * @param bread   SandwichBread enum for bread type
     * @param protein SandwichProtein enum for protein type
     * @param addons  SandwichAddons enum for
     */
    public Sandwich(SandwichBread bread, SandwichProtein protein, SandwichAddons[] addons) {
        this.bread = bread;
        this.protein = protein;
        this.addons = addons;
    }

    /**
     * Sets bread type
     * 
     * @param bread SandwichBread enum type
     */
    public void setBread(SandwichBread bread) {
        if (bread != null) {
            this.bread = bread;
        }
    }

    /**
     * Sets protein type
     * 
     * @param protein SandwichProtein enum type
     */
    public void setProtein(SandwichProtein protein) {
        if (protein != null) {
            this.protein = protein;
        }
    }

    /**
     * Sets addons for Sandwich
     * 
     * @param addons SandwichAddons Enum Array which contains all addons selected
     */
    public void setAddons(SandwichAddons[] addons) {
        if (addons != null) {
            this.addons = addons;
        }
    }

    /**
     * Computes and returns price of fully customized Sandwich
     * 
     * @return price of sandwich after all selections
     */
    @Override
    public double price() {
        double outprice = 0.0;
        for (SandwichAddons a : addons) {
            outprice += a.getPrice();
        }
        return protein.getPrice() + outprice;
    }

    /**
     * Returns formatted string of Sandwich Item
     * 
     * @return string representation of Sandwich Item
     */
    @Override
    public String toString() {
        String addonscsv = "";
        if (addons.length > 0) {
            addonscsv += " with ";
            for (int i = 0; i < addons.length; i++) {
                if (i < (addons.length - 2)) {
                    addonscsv += addons[i].toString().toLowerCase() + ", ";
                } else if (i < (addons.length - 1)) {
                    if (addons.length > 2) {
                        addonscsv += addons[i].toString().toLowerCase() + ", & ";
                    } else {
                        addonscsv += addons[i].toString().toLowerCase() + " & ";
                    }
                } else {
                    addonscsv += addons[i].toString().toLowerCase();
                }
            }
        }
        return this.protein + " & " + this.bread.toString().toLowerCase() + " sandwich" + addonscsv;
    }
}
