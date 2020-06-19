package com.visionarytech.eros.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;
import com.visionarytech.eros.Dialog.ProfilePictureUploadDialog;
import com.visionarytech.eros.Fragments.MatchGallery;
import com.visionarytech.eros.Networks.RequestHandler;
import com.visionarytech.eros.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.visionarytech.eros.Utils.RequestFormatter.encodeValue;

public class UserProfile extends AppCompatActivity implements
        View.OnClickListener,
        ProfilePictureUploadDialog.ProfilePictureUploadDialogListener {
    private static final String TAG = "ProfileFragment";

    private MatchGallery gallery = new MatchGallery();

    private Context context = null;
    private SharedPreferences sharedPref;

    private FirebaseAuth mAuth;
    private FragmentManager manager = getSupportFragmentManager();
    private FragmentTransaction transaction = manager.beginTransaction();

    private String mediaList = "", REQUEST_URL = "/addNewPicture/", REQUEST_URL_MEDIA = "/getUserMediaList/",
            DELETE_ACCOUNT_REQUEST_URL = "/deleteExistingAccount/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        context = getApplicationContext();

        ImageButton imageUploadButton = findViewById(R.id.imageUploadButton);
        imageUploadButton.setOnClickListener(this);

        ImageButton homeButton = findViewById(R.id.home);
        homeButton.setOnClickListener(this);

        ImageButton notificationButton = findViewById(R.id.notification);
        notificationButton.setOnClickListener(this);

        ImageButton profileSettingButton = findViewById(R.id.profile);
        profileSettingButton.setOnClickListener(this);

        ImageView userProfilePicture = findViewById(R.id.userProfilePicture);

        mAuth = FirebaseAuth.getInstance();

//        Referencing views in the fragment view.
        TextView username = findViewById(R.id.username);

        EditText bio = findViewById(R.id.bioEditText);
        EditText views = findViewById(R.id.viewsEditText);

        EditText work = findViewById(R.id.workEditText);
        EditText school = findViewById(R.id.schoolEditText);
        EditText religion = findViewById(R.id.religionEditText);

        EditText email = findViewById(R.id.emailAddressEditView);
        EditText phone = findViewById(R.id.phoneEditText);

//        Setting value for editTextViews in fragment with values from shared preferences.
        assert context != null;
        sharedPref = context.getSharedPreferences(getString((R.string.shared_preferences_of_user)), MODE_PRIVATE);
        REQUEST_URL += sharedPref.getString("UserId", null) + "/";
        REQUEST_URL_MEDIA += sharedPref.getString("UserId", null) + "/";
        DELETE_ACCOUNT_REQUEST_URL += sharedPref.getString("UserId", null);

        Picasso.get()
                .load(sharedPref.getString("PROFILE_IMG", null))
                .placeholder(R.drawable.gray)
                .error(R.drawable.gray)
                .into(userProfilePicture);

        username.setText(sharedPref.getString("Username", null) + ", ");
        bio.setText(sharedPref.getString("Bio", null));
        views.setText(sharedPref.getString("Views", null));
        work.setText(sharedPref.getString("Work", null));
        school.setText(sharedPref.getString("School", null));
        religion.setText(sharedPref.getString("Religion", null));
        email.setText(sharedPref.getString("Email", null));
        phone.setText(sharedPref.getString("Phone", null));

        Button logOut = findViewById(R.id.logOutButton);
        logOut.setOnClickListener(this);

        Button deleteAccount = findViewById(R.id.deleteAccountButton);
        deleteAccount.setOnClickListener(this);

//        Getting user's mediaList from api
        getMediaList(REQUEST_URL_MEDIA);
    }

    private void clearPreferences() {
        sharedPref = context.getSharedPreferences(getString((R.string.shared_preferences_of_user)), MODE_PRIVATE);
        sharedPref.edit().clear().apply();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.logOutButton):
                clearPreferences();
                mAuth.signOut();
                Intent intent = new Intent(context, UserLogin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case (R.id.deleteAccountButton):
//                todo: Must work on successfully deleting user account.
                RequestHandler handler = new RequestHandler(getApplicationContext(), DELETE_ACCOUNT_REQUEST_URL);
                handler.execute();

//                todo: Clear User Preferences.
                clearPreferences();

//                todo: Delete User Authentication credentials on fireBase.
                mAuth.signOut();
                FirebaseUser user = mAuth.getCurrentUser();

                assert user != null;
//                user.delete()
//                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if (task.isSuccessful()) {
//                                    Log.d(TAG, "onComplete: Account deleted.");
//                                    Intent intent = new Intent(context, UserLogin.class);
//                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                    startActivity(intent);
//                                } else {
//                                    Log.d(TAG, "onComplete: Account not deleted.");
//                                }
//                            }
//                        });

                break;
            case (R.id.imageUploadButton):
                openProfilePictureDialog();
                break;
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
        }
    }

    @Override
    public void updateProgressBar(int progressNumber) {
//        do nothing
    }

    @Override
    public void returnFileUrl(String fileUrl) {
//        append file url to API REQUEST string and send a request.
        REQUEST_URL = REQUEST_URL + encodeValue(fileUrl);
        Toast.makeText(context, "REQUEST_URL: " + REQUEST_URL, Toast.LENGTH_SHORT).show();

//        Send request to API to get NOTIFICATION LIST
        RequestHandler handler = new RequestHandler(getApplicationContext(), REQUEST_URL);
        handler.execute();

//      Todo: Restart Activity

    }

//          Creating Dialog Openers.
    private void openProfilePictureDialog() {
        ProfilePictureUploadDialog profilePictureDialog = new ProfilePictureUploadDialog();
        profilePictureDialog.show(getSupportFragmentManager(), "Profile Picture");
    }

    private void getMediaList(String REQUEST_URL_MEDIA) {
//          Send Request to API to get MEDIA LIST
        RequestQueue queue = Volley.newRequestQueue(context);

//          Request a string response from the provided URL.
        String BASE_URL = "https://guarded-beach-22346.herokuapp.com";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + REQUEST_URL_MEDIA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "onResponse: " + response);
                        //                Converting string to json and storing values.
                        try {
                            JSONObject jsonObj = new JSONObject(response);

                            if (jsonObj.has("msg")) {
                                Toast.makeText(context,
                                        "" + jsonObj.get("msg").toString(),
                                        Toast.LENGTH_SHORT
                                ).show();
                            }

                            if (jsonObj.has("mediaList")) {
                                mediaList = jsonObj.get("mediaList").toString();
                                Log.d(TAG, "getMediaList: " + mediaList);

//          Creating a bundle.
                                Bundle mediaListBundle = new Bundle();
                                mediaListBundle.putString("mediaList", mediaList);
                                mediaListBundle.putString("VIEWER_ID", sharedPref.getString("UserId", null));
                                gallery.setArguments(mediaListBundle);
                                transaction.add(R.id.gallery, gallery);
                                transaction.commit();
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
    }
}
