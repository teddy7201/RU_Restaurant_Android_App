package com.example.software_meth_project_5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.software_meth_project_5.Model.Addons;
import com.example.software_meth_project_5.Model.Bread;
import com.example.software_meth_project_5.Model.OrderManager;
import com.example.software_meth_project_5.Model.Protein;
import com.example.software_meth_project_5.Model.Sandwich;

import java.util.ArrayList;

public class SandwichActivity extends AppCompatActivity {
    private RadioGroup breadRadioGroup;
    private RadioGroup pattyRadioGroup;
    private ImageButton plusButton;
    private ImageButton minusButton;
    private TextView quantityTextView;
    private TextView priceTextView;
    private Button addToOrderButton;

    // Add-on checkboxes
    private CheckBox lettuceCheckBox;
    private CheckBox tomatoCheckBox;
    private CheckBox onionCheckBox;
    private CheckBox avocadoCheckBox;
    private CheckBox cheeseCheckBox;

    private int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sandwich_view);

        // Initialize views
        initializeViews();

        // Setup quantity buttons
        setupQuantityButtons();

        // Setup add to order button
        setupAddToOrderButton();

        // Add listeners for price updates
        setupPriceUpdateListeners();

        // Update initial price
        updatePrice();
    }

    private void initializeViews() {
        breadRadioGroup = findViewById(R.id.breadRadioGroup);
        pattyRadioGroup = findViewById(R.id.pattyRadioGroup);
        plusButton = findViewById(R.id.plusImageButton);
        minusButton = findViewById(R.id.minusImageButton);
        quantityTextView = findViewById(R.id.quantityValueTV);
        priceTextView = findViewById(R.id.salesTaxHolderTV);
        addToOrderButton = findViewById(R.id.addToOrderButton);

        // Initialize add-on checkboxes
        lettuceCheckBox = findViewById(R.id.lettuceCheckBox);
        tomatoCheckBox = findViewById(R.id.tomatoCheckBox);
        onionCheckBox = findViewById(R.id.onionCheckBox);
        avocadoCheckBox = findViewById(R.id.avocadoCheckBox);
        cheeseCheckBox = findViewById(R.id.cheeseCheckBox);
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

    private void setupPriceUpdateListeners() {
        // Add listeners for bread selection
        breadRadioGroup.setOnCheckedChangeListener((group, checkedId) -> updatePrice());

        // Add listeners for protein selection
        pattyRadioGroup.setOnCheckedChangeListener((group, checkedId) -> updatePrice());

        // Add listeners for add-ons
        lettuceCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> updatePrice());
        tomatoCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> updatePrice());
        onionCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> updatePrice());
        avocadoCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> updatePrice());
        cheeseCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> updatePrice());
    }

    private void setupAddToOrderButton() {
        addToOrderButton.setOnClickListener(v -> {
            if (!validateSelections()) {
                return;
            }

            Sandwich sandwich = createSandwich();
            OrderManager.getInstance().addItemToOrder(sandwich);
            Toast.makeText(this, "Sandwich added to order", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private boolean validateSelections() {
        if (breadRadioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select a bread type", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (pattyRadioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select a protein", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private Bread getSelectedBread() {
        int selectedId = breadRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedButton = findViewById(selectedId);
        String breadText = selectedButton.getText().toString();

        if (breadText.equals("Brioche")) {
            return Bread.BRIOCHE;
        } else if (breadText.equals("Wheat Bread")) {
            return Bread.WHEAT_BREAD;
        } else if (breadText.equals("Pretzel")) {
            return Bread.PRETZEL;
        } else if (breadText.equals("Bagel")) {
            return Bread.BAGEL;
        } else if (breadText.equals("Sourdough")) {
            return Bread.SOURDOUGH;
        } else {
            throw new IllegalStateException("Unexpected bread value: " + breadText);
        }
    }

    private Protein getSelectedProtein() {
        int selectedId = pattyRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedButton = findViewById(selectedId);
        String proteinText = selectedButton.getText().toString();

        if (proteinText.equals("Roast Beef")) {
            return Protein.ROAST_BEEF;
        } else if (proteinText.equals("Salmon")) {
            return Protein.SALMON;
        } else if (proteinText.equals("Chicken")) {
            return Protein.CHICKEN;
        } else {
            throw new IllegalStateException("Unexpected protein value: " + proteinText);
        }
    }

    private ArrayList<Addons> getSelectedAddons() {
        ArrayList<Addons> selectedAddons = new ArrayList<>();

        if (lettuceCheckBox.isChecked())
            selectedAddons.add(Addons.LETTUCE);
        if (tomatoCheckBox.isChecked())
            selectedAddons.add(Addons.TOMATOES);
        if (onionCheckBox.isChecked())
            selectedAddons.add(Addons.ONIONS);
        if (avocadoCheckBox.isChecked())
            selectedAddons.add(Addons.AVOCADO);
        if (cheeseCheckBox.isChecked())
            selectedAddons.add(Addons.CHEESE);

        return selectedAddons;
    }

    private Sandwich createSandwich() {
        Bread bread = getSelectedBread();
        Protein protein = getSelectedProtein();
        ArrayList<Addons> addons = getSelectedAddons();
        return new Sandwich(bread, protein, addons, quantity);
    }

    private void updatePrice() {
        if (!validateSelections()) {
            priceTextView.setText("$0.00");
            return;
        }

        Sandwich sandwich = createSandwich();
        double price = sandwich.price();
        priceTextView.setText(String.format("$%.2f", price));
    }
}