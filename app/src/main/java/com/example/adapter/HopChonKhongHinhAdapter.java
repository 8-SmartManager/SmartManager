package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.model.HopChonKhongHinhItem;
import com.example.smartmanagertwo.R;

import java.util.ArrayList;

public class HopChonKhongHinhAdapter extends BaseAdapter {
    Context context ;
    int item_layout;
    ArrayList<HopChonKhongHinhItem> hopChonItems ;

    public HopChonKhongHinhAdapter(Context context, int item_layout, ArrayList<HopChonKhongHinhItem> hopChonItems) {
        this.context = context;
        this.item_layout = item_layout;
        this.hopChonItems = hopChonItems;
    }

    @Override
    public int getCount() {
        return hopChonItems.size();
    }

    @Override
    public Object getItem(int i) {
        return hopChonItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        HopChonKhongHinhAdapter.ViewHolder holder=null;
        holder=new HopChonKhongHinhAdapter.ViewHolder();
        //nạp giao diện
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view==null)
        {
            view=inflater.inflate(item_layout, null);

            holder.txtName=view.findViewById(R.id.txtHopChonItemName);

            view.setTag(holder);
        }else {
            holder= (HopChonKhongHinhAdapter.ViewHolder) view.getTag();
        }
        //Binding data
        HopChonKhongHinhItem item= hopChonItems.get(i);

        holder.txtName.setText(item.getName());

        return view;
    }
    private static class ViewHolder{

        TextView txtName;
    }
}
