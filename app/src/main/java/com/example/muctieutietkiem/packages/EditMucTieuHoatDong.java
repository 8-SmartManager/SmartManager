package com.example.muctieutietkiem.packages;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartmanagertwo.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EditMucTieuHoatDong extends AppCompatActivity {

    EditText edtTenMucTieu, edtSoTienMucTieu ,edtSoTienDatDuoc, edtNgayKetThuc, edtLuuY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_muctieu_hoatdong);
        Drawable drawable=getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));
        getSupportActionBar().setTitle("Sửa mục tiêu");

        LinkViews();
        getData();
        addEvents();
//        LoadData();


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;


            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_hoatdong_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    private void addEvents() {

            edtNgayKetThuc.setOnClickListener(myClick);

    }

    private void getData() {
        Intent intent = getIntent();
        String tenMucTieu = intent.getStringExtra("tlName");
        edtTenMucTieu.setText(tenMucTieu);
    }

//    private void LoadData() {
//        Intent intent = new Intent(EditMucTieuHoatDong.this, TaoMucTieu.class);
//
//    }

    private void LinkViews() {

        edtTenMucTieu=findViewById(R.id.edtTenMucTieu);
        edtSoTienMucTieu=findViewById(R.id.edtSoTienMucTieu);
        edtSoTienDatDuoc=findViewById(R.id.edtSoTienDatDuoc);
        edtNgayKetThuc=findViewById(R.id.edtSelectDate);
        edtLuuY=findViewById(R.id.edtLuuY);
    }
    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view.getId()==R.id.edtSelectDate ) {
                Calendar calendarDate= Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                DatePickerDialog.OnDateSetListener callBack= new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendarDate.set(Calendar.YEAR,i);
                        calendarDate.set(Calendar.MONTH,i1);
                        calendarDate.set(Calendar.DAY_OF_MONTH,i2);
                        edtNgayKetThuc.setText(simpleDateFormat.format(calendarDate.getTime()));
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditMucTieuHoatDong.this,callBack,
                        calendarDate.get(Calendar.YEAR),
                        calendarDate.get(Calendar.MONTH),
                        calendarDate.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        }
    };
    }