package com.example.smartmanagertwo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.example.hopchoncohinh.HopChonAdapter;
import com.example.hopchoncohinh.HopChonItem;

import java.util.ArrayList;

public class HopChonNhacNhoTheLoai extends AppCompatActivity {
    GridView gvNhacNhoTheLoai;
    ArrayList<HopChonItem> items;
    HopChonAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hop_chon_chung_co_hinh);
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
        items.add(new HopChonItem(R.drawable.ic_thu_nhap_the_loai_nhac_nho,"Thu"));
        items.add(new HopChonItem(R.drawable.ic_di_chuyen_the_loai_nhac_nho,"Chi"));
        items.add(new HopChonItem(R.drawable.ic_quan__ao_the_loai_nhac_nho,"Tiết kiệm"));
        items.add(new HopChonItem(R.drawable.ic_mua_sam_the_loai_nhac_nho,"DS mua sắm"));


    }

    private void LoadData() {
        adapter = new HopChonAdapter(HopChonNhacNhoTheLoai.this,R.layout.hop_chon_item,items);
        gvNhacNhoTheLoai.setAdapter(adapter);
    }

    private void addEvents() {
    }
}