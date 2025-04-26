package com.example.software_meth_project_5.Model;

/**
 * This class represents a combo which includes a burger or sandwich, side, and
 * beverage.
 */
public class BurgerCombo extends MenuItem {
    private MenuItem mainItem; // Can be either Burger or Sandwich
    private String side;
    private String beverage;
    private static final double COMBO_DISCOUNT = 3.00; // $3 discount for combo

    public BurgerCombo(MenuItem mainItem, String side, String beverage) {
        this.mainItem = mainItem;
        this.side = side;
        this.beverage = beverage;
    }

    @Override
    public double price() {
        // Base price is main item price + side price + drink price - combo discount
        double sidePrice = 3.99; // Standard price for all sides
        double drinkPrice = 2.49; // Standard price for all drinks
        return mainItem.price() + sidePrice + drinkPrice - COMBO_DISCOUNT;
    }

    @Override
    public String toString() {
        String itemType = (mainItem instanceof Burger) ? "Burger" : "Sandwich";
        return String.format("%s Combo\n%s\nSide: %s\nBeverage: %s",
                itemType, mainItem.toString(), side, beverage);
    }
}