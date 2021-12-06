package com.example.smartmanagertwo;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.AdapterTaiKhoan;
import com.example.model.ThemTaiKhoanActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TaiKhoanThem extends AppCompatActivity {
    ListView lvHienThiDS;
    ArrayList<ThemTaiKhoanActivity> dsTaiKhoan = new ArrayList<>();
    AdapterTaiKhoan adapterTaiKhoan;
    //Tạo list để lưu vị trí click checkbox để xóa tài khoản
    public static  ArrayList<Integer> vitri = new ArrayList<>();

    EditText edtThemTK, edtsoTK;
    Button btnThemTK, btnXoaTK;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themtaikhoan);
        Drawable drawable=getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));
        getSupportActionBar().setTitle("Thêm tài khoản");

        linkViews();
        addEvents();

    }

    private void addEvents() {
        btnThemTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dsTaiKhoan.add(new ThemTaiKhoanActivity(edtThemTK.getText().toString(),edtsoTK.getText().toString()));
            }
        });
        btnXoaTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!vitri.isEmpty()){
                    for (int k:vitri){
                        dsTaiKhoan.remove(k);
                    }
                    vitri.clear();
                    adapterTaiKhoan.notifyDataSetChanged();
                }else {
                    Toast.makeText(TaiKhoanThem.this, "Chưa chọn loại tài khoản cần xóa", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void linkViews() {
        edtThemTK=findViewById(R.id.edtThemTK);
        edtsoTK=findViewById(R.id.edtSoTK);
        btnThemTK=findViewById(R.id.btnThemTK);
        btnXoaTK=findViewById(R.id.btnXoaTK);
        lvHienThiDS=findViewById(R.id.lvHienThiDS);
        dsTaiKhoan.add(new ThemTaiKhoanActivity("Ngân hàng BIDV","100000000"));
        dsTaiKhoan.add(new ThemTaiKhoanActivity("Ngân hàng ACB","200000"));
        dsTaiKhoan.add(new ThemTaiKhoanActivity("Mua quà Tết","1000000"));
        adapterTaiKhoan = new AdapterTaiKhoan(TaiKhoanThem.this, R.layout.item_themtaikhoan, dsTaiKhoan);
        lvHienThiDS.setAdapter(adapterTaiKhoan);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();
        if (v != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int[] sourceCoordinates = new int[2];
            v.getLocationOnScreen(sourceCoordinates);
            float x = ev.getRawX() + v.getLeft() - sourceCoordinates[0];
            float y = ev.getRawY() + v.getTop() - sourceCoordinates[1];
            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom()) {
                hideKeyboard(this);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null) {
            activity.getWindow().getDecorView();
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
            }
        }
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
}