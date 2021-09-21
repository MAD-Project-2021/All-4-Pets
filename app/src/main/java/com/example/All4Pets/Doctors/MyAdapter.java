package com.example.All4Pets.Doctors;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.All4Pets.Doctors.models.MainModel;
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
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into((ImageView) holder.ImageView);
        holder.name.setText(list.get(position).getName());
        holder.speciality.setText(list.get(position).getSpeciality());
        holder.price.setText(list.get(position).getPrice());
        holder.rate.setText(list.get(position).getRate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final Object ImageView;
        ImageView img;
        TextView name, speciality, price, rate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ImageView = itemView.findViewById((R.id.iv_dayc));
            name = itemView.findViewById(R.id.tv_dname);
            speciality = itemView.findViewById(R.id.tv_dlocation);
            price = itemView.findViewById(R.id.tv_price);
            rate = itemView.findViewById(R.id.tv_rate);


        }
    }
}
