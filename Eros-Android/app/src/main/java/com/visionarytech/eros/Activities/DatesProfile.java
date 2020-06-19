package com.visionarytech.eros.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.squareup.picasso.Picasso;
import com.visionarytech.eros.Fragments.MatchGallery;
import com.visionarytech.eros.Models.About;
import com.visionarytech.eros.Models.Contact;
import com.visionarytech.eros.Models.SocialBackGround;
import com.visionarytech.eros.Networks.RequestHandler;
import com.visionarytech.eros.R;

import static com.visionarytech.eros.Utils.RequestFormatter.capitalizeWord;

public class DatesProfile extends AppCompatActivity implements View.OnClickListener {
    private static final String LIKE_MATCH = "/likePossibleMatch",
            LOVE_MATCH = "/lovePossibleMatch",
            REQUEST_MESSAGE = "/requestMessageFromPossibleMatch";
    private static StringBuilder REQUEST_URL = new StringBuilder();
    private SharedPreferences sharedPref;
    private String _id = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_profile);

//          Get reference of the child fragment.
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        final MatchGallery gallery = new MatchGallery();

        sharedPref = getSharedPreferences(getString((R.string.shared_preferences_of_user)), MODE_PRIVATE);

//          Receive data
        Intent intent = getIntent();
        _id = intent.getStringExtra("_ID");
        String name = intent.getStringExtra("NAME");
        String age = intent.getStringExtra("AGE");
        String location = intent.getStringExtra("LOCATION");
        About aboutMe = (About) intent.getSerializableExtra("ABOUT_ME");
        SocialBackGround socialBackGround = (SocialBackGround) intent.getSerializableExtra("SOCIAL_BACKGROUND");
        Contact contactNumber = (Contact) intent.getSerializableExtra("CONTACT_INFORMATION");
        String userProfile = intent.getExtras().getString("USER_PROFILE");

        String mediaList = intent.getExtras().getString("MEDIA_LIST");

        ImageView userMainPicture = findViewById(R.id.app_bar_image);

        TextView uNname = findViewById(R.id.datesName);
        TextView uAge = findViewById(R.id.datesAge);
        TextView uLocation = findViewById(R.id.datesLocationTextView);
        TextView uBio = findViewById(R.id.bioDescriptionTextView);
        TextView uViews = findViewById(R.id.viewsDescriptionTextView);
        TextView uWork = findViewById(R.id.workTextView);
        TextView uReligion = findViewById(R.id.religionTextView);
        TextView uSchool = findViewById(R.id.schoolTextView);

        TextView uEmail = findViewById(R.id.emaillTextView);
        TextView uPhone = findViewById(R.id.phoneNumberTextView);

        CardView like = findViewById(R.id.like);
        CardView love = findViewById(R.id.love);
        CardView message = findViewById(R.id.message);

//        Setting OnClickListeners
        like.setOnClickListener(this);
        love.setOnClickListener(this);
        message.setOnClickListener(this);

//          Downloading and setting, user profile image using Picasso.
        Picasso.get()
                .load(userProfile)
                .placeholder(R.drawable.gray)
                .error(R.drawable.gray)
                .into(userMainPicture);

        String nameComma = name + ", ";
        uNname.setText(capitalizeWord(nameComma));
        uAge.setText(age);
        assert location != null;
        uLocation.setText(capitalizeWord(location));

        if (aboutMe != null) {
            uBio.setText(aboutMe.getBio());
        }
        if (aboutMe != null) {
            uViews.setText(aboutMe.getViews());
        }

        if (socialBackGround != null) {
            uWork.setText(socialBackGround.getWork());
        }
        if (socialBackGround != null) {
            uReligion.setText(socialBackGround.getReligion());
        }
        if (socialBackGround != null) {
            uSchool.setText(socialBackGround.getSchool());
        }

        if (contactNumber != null) {
            uEmail.setText(contactNumber.getEmailAddress());
        }
        if (contactNumber != null) {
            uPhone.setText(contactNumber.getPhoneNumber());
        }

        Toast.makeText(getApplicationContext(), _id, Toast.LENGTH_SHORT).show();

//          Creating a bundle.
        Bundle mediaListBundle = new Bundle();
        mediaListBundle.putString("mediaList", mediaList);
        mediaListBundle.putString("mediaList", mediaList);
        mediaListBundle.putString("VIEWER_ID", _id);

        gallery.setArguments(mediaListBundle);

        transaction.add(R.id.gallery, gallery);
        transaction.commit();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.like):
//                Toast.makeText(this, "Like", Toast.LENGTH_SHORT).show();
                apiRequesterHandler(LIKE_MATCH);
                break;
            case (R.id.love):
//                Toast.makeText(this, "Love", Toast.LENGTH_SHORT).show();
                apiRequesterHandler(LOVE_MATCH);
                break;
            case (R.id.message):
//                Toast.makeText(this, "Requested Message From ", Toast.LENGTH_SHORT).show();
                apiRequesterHandler(REQUEST_MESSAGE);
                break;
            default:
                break;
        }
    }

    private void apiRequesterHandler(String route) {
        REQUEST_URL.append(route)
                .append("/")
                .append(sharedPref.getString("UserId", null))
                .append("/")
                .append(_id);
        RequestHandler handler = new RequestHandler(getApplicationContext(), REQUEST_URL.toString());
        handler.execute();
        REQUEST_URL = new StringBuilder();
    }
}
