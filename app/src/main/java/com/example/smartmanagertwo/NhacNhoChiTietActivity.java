package com.example.smartmanagertwo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.nhacnho.HopChonNhacNhoChiTietChuKy;
import com.example.nhacnho.HopChonNhacNhoChiTietTen;
import com.example.nhacnho.HopChonNhacNhoChiTietTheLoai;
import com.example.nhacnho.NhacNho;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

public class NhacNhoChiTietActivity extends AppCompatActivity {
    TextView txtTheLoai, txtChuKy, txtNgayBatDau, txtGioNhac;
    public  static String theLoai="";
    EditText edtTen;
    ImageButton btnBack;
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
        edtTen.setOnClickListener(myClick);

        txtChuKy.setOnClickListener(myClick);
        txtNgayBatDau.setOnClickListener(myClick);
        txtGioNhac.setOnClickListener(myClick);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onBackPressed();
            }
        });

    }

    private void linkViews() {
        txtGioNhac= findViewById(R.id.txtNhacNhoChiTietGioNhac);
        txtTheLoai= findViewById(R.id.txtNhacNhoChiTietTheLoai);
        edtTen= findViewById(R.id.edtNhacNhoChiTietTen);
        txtChuKy= findViewById(R.id.txtNhacNhoChiTietChuKy);
        txtNgayBatDau= findViewById(R.id.txtNhacNhoChiTietNgayBatDau);



        btnBack=findViewById(R.id.btnNhacNhoChiTietBack);


    }

    private void getData() {
        Intent intent= getIntent();
        NhacNho nhacNho= (NhacNho) intent.getSerializableExtra("Nhac Nho");
        txtTheLoai.setText(nhacNho.getTheLoai());
        theLoai=txtTheLoai.getText().toString();
        edtTen.setText(nhacNho.getTen());
        txtChuKy.setText(nhacNho.getChuKy());
        txtNgayBatDau.setText(nhacNho.getNgayBatDau().toString());
        txtGioNhac.setText(nhacNho.getGioNhac().toString());
    }

    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId()==R.id.txtNhacNhoChiTietTheLoai ||view.getId()==R.id.edtNhacNhoChiTietTen||view.getId()==R.id.txtNhacNhoChiTietChuKy)
            {
            FragmentManager manager= getSupportFragmentManager();
            FragmentTransaction transaction= manager.beginTransaction();
            Fragment fragment= null;
            if(view.getId()==R.id.txtNhacNhoChiTietTheLoai ){

                txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                edtTen.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtChuKy.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtNgayBatDau.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtGioNhac.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                        fragment= new HopChonNhacNhoChiTietTheLoai();

            }
            if(view.getId()==R.id.edtNhacNhoChiTietTen ){
                txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                edtTen.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                txtChuKy.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtNgayBatDau.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtGioNhac.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                fragment= new HopChonNhacNhoChiTietTen();



            }
            if(view.getId()==R.id.txtNhacNhoChiTietChuKy){
                txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                edtTen.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtChuKy.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                txtNgayBatDau.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtGioNhac.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                fragment= new HopChonNhacNhoChiTietChuKy();



            }
            transaction.replace(R.id.layoutContainerChiTietNhacNho, fragment);
            transaction.commit();}
            if(view.getId()==R.id.txtNhacNhoChiTietNgayBatDau){
                txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                edtTen.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtChuKy.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtNgayBatDau.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                txtGioNhac.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                Calendar calendarDate= Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                DatePickerDialog.OnDateSetListener callBack= new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendarDate.set(Calendar.YEAR,i);
                        calendarDate.set(Calendar.MONTH,i1);
                        calendarDate.set(Calendar.DAY_OF_MONTH,i2);
                        txtNgayBatDau.setText(simpleDateFormat.format(calendarDate.getTime()));
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(NhacNhoChiTietActivity.this,callBack,
                        calendarDate.get(Calendar.YEAR),
                        calendarDate.get(Calendar.MONTH),
                        calendarDate.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();



            }
            if(view.getId()==R.id.txtNhacNhoChiTietGioNhac){
                txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                edtTen.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtChuKy.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtNgayBatDau.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtGioNhac.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                Calendar calendarTime= Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                TimePickerDialog.OnTimeSetListener callback = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        calendarTime.get(Calendar.HOUR);
                        calendarTime.get(Calendar.MINUTE);
                        txtGioNhac.setText(simpleDateFormat.format(calendarTime.getTime()));
                    }
                };
                TimePickerDialog timePickerDialog = new TimePickerDialog(NhacNhoChiTietActivity.this,callback,
                        calendarTime.get(Calendar.HOUR),
                        calendarTime.get(Calendar.MINUTE),true);
                timePickerDialog.show();



            }

        }
    };

}
