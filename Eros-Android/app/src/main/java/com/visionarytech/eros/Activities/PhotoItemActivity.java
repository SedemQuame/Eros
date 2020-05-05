package com.visionarytech.eros.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.visionarytech.eros.R;

public class PhotoItemActivity extends AppCompatActivity {
    ImageView fullPhotoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_photo_item_full_view);
        fullPhotoImageView = findViewById(R.id.galleryImageFullView);

//        Getting picture id sent from gallery activity
        Intent intent = getIntent();
        int photoId = intent.getExtras().getInt("photoId");

//        Getting image Id from intent, and setting up the imageView
        fullPhotoImageView.setImageResource(photoId);

    }
}
