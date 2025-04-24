package com.example.software_meth_project_5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.software_meth_project_5.Model.OrderManager;
import com.example.software_meth_project_5.Model.Side;
import com.example.software_meth_project_5.Model.SideType;
import com.example.software_meth_project_5.Model.Size;

public class SideActivity extends AppCompatActivity {
    private RadioGroup sizeRadioGroup;
    private RadioGroup sideTypeRadioGroup;
    private ImageButton plusButton;
    private ImageButton minusButton;
    private TextView quantityTextView;
    private TextView priceTextView;
    private Button addToOrderButton;
    private int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.side_view);

        // Initialize views
        sizeRadioGroup = findViewById(R.id.sizeRadioGroup);
        sideTypeRadioGroup = findViewById(R.id.sideTypeRadioGroup);
        plusButton = findViewById(R.id.plusImageButton);
        minusButton = findViewById(R.id.minusImageButton);
        quantityTextView = findViewById(R.id.quantityValueTV);
        priceTextView = findViewById(R.id.salesTaxHolderTV);
        addToOrderButton = findViewById(R.id.addToOrderButton);

        // Set initial quantity
        quantityTextView.setText(String.valueOf(quantity));

        // Set up button listeners
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

        // Set up radio group listeners
        sizeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> updatePrice());
        sideTypeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> updatePrice());

        // Set up add to order button
        addToOrderButton.setOnClickListener(v -> addToOrder());
    }

    private void updatePrice() {
        Side side = createSide();
        if (side != null) {
            priceTextView.setText(String.format("$%.2f", side.price()));
        }
    }

    private Side createSide() {
        // Get selected size
        Size size = Size.SMALL;
        int selectedSizeId = sizeRadioGroup.getCheckedRadioButtonId();
        if (selectedSizeId == R.id.sizeMediumRadioButton) {
            size = Size.MEDIUM;
        } else if (selectedSizeId == R.id.sizeLargeRadioButton) {
            size = Size.LARGE;
        }

        // Get selected side type
        SideType type = null;
        int selectedTypeId = sideTypeRadioGroup.getCheckedRadioButtonId();
        if (selectedTypeId == R.id.radioButton2) {
            type = SideType.CHIPS;
        } else if (selectedTypeId == R.id.radioButton3) {
            type = SideType.FRIES;
        } else if (selectedTypeId == R.id.radioButton4) {
            type = SideType.ONION_RINGS;
        } else if (selectedTypeId == R.id.radioButton5) {
            type = SideType.APPLE_SLICES;
        }

        if (type == null) {
            return null;
        }

        return new Side(size, type, quantity);
    }

    private void addToOrder() {
        Side side = createSide();
        if (side != null) {
            OrderManager.getInstance().addItemToOrder(side);
            Toast.makeText(this, "Side added to order", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Please select a side type", Toast.LENGTH_SHORT).show();
        }
    }
}