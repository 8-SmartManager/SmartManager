package com.example.KeHoachMuaSam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartmanagertwo.R;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    ListData[] myListData;
    Context context;

    public ListAdapter(ListData[] myListData, DanhSachMuaSamThem activity) {
        this.myListData = myListData;
        this.context = activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_dsmuasam,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ListData myShopingListData = myListData[position];
        holder.txtTitle.setText(myShopingListData.getListTitle());
        holder.txtTotal.setText(myShopingListData.getListTotal());
        holder.txtPrice.setText((int) myShopingListData.getListPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return myListData.length;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle;
        TextView txtTotal;
        TextView txtPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtTotal=itemView.findViewById(R.id.txtTotal);
            txtPrice=itemView.findViewById(R.id.txtPrice);
        }
    }

}
