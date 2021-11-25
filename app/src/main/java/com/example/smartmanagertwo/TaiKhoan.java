package com.example.smartmanagertwo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

public class TaiKhoan extends AppCompatActivity {
    TextView txtTienMat;
    TableRow tbrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan);
        linkViews();
        addEvents();
    }

    private void addEvents() {
//        txtTienMat.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClick(View view) {
//                txtTienMat.setTextColor(R.color.thu_cap);
//                Intent intent = new Intent(TaiKhoan.this, TaiKhoanChiTiet.class);
//                startActivity(intent);
//
//            }
//        });
        tbrow.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaiKhoan.this, TaiKhoanChiTiet.class);
                startActivity(intent);


            }
        });

    }

    private void linkViews() {
        txtTienMat=findViewById(R.id.txtTienMat);
        tbrow=findViewById(R.id.tbrow);
    }
}