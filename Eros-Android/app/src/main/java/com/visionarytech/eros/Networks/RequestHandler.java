package com.visionarytech.eros.Networks;

import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

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
import com.visionarytech.eros.Models.Dates;
import com.visionarytech.eros.Models.Preferences;
import com.visionarytech.eros.Models.SocialBackGround;
import com.visionarytech.eros.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RequestHandler {
    private static final String TAG = "RequestHandler";
    private final String BASE_URL = "https://guarded-beach-22346.herokuapp.com";
    private Context CONTEXT;
    private String REQUEST_URL;
    private String METHOD;


    public RequestHandler(Context CONTEXT, String REQUEST_URL, String METHOD) {
        this.CONTEXT = CONTEXT;
        this.REQUEST_URL = REQUEST_URL.replace(" ", "%20");
        this.METHOD = METHOD;
    }

    public void execute() {
        Log.d(TAG, "String: " + BASE_URL + REQUEST_URL);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this.CONTEXT);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + this.REQUEST_URL,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "onResponse: " + response);
                    //                Converting string to json and storing values.
                    try {
                        JSONObject jsonObj = new JSONObject(response);
                        Log.d(TAG, "onSubmit: " + jsonObj.get("_id"));

//                        Storing user_id in user preferences.

                        SharedPreferences sharedPref = CONTEXT.getSharedPreferences(String.valueOf(R.string.shared_preferences_of_user), CONTEXT.MODE_PRIVATE);
//                      Creating Editor For Shared Preferences.
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("UserId", jsonObj.get("_id").toString());
//                      Saving New User Preferences.
                        editor.apply();
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
    }

//    public List<Dates> getUserProfiles(final ProgressDialog progressDialog){
//        // Instantiate the RequestQueue.
//        RequestQueue queue = Volley.newRequestQueue(this.CONTEXT);
//
//        // Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + this.REQUEST_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        progressDialog.dismiss();
//                        Log.d(TAG, "onResponse: " + response);
//                        try {
//                            JSONObject jsonObj = new JSONObject(response);
//                            JSONArray jsonArr = jsonObj.getJSONArray("users");
//                            for (int i = 0; i < jsonArr.length(); i++) {
//                                JSONObject obj = jsonArr.getJSONObject(i);
//
//                                String aboutMeStr = obj.getString("aboutMe");
////                                Converting aboutMe to About Object.
//                                JSONObject aboutMeObj = new JSONObject(aboutMeStr);
//                                About aboutDate = new About(aboutMeObj.getString("_id"),
//                                                        aboutMeObj.getString("bio"),
//                                                        aboutMeObj.getString("views")
//                                );
//
//                                String preferenceStr = obj.getString("preferences");
////                                Converting preferences to Preferences Object.
//                                JSONObject preferencesObj = new JSONObject(preferenceStr);
//                                Preferences datePreferences = new Preferences(
//                                        preferencesObj.getString("_id"),
//                                        preferencesObj.getString("gender"),
//                                        preferencesObj.getString("lookingFor"),
//                                        preferencesObj.getString("ageRange")
//                                );
//
//                                String socialBackgroundStr = obj.getString("socialBackground");
////                                Converting socialBackground to SocialBackGround Object.
//                                JSONObject socialBackgroundObj = new JSONObject(socialBackgroundStr);
//                                SocialBackGround dateSocialBackground = new SocialBackGround(
//                                        socialBackgroundObj.getString("_id"),
//                                        socialBackgroundObj.getString("work"),
//                                        socialBackgroundObj.getString("school"),
//                                        socialBackgroundObj.getString("religion")
//                                );
//
//                                Dates possibleDates = new Dates(
//                                        obj.getString("_id"),
//                                        obj.getString("name"),
//                                        obj.getString("age"),
//                                        aboutDate,
//                                        datePreferences,
//                                        dateSocialBackground
//                                );
//                                listOfProspectiveDates.add(possibleDates);
//                            }
//                            Log.d(TAG, "onSuccess: " + getListOfProspectiveDates().toString());
//                        } catch (JSONException e) {
//                            Log.d(TAG, "onError: Error occurred: " + e.getMessage());
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                progressDialog.dismiss();
//                Toast.makeText(CONTEXT, "Error Occurred: " + error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        // Add the request to the RequestQueue.
//        queue.add(stringRequest);
//
//        return getListOfProspectiveDates();
//    }
}
