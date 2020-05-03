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

import com.visionarytech.eros.Adapters.MessageNotification;
import com.visionarytech.eros.Adapters.RecyclerViewAdapter;
import com.visionarytech.eros.MessageItems;
import com.visionarytech.eros.R;

import java.util.ArrayList;
import java.util.List;

public class MessagesFragment extends Fragment {
  View v;
  private RecyclerView recyclerView;
  private List<MessageItems> listOfRequests;

  public MessagesFragment() {}

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    v = inflater.inflate(R.layout.messages_fragment, container, false);
    recyclerView = v.findViewById(R.id.message_requests);
    MessageNotification recyclerAdapter = new MessageNotification(getContext(), listOfRequests);
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
    listOfRequests.add(new MessageItems("Anthony Marshall" + template1, "31 mins ago", R.drawable.man1));
    listOfRequests.add(new MessageItems("Dede Ayew" + template1, "12 mins ago", R.drawable.man2));
    listOfRequests.add(new MessageItems("Tony Stark" + template2, "17 mins ago", R.drawable.man3));
    listOfRequests.add(new MessageItems("Andre Ward" + template1, "13 mins ago", R.drawable.man4));
    listOfRequests.add(new MessageItems("Mante SpiritMan" + template2, "16 mins ago", R.drawable.man5));
  }
}