package com.visionarytech.eros.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.visionarytech.eros.Activities.LoginActivity;
import com.visionarytech.eros.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {
    View v;
    private EditText bio;
    private EditText views;
//    ==========================
    private EditText work;
    private EditText school;
    private EditText religion;
//    ==========================
    private EditText email;
    private EditText phone;


    private Button logOut;
    private FirebaseAuth mAuth;

    public ProfileFragment() {
//        Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        v = inflater.inflate(R.layout.fragment_profile, container, false);

//        Referencing views in the fragment view.
        bio = v.findViewById(R.id.bioEditText);
        views = v.findViewById(R.id.viewsEditText);
        work = v.findViewById(R.id.workEditText);
        school = v.findViewById(R.id.schoolEditText);
        religion = v.findViewById(R.id.religionEditText);
        email = v.findViewById(R.id.emailAddressEditView);
        phone = v.findViewById(R.id.phoneEditText);

//        Setting value for editTextViews in fragment with values from shared preferences.
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.shared_preferences_of_user), context.MODE_PRIVATE);


        bio.setText(sharedPref.getString("Bio", null));
        views.setText(sharedPref.getString("Views", null));
        work.setText(sharedPref.getString("Work", null));
        school.setText(sharedPref.getString("School", null));
        religion.setText(sharedPref.getString("Religion", null));
        email.setText(sharedPref.getString("Email", null));
        phone.setText(sharedPref.getString("Phone", null));

        logOut = v.findViewById(R.id.logOutButton);
        logOut.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.logOutButton):
                mAuth.signOut();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
