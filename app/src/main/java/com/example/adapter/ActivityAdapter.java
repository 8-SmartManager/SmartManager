package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.model.ThuChiActivity;
import com.example.smartmanagertwo.R;

import java.util.List;

public class ActivityAdapter extends BaseAdapter {
    Context context;
    int item_thuchi;
    List<ThuChiActivity> activity;

    public ActivityAdapter(Context context, int item_thuchi, List<ThuChiActivity> activity) {
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
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_thuchi, null);

            holder.txtName = view.findViewById(R.id.txtActivityName);
            holder.txtAccount = view.findViewById(R.id.txtActivityAccount);
            holder.txtAmount = view.findViewById(R.id.txtActivityAmount);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        //binding
        ThuChiActivity a = activity.get(i);
        holder.txtName.setText(a.getActivityName());
        holder.txtAccount.setText(a.getActivityAccount());
        holder.txtAmount.setText(toString().valueOf(a.getActivityAmount()));

        return view;
    }

    private class ViewHolder{
        TextView txtName, txtAccount, txtAmount;
    }
}
