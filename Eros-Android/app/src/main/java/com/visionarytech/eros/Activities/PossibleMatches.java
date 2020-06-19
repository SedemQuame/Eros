package com.visionarytech.eros.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.visionarytech.eros.Adapters.RecyclerViewAdapter;
import com.visionarytech.eros.Models.About;
import com.visionarytech.eros.Models.Contact;
import com.visionarytech.eros.Models.Dates;
import com.visionarytech.eros.Models.Preferences;
import com.visionarytech.eros.Models.SocialBackGround;
import com.visionarytech.eros.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PossibleMatches extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "UserProfileFragment";
    private List<Dates> listOfProspectiveDates = null;
    private Context context = null;
    private ImageButton homeButton, notificationButton, profileSettingButton;
    private SharedPreferences sharedPref;
    private CardView progressIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_possible_matches);
        sharedPref = getSharedPreferences(getString((R.string.shared_preferences_of_user)), MODE_PRIVATE);


        context = getApplicationContext();
        homeButton = findViewById(R.id.home);
        homeButton.setOnClickListener(this);

        notificationButton = findViewById(R.id.notification);
        notificationButton.setOnClickListener(this);

        profileSettingButton = findViewById(R.id.profile);
        profileSettingButton.setOnClickListener(this);

        progressIndicator = findViewById(R.id.progressIndicator);

        RequestQueue queue = Volley.newRequestQueue(context);

        //        Request a string response from the provided URL.
        String BASE_URL = "https://guarded-beach-22346.herokuapp.com";
        String REQUEST_URL = "/getAllUsers/";
        REQUEST_URL += sharedPref.getString("UserId", null);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + REQUEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        progressDialog.dismiss();
                        Log.d(TAG, "onResponse: " + response);
                        progressIndicator.setVisibility(View.GONE);

                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            JSONArray jsonArr = jsonObj.getJSONArray("users");

                            listOfProspectiveDates = new ArrayList<>();
                            for (int i = 0; i < jsonArr.length(); i++) {
                                JSONObject obj = jsonArr.getJSONObject(i);

                                String aboutMeStr = obj.getString("aboutMe");
//                                Converting aboutMe to About Object.
                                JSONObject aboutMeObj = new JSONObject(aboutMeStr);
                                About aboutDate = new About(aboutMeObj.getString("_id"),
                                        aboutMeObj.getString("bio"),
                                        aboutMeObj.getString("views")
                                );

                                String preferenceStr = obj.getString("preferences");
//                                Converting preferences to Preferences Object.
                                JSONObject preferencesObj = new JSONObject(preferenceStr);
                                Preferences datePreferences = new Preferences(
                                        preferencesObj.getString("_id"),
                                        preferencesObj.getString("gender"),
                                        preferencesObj.getString("lookingFor"),
                                        preferencesObj.getString("ageRange")
                                );

                                String socialBackgroundStr = obj.getString("socialBackground");
//                                Converting socialBackground to SocialBackGround Object.
                                JSONObject socialBackgroundObj = new JSONObject(socialBackgroundStr);
                                SocialBackGround dateSocialBackground = new SocialBackGround(
                                        socialBackgroundObj.getString("_id"),
                                        socialBackgroundObj.getString("work"),
                                        socialBackgroundObj.getString("school"),
                                        socialBackgroundObj.getString("religion")
                                );

                                String contactInformationStr = obj.getString("contactInformation");
//                                Converting socialBackground to SocialBackGround Object.
                                JSONObject contactInformationObj = new JSONObject(contactInformationStr);
                                Contact dateContact = new Contact(
                                        contactInformationObj.getString("_id"),
                                        contactInformationObj.getString("emailAddress"),
                                        contactInformationObj.getString("phoneNumber")
                                );

//                                String mediaListStr = obj.getString("mediaList");
//                                Converting mediaList string to mediaList Objects
                                Dates possibleDates = new Dates(
                                        obj.getString("_id"),
                                        obj.getString("name"),
                                        obj.getString("age"),
                                        obj.getString("location"),
                                        obj.getString("profileImg"),
                                        aboutDate,
                                        datePreferences,
                                        dateSocialBackground,
                                        dateContact,
                                        obj.getString("mediaList")

                                );
                                listOfProspectiveDates.add(possibleDates);
                            }
                            Log.d(TAG, "onSuccess: " + listOfProspectiveDates.toString());

//                          Dismiss Progress Dialog Upon Error or Response.
                            RecyclerView recyclerView = findViewById(R.id.recyclerView);
                            RecyclerViewAdapter adapter = new RecyclerViewAdapter(context, listOfProspectiveDates);
                            recyclerView.setLayoutManager(new GridLayoutManager(context, 1));
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            Log.d(TAG, "onError: Error occurred: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                progressDialog.dismiss();
                Log.d(TAG, "onError: Error occurred: " + error.getMessage());
                Toast.makeText(context, "Error Occurred: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);

    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
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
//                do nothing
        }
    }
}
