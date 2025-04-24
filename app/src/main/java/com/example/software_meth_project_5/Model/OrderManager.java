package com.example.software_meth_project_5.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class manages the orders in the menu.
 * 
 * @author Zeyu weng
 */
public class OrderManager {
    private static OrderManager instance = null;
    private Order currentOrder;
    private Map<String, ArchivedOrder> archivedOrders;
    private int nextOrderNumber = 1;

    /**
     * Constructor for the OrderManager class
     */
    private OrderManager() {
        // Initialize with an empty order
        currentOrder = new Order();
        archivedOrders = new HashMap<>();
    }

    /**
     *
     * @return The OrderManager instance
     */
    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    /**
     * Get the current order
     *
     * @return The current order
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Check if the current order is empty
     *
     * @return 1 if the order is empty, 0 if it is not
     */
    public Integer isOrderEmpty() {
        if (currentOrder.getItems().isEmpty()) {
            return 1;
        }
        return 0;
    }

    /**
     * Add an item to the current order
     *
     * @param item MenuItem to add to the order
     */
    public void addItemToOrder(MenuItem item) {
        currentOrder.addToOrder(item);
    }

    /**
     * Place the current order, moving it to the archived orders, and create a new
     * empty order
     *
     * @return The order ID of the placed order
     */
    public String placeOrder() {
        if (currentOrder.getItems().isEmpty()) {
            return null;
        }

        // Create an order ID
        String orderId = "Order #" + nextOrderNumber++;

        // Calculate totals for the order
        double subtotal = calculateSubtotal(currentOrder);
        double tax = calculateTax(subtotal);
        double total = subtotal + tax;

        // Create a timestamp for the order
        LocalDateTime orderTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy h:mm a");
        String timestamp = orderTime.format(formatter);

        ArchivedOrder archivedOrder = new ArchivedOrder(
                orderId,
                timestamp,
                new ArrayList<>(currentOrder.getItems()),
                subtotal,
                tax,
                total);

        // Add to archived orders
        archivedOrders.put(orderId, archivedOrder);

        startNewOrder();

        return orderId;
    }

    /**
     * Clear the current order and create a new one
     */
    public void startNewOrder() {
        currentOrder = new Order();
    }

    /**
     * Get all archived order IDs
     *
     * @return Array of order IDs
     */
    public String[] getArchivedOrderIds() {
        return archivedOrders.keySet().toArray(new String[0]);
    }

    /**
     * Get an archived order by ID
     *
     * @param orderId The order ID
     * @return The archived order, or null if not found
     */
    public ArchivedOrder getArchivedOrder(String orderId) {
        return archivedOrders.get(orderId);
    }

    /**
     * Remove an archived order by ID
     *
     * @param orderId The order ID to remove
     * @return true if the order was removed, false if it wasn't found
     */
    public boolean removeArchivedOrder(String orderId) {
        return archivedOrders.remove(orderId) != null;
    }

    /**
     * Calculate the subtotal for an order
     */
    private double calculateSubtotal(Order order) {
        double subtotal = 0.0;
        for (MenuItem item : order.getItems()) {
            subtotal += item.price();
        }
        return subtotal;
    }

    /**
     * Calculate the tax for a subtotal
     */
    private double calculateTax(double subtotal) {
        return subtotal * 0.06625; // 6.625% tax rate
    }

    /**
     * This class represents an archived order in the menu.
     * 
     * @author Zeyu weng
     */
    public static class ArchivedOrder {
        private String orderId;
        private String timestamp;
        private ArrayList<MenuItem> items;
        private double subtotal;
        private double tax;
        private double total;

        /**
         * 
         * @param orderId
         * @param timestamp
         * @param items
         * @param subtotal
         * @param tax
         * @param total
         */
        public ArchivedOrder(String orderId, String timestamp, ArrayList<MenuItem> items,
                double subtotal, double tax, double total) {
            this.orderId = orderId;
            this.timestamp = timestamp;
            this.items = items;
            this.subtotal = subtotal;
            this.tax = tax;
            this.total = total;
        }

        /**
         * Get the order ID
         * 
         * @return The order ID
         */
        public String getOrderId() {
            return orderId;
        }

        /**
         * Get the timestamp
         * 
         * @return The timestamp
         */
        public String getTimestamp() {
            return timestamp;
        }

        /**
         * Get the items
         * 
         * @return The items
         */
        public ArrayList<MenuItem> getItems() {
            return items;
        }

        /**
         * Get the subtotal
         * 
         * @return The subtotal
         */
        public double getSubtotal() {
            return subtotal;
        }

        /**
         * Get the tax
         * 
         * @return The tax
         */
        public double getTax() {
            return tax;
        }

        /**
         * Get the total
         * 
         * @return The total
         */
        public double getTotal() {
            return total;
        }

        /**
         * Get the formatted details
         * 
         * @return The formatted details
         */
        public String getFormattedDetails() {
            StringBuilder details = new StringBuilder();
            details.append(orderId).append("\n");
            details.append("Date: ").append(timestamp).append("\n");
            details.append("Items:\n");

            for (MenuItem item : items) {
                details.append("- ").append(item.toString().replace("\n", " - "))
                        .append(" - $").append(String.format("%.2f", item.price())).append("\n");
            }

            details.append("\nSubtotal: $").append(String.format("%.2f", subtotal)).append("\n");
            details.append("Tax: $").append(String.format("%.2f", tax)).append("\n");
            details.append("Total: $").append(String.format("%.2f", total));

            return details.toString();
        }
    }
}