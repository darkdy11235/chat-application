package com.example.chat_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class SecondActivity extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name,email;
    Button signout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        name = findViewById(R.id.textView_name);
        email = findViewById(R.id.textView_email);
        signout = findViewById(R.id.button_signout);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            String personName = account.getDisplayName();
            String personEmail = account.getEmail();
            name.setText(personName);
            email.setText(personEmail);
        }

        signout.setOnClickListener(v -> {
            gsc.signOut();
            finish();
            nav();
        });


    }

    private void nav() {
        startActivity(new Intent(SecondActivity.this, Sign_in.class));
    }
}