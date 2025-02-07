package com.example.thuchi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thuchi.model.ThuChiXemActivity;
import com.example.smartmanagertwo.R;

import java.util.List;

public class XemActivityTuanAdapter extends BaseAdapter {
    Context context;
    int item_thuchi;
    List<ThuChiXemActivity> activity;

    public XemActivityTuanAdapter(Context context, int item_thuchi, List<ThuChiXemActivity> activity) {
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
        XemActivityTuanAdapter.ViewHolder holder;
        if(view == null){
            holder = new XemActivityTuanAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_thuchi, null);

            holder.txtDate = view.findViewById(R.id.txtActivityDate);
            holder.txtAmountChi = view.findViewById(R.id.txtChi);
            holder.txtAmountThu = view.findViewById(R.id.txtThu);
            view.setTag(holder);
        }else{
            holder = (XemActivityTuanAdapter.ViewHolder) view.getTag();
        }
        //binding
        ThuChiXemActivity a = activity.get(i);
        holder.txtDate.setText(a.getActivityDate());
        holder.txtAmountChi.setText(toString().valueOf(a.getActivityAmountChi()));
        holder.txtAmountThu.setText(toString().valueOf(a.getActivityAmountThu()));

        return view;
    }
    private class ViewHolder{
        TextView txtDate, txtAmountChi, txtAmountThu;
    }
}
