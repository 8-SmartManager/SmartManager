package com.example.thuchi;


import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adapter.HopChonAdapter;
import com.example.model.HopChonItem;
import com.example.smartmanagertwo.R;

import java.util.ArrayList;

public class HopChonTheLoaiThu extends AppCompatActivity {

    GridView gvNhacNhoTheLoai;
    ArrayList<HopChonItem> items;
    HopChonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hop_chon_chung);
        linkViews();
        initData();
        LoadData();
        addEvents();
    }

    private void linkViews() {
        gvNhacNhoTheLoai=findViewById(R.id.gvHopChonItem);
    }

    private void initData() {
        items=new ArrayList<HopChonItem>();
        items.add(new HopChonItem(R.drawable.ic_tai_chinh_thu_chi_the_loai,"Tiền lương"));
        items.add(new HopChonItem(R.drawable.ic_di_chuyen_thu_chi_the_loai,"Tiền cấp"));
        items.add(new HopChonItem(R.drawable.ic_tuy_chon_thu_chi_the_loai,"Khác"));


    }

    private void LoadData() {
        adapter = new HopChonAdapter(HopChonTheLoaiThu.this,R.layout.hop_chon_item_co_hinh,items);
        gvNhacNhoTheLoai.setAdapter(adapter);
    }

    private void addEvents() {
    }
}
