package com.example.All4Pets.Doctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.All4Pets.R;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    ArrayList<Vets> List;

    public MyAdapter(Context context, ArrayList<Vets> list) {
        this.context = context;
        List = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view v = LayoutInflater.from(context).inflate(R.layout,item,parent,false);
       return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Vets vet = list.get(position);
        holder.name.setText(vets.getname());
        holder.specialty.setText(vets.getspecialty());
        holder.price.setText(vets.getprice());
        holder.rate.setText(vets.getrate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.Adapter{

        textView name,specialty,price,rate;


        public MyViewHolder(@NotNull view ItemView){
            super(ItemView);

            View itemView = null;

            name = itemView.findViewById(R.id.name);
            specialty = itemView.findViewById(R.id.specialty);
            price = itemView.findViewById(R.id.price);
            rate = itemView.findViewById(R.id.rate);


        }
    }
}
