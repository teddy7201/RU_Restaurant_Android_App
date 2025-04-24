package com.example.software_meth_project_5.Model;

/**
 * This class represents a side item in the menu.
 * 
 * @author Henry Rodriguez
 */
public class Side extends MenuItem {

    private Size size;
    private SideType type;

    /**
     * Constructor for the Side class
     * 
     * @param size     The size of the side
     * @param type     The type of side
     * @param quantity The quantity of the side
     */
    public Side(Size size, SideType type, int quantity) {
        this.size = size;
        this.type = type;
        this.quantity = quantity;
    }

    public double price() {
        double price = 0;

        price += type.getPrice();

        if (this.size == Size.MEDIUM) {
            price += 0.50;
        } else if (this.size == Size.LARGE) {
            price += 1.00;
        }
        price = price * quantity;
        return price;
    }

    /**
     * Overriden toString() method that returns a string representation of the
     * side.
     *
     * @return A string representation of the side.
     */
    @Override
    public String toString() {
        return String.format("%s\n Size: %s Quantity: %d", type.getSideName(), size.getSizeName(), quantity);
    }
}