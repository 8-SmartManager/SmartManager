package com.example.thongke;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.model.ThuChiActivity;
import com.example.smartmanagertwo.R;

import java.util.List;

public class ThongKeChiTietAdapter extends BaseAdapter {

    Activity context;
    int item_listview;
    List<ThuChiActivity> thongKeChiTietList;

    public ThongKeChiTietAdapter(Activity context, int item_listview, List<ThuChiActivity> thongKeChiTietList) {
        this.context = context;
        this.item_listview = item_listview;
        this.thongKeChiTietList = thongKeChiTietList;
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

    public List<ThuChiActivity> getThongKeChiTietList() {
        return thongKeChiTietList;
    }

    public void setThongKeChiTietList(List<ThuChiActivity> thongKeChiTietList) {
        this.thongKeChiTietList = thongKeChiTietList;
    }

    @Override
    public int getCount() {
        return thongKeChiTietList.size();
    }

    @Override
    public Object getItem(int i) {
        return thongKeChiTietList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_listview, null);
            holder.txtTheLoai = view.findViewById(R.id.txtTKChiTietTheLoai);
            holder.txtTaiKhoan = view.findViewById(R.id.txtTKChiTietTaiKhoan);
            holder.txtMoney = view.findViewById(R.id.txtTKChiTietMoney);
            holder.txtTime = view.findViewById(R.id.txtTKChiTietTime);

            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        //Binding data
        ThuChiActivity t = thongKeChiTietList.get(i);
        holder.txtTheLoai.setText(t.getActivityName());
        holder.txtTaiKhoan.setText(t.getActivityAccount());
        holder.txtMoney.setText(String.valueOf(t.getActivityAmount()));
        holder.txtTime.setText(t.getActivityDate().toString());

        return view;
    }

    public static class ViewHolder{
        TextView txtTheLoai, txtTaiKhoan, txtMoney, txtTime;
    }
}
