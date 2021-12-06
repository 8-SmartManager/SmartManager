package com.example.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.model.ThemTaiKhoanActivity;
import com.example.smartmanagertwo.R;
import com.example.smartmanagertwo.TaiKhoanThem;

import java.util.List;

public class AdapterTaiKhoan extends ArrayAdapter<ThemTaiKhoanActivity> {
    //Màn hình sử dụng layout này
    Activity context;
    //layout cho từng dòng muốn hiển thị lên màn hình
    int resource;
    //Danh sách nguồn dữ liệu muốn hiển thị lên màn hình
    @NonNull List<ThemTaiKhoanActivity> objects;
    public AdapterTaiKhoan(@NonNull Activity context, int resource, @NonNull List<ThemTaiKhoanActivity> objects) {
        super(context, resource, objects);
        this.objects = objects;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View row, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        row = layoutInflater.inflate(this.resource, null);
        TextView txtTenTK = row.findViewById(R.id.txtTenTK);
        CheckBox chkInfo = row.findViewById(R.id.chkInfo);
        ThemTaiKhoanActivity themTaiKhoanActivity = this.objects.get(position);
        txtTenTK.setText(themTaiKhoanActivity.getTenTK() +" - "+themTaiKhoanActivity.getSoTien());
        //Khi nhấn check box sẽ lưu vị trí sang array citri bên class main
        chkInfo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chkInfo.isChecked()){
                    //gọi arraylist bên class main
                    TaiKhoanThem.vitri.add(position);
                    //refresh lại vị trí click checkbox khi nhấn checkbox, tiếp tục nhấn lại checkbox lần nữa
                }else {
                    for(int x:TaiKhoanThem.vitri){
                        if(x == position){
                            TaiKhoanThem.vitri.remove(x);
                        }
                    }
                }
            }
        });
        return row;
    }
}
