package com.example.software_meth_project_5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.software_meth_project_5.Model.OrderManager;

public class AllOrdersActivity extends AppCompatActivity {
    private ListView orderItemsList;
    private Spinner orderNumberSpinner;
    private Button mainMenuButton;
    private ArrayAdapter<String> listAdapter;
    private ArrayAdapter<String> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_orders_view);

        // Initialize views
        orderItemsList = findViewById(R.id.currentOrderItemsList);
        orderNumberSpinner = findViewById(R.id.orderNumberSpinner);
        mainMenuButton = findViewById(R.id.mainMenuButton);

        // Set up adapters
        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        orderItemsList.setAdapter(listAdapter);

        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderNumberSpinner.setAdapter(spinnerAdapter);

        // Set up button listener
        mainMenuButton.setOnClickListener(v -> goHome());

        // Set up spinner listener
        orderNumberSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                String selectedOrderId = (String) parent.getItemAtPosition(position);
                displayOrderDetails(selectedOrderId);
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Load order numbers into spinner
        loadOrderNumbers();
    }

    private void loadOrderNumbers() {
        String[] orderIds = OrderManager.getInstance().getArchivedOrderIds();
        if (orderIds.length == 0) {
            Toast.makeText(this, "No completed orders found", Toast.LENGTH_SHORT).show();
            return;
        }

        spinnerAdapter.clear();
        spinnerAdapter.addAll(orderIds);
        spinnerAdapter.notifyDataSetChanged();

        // Select the first order by default
        if (orderIds.length > 0) {
            displayOrderDetails(orderIds[0]);
        }
    }

    private void displayOrderDetails(String orderId) {
        OrderManager.ArchivedOrder order = OrderManager.getInstance().getArchivedOrder(orderId);
        if (order == null) {
            Toast.makeText(this, "Order not found", Toast.LENGTH_SHORT).show();
            return;
        }

        listAdapter.clear();
        listAdapter.addAll(order.getFormattedDetails().split("\n"));
        listAdapter.notifyDataSetChanged();
    }

    private void goHome() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}