package com.example.All4Pets.Daycares.Adapters;

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

import com.example.All4Pets.Daycares.Activities.ChangeBookingd;
import com.example.All4Pets.Daycares.Activities.Daycare_ShowMoreNew;
import com.example.All4Pets.Daycares.models.DaycareBookingModel;
import com.example.All4Pets.R;

import java.util.List;

public class DaycareBookingAdapter extends RecyclerView.Adapter<DaycareBookingAdapter.ViewHolder> {

    Context context;
    List<DaycareBookingModel> list;


    public DaycareBookingAdapter(Context context, List<DaycareBookingModel> list, ChangeBookingd changeBookingd) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DaycareBookingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_change_bookingd, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DaycareBookingAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tv_custname.setText(list.get(position).name);
        holder.tv_phn.setText(list.get(position).contact);
        holder.tv_cmail.setText(list.get(position).address);
        holder.chk_date.setText(list.get(position).checkin);
        holder.chkout_date.setText(list.get(position).checkout);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (context, Daycare_ShowMoreNew.class);
                intent.putExtra("activity_change_bookingd", (Parcelable) list.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_custname, tv_phn,tv_cmail, chk_date, chkout_date;
        Button btn_confirmbooking, btn_edit, btn_del;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            tv_custname = itemView.findViewById(R.id.tv_name);
            tv_phn = itemView.findViewById(R.id.tv_phn);
            tv_cmail = itemView.findViewById(R.id.tv_cmail);
            chk_date = itemView.findViewById(R.id.chk_date);
            chkout_date = itemView.findViewById(R.id.chkout_date);
            btn_confirmbooking = itemView.findViewById(R.id.btn_confirmbooking);
            btn_edit = itemView.findViewById(R.id.btn_edit);
            btn_del = itemView.findViewById(R.id.btn_del);



        }
    }
}
