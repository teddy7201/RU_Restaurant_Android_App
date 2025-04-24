package com.example.software_meth_project_5.Model;

/**
 * This enum represents the side types available in the menu.
 * 
 * @author Henry Rodriguez
 */
public enum SideType {
    CHIPS("Chips", 1.99),
    FRIES("Fries", 2.49),
    ONION_RINGS("Onion Rings", 3.29),
    APPLE_SLICES("Apple Slices", 1.29);

    private String sideName;
    private double price;

    /**
     * Constructor for the SideType enum
     * 
     * @param sideName The name of the side
     * @param price    The price of the side
     */
    SideType(String sideName, double price) {
        this.sideName = sideName;
        this.price = price;
    }

    /**
     * Get the price of the side
     * 
     * @return The price of the side
     */
    public double getPrice() {
        return price;
    }

    /**
     * Get the name of the side
     * 
     * @return The name of the side
     */
    public String getSideName() {
        return sideName;
    }

}
