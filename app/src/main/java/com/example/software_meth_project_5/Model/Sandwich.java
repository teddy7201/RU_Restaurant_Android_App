package com.example.software_meth_project_5.Model;

import java.util.ArrayList;

/**
 * This class is for creating Sandwiches and calculating the prices for them.
 *
 * @author Henry Rodriguez
 */
public class Sandwich extends MenuItem {
    protected Bread bread;
    protected Protein protein;
    protected ArrayList<Addons> addOns;

    /**
     * Constructor for Sandwich with 3 parameters.
     *
     * @param bread   A Bread object representing the type of bread of the sandwich.
     * @param protein A Protein object representing the type of protein of the
     *                sandwich.
     * @param addOns  An ArrayList of Addons containing all the addons added to the
     *                sandwich.
     */
    public Sandwich(Bread bread, Protein protein, ArrayList<Addons> addOns, int quantity) {
        this.bread = bread;
        this.protein = protein;
        this.addOns = addOns;
        this.quantity = quantity;
    }

    /**
     * Getter method for getting the bread of the sandwich.
     *
     * @return A Bread object that represents the bread of the sandwich.
     */
    public Bread getBread() {
        return bread;
    }

    /**
     * Getter method for getting the protein of the sandwich.
     *
     * @return A Protein object that represents the protein of the sandwich.
     */
    public Protein getProtein() {
        return protein;
    }

    /**
     * Getter method for getting the ArrayList of Addons
     *
     * @return An ArrayList of the addons added to the sandwich.
     */
    public ArrayList<Addons> getAddOns() {
        return addOns;
    }

    /**
     * Overriden price() method that calculates the price of the sandwich.
     *
     * @return A double representing the price of the sandwich.
     */
    @Override
    public double price() {
        double price = 0;

        price += this.protein.getPrice();

        if (addOns.isEmpty()) {
            price = price * quantity;
            return price;
        } else {
            for (Addons addon : addOns) {
                price += addon.getPrice();
            }
        }
        price = price * quantity;
        return price;
    }

    /**
     * Overriden toString() method that returns a string representation of the
     * sandwich.
     *
     * @return A string representation of the sandwich.
     */
    @Override
    public String toString() {
        if (addOns.isEmpty()) {
            return String.format("Sandwich\n Bread: %s Protein: %s Addons: None Quantity: %d", bread.getBreadType(),
                    protein.getProteinType(), quantity);
        }

        StringBuilder addonNames = new StringBuilder();
        for (Addons addon : addOns) {
            addonNames.append(addon.getAddonType() + " ");
        }

        return String.format("Sandwich\n Bread: %s Protein: %s Addons: %s Quantity: %d", bread.getBreadType(),
                protein.getProteinType(), addonNames, quantity);
    }

}