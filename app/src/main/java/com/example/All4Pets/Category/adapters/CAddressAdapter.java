package com.example.All4Pets.Category.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.All4Pets.Category.models.CAddressModel;
import com.example.All4Pets.R;

import java.util.List;

public class CAddressAdapter  extends RecyclerView.Adapter<CAddressAdapter.ViewHolder> {

    Context context;
    List <CAddressModel> cAddressModelList;
    SelectedAddress selectedAddress;

    private RadioButton selectedRadiobtn;

    public CAddressAdapter(Context context, List<CAddressModel> cAddressModelList, SelectedAddress selectedAddress) {
        this.context = context;
        this.cAddressModelList = cAddressModelList;
        this.selectedAddress = selectedAddress;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.select_address,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.address.setText(cAddressModelList.get(position).getUserAddress());
        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(CAddressModel address:cAddressModelList){
                    address.setSelected(false);

                }
                cAddressModelList.get(position).setSelected(true);

                if (selectedRadiobtn!=null){
                    selectedRadiobtn.setChecked(false);
                }
                selectedRadiobtn = (RadioButton) view;
                selectedRadiobtn.setChecked(true);
                selectedAddress.setAddress(cAddressModelList.get(position).getUserAddress());
            }
        });

    }

    @Override
    public int getItemCount() {
        return cAddressModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView address;
        RadioButton radioButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.select_address);
            radioButton = itemView.findViewById(R.id.radio_btn);
        }
    }
    public interface SelectedAddress{
        void setAddress(String address);
    }
}
