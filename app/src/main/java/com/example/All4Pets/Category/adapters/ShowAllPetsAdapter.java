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
import com.example.All4Pets.Category.models.PetsModel;
import com.example.All4Pets.Category.ui.DetailedPets;
import com.example.All4Pets.R;

import java.util.List;

public class ShowAllPetsAdapter extends RecyclerView.Adapter<ShowAllPetsAdapter.ViewHolder> {

    private Context context;
    private List<PetsModel> petsModelList;

    public ShowAllPetsAdapter(Context context, List<PetsModel> petsModelList) {
        this.context = context;
        this.petsModelList = petsModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShowAllPetsAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.show_all_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(petsModelList.get(position).getImg_url()).into(holder.petImage);
        holder.name.setText(petsModelList.get(position).getName());
        holder.price.setText(String.valueOf(petsModelList.get(position).getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View vi) {
                Intent intent = new Intent(vi.getContext(), DetailedPets.class);
                intent.putExtra("detailed1" , petsModelList.get(position));
                vi.getContext().startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return  petsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView petImage;
        TextView name, price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            petImage = itemView.findViewById(R.id.item_image);
            name = itemView.findViewById(R.id.item_nam);
            price = itemView.findViewById(R.id.item_cost);
        }
    }
}
