package com.example.nhacnho;

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

public class NhacNhoAdapter extends BaseAdapter {
    Activity context;
    int item_layout;
    List<NhacNho> nhacNhoList;

    public NhacNhoAdapter(Activity context, int item_layout, List<NhacNho> nhacNhoList) {
        this.context = context;
        this.item_layout = item_layout;
        this.nhacNhoList = nhacNhoList;
    }

    @Override
    public int getCount() {
        return nhacNhoList.size();
    }

    @Override
    public Object getItem(int i) {
        return nhacNhoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null)
        {
            //link item View
            holder=new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout, null);
            holder.txtTheLoai=view.findViewById(R.id.txtNhacNhoTheLoai);
            holder.txtTen=view.findViewById(R.id.txtNhacNhoTen);
            holder.txtChuKy=view.findViewById(R.id.txtNhacNhoChuKy);

            holder.txtThoiGianNhac=view.findViewById(R.id.txtNhacNhoThoiGianNhac);
            view.setTag(holder);
            //Binding view
        }else{
            holder= (ViewHolder) view.getTag();
        }
        NhacNho nhacNho= nhacNhoList.get(i);
        holder.txtTheLoai.setText(nhacNho.getTheLoai());
        holder.txtTen.setText(nhacNho.getTen());
        holder.txtChuKy.setText(nhacNho.getChuKy());
        holder.txtThoiGianNhac.setText(nhacNho.getNgayBatDau());
        return view;
    }
    public static class ViewHolder{

        TextView txtTheLoai, txtTen, txtChuKy,txtThoiGianNhac;
    }
}
