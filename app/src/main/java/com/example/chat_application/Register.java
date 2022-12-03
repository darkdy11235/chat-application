package com.example.chat_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Locale;

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private MaterialButton sign_up;
    private TextView sign_in;
    private EditText email,password,confirm_password,user_name;
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        sign_in = (TextView) findViewById(R.id.textView_Login);
        sign_up = (MaterialButton) findViewById(R.id.button_signup);
        email = (EditText) findViewById(R.id.editText_email);
        password = (EditText) findViewById(R.id.editText_password);
        confirm_password = (EditText) findViewById(R.id.editText_confirm_password);
        user_name = (EditText) findViewById(R.id.editText_userName);

        mAuth = FirebaseAuth.getInstance();

        sign_up.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                registerUser();
            }
        });
        sign_in.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Sign_in.class));
            }
        });
    }
    private void registerUser() {
        String email_text = email.getText().toString().trim();
        String password_text = password.getText().toString().trim();
        String confirm_password_text = confirm_password.getText().toString().trim();
        String user_name_text = user_name.getText().toString().trim();
        mAuth = FirebaseAuth.getInstance();
        if(user_name_text.isEmpty()) {
            user_name.setError("Username is required");
            user_name.requestFocus();
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
                FirebaseUser userFireBase = FirebaseAuth.getInstance().getCurrentUser();
                assert userFireBase != null;
                String uid = userFireBase.getUid();
                reference = FirebaseDatabase.getInstance().getReference("Users").child(uid);
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("id", uid);
                hashMap.put("username", user_name_text);
                hashMap.put("email", email_text);
                hashMap.put("imageURL", "default");
                hashMap.put("status", "offline");
                hashMap.put("search", user_name_text.toLowerCase());
                reference.setValue(hashMap).addOnCompleteListener(task1 -> {
                    if(task1.isSuccessful()) {
                        Toast.makeText(Register.this, task1.getException().toString(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Register.this,   Sign_in.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(Register.this, task1.getException().toString(), Toast.LENGTH_LONG).show();
                        sign_up.setVisibility(View.VISIBLE);
                    }
                });
            } else {
                Toast.makeText(Register.this, task.getException().toString(), Toast.LENGTH_LONG).show();
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
