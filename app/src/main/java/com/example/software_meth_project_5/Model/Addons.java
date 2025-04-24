package com.example.software_meth_project_5.Model;

/**
 * This enum class contains the types of addons available to use for the
 * sandwiches and burgers.
 * 
 * @author Henry Rodriguez
 */
public enum Addons {
    LETTUCE("Lettuce", 0.30),
    TOMATOES("Tomatoes", 0.30),
    ONIONS("Onions", 0.30),
    AVOCADO("Avocado", 0.50),
    CHEESE("Cheese", 1.00);

    private String addonType;
    private double price;

    /**
     * Constructor for the Addons enum
     * 
     * @param addonType The type of addon
     * @param price     The price of the addon
     */
    Addons(String addonType, double price) {
        this.addonType = addonType;
        this.price = price;
    }

    /**
     * Get the type of addon
     *
     * @return The type of addon
     */
    public String getAddonType() {
        return addonType;
    }

    /**
     * Get the price of the addon
     *
     * @return The price of the addon
     */
    public double getPrice() {
        return price;
    }
}
