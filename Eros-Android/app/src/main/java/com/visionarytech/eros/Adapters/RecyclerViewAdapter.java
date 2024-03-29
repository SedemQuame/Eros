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

import com.squareup.picasso.Picasso;
import com.visionarytech.eros.Activities.DatesProfile;
import com.visionarytech.eros.Models.Dates;
import com.visionarytech.eros.R;

import java.util.List;

import static com.visionarytech.eros.Utils.RequestFormatter.capitalizeWord;

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
        holder.personName.setText(capitalizeWord(mData.get(position).getName()));

        holder.personLocation.setText(capitalizeWord(mData.get(position).getLocation()));

        Picasso.get()
                .load(mData.get(position).getProfilePhoto())
                .placeholder(R.drawable.gray)
                .error(R.drawable.gray)
                .into(holder.personImage);

//        creating an onclick item listener
        holder.dateCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DatesProfile.class);
//                adding some extra information to the intent.
                intent.putExtra("_ID", mData.get(position).get_id());
                intent.putExtra("NAME", mData.get(position).getName());
                intent.putExtra("AGE", mData.get(position).getAge());
                intent.putExtra("LOCATION", mData.get(position).getLocation());
                intent.putExtra("PREFERENCES", mData.get(position).getPreferences());
                intent.putExtra("SOCIAL_BACKGROUND", mData.get(position).getBackground());
                intent.putExtra("CONTACT_INFORMATION", mData.get(position).getContactInfo());
                intent.putExtra("MEDIA_LIST", mData.get(position).getMedia());
//                Only load notifications for user's viewing their own profile walls.
//                intent.putExtra("NOTIFICATIONS", "location");
                intent.putExtra("USER_PROFILE", mData.get(position).getProfilePhoto());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView personName;
        TextView personLocation;
        ImageView personImage;
        CardView dateCardView;

        MyViewHolder(View itemView) {
            super(itemView);

            personName = itemView.findViewById(R.id.dateUserName);
            personLocation = itemView.findViewById(R.id.dateLocation);
            personImage = itemView.findViewById(R.id.dateMainImage);
            dateCardView = itemView.findViewById(R.id.dateCardView);

        }
    }
}
