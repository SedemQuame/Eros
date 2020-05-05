package com.visionarytech.eros.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.visionarytech.eros.Activities.MatchProfileActivity;
import com.visionarytech.eros.Models.Dates;
import com.visionarytech.eros.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Dates> mData;

    public RecyclerViewAdapter(Context mContext, List<Dates> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.prospect_cardview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.personName.setText(mData.get(position).getUserName());
        holder.personLocation.setText(mData.get(position).getUserLocation());
        holder.personImage.setImageResource(mData.get(position).getUserProfile());

//        creating an onclick item listener
        holder.dateCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MatchProfileActivity.class);
                //                adding some extra information to the intent.
                intent.putExtra("userId", mData.get(position).getUserId());
                intent.putExtra("userName", mData.get(position).getUserName());
                intent.putExtra("location", mData.get(position).getUserLocation());
                intent.putExtra("userProfile", mData.get(position).getUserProfile());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView personName;
        TextView personLocation;
        ImageView personImage;
        CardView dateCardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            personName = itemView.findViewById(R.id.dateUserName);
            personLocation = itemView.findViewById(R.id.dateLocation);
            personImage = itemView.findViewById(R.id.dateMainImage);
            dateCardView = itemView.findViewById(R.id.dateCardView);

        }
    }
}
