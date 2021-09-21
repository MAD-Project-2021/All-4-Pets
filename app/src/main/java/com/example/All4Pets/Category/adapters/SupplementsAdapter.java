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
import com.example.All4Pets.Category.models.SupplementsModel;
import com.example.All4Pets.Category.ui.DetailedItems;
import com.example.All4Pets.R;

import java.io.Serializable;
import java.util.List;

public class SupplementsAdapter extends RecyclerView.Adapter<SupplementsAdapter.ViewHolder> {

    Context context;
    List<SupplementsModel>  supplementsModelList;

    public SupplementsAdapter(Context context, List<SupplementsModel> supplementsModelList) {
        this.context = context;
        this.supplementsModelList = supplementsModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.supplements, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(supplementsModelList.get(position).getImg_url()).into(holder.supimageView);
        holder.name.setText(supplementsModelList.get(position).getName());
        holder.price.setText(String.valueOf(supplementsModelList.get(position).getPrice()));
        holder.description.setText(supplementsModelList.get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View vie) {
                Intent intent = new Intent(vie.getContext(), DetailedItems.class);
                intent.putExtra("detailed" , supplementsModelList.get(position));
                vie.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return supplementsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView supimageView;
        TextView name,description,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            supimageView = itemView.findViewById(R.id.sup_img);
            name = itemView.findViewById(R.id.sup_name);
            price = itemView.findViewById(R.id.sup_price);
            description = itemView.findViewById(R.id.sup_des);
        }
    }
}
