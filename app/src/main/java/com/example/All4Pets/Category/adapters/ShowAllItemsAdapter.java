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

public class ShowAllItemsAdapter extends RecyclerView.Adapter<ShowAllItemsAdapter.ViewHolder> {

    Context context;
    List<PetItemsModel> petItemsModelList;

    public ShowAllItemsAdapter(Context context, List<PetItemsModel> petItemsModelList) {
        this.context = context;
        this.petItemsModelList = petItemsModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShowAllItemsAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.show_all_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(petItemsModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(petItemsModelList.get(position).getName());
        holder.price.setText(String.valueOf(petItemsModelList.get(position).getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), DetailedItems.class);
                intent.putExtra("detailed", petItemsModelList.get(position));
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
        TextView name, price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
            name = itemView.findViewById(R.id.item_nam);
            price = itemView.findViewById(R.id.item_cost);

        }
    }
}
