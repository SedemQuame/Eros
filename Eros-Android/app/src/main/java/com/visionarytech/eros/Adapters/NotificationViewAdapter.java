package com.visionarytech.eros.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.visionarytech.eros.Models.Notifications;
import com.visionarytech.eros.R;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class NotificationViewAdapter extends RecyclerView.Adapter<NotificationViewAdapter.RequestNotifications> {

    private Context mContext;
    private List<Notifications> mData;

    public NotificationViewAdapter(Context mContext, List<Notifications> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RequestNotifications onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.fragment_notification_item, parent, false);

        return new RequestNotifications(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestNotifications holder, int position) {
        holder.messageTextView.setText(mData.get(position).getMessage());
        holder.timeSinceRequest.setText(mData.get(position).getTime());
        Picasso.get()
                .load(mData.get(position).getRequesterProfilePhoto())
                .placeholder(R.drawable.gray)
                .error(R.drawable.gray)
                .into(holder.requesterProfilePicture);
    }

    @Override
    public int getItemCount() {
        try {
            return mData.size();
        }catch (NullPointerException e){
            Log.d(TAG, "getItemCount: 0");
            return 0;
        }
    }

    static class RequestNotifications extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView messageTextView;
        private TextView timeSinceRequest;
        private ImageView requesterProfilePicture;

        RequestNotifications(@NonNull View itemView) {
            super(itemView);

            messageTextView = itemView.findViewById(R.id.messageDetails);
            timeSinceRequest = itemView.findViewById(R.id.timeSinceRequest);
            requesterProfilePicture = itemView.findViewById(R.id.requesterProfileImg);
            LinearLayout messageRequester = itemView.findViewById(R.id.messageRequester);
            messageRequester.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.messageRequester){
//                todo: create event for messageRequesterButton.
                Toast.makeText(view.getContext(), "Notification clicked ...", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
