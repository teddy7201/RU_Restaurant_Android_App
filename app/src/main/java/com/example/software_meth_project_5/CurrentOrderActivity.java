package com.example.software_meth_project_5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.software_meth_project_5.Model.MenuItem;
import com.example.software_meth_project_5.Model.OrderManager;

import java.util.ArrayList;

public class CurrentOrderActivity extends AppCompatActivity {
    private ListView orderItemsList;
    private TextView subtotalTV;
    private TextView salesTaxTV;
    private TextView orderTotalTV;
    private Button placeOrderButton;
    private Button cancelOrderButton;
    private Button removeSelectedItemButton;
    private Button goHomeButton;
    private ArrayAdapter<String> adapter;
    private int selectedPosition = ListView.INVALID_POSITION;
    private TextView orderNumberHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_order_view);

        orderItemsList = findViewById(R.id.currentOrderItemsList);
        subtotalTV = findViewById(R.id.orderTotalHolderTV);
        salesTaxTV = findViewById(R.id.salesTaxHolderTV);
        orderTotalTV = findViewById(R.id.subtotalHolderTV);
        placeOrderButton = findViewById(R.id.placeOrderButton);
        cancelOrderButton = findViewById(R.id.cancelOrderButton);
        removeSelectedItemButton = findViewById(R.id.removeSelectedItemButton);
        goHomeButton = findViewById(R.id.goHomeButton);
        orderNumberHolder = findViewById(R.id.orderNumberHolderTV);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, new ArrayList<>());
        orderItemsList.setAdapter(adapter);
        orderItemsList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        orderItemsList.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            removeSelectedItemButton.setEnabled(true);
        });

        placeOrderButton.setOnClickListener(v -> placeOrder());
        cancelOrderButton.setOnClickListener(v -> cancelOrder());
        removeSelectedItemButton.setOnClickListener(v -> removeSelectedItem());
        goHomeButton.setOnClickListener(v -> goHome());

        removeSelectedItemButton.setEnabled(false);

        updateOrderDisplay();
    }

    private void updateOrderDisplay() {
        adapter.clear();
        ArrayList<MenuItem> items = OrderManager.getInstance().getCurrentOrder().getItems();
        for (MenuItem item : items) {
            adapter.add(item.toString() + " - $" + String.format("%.2f", item.price()));
        }
        adapter.notifyDataSetChanged();

        selectedPosition = ListView.INVALID_POSITION;
        removeSelectedItemButton.setEnabled(false);
        orderItemsList.clearChoices();

        orderNumberHolder.setText(String.format("%d", OrderManager.getInstance().getCurrentOrder().getNumber()));

        double subtotal = calculateSubtotal();
        double tax = calculateTax(subtotal);
        double total = subtotal + tax;

        subtotalTV.setText(String.format("$%.2f", subtotal));
        salesTaxTV.setText(String.format("$%.2f", tax));
        orderTotalTV.setText(String.format("$%.2f", total));
    }

    private double calculateSubtotal() {
        double subtotal = 0.0;
        for (MenuItem item : OrderManager.getInstance().getCurrentOrder().getItems()) {
            subtotal += item.price();
        }
        return subtotal;
    }

    private double calculateTax(double subtotal) {
        return subtotal * 0.06625;
    }

    private void placeOrder() {
        if (OrderManager.getInstance().getCurrentOrder().getItems().isEmpty()) {
            Toast.makeText(this, "Order is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        String orderId = OrderManager.getInstance().placeOrder();
        if (orderId != null) {
            Toast.makeText(this, "Order placed successfully: " + orderId, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void cancelOrder() {
        if(OrderManager.getInstance().getCurrentOrder().getItems().isEmpty()){
            Toast.makeText(this, "Order is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        OrderManager.getInstance().startNewOrder();
        updateOrderDisplay();
        Toast.makeText(this, "Order cancelled", Toast.LENGTH_SHORT).show();
    }

    private void removeSelectedItem() {
        if (selectedPosition != ListView.INVALID_POSITION) {
            OrderManager.getInstance().getCurrentOrder().removeItemByIndex(selectedPosition);
            updateOrderDisplay();
            Toast.makeText(this, "Item removed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please select an item to remove", Toast.LENGTH_SHORT).show();
        }
    }

    private void goHome() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}