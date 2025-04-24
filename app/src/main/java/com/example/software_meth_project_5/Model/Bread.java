package com.example.software_meth_project_5.Model;

/**
 * This enum class contains the types of bread available to use for the
 * sandwiches and burgers.
 * 
 * @author Henry Rodriguez
 */
public enum Bread {
    BRIOCHE("Brioche"),
    WHEAT_BREAD("Wheat Bread"),
    PRETZEL("Pretzel"),
    BAGEL("Bagel"),
    SOURDOUGH("Sourdough");

    private String breadType;

    /**
     * Constructor for the Bread enum
     * 
     * @param breadType The type of bread
     */
    Bread(String breadType) {
        this.breadType = breadType;
    }

    /**
     * Get the type of bread
     *
     * @return The type of bread
     */
    public String getBreadType() {
        return breadType;
    }
}
