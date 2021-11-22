package com.example.KeHoachMuaSam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.smartmanagertwo.BanPhimSoFragment;
import com.example.smartmanagertwo.HopChonNhacNhoChuKy;
import com.example.smartmanagertwo.HopChonNhacNhoThemTen;
import com.example.smartmanagertwo.HopChonNhacNhoThemTheLoai;
import com.example.smartmanagertwo.NhacNhoThemActivity;
import com.example.smartmanagertwo.R;
import com.example.smartmanagertwo.TaiKhoanFragment;
import com.example.smartmanagertwo.TheLoaiFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.transform.sax.TemplatesHandler;

public class DanhSachMuaSam2 extends AppCompatActivity {

    TextView txtTaiKhoan, txtTheLoai,txtSoTien;
    Button btnThem;
    FragmentManager manager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_mua_sam2);

        linkViews();
        addEvent();


    }

    private void addEvent() {
        txtTaiKhoan.setOnClickListener(myClick);
        txtTheLoai.setOnClickListener(myClick);
        txtSoTien.setOnClickListener(myClick);
    }

    private void linkViews() {
        txtTaiKhoan=findViewById(R.id.txtTaiKhoan);
        txtTheLoai=findViewById(R.id.txtTheLoai);
        txtSoTien=findViewById(R.id.txtSoTien);

        btnThem=findViewById(R.id.btnThem);

    }
    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view.getId()==R.id.txtTaiKhoan||view.getId()==R.id.txtTheLoai||view.getId()==R.id.txtSoTien )
            {
                FragmentManager manager= getSupportFragmentManager();
                FragmentTransaction transaction= manager.beginTransaction();
                Fragment fragment= null;
                if(view.getId()==R.id.txtTaiKhoan){
                    txtTaiKhoan.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                    txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    txtSoTien.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    fragment= new TaiKhoanFragment();

                }
                if(view.getId()==R.id.txtTheLoai ){
                    txtTaiKhoan.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                    txtSoTien.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    fragment= new TheLoaiFragment();
                }

                if(view.getId()==R.id.txtSoTien){

                    txtTaiKhoan.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    txtSoTien.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                    fragment= new BanPhimSoFragment();

                }
                transaction.replace(R.id.layoutContainer, fragment);
                transaction.commit();}


        }
    };
}