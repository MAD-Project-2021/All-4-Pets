package com.example.All4Pets.Doctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.All4Pets.R;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    ArrayList<Vets> list;

    public MyAdapter(Context context, ArrayList<Vets> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Vets vet = list.get(position);
        holder.name.setText(Vets.getName());
        holder.specialty.setText(Vets.getSpeciality());
        holder.price.setText(Vets.getPrice());
        holder.rate.setText(Vets.getRate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyViewHolder<view> extends RecyclerView.ViewHolder{

         TextView name,specialty,price,rate;


        public MyViewHolder(@NotNull view itemView){
            super((View) itemView);



            name = ((View) itemView).findViewById(R.id.tv_name);
            specialty = ((View) itemView).findViewById(R.id.speciality);
            price = ((View) itemView).findViewById(R.id.price);
            rate = ((View) itemView).findViewById(R.id.rate);


        }
    }
}
