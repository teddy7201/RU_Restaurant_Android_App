package com.example.software_meth_project_5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ImageButton burgerIB;
    private ImageButton sideIB;
    private ImageButton currentOrderIB;
    private ImageButton completedOrdersIB;
    private ImageButton beverageIB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        burgerIB = findViewById(R.id.burgerImageButton);
        burgerIB.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BurgerActivity.class);
            startActivity(intent);
        });
        burgerIB = findViewById(R.id.sandwichImageButton);
        burgerIB.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SandwichActivity.class);
            startActivity(intent);
        });
        sideIB = findViewById(R.id.sideImageButton);
        sideIB.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SideActivity.class);
            startActivity(intent);
        });
        currentOrderIB = findViewById(R.id.currentOrderImageButton);
        currentOrderIB.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CurrentOrderActivity.class);
            startActivity(intent);
        });
        completedOrdersIB = findViewById(R.id.completedOrdersImageButton);
        completedOrdersIB.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AllOrdersActivity.class);
            startActivity(intent);
        });
        beverageIB = findViewById(R.id.bevImageButton);
        beverageIB.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BeverageActivity.class);
            startActivity(intent);
        });
    }
}