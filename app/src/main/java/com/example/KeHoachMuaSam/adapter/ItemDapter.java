package com.example.KeHoachMuaSam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.KeHoachMuaSam.DanhSachMuaSamChiTiet;
import com.example.KeHoachMuaSam.model.DanhSachItem;
import com.example.smartmanagertwo.R;

import java.util.List;

public class ItemDapter extends BaseAdapter {

    DanhSachMuaSamChiTiet context;
    int item_layout;
    List<DanhSachItem> items;

    public ItemDapter(DanhSachMuaSamChiTiet context, int item_layout, List<DanhSachItem> items) {
        this.context = context;
        this.item_layout = item_layout;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout,null);
            holder.txtName=view.findViewById(R.id.txtName);
            holder.txtPrice=view.findViewById(R.id.txtPrice);
            holder.imvEdit=view.findViewById(R.id.imvEdit);
            holder.imvDelete=view.findViewById(R.id.imvDelete);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        //Binding dữ liệu
        DanhSachItem t = items.get(i);
        // gắn dữ liệu lên
        holder.txtName.setText(t.getItemName());
        holder.txtPrice.setText(String.format("%,.0f",t.getItemPrice()));
        if(t.getItemCompleted()==1){
            holder.chkCompleted.setChecked(true);
        }
        holder.imvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Edit data
                context.openEditDialog(t);
            }
        });
        holder.imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Delete data
                context.deleteTask(t);
            }
        });

        return view;
    }

    private static class ViewHolder{
        TextView txtName, txtPrice;
        CheckBox chkCompleted;
        ImageView imvEdit, imvDelete;
    }
}