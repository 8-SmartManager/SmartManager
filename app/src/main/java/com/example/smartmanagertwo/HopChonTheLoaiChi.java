package com.example.smartmanagertwo;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hopchoncohinh.HopChonAdapter;
import com.example.hopchoncohinh.HopChonItem;

import java.util.ArrayList;

public class HopChonTheLoaiChi extends AppCompatActivity {
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
        items.add(new HopChonItem(R.drawable.ic_giai_tri_the_loai_chi,"Giải trí"));
        items.add(new HopChonItem(R.drawable.ic_thuc_pham_the_loai_chi,"Ăn uống"));
        items.add(new HopChonItem(R.drawable.ic_so_thich_the_loai_chi,"sở thích"));
        items.add(new HopChonItem(R.drawable.ic_di_chuyen_the_loai_chi,"Giáo dục"));
        items.add(new HopChonItem(R.drawable.ic_suc_khoe_the_loai_chi,"Sức khỏe"));
        items.add(new HopChonItem(R.drawable.ic_home_the_loai_chi,"Sinh hoạt"));
        items.add(new HopChonItem(R.drawable.ic_quan_ao_the_loai_chi,"Áo quần"));
        items.add(new HopChonItem(R.drawable.ic_lam_dep_the_loai_chi,"Làm đẹp"));
        items.add(new HopChonItem(R.drawable.ic_tuy_chon_the_loai_chi,"Khác"));
    }

    private void LoadData() {
        adapter = new HopChonAdapter(HopChonTheLoaiChi.this,R.layout.hop_chon_item,items);
        gvNhacNhoTheLoai.setAdapter(adapter);
    }

    private void addEvents() {
    }
}
