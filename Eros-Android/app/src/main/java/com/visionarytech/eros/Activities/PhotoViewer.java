package com.visionarytech.eros.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.squareup.picasso.Picasso;
import com.visionarytech.eros.Models.Media;
import com.visionarytech.eros.Networks.RequestHandler;
import com.visionarytech.eros.R;
import com.visionarytech.eros.Utils.RequestFormatter;

import java.util.List;

import static com.visionarytech.eros.Utils.RequestFormatter.encodeValue;

public class PhotoViewer extends AppCompatActivity implements View.OnClickListener {
    private ImageView fullPhotoImageView;
    private String photoUrl = "", REQUEST_URL = "/deletePicturePostedOnPlatform/", REQUEST_CHANGE_PROFILE = "/changeProfileImg/";
    private String viewerId = "";
    private int elementPosition = -1, listSize = -1;
    private List<Media> Media = null;
    private SharedPreferences sharedPref;
    private static final String TAG = "PhotoViewer";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_photo_item_full_view);
        sharedPref = getSharedPreferences(getString((R.string.shared_preferences_of_user)), MODE_PRIVATE);
        REQUEST_URL += sharedPref.getString("UserId", null) + "/";
        REQUEST_CHANGE_PROFILE += sharedPref.getString("UserId", null) + "/";

        fullPhotoImageView = findViewById(R.id.galleryImageFullView);
        CardView changeProfilePicture = findViewById(R.id.changeProfilePicture);
        CardView deleteButton = findViewById(R.id.deleteButton);
        CardView likeUserPicture = findViewById(R.id.likeUserPicture);


//        Getting picture id sent from gallery activity

        Intent intent = getIntent();
//        todo: Get viewer ID, and pass into Activity through intents.
        photoUrl = intent.getExtras().getString("MEDIA_ELEMENT");
        elementPosition = intent.getExtras().getInt("MEDIA_ELEMENT_POSITION");
        listSize = intent.getExtras().getInt("MEDIA_LIST_SIZE");
        viewerId = intent.getExtras().getString("VIEWER_ID");
        Media = (List<Media>) intent.getExtras().getSerializable("MEDIA_LIST");

//        Setting picture resource using picasso
        setImage(photoUrl);

//        Hiding Button depending on user authentication levels.
        Log.d(TAG, "OnCreate Test: " + viewerId);
        Log.d(TAG, "OnCreate Test: " + sharedPref.getString("UserId", null));
        if(viewerId == (sharedPref.getString("UserId", null))){
            likeUserPicture.setVisibility(View.GONE);
            changeProfilePicture.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
        }else{
            likeUserPicture.setVisibility(View.VISIBLE);
            changeProfilePicture.setVisibility(View.GONE);
            deleteButton.setVisibility(View.GONE);
        }

//        Setting event listener.
        fullPhotoImageView.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        changeProfilePicture.setOnClickListener(this);
        likeUserPicture.setOnClickListener(this);
    }

    private void setImage(String photoUrl) {
//        Getting image Id from intent, and setting up the imageView
        Picasso.get()
                .load(photoUrl)
                .placeholder(R.drawable.gray)
                .error(R.drawable.gray)
                .into(fullPhotoImageView);
    }



    @Override
    public void onClick(View view) {
        RequestHandler handler;
        switch (view.getId()) {
            case R.id.galleryImageFullView:
                elementPosition = elementPosition + 1;
                if (elementPosition == listSize || elementPosition < 0) {
                    elementPosition = 0;
                }
                photoUrl = Media.get(elementPosition).getAssetUrl();
//                  Setting picture resource using picasso
                setImage(photoUrl);
                break;
            case R.id.deleteButton:
                handler = new RequestHandler(getApplicationContext(), REQUEST_URL + Media.get(elementPosition).get_id());
                handler.execute();
                break;
            case R.id.changeProfilePicture:
                String request = (REQUEST_CHANGE_PROFILE + encodeValue(Media.get(elementPosition).getAssetUrl()));
                handler = new RequestHandler(getApplicationContext(), request);
                handler.execute();
//                Place url to image in shared preferences.
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("UserProfileImage", Media.get(elementPosition).getAssetUrl());
//                      Saving New User Preferences.
                editor.apply();
                break;
            default:
                break;
        }
    }
}
