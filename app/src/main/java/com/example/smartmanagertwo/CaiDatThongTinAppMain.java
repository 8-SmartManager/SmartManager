package com.example.smartmanagertwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CaiDatThongTinAppMain extends AppCompatActivity {
Button btnChinhSach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai_dat_thong_tin_app_main);
        linkViews();
        addEvent();
    }

    private void addEvent() {
        btnChinhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CaiDatThongTinAppMain.this, CaiDatThongTinAppChinhSach.class);
                startActivity(intent);
            }
        });
    }

    private void linkViews() {
        btnChinhSach=findViewById(R.id.btnChinhSachBaoMat);
    }
}