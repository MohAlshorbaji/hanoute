package com.example.tabeebook;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends BaseActivity implements
        View.OnClickListener {

private static final String TAG = "EmailPassword";

private TextView tvRegister;
private EditText mEmailField;
private EditText mPasswordField;

// [START declare_auth]
private FirebaseAuth mAuth;
// [END declare_auth]

@Override
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Views
        mEmailField = findViewById(R.id.editTextEmail);
        mPasswordField = findViewById(R.id.editTextPassword);
        tvRegister = findViewById(R.id.register);
        tvRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                        startActivity(intent);
                }
        });

        // Buttons
        findViewById(R.id.cirLoginButton).setOnClickListener(this);

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
        }

// [START on_start_check_user]
@Override
public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        }
// [END on_start_check_user]


private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
        return;
        }

        showProgressBar();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
@Override
public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()) {
        // Sign in success, update UI with the signed-in user's information
        Log.d(TAG, "signInWithEmail:success");
        FirebaseUser user = mAuth.getCurrentUser();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        } else {
        // If sign in fails, display a message to the user.
        Log.w(TAG, "signInWithEmail:failure", task.getException());
        Toast.makeText(LoginActivity.this, "Authentication failed.",
        Toast.LENGTH_SHORT).show();
        }

        // [START_EXCLUDE]
        if (!task.isSuccessful()) {
        }
        hideProgressBar();
        // [END_EXCLUDE]
        }
        });
        // [END sign_in_with_email]
        }


private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
        mEmailField.setError("Required.");
        valid = false;
        } else {
        mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
        mPasswordField.setError("Required.");
        valid = false;
        } else {
        mPasswordField.setError(null);
        }

        return valid;
        }


@Override
public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.cirLoginButton) {
        signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());

        }
        }
        }
