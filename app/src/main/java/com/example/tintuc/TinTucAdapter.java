package com.example.tintuc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartmanagertwo.R;

import java.util.List;

public class TinTucAdapter extends BaseAdapter{
    Context context;
    int item_tintuc;
    List<TinTucData> newsList;

    public TinTucAdapter(Context context, int item_tintuc, List<TinTucData> newsList) {
        this.context = context;
        this.item_tintuc = item_tintuc;
        this.newsList = newsList;
    }
    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int i) {
        return newsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TinTucAdapter.ViewHolder holder;
        if (view==null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_tintuc,null);
            holder.imvThumb= view.findViewById(R.id.imvThumb);
            holder.txtName= view.findViewById(R.id.txtName);
            holder.txtDetail= view.findViewById(R.id.txtDetail);
            view.setTag(holder);
        }
        else {holder= (TinTucAdapter.ViewHolder) view.getTag();
        }
        TinTucData n = newsList.get(i);
        holder.imvThumb.setImageResource(n.getNewsImage());
        holder.txtName.setText(n.getNewsName());
        holder.txtDetail.setText(n.getNewsDetail());
        return view;
    }
    public static class ViewHolder{
        ImageView imvThumb;
        TextView txtName, txtDetail;
    }
}
