package com.visionarytech.eros.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.visionarytech.eros.Fragments.AboutMeDialog;
import com.visionarytech.eros.Fragments.ContactInformationDialog;
import com.visionarytech.eros.Fragments.PreferenceDialog;
import com.visionarytech.eros.Fragments.SocialBackgroundDialog;
import com.visionarytech.eros.R;

public class ProfileRegistration extends AppCompatActivity implements
        AboutMeDialog.AboutDialogListener, PreferenceDialog.PreferencesDialogListener,
        ContactInformationDialog.ContactInformationDialogListener, SocialBackgroundDialog.SocialBackgroundDialogListener {
    private LinearLayout aboutMe;
    private LinearLayout socialBackground;
    private LinearLayout contactInformation;
    private LinearLayout preferences;
    private ProgressBar progressBar;
    private Button buttonNext;
    private TextView progressText;
    private int progressValue;


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
                Intent intent = new Intent(getApplicationContext(), ProspectsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    private void openAboutMeDialog(){
        AboutMeDialog aboutMeDialog = new AboutMeDialog();
        aboutMeDialog.show(getSupportFragmentManager(), "About me");
    }

    private void openSocialBackgroundDialog(){
        SocialBackgroundDialog socialBackgroundDialog = new SocialBackgroundDialog();
        socialBackgroundDialog.show(getSupportFragmentManager(), "About me");
    }

    private void openContactInformationDialog(){
        ContactInformationDialog contactInformationDialog = new ContactInformationDialog();
        contactInformationDialog.show(getSupportFragmentManager(), "Contact Information");
    }

    private void openPreferencesDialog(){
        PreferenceDialog preferenceDialog = new PreferenceDialog();
        preferenceDialog.show(getSupportFragmentManager(), "Preferences");
    }

    @Override
    public void updateProgressBar(int progressNumber) {
        progressBar.incrementProgressBy(progressNumber);
        progressValue += progressNumber;
//        updating progressText

        if(progressValue < 100){
            progressText.setText(new StringBuilder().append(progressValue).append("/100").toString());
        }
    }
}
