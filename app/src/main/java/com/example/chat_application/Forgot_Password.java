package com.example.chat_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Password extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText email;
    private MaterialButton reset_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        email = findViewById(R.id.editTextTextEmailAddress_forgot);
        reset_password = findViewById(R.id.button_reset);


        reset_password.setOnClickListener(new MaterialButton.OnClickListener() {
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        mAuth = FirebaseAuth.getInstance(); // Initialize Firebase Auth
        String email = this.email.getText().toString().trim();
        if (email.isEmpty()) {
            this.email.setError("Email is required");
            this.email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            this.email.setError("Please provide valid email");
            this.email.requestFocus();
            return;
        }
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Forgot_Password.this, "Check your email to reset your password!", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Forgot_Password.this, Sign_in.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else {
                    if (task.getException() != null) {
                        Toast.makeText(Forgot_Password.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }
}