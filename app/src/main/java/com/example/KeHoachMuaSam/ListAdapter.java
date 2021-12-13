package com.example.KeHoachMuaSam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smartmanagertwo.R;

import java.util.List;

public class ListAdapter extends BaseAdapter {
    Context context;
    List<ListData> listData;
    int item_layout;

    public ListAdapter(Context context, List<ListData> listData, int item_layout) {
        this.context = context;
        this.listData = listData;
        this.item_layout = item_layout;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return listData.get(i);
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
            holder.txtTitle=view.findViewById(R.id.txtTitle);
            holder.txtPrice=view.findViewById(R.id.txtPrice);
            holder.txtTotal=view.findViewById(R.id.txtTotal);
            holder.txtCompleted=view.findViewById(R.id.txtCompleted);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        //Binding dữ liệu
        ListData data= listData.get(i);
        // gắn dữ liệu lên
        holder.txtTitle.setText(data.getListTitle());
        holder.txtPrice.setText(String.format("%,.0f",data.getListPrice()));
        holder.txtTotal.setText(String.valueOf(data.getListTotal()));
        holder.txtCompleted.setText(String.valueOf(data.getListCompleted()));

        return view;
    }
    public static class ViewHolder{
        TextView txtTitle,txtCompleted,txtTotal,txtPrice;
    }
}
