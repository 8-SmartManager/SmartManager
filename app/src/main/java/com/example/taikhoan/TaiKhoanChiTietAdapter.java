package com.example.taikhoan;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.model.ThuChiActivity;
import com.example.smartmanagertwo.R;
import com.example.smartmanagertwo.TaiKhoanChiTietActivity;
import com.example.thongke.ThongKeChiTietAdapter;

import java.util.List;

public class TaiKhoanChiTietAdapter extends BaseAdapter {
    Activity context;
    int item_listview;
    List<ThuChiActivity> taikhoanChiTietList;
    public TaiKhoanChiTietAdapter(Activity context, int item_listview, List<ThuChiActivity> taikhoanChiTietList) {
        this.context = context;
        this.item_listview = item_listview;
        this.taikhoanChiTietList = taikhoanChiTietList;
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
        return taikhoanChiTietList;
    }

    public void setTaiKhoanChiTietList(List<ThuChiActivity> taikhoanChiTietList) {
        this.taikhoanChiTietList = taikhoanChiTietList;
    }

    @Override
    public int getCount() {
        return taikhoanChiTietList.size();
    }

    @Override
    public Object getItem(int i) {
        return taikhoanChiTietList.get(i);
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
            holder = (TaiKhoanChiTietAdapter.ViewHolder) view.getTag();
        }
        //Binding data
        ThuChiActivity t = taikhoanChiTietList.get(i);
        holder.txtTheLoai.setText(t.getActivityName());
        holder.txtTaiKhoan.setText(t.getActivityAccount());
        holder.txtMoney.setText(String.format("%,.0f",t.getActivityAmount()));
        holder.txtTime.setText(t.getActivityDate().toString());

        return view;
    }

    public static class ViewHolder{
        TextView txtTheLoai, txtTaiKhoan, txtMoney, txtTime;
    }
}
