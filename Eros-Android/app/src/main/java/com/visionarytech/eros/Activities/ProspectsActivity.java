package com.visionarytech.eros.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.visionarytech.eros.Fragments.MessagesFragment;
import com.visionarytech.eros.Fragments.NotificationsFragment;
import com.visionarytech.eros.Fragments.ProfileFragment;
import com.visionarytech.eros.Fragments.UserProfileFragment;
import com.visionarytech.eros.R;
import com.visionarytech.eros.Adapters.ViewPageAdapter;

public class ProspectsActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        adapter = new ViewPageAdapter(getSupportFragmentManager());

//        adding fragents here
        adapter.AddFragment(new UserProfileFragment(), "Home");
        adapter.AddFragment(new MessagesFragment(), "Messages");
        adapter.AddFragment(new NotificationsFragment(), "Notification");
        adapter.AddFragment(new ProfileFragment(), "Profile");

//        adapter.AddFragment();

//        add adapter to viewpager
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_message);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_notifications);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_person);

    }
}
