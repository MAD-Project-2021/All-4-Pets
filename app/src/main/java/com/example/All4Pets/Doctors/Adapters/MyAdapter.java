package com.example.All4Pets.Doctors.Adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.All4Pets.Doctors.Activities.Vet_ShowMore;
import com.example.All4Pets.Doctors.models.MainModel;
import com.example.All4Pets.Doctors.models.ViewShowMoreModel;
import com.example.All4Pets.R;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    Context context;
    List<MainModel> list;


    public MyAdapter(Context context, List<MainModel> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.doctors,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {


        //Load the data

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.img);
        holder.name.setText(list.get(position).getName());
        holder.speciality.setText(list.get(position).getSpeciality());
        holder.price.setText(list.get(position).getPrice());
        holder.rate.setText(String.valueOf(list.get(position).getRate()));



        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent (context, Vet_ShowMore.class);
                intent.putExtra("vet_show_more", (Parcelable) list.get(position));  //send an Intents to the Android system which starts another Activity .
                context.startActivity(intent);
            }
        });

        holder.arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (context,Vet_ShowMore.class);

                ViewShowMoreModel m = new ViewShowMoreModel();
                m.id = list.get(position).id;

                intent.putExtra("vet_show_more", m);
                view.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        ImageView img, arrow;
        TextView name, speciality, price, rate;

        Button  edit, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById((R.id.img1));
            arrow = itemView.findViewById((R.id.arrow));
            name = itemView.findViewById(R.id.name);
            speciality = itemView.findViewById(R.id.speciality);
            price = itemView.findViewById(R.id.price);
            rate = itemView.findViewById(R.id.rate);

            edit = (Button)itemView.findViewById(R.id.btn_edit);
            delete = (Button)itemView.findViewById(R.id.btn_delete1);
        }
    }
}
