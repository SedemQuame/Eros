package com.visionarytech.eros.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.visionarytech.eros.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoBoxFragment extends Fragment {

    private TextView fieldName;
    private String title;

    public InfoBoxFragment() {
        // Required empty public constructor
    }

    public InfoBoxFragment(String title) {
        this.title = title;
    }

    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_info_box, container, false);
        fieldName = v.findViewById(R.id.fieldTitle);
        fieldName.setText(getTitle());
        // Inflate the layout for this fragment
        return v;
    }

    public String getTitle() {
        return title;
    }
}
