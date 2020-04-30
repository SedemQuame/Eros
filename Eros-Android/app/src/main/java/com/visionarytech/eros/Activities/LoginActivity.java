package com.visionarytech.eros.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.visionarytech.eros.ForgotPassword;
import com.visionarytech.eros.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private EditText emailEditText, passwordEditText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        Activity Button
        Button loginButton = findViewById(R.id.routeToProspects);
        loginButton.setOnClickListener(this);

//        Activity TextView
        emailEditText = findViewById(R.id.emailAddressTextView);
        passwordEditText = findViewById(R.id.passwordTextView);

        TextView routeToSignUp = findViewById(R.id.routeToSignUp);
        routeToSignUp.setOnClickListener(this);

        TextView routeToForgotPassword = findViewById(R.id.routeToForgotPassword);
        routeToForgotPassword.setOnClickListener(this);

        // Initialize FireBase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    private void userLogin() {
        String emailAddress = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        //        validating entered text.
        if (emailAddress.isEmpty()) {
            emailEditText.setError("Email is required");
            emailEditText.requestFocus();
//            use material snackbar here
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            emailEditText.setError("Email is not valid");
            emailEditText.requestFocus();
//            use material snackbar here
            return;
        }

        if (password.isEmpty()) {
            passwordEditText.setError("Password is required");
            passwordEditText.requestFocus();
//            use material snackbar here
            return;
        }

        if (password.length() < 8) {
            passwordEditText.setError("Password should be at least 8 characters");
            passwordEditText.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(emailAddress, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent(getApplicationContext(), ProspectsActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            finish();
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.routeToProspects:
                userLogin();
                break;
            case R.id.routeToSignUp:
                Toast.makeText(this, "Routing to SignUPActivity", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;
            case R.id.routeToForgotPassword:
                Toast.makeText(this, "Routing to ForgotPasswordActivity", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, ForgotPassword.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), ProspectsActivity.class));
        }
    }
}
