package com.example.software_meth_project_5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.software_meth_project_5.Model.BurgerCombo;
import com.example.software_meth_project_5.Model.OrderManager;
import com.example.software_meth_project_5.Model.Sandwich;

public class SandwichComboActivity extends AppCompatActivity {
    private Spinner sidesSpinner;
    private Spinner beveragesSpinner;
    private TextView itemPriceTextView;
    private TextView comboPriceTextView;
    private Button addToOrderButton;
    private Sandwich sandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sandwich_combo_view);

        // Get sandwich from intent
        sandwich = getIntent().getParcelableExtra("sandwich");
        if (sandwich == null) {
            Toast.makeText(this, "Error: No sandwich details found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Initialize views
        initializeViews();

        // Setup spinners
        setupSpinners();

        // Setup add to order button
        setupAddToOrderButton();

        // Update prices
        updatePrices();
    }

    private void initializeViews() {
        sidesSpinner = findViewById(R.id.spinner);
        beveragesSpinner = findViewById(R.id.spinner3);
        itemPriceTextView = findViewById(R.id.itemPriceHolderTextView);
        comboPriceTextView = findViewById(R.id.comboPriceHolderTextView);
        addToOrderButton = findViewById(R.id.button);
    }

    private void setupSpinners() {
        // Setup sides spinner
        ArrayAdapter<CharSequence> sidesAdapter = ArrayAdapter.createFromResource(this,
                R.array.sides_array, android.R.layout.simple_spinner_item);
        sidesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sidesSpinner.setAdapter(sidesAdapter);

        // Setup beverages spinner
        ArrayAdapter<CharSequence> beveragesAdapter = ArrayAdapter.createFromResource(this,
                R.array.beverages_array, android.R.layout.simple_spinner_item);
        beveragesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        beveragesSpinner.setAdapter(beveragesAdapter);

        // Add listeners for price updates
        sidesSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position,
                    long id) {
                updatePrices();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
            }
        });

        beveragesSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position,
                    long id) {
                updatePrices();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
            }
        });
    }

    private void updatePrices() {
        if (sandwich != null) {
            // Update item price
            double itemPrice = sandwich.price();
            itemPriceTextView.setText(String.format("$%.2f", itemPrice));

            // Create temporary combo to calculate combo price
            String selectedSide = sidesSpinner.getSelectedItem().toString();
            String selectedBeverage = beveragesSpinner.getSelectedItem().toString();
            BurgerCombo combo = new BurgerCombo(sandwich, selectedSide, selectedBeverage);

            // Update combo price
            double comboPrice = combo.price();
            comboPriceTextView.setText(String.format("$%.2f", comboPrice));
        }
    }

    private void setupAddToOrderButton() {
        addToOrderButton.setOnClickListener(v -> {
            String selectedSide = sidesSpinner.getSelectedItem().toString();
            String selectedBeverage = beveragesSpinner.getSelectedItem().toString();

            BurgerCombo combo = new BurgerCombo(sandwich, selectedSide, selectedBeverage);
            OrderManager.getInstance().addItemToOrder(combo);

            Toast.makeText(this, "Sandwich combo added to order", Toast.LENGTH_SHORT).show();

            // Create intent to return to MainActivity
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}