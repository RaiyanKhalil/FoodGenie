package com.example.foodgenie;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity {
    TextView login;
    EditText userEmail;
    EditText userName;
    EditText userPass;
    Button submitBtn;


    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0); // Remove shadow if needed
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1F2029"))); // Replace #FF0000 with your desired color code
        }
        firebaseAuth = FirebaseAuth.getInstance();
        databaseRef = FirebaseDatabase.getInstance().getReference();


        submitBtn = findViewById(R.id.sub_btn);
        userEmail = findViewById(R.id.user_email);
        userName = findViewById(R.id.user_name);
        userPass = findViewById(R.id.user_pass);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = userEmail.getText().toString();
                String password = userPass.getText().toString();

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                String userId = firebaseAuth.getCurrentUser().getUid();
                                String name = userName.getText().toString();
                                saveUserData(userId, name);

                            } else {
                                Toast.makeText(SignUpActivity.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }

    public void loginUser(View view) {
        login =  findViewById(R.id.login_btn);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveUserData(String userId, String name) {
        databaseRef.child("users").child(userId).child("name").setValue(name)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(this, "Failed to save user data: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }



}