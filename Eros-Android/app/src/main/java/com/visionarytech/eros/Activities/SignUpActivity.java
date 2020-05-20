package com.visionarytech.eros.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.visionarytech.eros.R;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "SignUpActivity";
    private EditText userNameEditText, emailEditText, passwordEditText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button signUpButton = findViewById(R.id.routeToProspects);
        signUpButton.setOnClickListener(this);

        userNameEditText = findViewById(R.id.userNameTextView);
        emailEditText = findViewById(R.id.emailAddressTextView);
        passwordEditText = findViewById(R.id.passwordTextView);

        TextView routeToLogin = findViewById(R.id.routeToLogin);
        routeToLogin.setOnClickListener(this);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    private void registerUser() {
        final String userName = userNameEditText.getText().toString().trim();
        final String emailAddress = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

//        validating entered text.
        if (emailAddress.isEmpty()) {
            emailEditText.setError("Email is required");
            emailEditText.requestFocus();
//            use material snackBar here
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            emailEditText.setError("Email is not valid");
            emailEditText.requestFocus();
//            use material snackBar here
            return;
        }
        if (password.isEmpty()) {
            passwordEditText.setError("Password is required");
            passwordEditText.requestFocus();
//            use material snackBar here
            return;
        }
        if (password.length() < 8) {
            passwordEditText.setError("Password should be at least 8 characters");
            passwordEditText.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(emailAddress, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
//                      Storing Data In Shared Preferences.
                    Context context = getApplicationContext();
                    SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.shared_preferences_of_user), context.MODE_PRIVATE);
//                      Creating Editor For Shared Preferences.
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("Username", userName);
                    editor.putString("Email", emailAddress);
//                      Saving New User Preferences.
                    editor.apply();
//                      Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "createUserWithEmail:success");
                    Intent intent = new Intent(getApplicationContext(), ProfileRegistration.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
//                    Toast.makeText(SignUpActivity.this, "Authentication succeeded.",
//                            Toast.LENGTH_SHORT).show();
                } else {
                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "Email already registered.",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.routeToProspects:
                registerUser();
                break;
            case R.id.routeToLogin:
                Toast.makeText(this, "Routing to LoginActivity", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }

    }
}
