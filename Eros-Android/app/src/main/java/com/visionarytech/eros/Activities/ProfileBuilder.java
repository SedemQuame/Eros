package com.visionarytech.eros.Activities;

import android.annotation.SuppressLint;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.visionarytech.eros.Dialog.AboutMeDialog;
import com.visionarytech.eros.Dialog.ContactInformationDialog;
import com.visionarytech.eros.Dialog.PreferenceDialog;
import com.visionarytech.eros.Dialog.ProfilePictureUploadDialog;
import com.visionarytech.eros.Dialog.SocialBackgroundDialog;
import com.visionarytech.eros.Networks.RequestHandler;
import com.visionarytech.eros.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ProfileBuilder extends AppCompatActivity implements
        AboutMeDialog.AboutDialogListener, PreferenceDialog.PreferencesDialogListener,
        ContactInformationDialog.ContactInformationDialogListener, SocialBackgroundDialog.SocialBackgroundDialogListener,
        ProfilePictureUploadDialog.ProfilePictureUploadDialogListener {
    private ProgressBar progressBar;
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_registration);

//        storing references in the view to variables, here.
        progressBar = findViewById(R.id.progressBar);
        progressText = findViewById(R.id.progressText);

        LinearLayout profilePicture = findViewById(R.id.profilePicture);
        LinearLayout aboutMe = findViewById(R.id.aboutMe);
        LinearLayout socialBackground = findViewById(R.id.socialBackground);
        LinearLayout contactInformation = findViewById(R.id.contactInformation);
        LinearLayout preferences = findViewById(R.id.preferences);

        Button buttonNext = findViewById(R.id.buttonNext);
//        adding onClickListeners to buttons

        profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfilePictureDialog();
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
//                      Store user details in the shared server.
                SharedPreferences sharedPref = getSharedPreferences(getString((R.string.shared_preferences_of_user)), Context.MODE_PRIVATE);

                String REQUEST_URL = "/createNewUserAccount/userName/" +
                        sharedPref.getString("Username", null) +
                        "/profileImg/" +
                        encodeValue(sharedPref.getString("PROFILE_IMG", null)) +
                        "/aboutMe/" +
                        sharedPref.getString("Bio", null) +
                        "." +
                        sharedPref.getString("Views", null) +
                        "." +
                        sharedPref.getString("Location", null) +
                        "/preferences/" +
                        sharedPref.getString("Gender", null) +
                        "." +
                        sharedPref.getString("AgeRange", null) +
                        "." +
                        sharedPref.getString("LookingFor", null) +
                        "/socialBackground/" +
                        sharedPref.getString("Work", null) +
                        "." +
                        sharedPref.getString("Religion", null) +
                        "." +
                        sharedPref.getString("School", null) +
                        "/contactInformation/" +
                        sharedPref.getString("Email", null) +
                        "." +
                        sharedPref.getString("Phone", null);

//                RequestHandler handler = new RequestHandler(getApplicationContext(), REQUEST_URL);
////                handler.execute();

                String BASE_URL = "https://guarded-beach-22346.herokuapp.com";

                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + REQUEST_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
//                                Log.d(TAG, "onResponse: " + response);
                                //                Converting string to json and storing values.
                                try {
                                    JSONObject jsonObj = new JSONObject(response);

                                    if (jsonObj.has("_id")) {
//                                          Creating Editor For Shared Preferences.
                                        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.shared_preferences_of_user), Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPref.edit();
                                        editor.putString("UserId", jsonObj.get("_id").toString());
//                                          Saving New User Preferences.
                                        editor.apply();
                                    }

                                    if (jsonObj.has("msg")) {
                                        Toast.makeText(getApplicationContext(),
                                                "" + jsonObj.get("msg").toString() + " with id " + jsonObj.get("_id").toString(),
                                                Toast.LENGTH_SHORT
                                        ).show();
                                    }

                                } catch (JSONException e) {
                                    Log.d("Error", e.toString());
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);


                Intent intent = new Intent(getApplicationContext(), PossibleMatches.class);
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

    @SuppressLint("DefaultLocale")
    @Override
    public void updateProgressBar(int progressNumber) {
        progressBar.incrementProgressBy(progressNumber);
        progressValue += progressNumber;
//        updating progressText

        if (progressValue <= 100) {
            progressText.setText(String.format("%d/100", progressValue));
        }
    }
}
