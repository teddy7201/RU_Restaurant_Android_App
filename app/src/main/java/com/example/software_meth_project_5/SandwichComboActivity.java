package com.example.software_meth_project_5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.software_meth_project_5.Model.Beverage;
import com.example.software_meth_project_5.Model.Combo;
import com.example.software_meth_project_5.Model.Flavor;
import com.example.software_meth_project_5.Model.OrderManager;
import com.example.software_meth_project_5.Model.Sandwich;
import com.example.software_meth_project_5.Model.Side;
import com.example.software_meth_project_5.Model.SideType;
import com.example.software_meth_project_5.Model.Size;

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

        sandwich = getIntent().getParcelableExtra("sandwich");
        if (sandwich == null) {
            Toast.makeText(this, "Error: No sandwich details found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        initializeViews();

        setupSpinners();

        setupAddToOrderButton();

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
        ArrayAdapter<CharSequence> sidesAdapter = ArrayAdapter.createFromResource(this,
                R.array.sides_array, android.R.layout.simple_spinner_item);
        sidesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sidesSpinner.setAdapter(sidesAdapter);

        ArrayAdapter<CharSequence> beveragesAdapter = ArrayAdapter.createFromResource(this,
                R.array.beverages_array, android.R.layout.simple_spinner_item);
        beveragesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        beveragesSpinner.setAdapter(beveragesAdapter);

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
            double itemPrice = sandwich.price();
            itemPriceTextView.setText(String.format("$%.2f", itemPrice));

            String selectedSide = sidesSpinner.getSelectedItem().toString();
            Flavor selectedFlavor = getBeverageFlavor(beveragesSpinner.getSelectedItem().toString());

            SideType sideType = getSideType(selectedSide);
            Side side = new Side(Size.MEDIUM, sideType, sandwich.getQuantity());

            Beverage beverage = new Beverage(Size.MEDIUM, selectedFlavor, sandwich.getQuantity());
            Combo combo = new Combo(sandwich, beverage, side, sandwich.getQuantity());

            double comboPrice = combo.price();
            comboPriceTextView.setText(String.format("$%.2f", comboPrice));
        }
    }

    private void setupAddToOrderButton() {
        addToOrderButton.setOnClickListener(v -> {
            String selectedSide = sidesSpinner.getSelectedItem().toString();
            Flavor selectedFlavor = getBeverageFlavor(beveragesSpinner.getSelectedItem().toString());

            SideType sideType = getSideType(selectedSide);
            Side side = new Side(Size.MEDIUM, sideType, sandwich.getQuantity());

            Beverage beverage = new Beverage(Size.MEDIUM, selectedFlavor, sandwich.getQuantity());
            Combo combo = new Combo(sandwich, beverage, side, sandwich.getQuantity());
            OrderManager.getInstance().addItemToOrder(combo);

            Toast.makeText(this, "Sandwich combo added to order", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }

    private Flavor getBeverageFlavor(String beverageName) {
        switch (beverageName) {
            case "Coke":
                return Flavor.COKE;
            case "Iced Tea":
                return Flavor.ICED_TEA;
            case "Apple Juice":
                return Flavor.APPLE_JUICE;
            case "Water":
                return Flavor.WATER;
            default:
                return Flavor.COKE;
        }
    }

    private SideType getSideType(String sideName) {
        switch (sideName) {
            case "French Fries":
                return SideType.FRIES;
            case "Chips":
                return SideType.CHIPS;
            case "Apple Slices":
                return SideType.APPLE_SLICES;
            default:
                throw new IllegalArgumentException("Unknown side type: " + sideName);
        }
    }
}