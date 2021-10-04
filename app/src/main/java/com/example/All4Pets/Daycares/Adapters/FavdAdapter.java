package com.example.All4Pets.Daycares.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.All4Pets.Daycares.models.FavdModel;
import com.example.All4Pets.R;

import java.util.List;

public class FavdAdapter extends RecyclerView.Adapter<FavdAdapter.ViewHolder> {

    Context context ;
    List<FavdModel> list;

    public FavdAdapter(Context context, List<FavdModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.favday_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FavdAdapter.ViewHolder holder, int position) {
            holder.tv_named.setText(list.get(position).getDaycareName());
            holder.tv_loc.setText(list.get(position).getDaycareLocation());
            holder.tv_pri.setText(list.get(position).getDaycareContact());
            holder.c_time.setText(list.get(position).getCurrentTime());
            holder.c_date.setText(list.get(position).getCurrentDate());
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_named,tv_loc,tv_pri,c_time, c_date;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_named = itemView.findViewById(R.id.tv_named);
            tv_loc = itemView.findViewById(R.id.tv_loc);
            tv_pri = itemView.findViewById(R.id.tv_pri);
            c_time= itemView.findViewById(R.id.c_time);
            c_date = itemView.findViewById(R.id.c_date);





        }
    }
}
