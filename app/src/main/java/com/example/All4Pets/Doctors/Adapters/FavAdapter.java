package com.example.All4Pets.Doctors.Adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.All4Pets.Doctors.models.FavouriteModel;
import com.example.All4Pets.R;
import java.util.List;


public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {

    Context context;
    List<FavouriteModel> list;


    public FavAdapter(Context context, List<FavouriteModel> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.doc_favouritelist,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {


        holder.name.setText(list.get(position).getDoctor_name());
        holder.speciality.setText(list.get(position).getDoctor_speciality());
        holder.price.setText(list.get(position).getDoctor_price());
        holder.date.setText(list.get(position).getCurrentDate());
        holder.time.setText(list.get(position).getCurrentTime());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {



        TextView name, speciality, price,date,time;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.Doctor_name);
            speciality = itemView.findViewById(R.id.Doctor_speciality);
            price = itemView.findViewById(R.id.Doctor_price);
            date = itemView.findViewById(R.id.current_date);
            time = itemView.findViewById(R.id.current_time);


        }
    }
}

