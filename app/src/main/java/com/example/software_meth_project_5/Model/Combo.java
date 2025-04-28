package com.example.software_meth_project_5.Model;

/**
 * Represents a combo item in the menu.
 * It extends the MenuItem class and implements the price() method.
 * 
 * @author Henry Rodriguez
 */
public class Combo extends MenuItem {
    private Sandwich sandwich;
    private Beverage beverage;
    private Side side;

    /**
     * Constructor for the Combo class
     * 
     * @param sandwich The sandwich in the combo
     * @param beverage The beverage in the combo
     * @param side     The side in the combo
     * @param quantity The quantity of the combo
     */
    public Combo(Sandwich sandwich, Beverage beverage, Side side, int quantity) {
        this.sandwich = sandwich;
        this.beverage = beverage;
        this.side = side;
        this.quantity = quantity;
    }

    /**
     * Get the beverage in the combo
     * 
     * @return The beverage in the combo
     */
    public Beverage getBeverage() {
        return beverage;
    }

    /**
     * Get the sandwich in the combo
     * 
     * @return The sandwich in the combo
     */
    public Sandwich getSandwich() {
        return sandwich;
    }

    /**
     * Get the side in the combo
     * 
     * @return The side in the combo
     */
    public Side getSide() {
        return side;
    }

    /**
     * Calculate the price of the combo
     * 
     * @return The price of the combo
     */
    @Override
    public double price() {
        double price = 0;
        price = this.sandwich.price() + (2.00 * quantity);
        return price;
    }

    /**
     * Get the string representation of the combo
     * 
     * @return The string representation of the combo
     */
    @Override
    public String toString() {
        StringBuilder description = new StringBuilder();

        // Start with basic combo description
        if (sandwich instanceof Burger) {
            Burger burger = (Burger) sandwich;
            String pattyType = burger.isDoublePatty() ? "Double" : "Single";
            description.append(pattyType).append(" Burger Combo:\n");
        } else {
            Protein protein = sandwich.getProtein();
            String proteinName = protein.getProteinType();
            description.append(proteinName).append(" Sandwich Combo:\n");
        }

        // Add sandwich details with bread type
        description.append(" - Base Item: ");
        description.append(sandwich.getBread().getBreadType()).append(" ");
        description.append(sandwich.getProtein().getProteinType()).append(" ");

        // Add toppings info if available
        if (!sandwich.getAddOns().isEmpty()) {
            description.append("with ");
            for (Addons addon : sandwich.getAddOns()) {
                description.append(addon.getAddonType().toLowerCase()).append(", ");
            }
            // Remove trailing comma and space
            description.setLength(description.length() - 2);
        }
        description.append("\n");

        // Add beverage details
        description.append(" - Drink: ").append(beverage.getFlavor()).append(" (").append(beverage.getSize().getSizeName()).append(")\n");

        // Add side details
        description.append(" - Side: ").append(side.toString().split("\n")[0]);
        description.append("\n");
        description.append("- Quantity: " + quantity);
        description.append("\n");
        return description.toString();
    }
}
