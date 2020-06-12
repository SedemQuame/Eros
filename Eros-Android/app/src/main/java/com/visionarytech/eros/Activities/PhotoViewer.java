package com.visionarytech.eros.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.visionarytech.eros.Models.Media;
import com.visionarytech.eros.R;

import java.util.List;

public class PhotoViewer extends AppCompatActivity implements View.OnClickListener{
    private ImageView fullPhotoImageView;
    private String photoUrl = "";
    private int elementPosition = -1, listSize = -1;
    private List<Media> Media = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_photo_item_full_view);
        fullPhotoImageView = findViewById(R.id.galleryImageFullView);

//        Getting picture id sent from gallery activity
        Intent intent = getIntent();
        photoUrl = intent.getExtras().getString("MEDIA_ELEMENT");
        elementPosition = intent.getExtras().getInt("MEDIA_ELEMENT_POSITION");
        listSize = intent.getExtras().getInt ("MEDIA_LIST_SIZE");
        Media = (List<Media>) intent.getExtras().getSerializable("MEDIA_LIST");

//        Setting picture resource using picasso
        setImage(photoUrl);

//        Setting event listener.
        fullPhotoImageView.setOnClickListener(this);
    }

    private void setImage(String photoUrl){
//        Getting image Id from intent, and setting up the imageView
        Picasso.get()
            .load(photoUrl)
            .placeholder(R.drawable.gray)
            .error(R.drawable.gray)
            .into(fullPhotoImageView);
    }

    @Override
    public void onClick(View view) {
        //                do nothing
        if (view.getId() == R.id.galleryImageFullView) {
            elementPosition = elementPosition + 1;
            if (elementPosition == listSize || elementPosition < 0) {
                elementPosition = 0;
            }
            photoUrl = Media.get(elementPosition).getAssetUrl();
//                  Setting picture resource using picasso
            setImage(photoUrl);
        }
    }
}
