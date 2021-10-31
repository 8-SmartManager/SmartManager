package com.example.hopchoncohinh;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartmanagertwo.R;

import java.util.List;

public class HopChonAdapter extends BaseAdapter {
    Activity context;
    int item_layout;
    List<HopChonItem> items;

    public HopChonAdapter(Activity context, int item_layout, List<HopChonItem> items) {
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
        ViewHolder holder ;
        if(view==null){
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view= inflater.inflate(item_layout,null);
            holder.imvItemIcon=view.findViewById(R.id.imvHopChonItemIcon);
            holder.txtItemName = view.findViewById(R.id.txtHopChonItemName);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();

        }
        HopChonItem item= items.get(i);
        holder.imvItemIcon.setImageResource(item.getItemIcon());
        holder.txtItemName.setText(item.getItemName());
        return view;
    }

    private static class ViewHolder{
        ImageView imvItemIcon;
        TextView txtItemName;

    }
}
