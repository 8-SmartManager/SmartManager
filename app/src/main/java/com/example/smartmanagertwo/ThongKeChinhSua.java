package com.example.smartmanagertwo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.model.ThuChiActivity;
import com.example.thongke.HopChonTKTaiKhoan;
import com.example.thongke.HopChonTKTheLoai;
import com.example.thongke.ThongKeChiTiet;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ThongKeChinhSua extends AppCompatActivity {

    TextView txtNgay, txtTaiKhoan, txtTheLoai;
    EditText edtMoney;
    Button btnXoa, btnDanhDau;

    ThuChiActivity selectedThongKeChiTiet;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thong_ke_chinh_sua);

        getData1();

        Drawable drawable=getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));

        if (selectedThongKeChiTiet.getActivityType().equals("Chí")){
            getSupportActionBar().setTitle("Chi");
        }else if(selectedThongKeChiTiet.getActivityType().equals("Thu")){
            getSupportActionBar().setTitle("Thu");
        }


        linkViews();
        addEvents();
        getData();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.thong_ke_chi_chinh_sua, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.mnTKCheck:
                Calendar calendarDate= Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                DatePickerDialog.OnDateSetListener callBack= new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendarDate.set(Calendar.YEAR,i);
                        calendarDate.set(Calendar.MONTH,i1);
                        calendarDate.set(Calendar.DAY_OF_MONTH,i2);
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(ThongKeChinhSua.this,callBack,
                        calendarDate.get(Calendar.YEAR),
                        calendarDate.get(Calendar.MONTH),
                        calendarDate.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void linkViews() {
        txtNgay = findViewById(R.id.txtTKNgay);
        txtTaiKhoan = findViewById(R.id.txtTKTaiKhoan);
        txtTheLoai = findViewById(R.id.txtTKTheLoai);

        edtMoney = findViewById(R.id.edtTKMoney);

        btnXoa = findViewById(R.id.btnXoa);
        btnDanhDau = findViewById(R.id.btnDanhDau);
    }

    private void addEvents() {
        txtTheLoai.setOnClickListener(myClick);
        txtTaiKhoan.setOnClickListener(myClick);
        edtMoney.setOnClickListener(myClick);
        txtNgay.setOnClickListener(myClick);
    }

    private void getData1() {
        Intent intent = getIntent();
        selectedThongKeChiTiet = (ThuChiActivity) intent.getSerializableExtra("ThongKeChiTiet");
    }

    private void getData() {
        Intent intent = getIntent();
        selectedThongKeChiTiet = (ThuChiActivity) intent.getSerializableExtra("ThongKeChiTiet");
        txtNgay.setText(selectedThongKeChiTiet.getActivityDate().toString());
        txtTaiKhoan.setText(selectedThongKeChiTiet.getActivityAccount());
        txtTheLoai.setText(selectedThongKeChiTiet.getActivityName());

        edtMoney.setText(String.valueOf(selectedThongKeChiTiet.getActivityAmount()));
    }
    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.txtTKTheLoai || view.getId() == R.id.txtTKTaiKhoan || view.getId() == R.id.edtTKMoney){
                FragmentManager manager= getSupportFragmentManager();
                FragmentTransaction transaction= manager.beginTransaction();
                Fragment fragment= null;


                if (view.getId() == R.id.txtTKTheLoai){
                    txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                    txtTaiKhoan.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    txtNgay.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    edtMoney.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    fragment = new HopChonTKTheLoai();


                }
                if (view.getId() == R.id.txtTKTaiKhoan){
                    txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    txtTaiKhoan.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                    txtNgay.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    edtMoney.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    fragment = new HopChonTKTaiKhoan();
                }

                if (view.getId() == R.id.edtTKMoney){
                    txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    txtTaiKhoan.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    txtNgay.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                    edtMoney.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                    fragment = new Fragment();
                }
                transaction.replace(R.id.layoutContainerThongKe, fragment);
                transaction.commit();}
            if (view.getId() == R.id.txtTKNgay){
                txtTheLoai.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtTaiKhoan.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                txtNgay.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.thu_cap));
                edtMoney.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.mau_xam));
                Calendar calendarDate= Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                DatePickerDialog.OnDateSetListener callBack= new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendarDate.set(Calendar.YEAR,i);
                        calendarDate.set(Calendar.MONTH,i1);
                        calendarDate.set(Calendar.DAY_OF_MONTH,i2);
                        txtNgay.setText(simpleDateFormat.format(calendarDate.getTime()));
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(ThongKeChinhSua.this,callBack,
                        calendarDate.get(Calendar.YEAR),
                        calendarDate.get(Calendar.MONTH),
                        calendarDate.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();

            }
        }
    };
}
