package com.visionarytech.eros.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.visionarytech.eros.Fragments.AboutMeDialog;
import com.visionarytech.eros.Fragments.ContactInformationDialog;
import com.visionarytech.eros.Fragments.PreferenceDialog;
import com.visionarytech.eros.Fragments.SocialBackgroundDialog;
import com.visionarytech.eros.R;

public class ProfileRegistration extends AppCompatActivity implements
        AboutMeDialog.AboutDialogListener, PreferenceDialog.PreferencesDialogListener,
        ContactInformationDialog.ContactInformationDialogListener, SocialBackgroundDialog.SocialBackgroundDialogListener {
    private ImageButton buttonAboutMe;
    private ImageButton buttonSocialBackground;
    private ImageButton buttonContactInformation;
    private ImageButton buttonPreferences;
    private ProgressBar progressBar;
    private Button buttonNext;


    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_registration);

//        storing references in the view to variables, here.
        buttonAboutMe = findViewById(R.id.buttonAboutMe);
        buttonSocialBackground = findViewById(R.id.buttonSocialBackground);
        buttonContactInformation = findViewById(R.id.buttonContactInformation);
        buttonPreferences = findViewById(R.id.buttonPreferences);
        buttonNext = findViewById(R.id.buttonNext);
        progressBar = findViewById(R.id.progressBar);


//        adding onClickListeners to buttons
        buttonAboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAboutMeDialog();
            }
        });

        buttonSocialBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSocialBackgroundDialog();
            }
        });

        buttonContactInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openContactInformationDialog();
            }
        });

        buttonPreferences.setOnClickListener(new View.OnClickListener() {
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
    }
}
