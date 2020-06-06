package com.visionarytech.eros.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.visionarytech.eros.Dialog.AboutMeDialog;
import com.visionarytech.eros.Dialog.ContactInformationDialog;
import com.visionarytech.eros.Dialog.PreferenceDialog;
import com.visionarytech.eros.Dialog.ProfilePictureUploadDialog;
import com.visionarytech.eros.Dialog.SocialBackgroundDialog;
import com.visionarytech.eros.Networks.RequestHandler;
import com.visionarytech.eros.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ProfileBuilderActivity extends AppCompatActivity implements
        AboutMeDialog.AboutDialogListener, PreferenceDialog.PreferencesDialogListener,
        ContactInformationDialog.ContactInformationDialogListener, SocialBackgroundDialog.SocialBackgroundDialogListener,
        ProfilePictureUploadDialog.ProfilePictureUploadDialogListener {
    private LinearLayout profilePicture, aboutMe, socialBackground, contactInformation, preferences;
    private ProgressBar progressBar;
    private Button buttonNext;
    private TextView progressText;
    private int progressValue;

    // Method to encode a string value using `UTF-8` encoding scheme
    private String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_registration);

//        storing references in the view to variables, here.
        progressBar = findViewById(R.id.progressBar);
        progressText = findViewById(R.id.progressText);

        profilePicture = findViewById(R.id.profilePicture);
        aboutMe = findViewById(R.id.aboutMe);
        socialBackground = findViewById(R.id.socialBackground);
        contactInformation = findViewById(R.id.contactInformation);
        preferences = findViewById(R.id.preferences);

        buttonNext = findViewById(R.id.buttonNext);
//        adding onClickListeners to buttons

        profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openProfilePictureDialog();
            }
        });

        aboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAboutMeDialog();
            }
        });

        socialBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openSocialBackgroundDialog();
            }
        });

        contactInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openContactInformationDialog();
            }
        });

        preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPreferencesDialog();
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Store user details in the shared server.
                Context context = getApplicationContext();
                SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.shared_preferences_of_user), MODE_PRIVATE);

                StringBuilder REQUEST_URL = new StringBuilder("");
                REQUEST_URL.append("/createNewUserAccount/userName/")
                        .append(sharedPref.getString("Username", null))
                        .append("/profileImg/")
                        .append(encodeValue(sharedPref.getString("PROFILE_IMG", null)))
                        .append("/aboutMe/")
                        .append(sharedPref.getString("Bio", null))
                        .append(".")
                        .append(sharedPref.getString("Views", null))
                        .append(".")
                        .append(sharedPref.getString("Location", null))
                        .append("/preferences/")
                        .append(sharedPref.getString("Gender", null))
                        .append(".")
                        .append(sharedPref.getString("AgeRange", null))
                        .append(".")
                        .append(sharedPref.getString("LookingFor", null))
                        .append("/socialBackground/")
                        .append(sharedPref.getString("Work", null))
                        .append(".")
                        .append(sharedPref.getString("Religion", null))
                        .append(".")
                        .append(sharedPref.getString("School", null))
                        .append("/contactInformation/")
                        .append(sharedPref.getString("Email", null))
                        .append(".")
                        .append(sharedPref.getString("Phone", null));


                RequestHandler handler = new RequestHandler(getApplicationContext(), REQUEST_URL.toString(), "POST");
                handler.execute();

                Intent intent = new Intent(getApplicationContext(), ProspectsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    //      Creating Dialog Openers.
    private void openProfilePictureDialog() {
        ProfilePictureUploadDialog profilePictureDialog = new ProfilePictureUploadDialog();
        profilePictureDialog.show(getSupportFragmentManager(), "Profile Picture");
    }

    private void openAboutMeDialog() {
        AboutMeDialog aboutMeDialog = new AboutMeDialog();
        aboutMeDialog.show(getSupportFragmentManager(), "About me");
    }

    private void openSocialBackgroundDialog() {
        SocialBackgroundDialog socialBackgroundDialog = new SocialBackgroundDialog();
        socialBackgroundDialog.show(getSupportFragmentManager(), "Social Background");
    }

    private void openContactInformationDialog() {
        ContactInformationDialog contactInformationDialog = new ContactInformationDialog();
        contactInformationDialog.show(getSupportFragmentManager(), "Contact Information");
    }

    private void openPreferencesDialog() {
        PreferenceDialog preferenceDialog = new PreferenceDialog();
        preferenceDialog.show(getSupportFragmentManager(), "Preferences");
    }

    @Override
    public void updateProgressBar(int progressNumber) {
        progressBar.incrementProgressBy(progressNumber);
        progressValue += progressNumber;
//        updating progressText

        if (progressValue <= 100) {
            progressText.setText(new StringBuilder().append(progressValue).append("/100").toString());
        }
    }
}
