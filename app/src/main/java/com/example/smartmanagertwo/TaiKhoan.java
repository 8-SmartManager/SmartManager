package com.example.smartmanagertwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TaiKhoan extends AppCompatActivity {
    TextView txtTienMat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan);
        linkViews();
        addEvents();
    }

    private void addEvents() {
        txtTienMat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaiKhoan.this, TaiKhoanChiTiet.class);
                startActivity(intent);

            }
        });
    }

    private void linkViews() {
        txtTienMat=findViewById(R.id.txtTienMat);
    }
}