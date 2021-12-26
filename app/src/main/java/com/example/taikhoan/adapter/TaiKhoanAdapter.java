package com.example.taikhoan.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smartmanagertwo.R;
import com.example.taikhoan.model.TaiKhoanInfo;

import java.util.List;

public class TaiKhoanAdapter extends BaseAdapter {
    Activity context;
    int item_listview;
    List<TaiKhoanInfo> taiKhoanList;
    public TaiKhoanAdapter(Activity context, int item_listview, List<TaiKhoanInfo> taiKhoanList) {
        this.context = context;
        this.item_listview = item_listview;
        this.taiKhoanList = taiKhoanList;
    }
    @Override
    public int getCount() {
        return taiKhoanList.size();
    }

    @Override
    public Object getItem(int i) {
        return taiKhoanList.get(i);
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
            holder.txtAccount = view.findViewById(R.id.txtAccount);
            holder.txtSoTienTaiKhoan = view.findViewById(R.id.txtSoTienTaiKhoan);


            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        //Binding data
        TaiKhoanInfo t = taiKhoanList.get(i);
        holder.txtAccount.setText(t.getInfoTaiKhoan());
        holder.txtSoTienTaiKhoan.setText(String.format("%,.0f",t.getInfoSoTien()));

        return view;
    }
    public static class ViewHolder{
        TextView txtAccount, txtSoTienTaiKhoan;
    }
}
