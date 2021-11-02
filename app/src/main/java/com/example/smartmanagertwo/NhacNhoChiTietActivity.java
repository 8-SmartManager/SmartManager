package com.example.smartmanagertwo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.nhacnho.HopChonNhacNhoChiTietChuKy;
import com.example.nhacnho.HopChonNhacNhoChiTietTen;
import com.example.nhacnho.HopChonNhacNhoChiTietTheLoai;

public class NhacNhoChiTietActivity extends AppCompatActivity {
    TextView txtTheLoai, txtTen, txtChuKy, txtNgayBatDau, txtGioNhac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhacnho_chi_tiet);
        linkViews();
        getData();
        addEvents();
    }

    private void addEvents() {
        txtTheLoai.setOnClickListener(myClick);
        txtTen.setOnClickListener(myClick);
        txtChuKy.setOnClickListener(myClick);
        txtNgayBatDau.setOnClickListener(myClick);
        txtGioNhac.setOnClickListener(myClick);

    }

    private void linkViews() {
        txtGioNhac= findViewById(R.id.txtNhacNhoChiTietGioNhac);
        txtTheLoai= findViewById(R.id.txtNhacNhoChiTietTheLoai);
        txtTen= findViewById(R.id.txtNhacNhoChiTietTen);
        txtChuKy= findViewById(R.id.txtNhacNhoChiTietChuKy);
        txtNgayBatDau= findViewById(R.id.txtNhacNhoChiTietNgayBatDau);


    }

    private void getData() {
Intent intent= getIntent();
        txtTheLoai.setText(intent.getStringExtra("Thể loại"));
        txtTen.setText(intent.getStringExtra("Tên"));
        txtChuKy.setText(intent.getStringExtra("Chu kỳ"));
        txtNgayBatDau.setText(intent.getStringExtra("Ngày bắt đầu"));
        txtGioNhac.setText(intent.getStringExtra("Giờ nhắc"));
    }

    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FragmentManager manager= getSupportFragmentManager();
            FragmentTransaction transaction= manager.beginTransaction();
            Fragment fragment= null;
            if(view.getId()==R.id.txtNhacNhoChiTietTheLoai ){

                txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                txtTen.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtChuKy.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtNgayBatDau.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtGioNhac.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                        fragment= new HopChonNhacNhoChiTietTheLoai();

            }
            if(view.getId()==R.id.txtNhacNhoChiTietTen ){
                txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtTen.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                txtChuKy.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtNgayBatDau.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtGioNhac.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                fragment= new HopChonNhacNhoChiTietTen();



            }
            if(view.getId()==R.id.txtNhacNhoChiTietChuKy){
                txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtTen.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtChuKy.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                txtNgayBatDau.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtGioNhac.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                fragment= new HopChonNhacNhoChiTietChuKy();



            }
            if(view.getId()==R.id.txtNhacNhoChiTietNgayBatDau){
                txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtTen.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtChuKy.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtNgayBatDau.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                txtGioNhac.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                fragment= new TheLoaiFragment();



            }
            if(view.getId()==R.id.txtNhacNhoChiTietGioNhac){
                txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtTen.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtChuKy.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtNgayBatDau.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtGioNhac.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                fragment= new TheLoaiFragment();



            }
            transaction.replace(R.id.layoutContainerChiTietNhacNho, fragment);
            transaction.commit();
        }
    };
}
