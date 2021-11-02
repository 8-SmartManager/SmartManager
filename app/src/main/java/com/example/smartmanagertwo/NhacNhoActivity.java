package com.example.smartmanagertwo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.nhacnho.NhacNho;
import com.example.nhacnho.NhacNhoAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NhacNhoActivity extends AppCompatActivity {
    ListView lvNhacNho;
    ArrayList<NhacNho> nhacNhos;
    NhacNhoAdapter adapter;
    FloatingActionButton btnThemMoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhacnho_main);
        linkViews();
        initData();
        loadData();
        addEvents();
    }

    private void linkViews() {
        lvNhacNho=findViewById(R.id.lvNhacNho);
        btnThemMoi=findViewById(R.id.btnNhacNhoTao);
    }

    private void initData() {
        nhacNhos= new ArrayList<NhacNho>();
        nhacNhos.add(new NhacNho("Tiết kiệm","Mua xe","Hàng tháng","03/11/2021","11:00"));
        nhacNhos.add(new NhacNho("Chi","Tiền điện","Hàng tháng","0.3/11/2021","07:00"));

    }



    private void loadData() {
        adapter= new NhacNhoAdapter(NhacNhoActivity.this,R.layout.nhac_nho_item_layout,nhacNhos);
        lvNhacNho.setAdapter(adapter);
    }

    private void addEvents() {
        btnThemMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NhacNhoActivity.this,NhacNhoThemActivity.class);
                startActivity(intent);
            }
        });
        lvNhacNho.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(NhacNhoActivity.this, NhacNhoChiTietActivity.class);
                adapter= new NhacNhoAdapter(NhacNhoActivity.this,R.layout.nhac_nho_item_layout,nhacNhos);
                NhacNho nhacNho= (NhacNho) adapter.getItem(i);
                intent.putExtra("Thể loại",nhacNho.getTheLoai());
                intent.putExtra("Tên",nhacNho.getTen());
                intent.putExtra("Chu kỳ",nhacNho.getChuKy());
                intent.putExtra("Ngày bắt đầu", nhacNho.getNgayBatDau());
                intent.putExtra("Giờ nhắc", nhacNho.getGioNhac());
                startActivity(intent);
            }
        });
    }
}