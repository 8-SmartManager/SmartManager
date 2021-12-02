package com.example.thongke;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smartmanagertwo.R;

import java.util.List;

public class ThongKeAdapter extends BaseAdapter {

    Activity context;
    int item_listview;
    List<ThongKe> thongKeList;

    public ThongKeAdapter(Activity context, int item_listview, List<ThongKe> thongKeList) {
        this.context = context;
        this.item_listview = item_listview;
        this.thongKeList = thongKeList;
    }

    public Activity getContext() {
        return context;
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    public int getItem_listview() {
        return item_listview;
    }

    public void setItem_listview(int item_listview) {
        this.item_listview = item_listview;
    }

    public List<ThongKe> getThongKeList() {
        return thongKeList;
    }

    public void setThongKeList(List<ThongKe> thongKeList) {
        this.thongKeList = thongKeList;
    }

    @Override
    public int getCount() {
        return thongKeList.size();
    }

    @Override
    public Object getItem(int i) {
        return thongKeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if(view == null){
            //Link item view
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_listview, null);
            holder.txtTKPercent = view.findViewById(R.id.txtTKPercent);
            holder.txtTKCategory = view.findViewById(R.id.txtTKCategory);
            holder.txtTKMoney = view.findViewById(R.id.txtTKMoney);

            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        //Binding data
        ThongKe t = thongKeList.get(i);
        holder.txtTKPercent.setText(t.getInfoPercent());
        holder.txtTKCategory.setText(t.getInfoCategory());
        holder.txtTKMoney.setText(String.valueOf(t.getInfoMoney()));


        return view;
    }
    public static class ViewHolder{
        TextView txtTKPercent, txtTKCategory, txtTKMoney;
    }
}