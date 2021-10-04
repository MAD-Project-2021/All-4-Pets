package com.example.All4Pets.Daycares.Adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.All4Pets.Daycares.Activities.Daycare_ShowMoreNew;
import com.example.All4Pets.Daycares.models.MainModel;
import com.example.All4Pets.R;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    Context context;
    List<MainModel> list;


    public MyAdapter(Context context, List<MainModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.daycare_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into((ImageView) holder.imageView);
        holder.name.setText(list.get(position).getName());
        holder.location.setText(list.get(position).getLocation());
        holder.price.setText(list.get(position).getPrice());
        holder.rate.setRating(list.get(position).getRate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Daycare_ShowMoreNew.class);
                intent.putExtra("daycare_show_more_new", list.get(position));
                v.getContext().startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }
/*
    public void startListening() {
    }

    public void stopListening() {
    }

*/
    public class ViewHolder extends RecyclerView.ViewHolder {


    public View edit;
    ImageView imageView;
        TextView name, location, price;
        RatingBar rate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById((R.id.iv_dayc));
            name = itemView.findViewById(R.id.tv_dname);
            location = itemView.findViewById(R.id.tv_dlocation);
            price = itemView.findViewById(R.id.tv_price);
            rate = itemView.findViewById(R.id.tv_rate);


        }


}
}
