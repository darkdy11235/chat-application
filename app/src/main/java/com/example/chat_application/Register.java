package com.example.chat_application;

import androidx.appcompat.app.AppCompatActivity;

import android.app.MediaRouteButton;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;

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
        sign_up.setVisibility(View.INVISIBLE);
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email_text, password_text).addOnCompleteListener(task -> {
            if(task.isSuccessful()) {

            } else {
                Toast.makeText(Register.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                sign_up.setVisibility(View.VISIBLE);
            }
        });

//        mAuth.createUserWithEmailAndPassword(email_text, password_text)
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        FirebaseUser user = task.getResult().getUser();
//                        if (user != null) {
////                            Log.d("Huynh trongkhao", "onComplete: " + user.getUid());
//
//                            Toast.makeText(Register.this, "User has been registered successfully", Toast.LENGTH_LONG).show();
//                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(name_text).build();
//                            user.updateProfile(profileUpdates);
//                            sign_up.setVisibility(View.VISIBLE);
//                            startActivity(new Intent(Register.this, Sign_in.class));
//                        } else {
//                                    Toast.makeText(Register.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
//                                    sign_up.setVisibility(View.VISIBLE);
//                        }
//                    } else {
//                        if(task.getException().getMessage().equals("The email address is already in use by another account.")) {
//                            email.setError("The email address is already in use by another account");
//                        } else {
//                            Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
//                        }
//                        sign_up.setVisibility(View.VISIBLE);
//                    }
//                });
    }

}