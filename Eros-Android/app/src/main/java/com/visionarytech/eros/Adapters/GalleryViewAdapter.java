package com.visionarytech.eros.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.visionarytech.eros.Activities.PhotoViewer;
import com.visionarytech.eros.Models.Media;
import com.visionarytech.eros.R;

import java.io.Serializable;
import java.util.List;

public class GalleryViewAdapter extends RecyclerView.Adapter<GalleryViewAdapter.ItemHolder>{

    private Context mContext;
    private List<Media> mData;
    private int mDataLength;

    public GalleryViewAdapter(Context mContext, List<Media> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mDataLength = mData.size();
    }

    private List<Media> getmData() {
        return mData;
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

        Picasso.get()
                .load(mData.get(position).getAssetUrl())
                .placeholder(R.drawable.gray)
                .error(R.drawable.gray)
                .into(holder.datesPhotoItem);

        holder.datesPhotoItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast.makeText(mContext, "VIEWER_ID: " + mData.get(position).getViewerId(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, PhotoViewer.class);
                intent.putExtra("MEDIA_ELEMENT", mData.get(position).getAssetUrl());
                intent.putExtra("MEDIA_ELEMENT_POSITION", position);
                intent.putExtra("MEDIA_LIST_SIZE", mDataLength);
                intent.putExtra("MEDIA_LIST", (Serializable) getmData());
                intent.putExtra("VIEWER_ID", mData.get(position).getViewerId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ItemHolder extends RecyclerView.ViewHolder{
        ImageView datesPhotoItem;

        ItemHolder(@NonNull View itemView) {
            super(itemView);

            datesPhotoItem = itemView.findViewById(R.id.galleryPhoto);
        }
    }
}
