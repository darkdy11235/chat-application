package com.example.chat_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sign_in extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    Button googlebtn;
    TextView forgot_password, register;
    private FirebaseAuth mAuth;
    private Button sign_in;
    private EditText email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        googlebtn = findViewById(R.id.google_btn);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            navigateToSecondActivity();
        }
        googlebtn.setOnClickListener(v -> {
            Intent signInIntent = gsc.getSignInIntent();
               SignIn();
        });
        forgot_password = findViewById(R.id.textView_forgot_password);
        forgot_password.setOnClickListener(v -> {
            startActivity(new Intent(Sign_in.this, Sign_in.class));
        });
        register = findViewById(R.id.textView_register);
        register.setOnClickListener(v -> {
            startActivity(new Intent(Sign_in.this, Register.class));
        });

        sign_in = findViewById(R.id.button_sign_in);
        sign_in.setOnClickListener(v -> {
            Sign_in_with_email();
        });
        email = findViewById(R.id.editTextTextPersonName_email);
        password = findViewById(R.id.editTextTextPassword_password);
    }

    private void Sign_in_with_email() {
        String email_text = email.getText().toString();
        String password_text = password.getText().toString();
        mAuth = FirebaseAuth.getInstance();
        if (email_text.isEmpty()) {
            email.setError("Email is required");
            email.requestFocus();
            return;
        }
        if (password_text.isEmpty()) {
            password.setError("Password is required");
            password.requestFocus();
            return;
        }
        if (password_text.length() < 6) {
            password.setError("Password must be greater than 6 characters");
            password.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email_text, password_text).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    navigateToSecondActivity();
                } else {
                    Toast.makeText(Sign_in.this, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void SignIn() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            navigateToSecondActivity();
        } catch (ApiException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToSecondActivity() {
        Intent intent = new Intent( Sign_in.this, SecondActivity.class);
        startActivity(intent);
    }
}