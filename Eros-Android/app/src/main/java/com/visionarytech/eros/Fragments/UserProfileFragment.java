package com.visionarytech.eros.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.visionarytech.eros.Models.Dates;
import com.visionarytech.eros.R;
import com.visionarytech.eros.Adapters.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class UserProfileFragment extends Fragment {
    List<Dates> listOfProspectiveDates;

    View v;
    public UserProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.users_fragment, container, false);

        listOfProspectiveDates = new ArrayList<>();
        listOfProspectiveDates.add(new Dates("Ab4234242", "Abigail Goku", "Hatso", R.drawable.woman1, ""));
        listOfProspectiveDates.add(new Dates("aslksdall", "Derborah Ichigo", "Madina", R.drawable.woman2, ""));
        listOfProspectiveDates.add(new Dates("fsdfsfsfs", "Priscilla Oduro", "Zongo", R.drawable.woman3, ""));
        listOfProspectiveDates.add(new Dates("dffsfdsdf", "Faustina Boatemma", "Accra", R.drawable.woman4, ""));
        listOfProspectiveDates.add(new Dates("21sdffsdf", "Marilyn Hulk", "Central", R.drawable.woman5, ""));
        listOfProspectiveDates.add(new Dates("dfsbdbdbd", "Beatrice Saitama", "Legon", R.drawable.woman6, ""));
        listOfProspectiveDates.add(new Dates("sfdsdfdsf", "Sabrina Stark", "Dansoman", R.drawable.woman7, ""));
        listOfProspectiveDates.add(new Dates("asdwerrte", "Princess Parku", "Circle", R.drawable.woman8, ""));
        listOfProspectiveDates.add(new Dates("zcvxbbbbb", "Fredricka Hazel", "Kumasi", R.drawable.woman9, ""));
        listOfProspectiveDates.add(new Dates("oipwpoeru", "Ekua Jet Lee", "Kumawu", R.drawable.woman10, ""));
        listOfProspectiveDates.add(new Dates("90jiksfdl", "Abena Jackie Chan", "Dodowa", R.drawable.woman11, ""));
        listOfProspectiveDates.add(new Dates("fsfsffsfs", "Akosua Thanos", "Hatso", R.drawable.woman12, ""));

        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), listOfProspectiveDates);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setAdapter(adapter);
        return v;
    }
}
