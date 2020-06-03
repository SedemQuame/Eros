package com.visionarytech.eros.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.visionarytech.eros.Models.About;
import com.visionarytech.eros.Models.Contact;
import com.visionarytech.eros.Models.Preferences;
import com.visionarytech.eros.Models.SocialBackGround;
import com.visionarytech.eros.R;

import static com.visionarytech.eros.Utils.StringFormatter.capitalizeWord;

public class MatchProfileActivity extends AppCompatActivity {

    private TextView uNname, uAge, uLocation;
    private TextView uBio, uViews, uWork, uReligion, uSchool, uEmail, uPhone;
    private ImageView userMainPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_profile);

//          Receive data
        Intent intent = getIntent();
        String _id = intent.getStringExtra("_ID");
        String name = intent.getStringExtra("NAME");
        String age = intent.getStringExtra("AGE");
        String location = intent.getStringExtra("LOCATION");
        About aboutMe = (About)intent.getSerializableExtra("ABOUT_ME");
        Preferences preferences = (Preferences)intent.getSerializableExtra("PREFERENCES");
        SocialBackGround socialBackGround = (SocialBackGround) intent.getSerializableExtra("SOCIAL_BACKGROUND");
        Contact contactNumber = (Contact) intent.getSerializableExtra("CONTACT_INFORMATION");
        int userProfile = intent.getExtras().getInt("USER_PROFILE");


        userMainPicture = findViewById(R.id.app_bar_image);

        uNname = findViewById(R.id.datesName);
        uAge = findViewById(R.id.datesAge);
        uLocation = findViewById(R.id.datesLocationTextView);

        uBio = findViewById(R.id.bioDescriptionTextView);
        uViews = findViewById(R.id.viewsDescriptionTextView);
        uWork = findViewById(R.id.workTextView);
        uReligion = findViewById(R.id.religionTextView);
        uSchool = findViewById(R.id.schoolTextView);

        uEmail = findViewById(R.id.emaillTextView);
        uPhone = findViewById(R.id.phoneNumberTextView);

        String nameComma = name + ", ";
        this.uNname.setText(capitalizeWord(nameComma));
        this.uAge.setText(age);
        assert location != null;
        this.uLocation.setText(capitalizeWord(location));

        if (aboutMe != null) {
            this.uBio.setText(aboutMe.getBio());
        }
        if (aboutMe != null) {
            this.uViews.setText(aboutMe.getViews());
        }

        if (socialBackGround != null) {
            this.uWork.setText(socialBackGround.getWork());
        }
        if (socialBackGround != null) {
            this.uReligion.setText(socialBackGround.getReligion());
        }
        if (socialBackGround != null) {
            this.uSchool.setText(socialBackGround.getSchool());
        }

        if (contactNumber != null) {
            this.uEmail.setText(contactNumber.getEmailAddress());
        }
        if (contactNumber != null) {
            this.uPhone.setText(contactNumber.getPhoneNumber());
        }

        userMainPicture.setImageResource(userProfile);
    }
}
