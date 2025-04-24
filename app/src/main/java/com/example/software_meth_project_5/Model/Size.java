package com.example.software_meth_project_5.Model;

/**
 * This enum represents the sizes available in the menu.
 * 
 * @author Henry Rodriguez
 */
public enum Size {
    LARGE("Large"),
    MEDIUM("Medium"),
    SMALL("Small");

    private String sizeName;

    /**
     * Constructor for the Size enum
     * 
     * @param sizeName The name of the size
     */
    Size(String sizeName) {
        this.sizeName = sizeName;
    }

    /**
     * Get the name of the size
     * 
     * @return The name of the size
     */
    public String getSizeName() {
        return sizeName;
    }

}
