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
import com.example.All4Pets.Category.models.SupplementsModel;
import com.example.All4Pets.Category.ui.DetailedItems;
import com.example.All4Pets.R;

import java.util.List;

public class ShowAllSupAdepter extends RecyclerView.Adapter<ShowAllSupAdepter.ViewHolder> {


    Context context;
    List<SupplementsModel> supplementsModelList;

    public ShowAllSupAdepter(Context context, List<SupplementsModel> supplementsModelList) {
        this.context = context;
        this.supplementsModelList = supplementsModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShowAllSupAdepter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.show_all_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(supplementsModelList.get(position).getImg_url()).into(holder.supimageV);
        holder.name.setText(supplementsModelList.get(position).getName());
        holder.price.setText(String.valueOf(supplementsModelList.get(position).getPrice()));

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
        ImageView supimageV;
        TextView name, price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            supimageV = itemView.findViewById(R.id.item_image);
            name = itemView.findViewById(R.id.item_nam);
            price = itemView.findViewById(R.id.item_cost);

        }
    }
}
