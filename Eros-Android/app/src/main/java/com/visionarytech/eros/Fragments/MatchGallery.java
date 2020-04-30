package com.visionarytech.eros.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.visionarytech.eros.GalleryPhoto;
import com.visionarytech.eros.Adapters.GalleryViewAdapter;
import com.visionarytech.eros.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MatchGallery extends Fragment {

    List<GalleryPhoto> listOfGalleryItems;
    View v;
    public MatchGallery() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_match_gallery, container, false);

        listOfGalleryItems = new ArrayList<>();
        listOfGalleryItems.add(new GalleryPhoto(R.drawable.woman1));
        listOfGalleryItems.add(new GalleryPhoto(R.drawable.woman2));
        listOfGalleryItems.add(new GalleryPhoto(R.drawable.woman3));
        listOfGalleryItems.add(new GalleryPhoto(R.drawable.woman7));
        listOfGalleryItems.add(new GalleryPhoto(R.drawable.woman8));

        RecyclerView galleryItems = v.findViewById(R.id.galleryRecyclerView);
        GalleryViewAdapter galleryAdapter = new GalleryViewAdapter(getContext(), listOfGalleryItems);
        galleryItems.setLayoutManager(new GridLayoutManager(getContext(), 3));
        galleryItems.setAdapter(galleryAdapter);
        return v;
    }

}
