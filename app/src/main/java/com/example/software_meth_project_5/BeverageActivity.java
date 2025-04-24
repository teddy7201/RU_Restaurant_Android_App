package com.example.software_meth_project_5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.software_meth_project_5.Model.Beverage;
import com.example.software_meth_project_5.Model.Flavor;
import com.example.software_meth_project_5.Model.OrderManager;
import com.example.software_meth_project_5.Model.Size;

import java.util.Arrays;
import java.util.List;

public class BeverageActivity extends AppCompatActivity {
    private RecyclerView beverageRecyclerView;
    private BeverageAdapter beverageAdapter;
    private RadioGroup sizeRadioGroup;
    private ImageButton plusButton;
    private ImageButton minusButton;
    private TextView quantityTextView;
    private TextView priceTextView;
    private Button addToOrderButton;

    private Flavor selectedFlavor;
    private Size selectedSize = Size.SMALL;
    private int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beverage_view);

        // Initialize views
        beverageRecyclerView = findViewById(R.id.beverageRecyclerView);
        sizeRadioGroup = findViewById(R.id.sizeRadioGroup);
        plusButton = findViewById(R.id.plusImageButton);
        minusButton = findViewById(R.id.minusImageButton);
        quantityTextView = findViewById(R.id.quantityValueTV);
        priceTextView = findViewById(R.id.salesTaxHolderTV);
        addToOrderButton = findViewById(R.id.addToOrderButton);

        // Setup RecyclerView
        setupRecyclerView();

        // Setup quantity buttons
        setupQuantityButtons();

        // Setup size selection
        setupSizeSelection();

        // Setup add to order button
        setupAddToOrderButton();

        // Update initial price
        updatePrice();
    }

    private void setupRecyclerView() {
        List<Flavor> flavors = Arrays.asList(Flavor.values());
        beverageAdapter = new BeverageAdapter(flavors, flavor -> {
            selectedFlavor = flavor;
            updatePrice();
        });
        beverageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        beverageRecyclerView.setAdapter(beverageAdapter);
    }

    private void setupQuantityButtons() {
        plusButton.setOnClickListener(v -> {
            quantity++;
            quantityTextView.setText(String.valueOf(quantity));
            updatePrice();
        });

        minusButton.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                quantityTextView.setText(String.valueOf(quantity));
                updatePrice();
            }
        });
    }

    private void setupSizeSelection() {
        sizeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.sizeSmallRadioButton) {
                selectedSize = Size.SMALL;
            } else if (checkedId == R.id.sizeMediumRadioButton) {
                selectedSize = Size.MEDIUM;
            } else if (checkedId == R.id.sizeLargeRadioButton) {
                selectedSize = Size.LARGE;
            }
            updatePrice();
        });
    }

    private void setupAddToOrderButton() {
        addToOrderButton.setOnClickListener(v -> {
            if (selectedFlavor == null) {
                Toast.makeText(this, "Please select a flavor", Toast.LENGTH_SHORT).show();
                return;
            }

            Beverage beverage = new Beverage(selectedSize, selectedFlavor, quantity);
            OrderManager.getInstance().addItemToOrder(beverage);
            Toast.makeText(this, "Beverage added to order", Toast.LENGTH_SHORT).show();
            goHome();
        });
    }

    private void updatePrice() {
        if (selectedFlavor == null) {
            priceTextView.setText("$0.00");
            return;
        }

        Beverage beverage = new Beverage(selectedSize, selectedFlavor, quantity);
        double totalPrice = beverage.price();
        priceTextView.setText(String.format("$%.2f", totalPrice));
    }

    private void goHome() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}