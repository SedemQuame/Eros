package com.visionarytech.eros.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.visionarytech.eros.Models.About;
import com.visionarytech.eros.Models.Preferences;
import com.visionarytech.eros.Models.SocialBackGround;
import com.visionarytech.eros.R;

public class MatchProfileActivity extends AppCompatActivity {

    private TextView userNameTextView, userAgeTextView, userLocationTextView, userDescriptionTextView;
    private ImageView userMainPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_profile);

        userNameTextView = findViewById(R.id.datesName);
        userAgeTextView = findViewById(R.id.datesAge);
        userLocationTextView = findViewById(R.id.datesLocation);
        userDescriptionTextView = findViewById(R.id.datesBio);
        userMainPicture = findViewById(R.id.app_bar_image);

//        Receive data
        Intent intent = getIntent();
        String _id = intent.getStringExtra("_ID");
        String name = intent.getStringExtra("NAME");
        String age = intent.getStringExtra("AGE");
        String location = intent.getStringExtra("LOCATION");
        About aboutMe = (About)intent.getSerializableExtra("ABOUT_ME");
        Preferences preferences = (Preferences)intent.getSerializableExtra("PREFERENCES");
        SocialBackGround socialBackGround = (SocialBackGround) intent.getSerializableExtra("SOCIAL_BACKGROUND");
//        Con socialBackGround = (SocialBackGround) intent.getSerializableExtra("SOCIAL_BACKGROUND");
        int userProfile = intent.getExtras().getInt("USER_PROFILE");

//        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();

        userNameTextView.setText(name + ", ");
        userAgeTextView.setText(age);
        userLocationTextView.setText(location);
        userMainPicture.setImageResource(userProfile);
    }
}
