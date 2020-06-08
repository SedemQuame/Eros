package com.visionarytech.eros.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

public class UserProfileFragment extends Fragment {
    private static final String TAG = "UserProfileFragment";
    private final String BASE_URL = "https://guarded-beach-22346.herokuapp.com";
    private final String REQUEST_URL = "/getAllUsers";
    private List<Dates> listOfProspectiveDates = null;
    private View v = null;

    public UserProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.users_fragment, container, false);

//        Create New Progress Dialog.
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Fetching likely matches ...");
        progressDialog.show();

        RequestQueue queue = Volley.newRequestQueue(getContext());

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + this.REQUEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.d(TAG, "onResponse: " + response);
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
                            RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
                            RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), listOfProspectiveDates);
                            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            Log.d(TAG, "onError: Error occurred: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.d(TAG, "onError: Error occurred: " + error.getMessage());
                Toast.makeText(getContext(), "Error Occurred: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        return v;
    }
}
