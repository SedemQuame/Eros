package com.visionarytech.eros.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.visionarytech.eros.Adapters.NotificationViewAdapter;
import com.visionarytech.eros.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Notifications extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "NotificationsFragment";
    private List<com.visionarytech.eros.Models.Notifications> notificationsList = null;
    private Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        context = getApplicationContext();
        ImageButton homeButton = findViewById(R.id.home);
        homeButton.setOnClickListener(this);
        ImageButton notificationButton = findViewById(R.id.notification);
        notificationButton.setOnClickListener(this);
        ImageButton profileSettingButton = findViewById(R.id.profile);
        profileSettingButton.setOnClickListener(this);

        SharedPreferences sharedPref = context.getSharedPreferences(getString((R.string.shared_preferences_of_user)), MODE_PRIVATE);

        String BASE_URL = "https://guarded-beach-22346.herokuapp.com";
        String REQUEST_URL = "/getUserNotifications/" + sharedPref.getString("UserId", null) + "/";

        Log.d(TAG, "String: " + BASE_URL + REQUEST_URL);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + REQUEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "onNotification: " + response);

//                          Converting string to json and storing values.
                        notificationsList = new ArrayList<>();
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            JSONArray jsonArr = jsonObj.getJSONArray("notifications");

                            for (int i = 0; i < jsonArr.length(); i++) {
                                JSONObject obj = jsonArr.getJSONObject(i);

                                com.visionarytech.eros.Models.Notifications notifications = new com.visionarytech.eros.Models.Notifications(
                                        obj.getString("subject"),
                                        obj.getString("date"),
                                        obj.getString("requesterImg")
                                );

                                notificationsList.add(notifications);
                            }

                            RecyclerView recyclerView = findViewById(R.id.notificationQueue);
                            NotificationViewAdapter recyclerAdapter = new NotificationViewAdapter(context, notificationsList);
                            recyclerView.setLayoutManager(new LinearLayoutManager(context));
                            recyclerView.setAdapter(recyclerAdapter);

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
