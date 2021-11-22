package com.example.KeHoachMuaSam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.smartmanagertwo.R;

public class DanhSachMuaSam extends AppCompatActivity {

    Button btnTaoDanhSach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_mua_sam);

        linkViews();
        addEvents();
    }

    private void linkViews() {
        btnTaoDanhSach=findViewById(R.id.btnTaoDanhSach);
    }

    private void addEvents() {
        btnTaoDanhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DanhSachMuaSam.this, DanhSachMuaSam2.class);
                startActivity(intent);
            }
        });

    }
}