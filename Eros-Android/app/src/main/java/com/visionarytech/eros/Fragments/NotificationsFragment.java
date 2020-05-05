package com.visionarytech.eros.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.visionarytech.eros.Adapters.NotificationViewAdapter;
import com.visionarytech.eros.Models.Notifications;
import com.visionarytech.eros.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {
  View v;
  private RecyclerView recyclerView;
  private List<Notifications> listOfRequests;

  public NotificationsFragment() {
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    v = inflater.inflate(R.layout.notifications_fragment, container, false);
    recyclerView = v.findViewById(R.id.message_requests);
    NotificationViewAdapter recyclerAdapter = new NotificationViewAdapter(getContext(), listOfRequests);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.setAdapter(recyclerAdapter);
    return v;
  }


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    listOfRequests = new ArrayList<>();
    String template1 = ", sent you a message request";
    String template2 = ", liked your a picture.";
    listOfRequests.add(new Notifications("Anthony Marshall" + template1, "31 mins ago", R.drawable.man1));
    listOfRequests.add(new Notifications("Dede Ayew" + template1, "12 mins ago", R.drawable.man2));
    listOfRequests.add(new Notifications("Tony Stark" + template2, "17 mins ago", R.drawable.man3));
    listOfRequests.add(new Notifications("Andre Ward" + template1, "13 mins ago", R.drawable.man4));
    listOfRequests.add(new Notifications("Mante SpiritMan" + template2, "16 mins ago", R.drawable.man5));
  }
}