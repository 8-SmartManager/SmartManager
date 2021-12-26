package com.example.taikhoan;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import com.example.smartmanagertwo.MyDatabaseHelper;
import com.example.smartmanagertwo.R;
import com.example.thuchi.model.ThuChiActivity;
import com.example.taikhoan.model.TaiKhoanInfo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TaiKhoanChiTietActivity extends AppCompatActivity {
    ListView lvTaiKhoanChiTiet;
    ArrayList<ThuChiActivity> InfoTaiKhoanChiTiet;
    TaiKhoanChiTietAdapter chiTietTKAdapter;
    TaiKhoanInfo selectedTaiKhoan;

    public static MyDatabaseHelper db;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan_chi_tiet);

        getData();
        Drawable drawable=getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.chu_dao)));
        getSupportActionBar().setTitle(selectedTaiKhoan.getInfoTaiKhoan());

        prepareDb();
        linkViews();
        loadData();
        addEvents();
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
        getMenuInflater().inflate(R.menu.thong_ke_chi_tiet_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.mnChooseDate:
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(TaiKhoanChiTietActivity.this,callBack,
                        calendarDate.get(Calendar.YEAR),
                        calendarDate.get(Calendar.MONTH),
                        calendarDate.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        loadData();
        super.onResume();
    }
    private void linkViews() {
        lvTaiKhoanChiTiet=findViewById(R.id.lvTaiKhoanChiTiet);
    }

    private void prepareDb() {
        db = new MyDatabaseHelper(this);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<ThuChiActivity> getDataFromDb(){
        InfoTaiKhoanChiTiet = new ArrayList<>();
        Cursor cursor = db.getData("SELECT * FROM " + MyDatabaseHelper.TBL_NAME_THUCHI+ " WHERE "+MyDatabaseHelper.COL_THUCHI_ACCOUNT+"='"+selectedTaiKhoan.getInfoTaiKhoan()+"'");
        InfoTaiKhoanChiTiet.clear();
        while (cursor.moveToNext()){
            InfoTaiKhoanChiTiet.add(new ThuChiActivity(cursor.getInt(0),LocalDate.parse(cursor.getString(5)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getDouble(4)));
        }
        cursor.close();
        return InfoTaiKhoanChiTiet;
    }

    private void getData() {
        Intent intent = getIntent();
        selectedTaiKhoan = (TaiKhoanInfo) intent.getSerializableExtra("Tai Khoan");
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadData() {
        //chiTietTKAdapter = new TaiKhoanChiTietAdapter(TaiKhoanChiTietActivity.this, R.layout.item_tai_khoan_layout, getDataFromDb());
        chiTietTKAdapter = new TaiKhoanChiTietAdapter(TaiKhoanChiTietActivity.this,R.layout.item_tai_khoan_chi_tiet,getDataFromDb());
        lvTaiKhoanChiTiet.setAdapter(chiTietTKAdapter);
    }
    private void addEvents() {

        lvTaiKhoanChiTiet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Intent intent = new Intent(TaiKhoanChiTietActivity.this, TaiKhoanChinhSua.class);
                    chiTietTKAdapter = new TaiKhoanChiTietAdapter(TaiKhoanChiTietActivity.this, R.layout.item_thong_ke_chi_tiet, InfoTaiKhoanChiTiet);
                    ThuChiActivity thongKeChiTiet = (ThuChiActivity) chiTietTKAdapter.getItem(i);
                    intent.putExtra("ThongKeChiTiet", thongKeChiTiet);
                    startActivity(intent);
            }
        });
    }
}