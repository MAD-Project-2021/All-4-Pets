package com.example.All4Pets.Category.adapters;

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
import com.example.All4Pets.Category.models.PetsModel;
import com.example.All4Pets.Category.ui.DetailedItems;
import com.example.All4Pets.Category.ui.DetailedPets;
import com.example.All4Pets.R;

import java.util.List;

public class petsAdapters extends RecyclerView.Adapter<petsAdapters.ViewHolder> {


    private Context context;
    private List<PetsModel> petsModelList;

    public petsAdapters(Context context, List<PetsModel> petsModelList) {
        this.context = context;
        this.petsModelList = petsModelList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pets, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(petsModelList.get(position).getImg_url()).into(holder.petsImage);
        holder.name.setText(petsModelList.get(position).getName());
        holder.price.setText(String.valueOf(petsModelList.get(position).getPrice()));
        holder.description.setText(petsModelList.get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View vi) {
                Intent intent = new Intent(vi.getContext(), DetailedPets.class);
                intent.putExtra("detailed1" , petsModelList.get(holder.getBindingAdapterPosition()));
                vi.getContext().startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return petsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView petsImage;
        TextView name, price, description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            petsImage = itemView.findViewById(R.id.pet_img);
            name = itemView.findViewById(R.id.pet_name);
            price = itemView.findViewById(R.id.pet_price);
            description = itemView.findViewById(R.id.pet_des);
        }
    }
}
