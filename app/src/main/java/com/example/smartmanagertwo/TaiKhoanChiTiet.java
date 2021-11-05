package com.example.smartmanagertwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TaiKhoanChiTiet extends AppCompatActivity {
    FloatingActionButton btnThemMoi;
    TextView txtTienMat_Detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan_chi_tiet);
        linkViews();
        addEvents();
    }

    private void addEvents() {
        btnThemMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaiKhoanChiTiet.this,NhacNhoThemActivity.class);
                startActivity(intent);
            }
        });
        txtTienMat_Detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaiKhoanChiTiet.this,TaiKhoanSuaChiTiet.class);
                startActivity(intent);
            }
        });
    }

    private void linkViews() {
        btnThemMoi=findViewById(R.id.btnThemMoi);
        txtTienMat_Detail=findViewById(R.id.txtTienMat_Detail);
    }

}