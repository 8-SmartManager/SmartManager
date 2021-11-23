package com.example.muctieutietkiem.packages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.smartmanagertwo.R;

public class TaoMucTieuChiTiet extends AppCompatActivity {
    Button btnTao;
    EditText edtTenMucTieu, edtSoTienMucTieu ,edtSoTienDatDuoc, editNgayKetThuc, edtLuuY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_muc_tieu_chi_tiet);
        LinkViews();
        LoadData();


    }

    private void LoadData() {
        Intent intent = new Intent(TaoMucTieuChiTiet.this, TaoMucTieu.class);

    }

    private void LinkViews() {
        btnTao= findViewById(R.id.btnTao);
        edtTenMucTieu=findViewById(R.id.edtTenMucTieu);
        edtSoTienMucTieu=findViewById(R.id.edtSoTienMucTieu);
        edtSoTienDatDuoc=findViewById(R.id.edtSoTienDatDuoc);
        editNgayKetThuc=findViewById(R.id.edtSelectDate);
        edtLuuY=findViewById(R.id.edtLuuY);
    }
}