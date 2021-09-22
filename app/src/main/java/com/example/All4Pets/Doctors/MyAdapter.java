package com.example.All4Pets.Doctors;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.All4Pets.Doctors.models.DetailedModel;
import com.example.All4Pets.Doctors.models.MainModel;
import com.example.All4Pets.Doctors.models.ViewShowMoreModel;
import com.example.All4Pets.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    Context context;
    List<MainModel> list;
//    ImageButton fvrt_btn;
//    DatabaseReference favouriteref;
//    FirebaseDatabase database = FirebaseDatabase.getInstance();



    public MyAdapter(Context context, List<MainModel> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.doctors,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.img);
        holder.name.setText(list.get(position).getName());
        holder.speciality.setText(list.get(position).getSpeciality());
        holder.price.setText(list.get(position).getPrice());
        holder.rate.setText(list.get(position).getRate());



        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent (context,Vet_ShowMore.class);
                intent.putExtra("vet_show_more", (Parcelable) list.get(position));
                context.startActivity(intent);
            }
        });

        holder.arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (context,Vet_ShowMore.class);

                ViewShowMoreModel m = new ViewShowMoreModel();
                m.id = list.get(position).id;

                intent.putExtra("vet_show_more", m);
                view.getContext().startActivity(intent);
            }
        });

//        holder.edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
//                        .setContentHolder(new ViewHolder(R.layout.detailed_customer))
//                        .setExpanded(true,1200)
//                        .create();
//
//                View view = dialogPlus.getHolderView();
//
//
//                // find the customers who booked appointments
//
//                EditText et_name = view.findViewById(R.id.et_name);
//                EditText et_address = view.findViewById(R.id.et_address);
//                EditText et_email = view.findViewById(R.id.et_email);
//                EditText et_date = view.findViewById(R.id.et_date);
//                EditText et_time = view.findViewById(R.id.et_time);
//
//                Button btn_save =view.findViewById(R.id.btn_save);
//
//                et_name.setText(DetailedModel.getEt_name());
//                et_name.setText(DetailedModel.getEt_address());
//                et_name.setText(DetailedModel.getEt_email());
//                et_name.setText(DetailedModel.getEt_date());
//                et_name.setText(DetailedModel.getEt_time());
//
//
//                dialogPlus.show();
//
//                btn_save.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Map<String,Object>map = new HashMap<>();
//                        map.put("name", et_name.getText().toString());
//                        map.put("address", et_address.getText().toString());
//                        map.put("email", et_email.getText().toString());
//                        map.put("date", et_date.getText().toString());
//                        map.put("time", et_time.getText().toString());
//
//                        FirebaseDatabase.getInstance().getReference().child("Customers")
//                                .child(getRef(position).getKey()).updateChildren(map)
//                                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void unused) {
//                                        Toast.makeText(holder.name.getContext(), "Data updated Successfully.", Toast.LENGTH_SHORT).show();
//                                        dialogPlus.dismiss();
//                                    }
//                                })
//                                .addOnFailureListener(new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        Toast.makeText(holder.name.getContext(), "Error While Updating", Toast.LENGTH_SHORT).show();
//                                        dialogPlus.dismiss();
//                                    }
//                                });
//
//
//                    }
//                });
//            }
//        });
//
//
//        // Delete the customers
//         holder.delete.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View view) {
//                 AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
//                 builder.setTitle("Are you sure?");
//                 builder.setMessage("Delete data can't be undo .");
//
//                 builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
//                     @Override
//                     public void onClick(DialogInterface dialogInterface, int i) {
//                                       FirebaseDatabase.getInstance().getReference().child("Customers")
//                                       .child(getRef(position).getKey()).removeValue();
//
//                     }
//                 });
//                 builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                     @Override
//                     public void onClick(DialogInterface dialogInterface, int i) {
//                         Toast.makeText(holder.name.getContext(), "Canceled", Toast.LENGTH_SHORT).show();
//                     }
//                 });
//                 builder.show();
//             }
//         });
   }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        ImageView img, arrow;
        TextView name, speciality, price, rate;

        Button  edit, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById((R.id.img1));
            arrow = itemView.findViewById((R.id.arrow));
            name = itemView.findViewById(R.id.name);
            speciality = itemView.findViewById(R.id.speciality);
            price = itemView.findViewById(R.id.price);
            rate = itemView.findViewById(R.id.rate);

            edit = (Button)itemView.findViewById(R.id.edit);
            delete = (Button)itemView.findViewById(R.id.delete);
        }
    }
}
