package com.visionarytech.eros.Networks;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RequestHandler {
    private static final String TAG = "RequestHandler";
    private Context PARENT_CONTEXT;
    private String REQUEST_URL;
    private String returnedValue = "";

    public RequestHandler(Context CONTEXT, String REQUEST_URL) {
        this.PARENT_CONTEXT = CONTEXT.getApplicationContext();
        this.REQUEST_URL = REQUEST_URL.replace(" ", "%20");
    }

    public String getReturnedValue() {
        return returnedValue;
    }

    private void setReturnedValue(String values) {
        this.returnedValue = values;
    }

    public String execute() {
        String BASE_URL = "https://guarded-beach-22346.herokuapp.com";
        Log.d(TAG, "String: " + BASE_URL + REQUEST_URL);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this.PARENT_CONTEXT);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + this.REQUEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "onResponse: " + response);
                        //                Converting string to json and storing values.
                        try {
                            JSONObject jsonObj = new JSONObject(response);

                            if (jsonObj.has("_id")) {
                                setReturnedValue(jsonObj.get("_id").toString());
                            }

                            if (jsonObj.has("msg")) {
                                Toast.makeText(PARENT_CONTEXT,
                                        "" + jsonObj.get("msg").toString(),
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
        return getReturnedValue();
    }
}
