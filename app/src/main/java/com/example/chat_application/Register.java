package com.example.chat_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button sign_in,sign_up;
    private EditText email,password,confirm_password,full_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sign_in = (Button) findViewById(R.id.button_signin);
        sign_up = (Button) findViewById(R.id.button_signup);
        email = (EditText) findViewById(R.id.editText_email);
        password = (EditText) findViewById(R.id.editText_password);
        confirm_password = (EditText) findViewById(R.id.editText_confirm_password);
        full_name = (EditText) findViewById(R.id.editText_name);

        mAuth = FirebaseAuth.getInstance();

        sign_up.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                registerUser();
            }
        });
    }
    private void registerUser() {
        String email_text = email.getText().toString().trim();
        String password_text = password.getText().toString().trim();
        String confirm_password_text = confirm_password.getText().toString().trim();
        String name_text = full_name.getText().toString().trim();
        if(name_text.isEmpty()) {
            full_name.setError("Username is required");
            full_name.requestFocus();
            return;
        }
        if(email_text.isEmpty()) {
            email.setError("Email is required");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email_text).matches()) {
            email.setError("Please provide valid email");
            email.requestFocus();
            return;
        }
        if(password_text.isEmpty()) {
            password.setError("Password is required");
            password.requestFocus();
            return;
        }
        if(confirm_password_text.isEmpty()) {
            confirm_password.setError("Confirm Password is required");
            confirm_password.requestFocus();
            return;
        }
        if(password_text.length() < 6) {
            password.setError("Min password length should be 6 characters");
            password.requestFocus();
            return;
        }
        if(!password_text.equals(confirm_password_text)) {
            confirm_password.setError("Passwords do not match");
            confirm_password.requestFocus();
            return;
        }


        mAuth.createUserWithEmailAndPassword(email_text, password_text);
    }
}