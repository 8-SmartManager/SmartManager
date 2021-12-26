package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.model.HopChonItem;
import com.example.smartmanagertwo.R;

import java.util.ArrayList;

public class HopChonAdapter extends BaseAdapter {
    Context context ;
    int item_layout;
    ArrayList<HopChonItem> hopChonItems ;

    public HopChonAdapter(Context context, int item_layout, ArrayList<HopChonItem> hopChonItems) {
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
        ViewHolder holder=null;
        holder=new ViewHolder();
        //nạp giao diện
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view==null)
        {
            view=inflater.inflate(item_layout, null);
            holder.imvIcon=view.findViewById(R.id.imvHopChonItemIcon);
            holder.txtName=view.findViewById(R.id.txtHopChonItemName);

            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        //Binding data
        HopChonItem item= hopChonItems.get(i);
        holder.imvIcon.setImageResource(item.getItemIcon());
        holder.txtName.setText(item.getItemName());


        return view;

    }
    private static class ViewHolder{
        ImageView imvIcon;
        TextView txtName;
    }
}
