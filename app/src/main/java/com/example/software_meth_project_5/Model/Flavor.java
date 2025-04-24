package com.example.software_meth_project_5.Model;

/**
 * This enum class contains the types of flavors available to use for the
 * beverages.
 * 
 * @author Henry Rodriguez
 */
public enum Flavor {
    DR_PEPPER("Dr Pepper"),
    APPLE_JUICE("Apple Juice"),
    SPRITE("Sprite"),
    COKE("Coke"),
    PEPSI("Pepsi"),
    FANTA_ORANGE("Fanta Orange"),
    FANTA_GRAPE("Fanta Grape"),
    LEMONADE("Lemonade"),
    ICED_TEA("Iced Tea"),
    DIET_PEPSI("Diet Pepsi"),
    DIET_COKE("Diet Coke"),
    ORANGE_JUICE("Orange Juice"),
    WATER("Water"),
    SELTZER("Seltzer"),
    DIET_DR_PEPPER("Diet Dr Pepper");

    private String flavorName;

    /**
     * Constructor for the Flavor enum
     * 
     * @param flavorName The name of the flavor
     */
    Flavor(String flavorName) {
        this.flavorName = flavorName;
    }

    /**
     * Get the name of the flavor
     * 
     * @return The name of the flavor
     */
    public String getFlavorName() {
        return flavorName;
    }
}