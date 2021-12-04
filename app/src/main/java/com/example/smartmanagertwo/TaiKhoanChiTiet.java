package com.example.smartmanagertwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
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
        Drawable drawable=getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));
        getSupportActionBar().setTitle("Tiền mặt");

        linkViews();
        addEvents();
    }

    private void addEvents() {
        btnThemMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaiKhoanChiTiet.this,ThongKeChiTietActivity.class);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.thong_ke_chi_tiet_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

}