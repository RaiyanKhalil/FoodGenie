package com.example.foodgenie;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    Button recipeBtn, analyzeBtn;
    TextView welcomeMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0); // Remove shadow if needed
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1F2029"))); // Replace #FF0000 with your desired color code
        }

        Intent intent = getIntent();
        String str = intent.getStringExtra("userName");
//        Toast.makeText(HomeActivity.this, "yolo, " + str + "!", Toast.LENGTH_SHORT).show();

        welcomeMsg = findViewById(R.id.txtWelcomeName);
        welcomeMsg.setText("Welcome to Food Genie, "+ str + ".");

        analyzeBtn = findViewById(R.id.analyzeBtn);

        analyzeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NutrientActivity.class);
                startActivity(intent);
            }
        });

        recipeBtn = findViewById(R.id.recipeBtn);

        recipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RecipesActivity.class);
                startActivity(intent);
            }
        });


    }
}