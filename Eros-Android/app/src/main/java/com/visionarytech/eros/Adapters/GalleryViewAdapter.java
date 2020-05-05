package com.visionarytech.eros.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.visionarytech.eros.Models.GalleryPhoto;
import com.visionarytech.eros.Activities.PhotoItemActivity;
import com.visionarytech.eros.R;

import java.util.List;

public class GalleryViewAdapter extends RecyclerView.Adapter<GalleryViewAdapter.ItemHolder>{

    private Context mContext;
    private List<GalleryPhoto> mData;

    public GalleryViewAdapter(Context mContext, List<GalleryPhoto> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.gallery_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, final int position) {
        holder.datesPhotoItem.setImageResource(mData.get(position).getPhoto());
        holder.datesPhotoItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, PhotoItemActivity.class);
                intent.putExtra("photoId", mData.get(position).getPhoto());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder{
        ImageView datesPhotoItem;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            datesPhotoItem = itemView.findViewById(R.id.galleryPhoto);
        }
    }
}
