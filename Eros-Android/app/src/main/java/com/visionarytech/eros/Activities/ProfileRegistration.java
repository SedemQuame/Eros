package com.visionarytech.eros.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.visionarytech.eros.Fragments.AboutMeDialog;
import com.visionarytech.eros.Fragments.ContactInformationDialog;
import com.visionarytech.eros.Fragments.PreferenceDialog;
import com.visionarytech.eros.Fragments.SocialBackgroundDialog;
import com.visionarytech.eros.Networks.RequestHandler;
import com.visionarytech.eros.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

public class ProfileRegistration extends AppCompatActivity implements
        AboutMeDialog.AboutDialogListener, PreferenceDialog.PreferencesDialogListener,
        ContactInformationDialog.ContactInformationDialogListener, SocialBackgroundDialog.SocialBackgroundDialogListener {
    private static final String TAG = "ProfileRegistration";
    private final String BASE_URL = "https://guarded-beach-22346.herokuapp.com";
    private LinearLayout aboutMe;
    private LinearLayout socialBackground;
    private LinearLayout contactInformation;
    private LinearLayout preferences;
    private ProgressBar progressBar;
    private Button buttonNext;
    private TextView progressText;
    private int progressValue;

    // Method to encode a string value using `UTF-8` encoding scheme
    public static String encode(String url) {

        try {

            String encodeURL = URLEncoder.encode(url, "UTF-8");

            return encodeURL;

        } catch (UnsupportedEncodingException e) {

            return "Issue while encoding" + e.getMessage();

        }

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

        aboutMe = findViewById(R.id.aboutMe);
        socialBackground = findViewById(R.id.socialBackground);
        contactInformation = findViewById(R.id.contactInformation);
        preferences = findViewById(R.id.preferences);

        buttonNext = findViewById(R.id.buttonNext);
//        adding onClickListeners to buttons
        aboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAboutMeDialog();
            }
        });

        socialBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSocialBackgroundDialog();
            }
        });

        contactInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openContactInformationDialog();
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
                        .append("/aboutMe/")
                        .append(sharedPref.getString("Bio", null))
                        .append(".")
                        .append(sharedPref.getString("Views", null))
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
    private void openAboutMeDialog() {
        AboutMeDialog aboutMeDialog = new AboutMeDialog();
        aboutMeDialog.show(getSupportFragmentManager(), "About me");
    }

    private void openSocialBackgroundDialog() {
        SocialBackgroundDialog socialBackgroundDialog = new SocialBackgroundDialog();
        socialBackgroundDialog.show(getSupportFragmentManager(), "About me");
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
