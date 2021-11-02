package com.example.smartmanagertwo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NhacNhoThemActivity extends AppCompatActivity {
    TextView txtTheLoai, txtTen, txtChuKy, txtNgayBatDau, txtGioNhac;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhacnho_new_item);

        linkViews();
        getData();
        addEvents();
    }

    private void getData() {
        Intent intent= getIntent();
    }

    private void addEvents() {

        txtTheLoai.setOnClickListener(myClick);
        txtTen.setOnClickListener(myClick);
        txtChuKy.setOnClickListener(myClick);
        txtNgayBatDau.setOnClickListener(myClick);
        txtGioNhac.setOnClickListener(myClick);


    }


    private void linkViews() {
        txtTheLoai=findViewById(R.id.txtNhacNhoThemTheLoai);
        txtTen=findViewById(R.id.txtNhacNhoThemTen);
        txtChuKy=findViewById(R.id.txtNhacNhoThemChuKy);
        txtNgayBatDau= findViewById(R.id.txtNhacNhoThemNgayBatDau);
        txtGioNhac=findViewById(R.id.txtNhacNhoThemGioNhac);
    }
    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FragmentManager manager= getSupportFragmentManager();
            FragmentTransaction transaction= manager.beginTransaction();
            Fragment fragment= null;
            if(view.getId()==R.id.txtNhacNhoThemTheLoai){
                txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                txtTen.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtChuKy.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtNgayBatDau.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtGioNhac.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                fragment= new HopChonNhacNhoThemTheLoai();
            }
            if(view.getId()==R.id.txtNhacNhoThemTen ){
                txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtTen.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                txtChuKy.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtNgayBatDau.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtGioNhac.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                fragment= new HopChonNhacNhoThemTen();



            }
            if(view.getId()==R.id.txtNhacNhoThemChuKy){
                txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtTen.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtChuKy.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                txtNgayBatDau.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtGioNhac.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                fragment= new HopChonNhacNhoChuKy();



            }
            if(view.getId()==R.id.txtNhacNhoThemNgayBatDau){
                txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtTen.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtChuKy.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtNgayBatDau.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                txtGioNhac.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                fragment= new TheLoaiFragment();



            }
            if(view.getId()==R.id.txtNhacNhoThemGioNhac){
                txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtTen.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtChuKy.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtNgayBatDau.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtGioNhac.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                fragment= new TheLoaiFragment();



            }
            transaction.replace(R.id.layoutContainerThemNhacNho, fragment);
            transaction.commit();
        }
    };
}
