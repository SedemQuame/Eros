package com.visionarytech.eros.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.visionarytech.eros.R;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {


    private EditText emailAddressEdtView;
    private Button resetPasswordBtn;
    private FirebaseAuth mAuth;
    private TextView routeToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        emailAddressEdtView = findViewById(R.id.emailAddressEditView);

        resetPasswordBtn = findViewById(R.id.resetPasswordButton);
        resetPasswordBtn.setOnClickListener(this);

        routeToLogin = findViewById(R.id.routeToLogin);
        routeToLogin.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    private void resetPassword(){
        String emailAddress = emailAddressEdtView.getText().toString();
        //        validating entered text.
        if (emailAddress.isEmpty()) {
            emailAddressEdtView.setError("Email is required");
            emailAddressEdtView.requestFocus();
//            use material snackbar here
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            emailAddressEdtView.setError("Email is not valid");
            emailAddressEdtView.requestFocus();
//            use material snackbar here
            return;
        }

        mAuth.sendPasswordResetEmail(emailAddress).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this, "Password reset link sent to your email", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(ForgotPassword.this, "Unable to send password reset link.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case(R.id.resetPasswordButton):
//                Call function to reset password with email address.
                resetPassword();
                break;
            case(R.id.routeToLogin):
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }

    }
}
