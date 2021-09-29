//package com.example.All4Pets.Doctors.Adapters;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Parcelable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AlertDialog;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.example.All4Pets.Doctors.Activities.Vet_ShowMore;
//
//import com.example.All4Pets.Doctors.models.DetailedModel;
//import com.example.All4Pets.Doctors.models.MainModel;
//import com.example.All4Pets.R;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.database.FirebaseDatabase;
//import com.orhanobut.dialogplus.DialogPlus;
//import com.orhanobut.dialogplus.Holder;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class DetailedAdapter extends RecyclerView.Adapter<DetailedModel,DetailedAdapter.ViewHolder>{
//
//    Context context;
//    List<MainModel> list;
//
//
//    public DetailedAdapter(Context context, List<MainModel> list) {
//        this.context = context;
//        this.list = list;
//    }
//
//
//    @NonNull
//    @Override
//    public DetailedModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new DetailedAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.doctors, parent, false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull DetailedModel holder, @SuppressLint("RecyclerView") int position) {
//
//
//
//        Glide.with(context).load(list.get(position).getImg_url()).into(holder.img);
//        holder.setEt_name((list.get(position).getName()));
//        holder.setEt_address(list.get(position).getSpeciality());
//        holder.setEt_email(list.get(position).getPrice());
//        holder.setEt_date(String.valueOf(list.get(position)));
//        holder.setEt_time(String.valueOf(list.get(position)));
//
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, Vet_ShowMore.class);
//                intent.putExtra("vet_show_more", (Parcelable) list.get(position));
//                context.startActivity(intent);
//            }
//        });
//
//
//
//
//        holder.edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
//                        .setContentHolder((Holder) new ViewHolder(R.layout.detailed_customer))
//                        .setExpanded(true, 1200)
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
//                Button btn_save = view.findViewById(R.id.btn_save);
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
//
//                //update / Edit the details of the customers
//
//                btn_save.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Map<String, Object> map = new HashMap<>();
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
//                                        Toast.makeText(holder.setEt_name().getContext(), "Data updated Successfully.", Toast.LENGTH_SHORT).show();
//                                        dialogPlus.dismiss();
//                                    }
//                                })
//                                .addOnFailureListener(new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        Toast.makeText(holder.edit.getContext(), "Error While Updating", Toast.LENGTH_SHORT).show();
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
//
//        // Customer can Delete the customer details
//
//        holder.delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(holder.delete.getContext());
//                builder.setTitle("Are you sure?");
//                builder.setMessage("Delete data can't be undo .");
//
//                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        FirebaseDatabase.getInstance().getReference().child("Customers")
//                                .child(getRef(position)).removeValue();
//
//                    }
//                });
//                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(holder.delete.getContext(), "Canceled", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder.show();
//            }
//        });
//    }
//
//    private String getRef(int position) {
//        return null;
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//
//    public class ViewHolder extends DetailedModel {
//        public ViewHolder(int inflate) {
//        }
//
//        public ViewHolder(View inflate) {
//        }
//    }
//}
//
//
//
