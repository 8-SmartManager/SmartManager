package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.model.XemThuChiActivity;
import com.example.smartmanagertwo.R;

import java.util.List;

public class XemActivityAdapter extends BaseAdapter {
    Context context;
    int item_thuchi;
    List<XemThuChiActivity> activity;

    public XemActivityAdapter(Context context, int item_thuchi, List<XemThuChiActivity> activity) {
        this.context = context;
        this.item_thuchi = item_thuchi;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return activity.size();
    }

    @Override
    public Object getItem(int i) {
        return activity.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        XemActivityAdapter.ViewHolder holder;
        if(view == null){
            holder = new XemActivityAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_thuchi, null);

            holder.txtDate = view.findViewById(R.id.txtDate);
            holder.txtAmountChi = view.findViewById(R.id.txtAmountChi);
            holder.txtAmountThu = view.findViewById(R.id.txtAmountThu);
            view.setTag(holder);
        }else{
            holder = (XemActivityAdapter.ViewHolder) view.getTag();
        }
        //binding
        XemThuChiActivity a = activity.get(i);
        holder.txtDate.setText(toString().valueOf(a.getActivityDate()));
        holder.txtAmountChi.setText(toString().valueOf(a.getActivityAmountChi()));
        holder.txtAmountThu.setText(toString().valueOf(a.getActivityAmountThu()));

        return view;
    }
    private class ViewHolder{
        TextView txtDate, txtAmountChi, txtAmountThu;
    }
}
