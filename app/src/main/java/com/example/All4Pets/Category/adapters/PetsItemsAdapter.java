package com.example.All4Pets.Category.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.All4Pets.Category.models.PetItemsModel;
import com.example.All4Pets.Category.ui.DetailedItems;
import com.example.All4Pets.R;

import java.util.List;

public class PetsItemsAdapter extends RecyclerView.Adapter<PetsItemsAdapter.ViewHolder> {

    Context context;
    List<PetItemsModel> petItemsModelList;

    public PetsItemsAdapter(Context context, List<PetItemsModel> petItemsModelList) {
        this.context = context;
        this.petItemsModelList = petItemsModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pets_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(petItemsModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(petItemsModelList.get(position).getName());
        holder.price.setText(String.valueOf(petItemsModelList.get(position).getPrice()));
        holder.description.setText(petItemsModelList.get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailedItems.class);
                intent.putExtra("detailed" , petItemsModelList.get(position));
                v.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return petItemsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,description,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.petitem_img);
            name = itemView.findViewById(R.id.petitem_name);
            price = itemView.findViewById(R.id.petitem_price);
            description = itemView.findViewById(R.id.petitem_des);
        }
    }
}
