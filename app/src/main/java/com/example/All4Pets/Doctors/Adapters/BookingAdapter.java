package com.example.All4Pets.Doctors.Adapters;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.All4Pets.Doctors.Activities.Booking;
import com.example.All4Pets.Doctors.Activities.Vet_ShowMore;
import com.example.All4Pets.Doctors.models.BookingModel;
import com.example.All4Pets.R;

import java.util.List;


public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {

    Context context;
    List<BookingModel> list;

    public BookingAdapter(Context context, List<BookingModel> list, Booking booking) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BookingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_booking,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        holder.tv_name.setText(list.get(position).name);
        holder.tv_address.setText(list.get(position).address);
        holder.tv_email.setText(list.get(position).email);
        holder.tv_date.setText(list.get(position).date);
        holder.tv_time.setText(list.get(position).time);



        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent (context, Vet_ShowMore.class);
                intent.putExtra("activity_booking", (Parcelable) list.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView tv_name,tv_address,tv_email,tv_date,tv_time;
        Button btn_edit,btn_delete1,btn_confirm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_address = itemView.findViewById(R.id.tv_address);
            tv_email = itemView.findViewById(R.id.tv_email);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_time = itemView.findViewById(R.id.tv_time);
            btn_edit = itemView.findViewById(R.id.btn_edit);
            btn_delete1 = itemView.findViewById(R.id.btn_delete1);
            btn_confirm = itemView.findViewById(R.id.btn_confirm);

        }
    }


    }


