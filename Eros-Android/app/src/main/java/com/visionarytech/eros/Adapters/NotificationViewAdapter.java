package com.visionarytech.eros.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.visionarytech.eros.Models.Notifications;
import com.visionarytech.eros.R;

import java.util.List;

public class NotificationViewAdapter extends RecyclerView.Adapter<NotificationViewAdapter.RequestNotifications> {

    Context mContext;
    List<Notifications> mData;

    public NotificationViewAdapter(Context mContext, List<Notifications> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RequestNotifications onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.fragment_notification_item, parent, false);

        RequestNotifications requestHolder = new RequestNotifications(v);

        return requestHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RequestNotifications holder, int position) {
        holder.messageTextView.setText(mData.get(position).getMessage());
        holder.timeSinceRequest.setText(mData.get(position).getTime());
        holder.requesterProfilePicture.setImageResource(mData.get(position).getRequesterProfilePhoto());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class RequestNotifications extends RecyclerView.ViewHolder{
        private TextView messageTextView;
        private TextView timeSinceRequest;
        private ImageView requesterProfilePicture;
        private Button messageRequester;

        public RequestNotifications(@NonNull View itemView) {
            super(itemView);

            messageTextView = itemView.findViewById(R.id.messageDetails);
            timeSinceRequest = itemView.findViewById(R.id.timeSinceRequest);
            requesterProfilePicture = itemView.findViewById(R.id.requesterProfileImg);
            messageRequester = itemView.findViewById(R.id.messageRequesterBtn);
        }
    }
}
