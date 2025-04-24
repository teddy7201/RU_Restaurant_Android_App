package com.example.software_meth_project_5.Model;

import java.util.ArrayList;

/**
 * This class represents an order in the menu.
 * 
 * @author Henry Rodriguez
 */
public class Order {
    private static int number = 0;
    private ArrayList<MenuItem> items;

    /**
     * Constructor for the Order class
     */
    public Order() {
        this.items = new ArrayList<>();
        number = number + 1;
    }

    /**
     * Constructor for the Order class
     * 
     * @param items The items in the order
     */
    public Order(ArrayList items) {
        this.items = items;
        number = number + 1;
    }

    /**
     * Get the number of the order
     * 
     * @return The number of the order
     */
    public int getNumber() {
        return number;
    }

    /**
     * Get the items in the order
     * 
     * @return The items in the order
     */
    public ArrayList<MenuItem> getItems() {
        return items;
    }

    /**
     * Add an item to the order
     * 
     * @param item The item to add
     */
    public void addToOrder(MenuItem item) {
        items.add(item);
    }

    /**
     * Remove an item from the order
     * 
     * @param item The item to remove
     */
    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    /**
     * Remove an item from the order by index
     * 
     * @param i The index of the item to remove
     */
    public void removeItemByIndex(int i) {
        items.remove(i);
    }

    /**
     * Get the string representation of the order
     * 
     * @return The string representation of the order
     */
    @Override
    public String toString() {
        StringBuilder orderDetails = new StringBuilder();
        for (MenuItem item : items) {
            orderDetails.append(item.toString() + "\n");
        }
        return orderDetails.toString();
    }

}