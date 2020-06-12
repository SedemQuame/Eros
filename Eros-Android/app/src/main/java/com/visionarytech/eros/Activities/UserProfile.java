package com.visionarytech.eros.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.visionarytech.eros.Dialog.ProfilePictureUploadDialog;
import com.visionarytech.eros.R;

public class UserProfile extends AppCompatActivity implements
        View.OnClickListener,
        ProfilePictureUploadDialog.ProfilePictureUploadDialogListener {
    private static final String TAG = "ProfileFragment";
    private SharedPreferences sharedPref;
    private FirebaseAuth mAuth;
    private Context context = null;
    private ImageButton imageUploadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        context = getApplicationContext();

        imageUploadButton = findViewById(R.id.imageUploadButton);
        imageUploadButton.setOnClickListener(this);
        ImageButton homeButton = findViewById(R.id.home);
        homeButton.setOnClickListener(this);
        ImageButton notificationButton = findViewById(R.id.notification);
        notificationButton.setOnClickListener(this);
        ImageButton profileSettingButton = findViewById(R.id.profile);
        profileSettingButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

//        Referencing views in the fragment view.
        TextView username = findViewById(R.id.usernameTextView);

        EditText bio = findViewById(R.id.bioEditText);
        EditText views = findViewById(R.id.viewsEditText);
        //    ==========================
        EditText work = findViewById(R.id.workEditText);
        EditText school = findViewById(R.id.schoolEditText);
        EditText religion = findViewById(R.id.religionEditText);
        //    ==========================
        EditText email = findViewById(R.id.emailAddressEditView);
        EditText phone = findViewById(R.id.phoneEditText);

//        Setting value for editTextViews in fragment with values from shared preferences.
        Context context = getApplicationContext();
        assert context != null;
        sharedPref = context.getSharedPreferences(getString((R.string.shared_preferences_of_user)), MODE_PRIVATE);

        username.setText(sharedPref.getString("Username", null));
//        username.setText(sharedPref.getString("UserId", null));

        bio.setText(sharedPref.getString("Bio", null));
        views.setText(sharedPref.getString("Views", null));
        work.setText(sharedPref.getString("Work", null));
        school.setText(sharedPref.getString("School", null));
        religion.setText(sharedPref.getString("Religion", null));
        email.setText(sharedPref.getString("Email", null));
        phone.setText(sharedPref.getString("Phone", null));

        Button logOut = findViewById(R.id.logOutButton);
        Button deleteAccount = findViewById(R.id.deleteAccountButton);
        logOut.setOnClickListener(this);
        deleteAccount.setOnClickListener(this);
    }

    private void clearPreferences() {
//        sharedPref = context.getSharedPreferences(String.valueOf(R.string.shared_preferences_of_user), MODE_PRIVATE);
        sharedPref = context.getSharedPreferences(getString((R.string.shared_preferences_of_user)), MODE_PRIVATE);
        sharedPref.edit().clear().apply();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.logOutButton):
                clearPreferences();
                mAuth.signOut();
                Intent intent = new Intent(context, UserLogin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case (R.id.deleteAccountButton):
                clearPreferences();
                mAuth.signOut();
                FirebaseUser user = mAuth.getCurrentUser();
//                todo: Must work on successfully deleting user account.
                assert user != null;
                user.delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "onComplete: Account deleted.");
                                    Intent intent = new Intent(context, UserLogin.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                } else {
                                    Log.d(TAG, "onComplete: Account not deleted.");
                                }
                            }
                        });
                break;
            case(R.id.imageUploadButton):
                openProfilePictureDialog();
                break;
            case (R.id.home):
                intent = new Intent(context, PossibleMatches.class);
                startActivity(intent);
                break;
            case (R.id.notification):
                intent = new Intent(context, Notifications.class);
                startActivity(intent);
                break;
            case (R.id.profile):
                intent = new Intent(context, UserProfile.class);
                startActivity(intent);
                break;
            default:
        }
    }

    @Override
    public void updateProgressBar(int progressNumber) {
//        do nothing
    }

//      Creating Dialog Openers.
    private void openProfilePictureDialog() {
        ProfilePictureUploadDialog profilePictureDialog = new ProfilePictureUploadDialog();
        profilePictureDialog.show(getSupportFragmentManager(), "Profile Picture");
    }
}
