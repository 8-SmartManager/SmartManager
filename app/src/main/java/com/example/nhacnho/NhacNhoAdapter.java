package com.example.nhacnho;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.smartmanagertwo.R;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

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

    @RequiresApi(api = Build.VERSION_CODES.O)
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
        String t= holder.txtChuKy.getText().toString();
        if(holder.txtChuKy.getText().equals("Một lần")||holder.txtChuKy.getText().equals("Hàng ngày") ){

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String str = sdf.format(nhacNho.getGioNhac());
            holder.txtThoiGianNhac.setText(str);
        }
        else if (holder.txtChuKy.getText().equals("Hàng tuần"))
        {
            LocalDate date = nhacNho.getNgayBatDau();
            DayOfWeek dow = date.getDayOfWeek();
            String ngay="";
            if (dow.toString()=="MONDAY")
            {
                ngay="Thứ hai";
            }
            if (dow.toString()=="TUESDAY")
            {
                ngay="Thứ ba";
            }
            if (dow.toString()=="WEDNESDAY")
            {
                ngay="Thứ tư";
            }if (dow.toString()=="THURSDAY")
        {
            ngay="Thứ năm";
        }if (dow.toString()=="FRIDAY")
        {
            ngay="Thứ sáu";
        }
            if (dow.toString()=="SATURDAY")
            {
                ngay="Thứ bảy";
            }
            if (dow.toString()=="SUNDAY")
            {
                ngay="Chủ nhật";
            }



            holder.txtThoiGianNhac.setText(ngay);}
        else if (holder.txtChuKy.getText().equals("Hàng tháng")){

            holder.txtThoiGianNhac.setText("Ngày "+nhacNho.getNgayBatDau().getDayOfMonth());
        }else if (holder.txtChuKy.getText().equals("Hàng năm")){

            holder.txtThoiGianNhac.setText("Ngày "+nhacNho.getNgayBatDau().getDayOfMonth()+" / "+nhacNho.getNgayBatDau().getMonthValue());
        }

        return view;
    }
    public static class ViewHolder{

        TextView txtTheLoai, txtTen, txtChuKy,txtThoiGianNhac;
    }
}
