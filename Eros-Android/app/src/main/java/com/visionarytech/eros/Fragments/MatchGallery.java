package com.visionarytech.eros.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.visionarytech.eros.Adapters.GalleryViewAdapter;
import com.visionarytech.eros.Models.Media;
import com.visionarytech.eros.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MatchGallery extends Fragment {
    private static final String TAG = "MatchGallery";
    List<Media> mediaList;
    View v;
    public MatchGallery() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_match_gallery, container, false);

        Bundle mediaListBundle = getArguments();
        assert mediaListBundle != null;
        String mediaListString = mediaListBundle.getString("mediaList");
        Log.d(TAG, "onCreateView: " + mediaListString);

        mediaList = new ArrayList<>();
        try {
            JSONArray jsonArr = new JSONArray(mediaListString);

            for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject obj = jsonArr.getJSONObject(i);
                Media media = new Media(
                        obj.getInt("numberOfLikes"),
                        obj.getString("assetType"),
                        obj.getString("assetUrl")
                );

                mediaList.add(media);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        RecyclerView galleryItems = v.findViewById(R.id.galleryRecyclerView);
        GalleryViewAdapter galleryAdapter = new GalleryViewAdapter(getContext(), mediaList);
        galleryItems.setLayoutManager(new GridLayoutManager(getContext(), 3));
        galleryItems.setAdapter(galleryAdapter);
        return v;
    }

}
