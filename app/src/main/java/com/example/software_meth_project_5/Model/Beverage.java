package com.example.software_meth_project_5.Model;

/**
 * This class represents a beverage item in the menu.
 * It extends the MenuItem class and implements the price() method.
 * 
 * @author Henry Rodriguez
 */
public class Beverage extends MenuItem {
    private Size size;
    private Flavor flavor;

    /**
     * Constructor for the Beverage class
     * 
     * @param size     The size of the beverage
     * @param flavor   The flavor of the beverage
     * @param quantity The quantity of the beverage
     */
    public Beverage(Size size, Flavor flavor, int quantity) {
        this.size = size;
        this.flavor = flavor;
        this.quantity = quantity;
    }

    /**
     * Get the flavor of the beverage
     *
     * @return The flavor of the beverage
     */
    public Flavor getFlavor() {
        return flavor;
    }

    /**
     * Get the size of the beverage
     *
     * @return The size of the beverage
     */
    public Size getSize() {
        return size;
    }

    /**
     * Calculate the price of the beverage
     *
     * @return The price of the beverage
     */
    @Override
    public double price() {
        double price = 0;
        if (this.size == Size.SMALL) {
            price += 1.99;
        } else if (this.size == Size.MEDIUM) {
            price += 2.49;
        } else {
            price += 2.99;
        }
        return price * quantity;
    }

    /**
     * Get the string representation of the beverage
     *
     * @return The string representation of the beverage
     */
    @Override
    public String toString() {
        return String.format("%s\n Size: %s Quantity: %d", flavor.getFlavorName(), size.getSizeName(), quantity);
    }

}
