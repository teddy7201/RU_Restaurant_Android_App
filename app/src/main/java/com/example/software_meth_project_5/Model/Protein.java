package com.example.software_meth_project_5.Model;

/**
 * This enum represents the protein types available in the menu.
 * 
 * @author Henry Rodriguez
 */
public enum Protein {
    ROAST_BEEF("Roast Beef", 10.99),
    SALMON("Salmon", 9.99),
    CHICKEN("Chicken", 8.99),
    BEEF_PATTY("Beef Patty", 6.99);

    private String proteinType;
    private double price;

    /**
     * Constructor for the Protein enum
     * 
     * @param proteinType The type of protein
     * @param price       The price of the protein
     */
    Protein(String proteinType, double price) {
        this.proteinType = proteinType;
        this.price = price;
    }

    /**
     * Get the type of protein
     * 
     * @return The type of protein
     */
    public String getProteinType() {
        return proteinType;
    }

    /**
     * Get the price of the protein
     * 
     * @return The price of the protein
     */
    public double getPrice() {
        return price;
    }
}
