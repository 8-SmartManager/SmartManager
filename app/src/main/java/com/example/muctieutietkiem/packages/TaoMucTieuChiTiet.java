package com.example.muctieutietkiem.packages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.nhacnho.HopChonNhacNhoChiTietTen;
import com.example.smartmanagertwo.NhacNhoChiTietActivity;
import com.example.smartmanagertwo.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TaoMucTieuChiTiet extends AppCompatActivity {

    Button btnTao;
    EditText edtTenMucTieu, edtSoTienMucTieu ,edtSoTienDatDuoc, edtNgayKetThuc, edtLuuY;
    ImageView imvColor, imvDropdown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_muc_tieu_chi_tiet);
        Drawable drawable=getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));
        getSupportActionBar().setTitle("Tạo mục tiêu");

        LinkViews();
        getData();
        addEvents();
        LoadData();


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

    private void addEvents() {

            edtNgayKetThuc.setOnClickListener(myClick);
            imvColor.setOnClickListener(myClick);

    }

    private void getData() {
        Intent intent = getIntent();
        String tenMucTieu = intent.getStringExtra("tlName");
        edtTenMucTieu.setText(tenMucTieu);
    }

    private void LoadData() {
        Intent intent = new Intent(TaoMucTieuChiTiet.this, TaoMucTieu.class);

    }

    private void LinkViews() {
        btnTao= findViewById(R.id.btnTao);
        edtTenMucTieu=findViewById(R.id.edtTenMucTieu);
        edtSoTienMucTieu=findViewById(R.id.edtSoTienMucTieu);
        edtSoTienDatDuoc=findViewById(R.id.edtSoTienDatDuoc);
        edtNgayKetThuc=findViewById(R.id.edtSelectDate);
        edtLuuY=findViewById(R.id.edtLuuY);
        imvColor=findViewById(R.id.imvColor);
        imvDropdown=findViewById(R.id.imvDrop);
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(TaoMucTieuChiTiet.this,callBack,
                        calendarDate.get(Calendar.YEAR),
                        calendarDate.get(Calendar.MONTH),
                        calendarDate.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
            if (view.getId() == R.id.imvColor) {
                closeKeyBoard();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment = new HopChonColor();
                transaction.replace(R.id.LayoutContainerMucTieu, fragment,"fragColor");

                transaction.commit();


            }if(view.getId() == R.id.edtTenMucTieu||view.getId() == R.id.edtSoTienMucTieu||view.getId() == R.id.edtSoTienDatDuoc||view.getId() == R.id.edtSelectDate||view.getId() == R.id.edtLuuY){
                Fragment fragment =getSupportFragmentManager().findFragmentByTag("fragColor");
                if(fragment!=null) {
                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                }


            }

        }
    };

    private void closeKeyBoard() {
        View view= this.getCurrentFocus();
        if(view!=null)
        {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
}