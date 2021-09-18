package com.example.All4Pets.Ratings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.All4Pets.R;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{
    ArrayList<MainModel> mainModels;
    Context context;

    public MainAdapter(Context context,ArrayList<MainModel>mainModels){
        this.context = context;
        this.mainModels = mainModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //set gallery to image view
        holder.image_view6.setImageResource(mainModels.get(position).getGallery());

        //set name to text view
        holder.tv_desc.setText(mainModels.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mainModels.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //initialize variable
        ImageView image_view6;
        TextView tv_desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //assign variables
            image_view6 = itemView.findViewById(R.id.image_view6);
            tv_desc = itemView.findViewById(R.id.tv_desc);
        }
    }
}
